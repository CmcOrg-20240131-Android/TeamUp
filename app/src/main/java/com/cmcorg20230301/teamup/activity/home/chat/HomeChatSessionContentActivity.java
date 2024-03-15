package com.cmcorg20230301.teamup.activity.home.chat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.home.HomeActivity;
import com.cmcorg20230301.teamup.api.http.SysImSessionContentApi;
import com.cmcorg20230301.teamup.api.http.SysImSessionRefUserApi;
import com.cmcorg20230301.teamup.api.socket.WebSocketApi;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.dto.NotNullId;
import com.cmcorg20230301.teamup.model.dto.NotNullIdAndLongSet;
import com.cmcorg20230301.teamup.model.dto.SysImSessionContentListDTO;
import com.cmcorg20230301.teamup.model.dto.SysImSessionContentSendTextDTO;
import com.cmcorg20230301.teamup.model.dto.SysImSessionContentSendTextListDTO;
import com.cmcorg20230301.teamup.model.dto.WebSocketMessageDTO;
import com.cmcorg20230301.teamup.model.entity.SysImSessionContentDO;
import com.cmcorg20230301.teamup.model.enums.AppDispatchKeyEnum;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.model.enums.WebSocketUriEnum;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.LongObjectMapVO;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.model.vo.SysImSessionRefUserQueryRefUserInfoMapVO;
import com.cmcorg20230301.teamup.model.vo.UserSelfInfoVO;
import com.cmcorg20230301.teamup.util.MyLocalStorage;
import com.cmcorg20230301.teamup.util.UserUtil;
import com.cmcorg20230301.teamup.util.common.LogUtil;
import com.cmcorg20230301.teamup.util.common.MyDateUtil;
import com.cmcorg20230301.teamup.util.common.MyThreadUtil;
import com.cmcorg20230301.teamup.util.common.RecyclerViewUtil;
import com.cmcorg20230301.teamup.util.common.ToastUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * 聊天会话-内容页
 */
public class HomeChatSessionContentActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private HomeChatSessionContentRecycleAdapter recyclerAdapter;

    private Long sessionId;

    // key：时间戳
    private final Map<Long, SysImSessionContentSendTextDTO> toSendMap = new ConcurrentHashMap<>();

    private final UserSelfInfoVO userSelfInfoVO = UserUtil.getUserSelfInfoFromStorage();

    // getContentId()
    private final Set<String> contentIdSet = new ConcurrentHashSet<>();

    // 当前显示的消息列表
    private final List<SysImSessionContentDO> contentList = new CopyOnWriteArrayList<>();

    // 会话里面的用户信息
    public final static Map<Long, SysImSessionRefUserQueryRefUserInfoMapVO> USER_INFO_MAP = new ConcurrentHashMap<>();

    // 是否：加载完成所有数据
    private boolean loadFullFlag = false;

    // 上一次加载的 createTs
    private long lastLoadCreateTs;

    private ScheduledFuture<?> scheduleSend = null;

    private ScheduledFuture<?> scheduleSync = null;

    private final static Map<AppDispatchKeyEnum, Consumer<Object>> APP_DISPATCH_MAP = new HashMap<>();

    private final static Map<String, Consumer<WebSocketMessageDTO<?>>> URI_MAP = new HashMap<>();

    static {

        APP_DISPATCH_MAP.put(AppDispatchKeyEnum.SET_WEB_SOCKET_MESSAGE, data -> {

            WebSocketMessageDTO<?> webSocketMessageDTO = (WebSocketMessageDTO<?>)data;

            Consumer<WebSocketMessageDTO<?>> webSocketMessageDtoConsumer = URI_MAP.get(webSocketMessageDTO.getUri());

            if (webSocketMessageDtoConsumer != null) {

                webSocketMessageDtoConsumer.accept(webSocketMessageDTO);

            }

        });

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        Intent intent = getIntent();

        sessionId = Convert.toLong(intent.getStringExtra(CommonConstant.EXTRA));

        if (sessionId == null) {

            BaseActivity.getAppNav(HomeActivity.class);

            finish();

            return;

        }

        setContentView(R.layout.home_chat_session_content);

        doInitRecyclerView();

        TextView homeChatSessionContentUserInputSend = findViewById(R.id.homeChatSessionContentUserInputSend);

        EditText homeChatSessionContentUserInput = findViewById(R.id.homeChatSessionContentUserInput);

        homeChatSessionContentUserInputSend.setOnClickListener(v -> {

            String homeChatSessionContentUserInputStr = homeChatSessionContentUserInput.getText().toString();

            if (StrUtil.isBlank(homeChatSessionContentUserInputStr)) {

                ToastUtil.makeText("请输入内容");
                return;

            }

            homeChatSessionContentUserInput.setText("");

            SysImSessionContentSendTextDTO sysImSessionContentSendTextDTO =
                getSysImSessionContentSendTextDTO(homeChatSessionContentUserInputStr);

            // 先：渲染前端页面
            handleSysImSessionContentDOList(
                CollUtil.newArrayList(getSysImSessionContentDO(sysImSessionContentSendTextDTO)), false, true);

            // 执行：发送
            doSendToServer(sysImSessionContentSendTextDTO, true);

        });

        firstHandleSessionId(); // 第一次：处理：sessionId

        scheduleSend = MyThreadUtil.scheduleAtFixedRate(() -> {

            doSendToSendMap(); // 把之前未发送的消息，再发送一次

            WebSocketApi.imSessionContentRefUserUpdateLastOpenTsUserSelf(new NotNullId(sessionId)); // 更新-最后一次打开会话的时间戳

        }, CommonConstant.SECOND_3_EXPIRE_TIME, CommonConstant.SECOND_3_EXPIRE_TIME, TimeUnit.MILLISECONDS);

        // scheduleSync = MyThreadUtil.scheduleAtFixedRate(() -> {
        //
        // loadUserInfoData(null); // 定时，加载会话里面的用户信息
        //
        // loadData(null); // 定时，加载最新数据
        //
        // }, CommonConstant.SECOND_10_EXPIRE_TIME, CommonConstant.SECOND_10_EXPIRE_TIME, TimeUnit.MILLISECONDS);

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        if (scheduleSend != null) {
            scheduleSend.cancel(true);
        }

        if (scheduleSync != null) {
            scheduleSync.cancel(true);
        }

    }

    /**
     * 第一次：处理：sessionId
     */
    public void firstHandleSessionId() {

        doSendToSendMap(); // 把之前未发送的消息，再发送一次

        loadUserInfoData(null); // 加载：会话里面的用户信息

        loadData(null); // 加载数据

        WebSocketApi.imSessionContentRefUserUpdateLastOpenTsUserSelf(new NotNullId(sessionId)); // 更新-最后一次打开会话的时间戳

    }

    /**
     * 加载：会话里面的用户信息
     */
    public void loadUserInfoData(@org.jetbrains.annotations.Nullable Set<Long> userIdSet) {

        NotNullIdAndLongSet notNullIdAndLongSet = new NotNullIdAndLongSet();

        notNullIdAndLongSet.setId(sessionId);
        notNullIdAndLongSet.setValueSet(userIdSet);

        SysImSessionRefUserApi.queryRefUserInfoMap(notNullIdAndLongSet,
            new IHttpHandle<LongObjectMapVO<SysImSessionRefUserQueryRefUserInfoMapVO>>() {

                @Override
                public void success(ApiResultVO<LongObjectMapVO<SysImSessionRefUserQueryRefUserInfoMapVO>> apiResultVO)
                    throws Exception {

                    LongObjectMapVO<SysImSessionRefUserQueryRefUserInfoMapVO> map = apiResultVO.getData();

                    if (CollUtil.isEmpty(userIdSet)) {

                        USER_INFO_MAP.clear();

                    }

                    USER_INFO_MAP.putAll(map.getMap());

                    if (recyclerAdapter != null) {

                        runOnUiThread(() -> {

                            recyclerAdapter.notifyDataSetChanged(); // 刷新页面

                        });

                    }

                }

                @Override
                public boolean getHiddenErrorMsgFlag() {
                    return true;
                }

            });

    }

    /**
     * 加载数据
     * 
     * @param createTs 当滚动加载时，才会传递该值
     */
    public void loadData(@org.jetbrains.annotations.Nullable Long createTs) {

        long oldLastLoadCreateTs = lastLoadCreateTs;

        if (createTs != null) {

            if (loadFullFlag) {
                return;
            }

            if (lastLoadCreateTs == createTs) {
                return;
            }

            lastLoadCreateTs = createTs;

        }

        SysImSessionContentListDTO sysImSessionContentListDTO = new SysImSessionContentListDTO();

        sysImSessionContentListDTO.setSessionId(sessionId);
        sysImSessionContentListDTO.setId(createTs);

        SysImSessionContentApi.scrollPageUserSelf(sysImSessionContentListDTO,
            new IHttpHandle<Page<SysImSessionContentDO>>() {

                @Override
                public void success(ApiResultVO<Page<SysImSessionContentDO>> apiResultVO) throws Exception {

                    if (apiResultVO.getData().getRecords().size() < CommonConstant.DEFAULT_PAGE_SIZE) {
                        loadFullFlag = true;
                    }

                    // 处理数据
                    handleSysImSessionContentDOList(apiResultVO.getData().getRecords(), createTs != null, false);

                }

                @Override
                public void error(@Nullable ApiResultVO<Page<SysImSessionContentDO>> apiResultVO) {

                    lastLoadCreateTs = oldLastLoadCreateTs;

                }

                @Override
                public boolean getHiddenErrorMsgFlag() {
                    return true;
                }

            });

    }

    /**
     * 执行：初始化 RecyclerView
     */
    private void doInitRecyclerView() {

        runOnUiThread(() -> {

            // 初始化：RecyclerView
            initRecyclerView();

        });

    }

    /**
     * 初始化：RecyclerView
     */
    private void initRecyclerView() {

        // 获取：RecyclerView
        recyclerView = findViewById(R.id.homeChatSessionContentRecyclerView);

        // 创建：adapter
        recyclerAdapter = new HomeChatSessionContentRecycleAdapter(this, contentList);

        // 给：RecyclerView设置adapter
        recyclerView.setAdapter(recyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // linearLayoutManager.setStackFromEnd(stackFromEnd); // 从最后一个开始滚动

        // 设置：layoutManager
        recyclerView.setLayoutManager(linearLayoutManager);

        // 设置：item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 设置：元素点击事件
        recyclerAdapter.setOnItemClickListener(myViewHolder -> {

        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (RecyclerViewUtil.notCanScrollUp(recyclerView)) { // 下拉加载

                    loadData(contentList.size() > 1 ? contentList.get(0).getCreateTs() : null); // 加载数据

                }

            }

        });

    }

    /**
     * 获取：前端的消息 id
     */
    public static String getContentId(SysImSessionContentDO sysImSessionContentDO) {

        return sysImSessionContentDO.getCreateTs().toString() + sysImSessionContentDO.getCreateId();

    }

    /**
     * 获取：一个发送消息对象
     */
    public static SysImSessionContentSendTextDTO getSysImSessionContentSendTextDTO(String text) {

        SysImSessionContentSendTextDTO sysImSessionContentSendTextDTO = new SysImSessionContentSendTextDTO();

        sysImSessionContentSendTextDTO.setContent(text);
        sysImSessionContentSendTextDTO.setCreateTs(MyDateUtil.getServerTimestamp());

        return sysImSessionContentSendTextDTO;

    }

    /**
     * 获取：一个消息对象
     */
    public SysImSessionContentDO getSysImSessionContentDO(SysImSessionContentSendTextDTO dto) {

        SysImSessionContentDO sysImSessionContentDO = new SysImSessionContentDO();

        sysImSessionContentDO.setContent(dto.getContent());
        sysImSessionContentDO.setShowFlag(true);
        sysImSessionContentDO.setCreateTs(dto.getCreateTs());

        sysImSessionContentDO.setCreateId(userSelfInfoVO.getId());

        return sysImSessionContentDO;

    }

    /**
     * 处理：数据
     * 
     * @param scrollFlag 是否是：滚动加载
     * @param mustScrollToLastContentFlag 是否：必须滚动到底部
     */
    public void handleSysImSessionContentDOList(List<SysImSessionContentDO> sysImSessionContentDOList,
        boolean scrollFlag, boolean mustScrollToLastContentFlag) {

        if (CollUtil.isEmpty(sysImSessionContentDOList)) {
            return;
        }

        int contentListAddTotal = 0;

        for (SysImSessionContentDO item : sysImSessionContentDOList) {

            String contentId = getContentId(item);

            if (item.getId() != null) {

                SysImSessionContentSendTextDTO sysImSessionContentSendTextDTO = new SysImSessionContentSendTextDTO();

                sysImSessionContentSendTextDTO.setCreateTs(item.getCreateTs());

                setToSendMap(sysImSessionContentSendTextDTO, true); // 如果已经在后台处理过了，则在 map里面移除

            }

            if (contentIdSet.contains(contentId)) {
                continue;
            }

            contentIdSet.add(contentId);
            contentList.add(item);

            contentListAddTotal = contentListAddTotal + 1;

        }

        if (contentListAddTotal == 0) {
            return;
        }

        // 排序
        contentList.sort((a, b) -> {

            Long createTsOne = a.getCreateTs();

            Long createTsTwo = b.getCreateTs();

            if (createTsOne.equals(createTsTwo)) {

                return a.getCreateId() > a.getCreateId() ? 1 : -1;

            } else {

                return createTsOne > createTsTwo ? 1 : -1;

            }

        });

        boolean scrollToLastContentFlag;

        if (scrollFlag) {

            scrollToLastContentFlag = false; // 如果是滚动加载

        } else {

            if (mustScrollToLastContentFlag) {

                scrollToLastContentFlag = true;

            } else {

                scrollToLastContentFlag = RecyclerViewUtil.notCanScrollDown(recyclerView);

            }

        }

        // 更新页面显示
        if (recyclerAdapter != null) {

            int finalContentListAddTotal = contentListAddTotal;

            runOnUiThread(() -> {

                // 更新页面显示
                initRecyclerView();

                if (scrollFlag) { // 如果是滚动加载

                    recyclerView.scrollToPosition(finalContentListAddTotal + RecyclerViewUtil.UP_LIMIT_NUMBER);

                } else if (scrollToLastContentFlag) { // 滚动到底部

                    recyclerView.scrollToPosition(recyclerAdapter.getItemCount() - 1);

                }

            });

            LogUtil.debug("contentList：{}", JSONUtil.toJsonStr(contentList));

        }

    }

    /**
     * 设置：待发送的消息
     */
    public void setToSendMap(SysImSessionContentSendTextDTO sysImSessionContentSendTextDTO, boolean removeFlag) {

        Long createTs = sysImSessionContentSendTextDTO.getCreateTs();

        if (createTs == null) {
            return;
        }

        if (removeFlag) {

            if (!toSendMap.containsKey(createTs)) {
                return;
            }

            toSendMap.remove(createTs);

        } else {

            if (toSendMap.containsKey(createTs)) {
                return;
            }

            toSendMap.put(createTs, sysImSessionContentSendTextDTO);

        }

        String jsonStr = JSONUtil.toJsonStr(toSendMap);

        LogUtil.debug("待发送的消息 map：{}，{}", removeFlag ? ("移除：" + createTs) : "", jsonStr);

        MyLocalStorage.setItem(LocalStorageKeyEnum.IM_SESSION_TO_SEND_MAP_JSON_STR.name() + sessionId, jsonStr);

    }

    /**
     * 执行：发送给服务器
     */
    public void doSendToServer(SysImSessionContentSendTextDTO sysImSessionContentSendTextDTO, boolean firstFlag) {

        if (firstFlag) {

            setToSendMap(sysImSessionContentSendTextDTO, false);

        }

        SysImSessionContentSendTextListDTO sysImSessionContentSendTextListDTO =
            new SysImSessionContentSendTextListDTO();

        sysImSessionContentSendTextListDTO.setSessionId(sessionId);
        sysImSessionContentSendTextListDTO.setContentSet(CollUtil.newHashSet(sysImSessionContentSendTextDTO));

        // 发送消息
        boolean sendFlag = WebSocketApi.imSessionContentSendTextUserSelf(sysImSessionContentSendTextListDTO);

        if (!sendFlag) {

            MyThreadUtil.schedule(() -> {

                // 执行：发送
                doSendToServer(sysImSessionContentSendTextDTO, false);

            }, 2000, TimeUnit.MILLISECONDS);

        }

    }

    /**
     * 把之前未发送的消息，再发送一次
     * <p>
     * 备注：只会发送创建时间超过 3秒的数据
     */
    public void doSendToSendMap() {

        // 把之前未发送的消息，再发送一次
        String toSendMapJsonStr =
            MyLocalStorage.getItem(LocalStorageKeyEnum.IM_SESSION_TO_SEND_MAP_JSON_STR.name() + sessionId);

        if (StrUtil.isBlank(toSendMapJsonStr)) {
            return;
        }

        Map<Long, SysImSessionContentSendTextDTO> toSendMapTemp =
            JSONUtil.toBean(toSendMapJsonStr, new TypeReference<Map<Long, SysImSessionContentSendTextDTO>>() {}, false);

        long checkTimestamp = MyDateUtil.getServerTimestamp() - CommonConstant.SECOND_3_EXPIRE_TIME;

        for (Map.Entry<Long, SysImSessionContentSendTextDTO> item : toSendMapTemp.entrySet()) {

            SysImSessionContentSendTextDTO sysImSessionContentSendTextDTO = item.getValue();

            if (sysImSessionContentSendTextDTO.getCreateTs() > checkTimestamp) {
                continue;
            }

            LogUtil.debug("恢复数据：{}", JSONUtil.toJsonStr(sysImSessionContentSendTextDTO));

            // 执行：发送
            doSendToServer(sysImSessionContentSendTextDTO, true);

        }

    }

    @Override
    public Map<AppDispatchKeyEnum, Consumer<Object>> getAppDispatchMap() {

        // 接收到新的消息
        URI_MAP.put(WebSocketUriEnum.SYS_IM_SESSION_CONTENT_SEND.getUri(), webSocketMessageDTO -> {

            Object data = webSocketMessageDTO.getData();

            if (data != null) {

                SysImSessionContentDO sysImSessionContentDO =
                    BeanUtil.copyProperties(data, SysImSessionContentDO.class);

                if (sessionId.equals(sysImSessionContentDO.getSessionId())) {

                    handleSysImSessionContentDOList(CollUtil.newArrayList(sysImSessionContentDO), false, false);

                }

            }

        });

        // 发送消息的回调
        URI_MAP.put(WebSocketUriEnum.SYS_IM_SESSION_CONTENT_WEB_SOCKET_SEND_TEXT_USER_SELF.getUri(),
            webSocketMessageDTO -> {

                if (webSocketMessageDTO.getCode().equals(CommonConstant.API_OK_CODE)) {

                    Object data = webSocketMessageDTO.getData();

                    if (data != null) {

                        // 格式：{"id":"","valueSet":""}
                        JSONObject jsonObject = (JSONObject)data;

                        JSONArray jsonArray = jsonObject.getJSONArray(sessionId.toString());

                        if (CollUtil.isNotEmpty(jsonArray)) {

                            for (Object item : jsonArray) {

                                Long createTs = Convert.toLong(item);

                                if (createTs == null) {
                                    continue;
                                }

                                SysImSessionContentSendTextDTO sysImSessionContentSendTextDTO =
                                    new SysImSessionContentSendTextDTO();

                                sysImSessionContentSendTextDTO.setCreateTs(createTs);

                                setToSendMap(sysImSessionContentSendTextDTO, true);

                            }

                        }

                    }

                } else {

                    ToastUtil.makeText(webSocketMessageDTO.getMsg());

                }

            });

        // 加入新用户的回调
        URI_MAP.put(WebSocketUriEnum.SYS_IM_SESSION_REF_USER_JOIN_USER_ID_SET.getUri(), webSocketMessageDTO -> {

            Object data = webSocketMessageDTO.getData();

            if (data != null) {

                JSONObject jsonObject = BeanUtil.toBean(data, JSONObject.class);

                JSONArray jsonArray = jsonObject.getJSONArray(sessionId.toString());

                if (CollUtil.isNotEmpty(jsonArray)) {

                    Set<Long> userIdSet = new HashSet<>();

                    for (Object item : jsonArray) {

                        Long userId = Convert.toLong(item);

                        if (userId != null) {

                            userIdSet.add(userId);

                        }

                    }

                    if (CollUtil.isNotEmpty(userIdSet)) {

                        loadUserInfoData(userIdSet);

                    }

                }

            }

        });

        return APP_DISPATCH_MAP;

    }

}

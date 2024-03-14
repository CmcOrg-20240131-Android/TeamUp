package com.cmcorg20230301.teamup.activity.home.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.home.HomeActivity;
import com.cmcorg20230301.teamup.api.http.SysImSessionContentApi;
import com.cmcorg20230301.teamup.api.socket.WebSocketApi;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.dto.SysImSessionContentListDTO;
import com.cmcorg20230301.teamup.model.dto.SysImSessionContentSendTextDTO;
import com.cmcorg20230301.teamup.model.dto.SysImSessionContentSendTextListDTO;
import com.cmcorg20230301.teamup.model.entity.SysImSessionContentDO;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.Page;
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

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * 聊天会话-内容页
 */
public class HomeChatSessionContentActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private HomeChatSessionContentRecycleAdapter recyclerAdapter;

    private Long sessionId;

    // key：时间戳
    private Map<Long, SysImSessionContentSendTextDTO> toSendMap = new ConcurrentHashMap<>();

    private final UserSelfInfoVO userSelfInfoVO = UserUtil.getUserSelfInfoFromStorage();

    // getContentId()
    private final Set<String> contentIdSet = new ConcurrentHashSet<>();

    // 当前显示的消息列表
    public List<SysImSessionContentDO> contentList = new ArrayList<>();

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

        MyThreadUtil.execute(() -> {

            SysImSessionContentListDTO sysImSessionContentListDTO = new SysImSessionContentListDTO();

            sysImSessionContentListDTO.setSessionId(sessionId);

            SysImSessionContentApi.scrollPageUserSelf(sysImSessionContentListDTO,
                new IHttpHandle<Page<SysImSessionContentDO>>() {

                    @Override
                    public void success(ApiResultVO<Page<SysImSessionContentDO>> apiResultVO) {

                        // 初始化：RecyclerView
                        doInitRecyclerView(apiResultVO.getData().getRecords());

                    }

                });

        });

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

    }

    /**
     * 执行：初始化 RecyclerView
     */
    private void doInitRecyclerView(List<SysImSessionContentDO> dataList) {

        runOnUiThread(() -> {

            // 初始化：RecyclerView
            initRecyclerView(dataList);

        });

    }

    /**
     * 初始化：RecyclerView
     */
    private void initRecyclerView(List<SysImSessionContentDO> dataList) {

        // 获取：RecyclerView
        recyclerView = findViewById(R.id.homeChatSessionContentRecyclerView);

        // 创建：adapter
        recyclerAdapter = new HomeChatSessionContentRecycleAdapter(this, dataList);

        // 给：RecyclerView设置adapter
        recyclerView.setAdapter(recyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        linearLayoutManager.setReverseLayout(true); // 布局反向
        linearLayoutManager.setStackFromEnd(true); // 从最后一个开始滚动

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

                if (RecyclerViewUtil.notCanScrollDown(recyclerView)) { // 下拉加载

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

        boolean addFlag = false;

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

            addFlag = true;

        }

        if (addFlag == false) {
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

        boolean scrollToLastContentFlag = false;

        if (scrollFlag) { // 如果是滚动加载

        } else {

            if (mustScrollToLastContentFlag) {

                scrollToLastContentFlag = true;

            } else {

                scrollToLastContentFlag = RecyclerViewUtil.notCanScrollDown(recyclerView);

            }

        }

        // 更新页面显示
        initRecyclerView(contentList);

        if (scrollFlag) { // 如果是滚动加载

        } else if (scrollToLastContentFlag) { // 滚动到底部

            recyclerView.scrollToPosition(recyclerAdapter.getItemCount() - 1);

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

}

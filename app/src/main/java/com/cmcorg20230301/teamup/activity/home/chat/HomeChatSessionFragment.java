package com.cmcorg20230301.teamup.activity.home.chat;

import java.util.List;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.api.http.SysImSessionApi;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.layout.BaseFragment;
import com.cmcorg20230301.teamup.model.dto.SysImSessionSelfPageDTO;
import com.cmcorg20230301.teamup.model.entity.SysImSessionDO;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.util.MyLocalStorage;
import com.cmcorg20230301.teamup.util.common.MyThreadUtil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * 聊天会话页
 */
public class HomeChatSessionFragment extends BaseFragment {

    private RecyclerView recyclerView;

    private HomeChatSessionRecycleAdapter recyclerAdapter;

    @Override
    public Integer getLayoutId() {
        return R.layout.home_chat_session;
    }

    @Override
    public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {

        String imSessionListJsonStr = MyLocalStorage.getItem(LocalStorageKeyEnum.IM_SESSION_LIST);

        if (StrUtil.isNotBlank(imSessionListJsonStr)) {

            // 初始化：RecyclerView
            doInitRecyclerView(JSONUtil.toList(imSessionListJsonStr, SysImSessionDO.class));

        }

        MyThreadUtil.execute(() -> {

            SysImSessionApi.myPageSelf(new SysImSessionSelfPageDTO(), new IHttpHandle<Page<SysImSessionDO>>() {

                @Override
                public void success(ApiResultVO<Page<SysImSessionDO>> apiResultVO) {

                    doInitRecyclerView(apiResultVO.getData().getRecords());

                }

            });

        });

    }

    /**
     * 执行：初始化 RecyclerView
     */
    private void doInitRecyclerView(List<SysImSessionDO> dataList) {

        getActivity().runOnUiThread(() -> {

            // 初始化：RecyclerView
            initRecyclerView(dataList);

        });

    }

    /**
     * 初始化：RecyclerView
     */
    private void initRecyclerView(List<SysImSessionDO> dataList) {

        FragmentActivity fragmentActivity = getActivity();

        // 获取：RecyclerView
        recyclerView = view.findViewById(R.id.homeChatSessionRecyclerView);

        // 创建：adapter
        recyclerAdapter = new HomeChatSessionRecycleAdapter(fragmentActivity, dataList);

        // 给：RecyclerView设置adapter
        recyclerView.setAdapter(recyclerAdapter);

        // 设置：layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentActivity, LinearLayoutManager.VERTICAL, false));

        // 设置：item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(fragmentActivity, DividerItemDecoration.VERTICAL));

        // 设置：元素点击事件
        recyclerAdapter.setOnItemClickListener(myViewHolder -> {

            BaseActivity.getAppNav(HomeChatSessionContentActivity.class, myViewHolder.data.getId());

        });

    }

}

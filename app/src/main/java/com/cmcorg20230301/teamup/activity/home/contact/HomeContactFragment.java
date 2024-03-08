package com.cmcorg20230301.teamup.activity.home.contact;

import java.util.ArrayList;
import java.util.List;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.home.chat.HomeChatSessionContentActivity;
import com.cmcorg20230301.teamup.api.http.SysImSessionApplyApi;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.layout.BaseFragment;
import com.cmcorg20230301.teamup.model.dto.SysImSessionApplyPrivateChatSelfPageDTO;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatSelfPageVO;
import com.cmcorg20230301.teamup.util.MyLocalStorage;
import com.cmcorg20230301.teamup.util.common.MyThreadUtil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * 联系人页
 */
public class HomeContactFragment extends BaseFragment {

    private RecyclerView recyclerView;

    private HomeContactRecycleAdapter recyclerAdapter;

    @Override
    public Integer getLayoutId() {
        return R.layout.home_contact;
    }

    @Override
    public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {

        String imContactListJsonStr = MyLocalStorage.getItem(LocalStorageKeyEnum.IM_CONTACT_LIST);

        if (StrUtil.isNotBlank(imContactListJsonStr)) {

            // 初始化：RecyclerView
            initRecyclerView(JSONUtil.toList(imContactListJsonStr, SysImSessionApplyPrivateChatSelfPageVO.class));

        } else {

            // 初始化：RecyclerView
            initRecyclerView(new ArrayList<>());

        }

        MyThreadUtil.execute(() -> {

            SysImSessionApplyApi.privateChatPageSelf(new SysImSessionApplyPrivateChatSelfPageDTO(),
                new IHttpHandle<Page<SysImSessionApplyPrivateChatSelfPageVO>>() {

                    @Override
                    public void success(ApiResultVO<Page<SysImSessionApplyPrivateChatSelfPageVO>> apiResultVO) {

                        // 初始化：RecyclerView
                        initRecyclerView(apiResultVO.getData().getRecords());

                    }

                });

        });

        TextView homeContactGoHomeContactFindNew = findViewById(R.id.homeContactGoHomeContactFindNew);

        homeContactGoHomeContactFindNew.setOnClickListener(v -> {

            BaseActivity.getAppNav(HomeContactFindNewActivity.class);

        });

        TextView homeContactGoHomeContactApply = findViewById(R.id.homeContactGoHomeContactApply);

        homeContactGoHomeContactApply.setOnClickListener(v -> {

            BaseActivity.getAppNav(HomeContactApplyActivity.class);

        });

    }

    /**
     * 初始化：RecyclerView
     */
    private void initRecyclerView(List<SysImSessionApplyPrivateChatSelfPageVO> dataList) {

        FragmentActivity fragmentActivity = getActivity();

        // 获取：RecyclerView
        recyclerView = view.findViewById(R.id.homeContactRecyclerView);

        // 创建：adapter
        recyclerAdapter = new HomeContactRecycleAdapter(fragmentActivity, dataList);

        // 给：RecyclerView设置adapter
        recyclerView.setAdapter(recyclerAdapter);

        // 设置：layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentActivity, LinearLayoutManager.VERTICAL, false));

        // 设置：item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(fragmentActivity, DividerItemDecoration.VERTICAL));

        // 设置：元素点击事件
        recyclerAdapter.setOnItemClickListener(myViewHolder -> {

            BaseActivity.getAppNav(HomeChatSessionContentActivity.class, myViewHolder.data.getSessionId());

        });

    }

}

package com.cmcorg20230301.teamup.activity.home.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.model.base.BaseFragment;
import com.cmcorg20230301.teamup.model.base.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.entity.SysImSessionDO;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人申请页
 */
public class HomeContactFindNewActivity extends BaseFragment {

    private RecyclerView recyclerView;

    private HomeContactFindNewRecycleAdapter recyclerAdapter;

    @Override
    public Integer getLayoutId() {
        return R.layout.home_contact_apply;
    }

    @Override
    public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {

        // 初始化：RecyclerView
        initRecyclerView(new ArrayList<>());

    }

    /**
     * 初始化：RecyclerView
     */
    private void initRecyclerView(List<SysImSessionDO> dataList) {

        FragmentActivity fragmentActivity = getActivity();

        // 获取：RecyclerView
        recyclerView = view.findViewById(R.id.homeChatSessionRecyclerView);

        // 创建：adapter
        recyclerAdapter = new HomeContactFindNewRecycleAdapter(fragmentActivity, dataList);

        // 给：RecyclerView设置adapter
        recyclerView.setAdapter(recyclerAdapter);

        // 设置：layoutManager
        recyclerView.setLayoutManager(
            new LinearLayoutManager(fragmentActivity, LinearLayoutManager.VERTICAL, false));

        // 设置：item的分割线
        recyclerView.addItemDecoration(
            new DividerItemDecoration(fragmentActivity, DividerItemDecoration.VERTICAL));

        // 设置：元素点击事件
        recyclerAdapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view) {

            }

        });

    }

}

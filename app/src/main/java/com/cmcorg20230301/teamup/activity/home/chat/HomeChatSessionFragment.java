package com.cmcorg20230301.teamup.activity.home.chat;

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

import com.cmcorg20230301.teamup.BaseFragment;
import com.cmcorg20230301.teamup.R;

/**
 * 聊天会话页
 */
public class HomeChatSessionFragment extends BaseFragment {

    private View view; // 定义 view用来设置 fragment的layout

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 布局
        view = inflater.inflate(R.layout.home_chat_session, container, false);

        // 初始化：RecyclerView
        initRecyclerView();

        return view;

    }

    /**
     * 初始化：RecyclerView
     */
    private void initRecyclerView() {

        FragmentActivity fragmentActivity = getActivity();

        // 获取：RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.homeChatSessionRecyclerView);

        // 创建：adapter
        HomeChatSessionRecycleAdapter recyclerAdapter = new HomeChatSessionRecycleAdapter(fragmentActivity);

        // 给：RecyclerView设置adapter
        recyclerView.setAdapter(recyclerAdapter);

        // 设置：layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentActivity, LinearLayoutManager.VERTICAL, false));

        // 设置：item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(fragmentActivity, DividerItemDecoration.VERTICAL));

        // 设置：元素点击事件
        recyclerAdapter.setOnItemClickListener(new HomeChatSessionRecycleAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view) {

            }

        });

    }

}

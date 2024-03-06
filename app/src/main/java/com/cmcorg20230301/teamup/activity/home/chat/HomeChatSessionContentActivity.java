package com.cmcorg20230301.teamup.activity.home.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.model.base.BaseActivity;
import com.cmcorg20230301.teamup.model.base.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.entity.SysImSessionContentDO;

import java.util.List;

/**
 * 聊天会话-内容页
 */
public class HomeChatSessionContentActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private HomeChatSessionContentRecycleAdapter recyclerAdapter;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        Intent intent = getIntent();

        setContentView(R.layout.home_chat_session_content);

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

        // 设置：layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 设置：item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 设置：元素点击事件
        recyclerAdapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view) {

            }

        });

    }

}
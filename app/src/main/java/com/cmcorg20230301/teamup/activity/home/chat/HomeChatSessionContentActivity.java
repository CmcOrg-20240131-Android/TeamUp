package com.cmcorg20230301.teamup.activity.home.chat;

import java.util.List;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.home.HomeActivity;
import com.cmcorg20230301.teamup.api.http.SysImSessionContentApi;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.dto.SysImSessionContentListDTO;
import com.cmcorg20230301.teamup.model.entity.SysImSessionContentDO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.util.common.MyThreadUtil;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.hutool.core.convert.Convert;

/**
 * 聊天会话-内容页
 */
public class HomeChatSessionContentActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private HomeChatSessionContentRecycleAdapter recyclerAdapter;

    private Long sessionId;

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

        // 设置：layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 设置：item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 设置：元素点击事件
        recyclerAdapter.setOnItemClickListener(myViewHolder -> {

        });

    }

}

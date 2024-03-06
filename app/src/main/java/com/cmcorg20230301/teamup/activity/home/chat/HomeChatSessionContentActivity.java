package com.cmcorg20230301.teamup.activity.home.chat;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.api.http.SysImSessionContentApi;
import com.cmcorg20230301.teamup.model.base.BaseActivity;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.dto.SysImSessionContentListDTO;
import com.cmcorg20230301.teamup.model.entity.SysImSessionContentDO;
import com.cmcorg20230301.teamup.model.entity.SysImSessionDO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.util.MyThreadUtil;
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

        String sessionIdStr = intent.getStringExtra(CommonConstant.EXTRA);

        setContentView(R.layout.home_chat_session_content);

        MyThreadUtil.execute(() -> {

            SysImSessionContentListDTO sysImSessionContentListDTO = new SysImSessionContentListDTO();

            SysImSessionContentApi.scrollPageUserSelf(sysImSessionContentListDTO,
                new IHttpHandle<Page<SysImSessionDO>>() {

                    @Override
                    public void success(ApiResultVO<Page<SysImSessionDO>> apiResultVO) {

                        // 初始化：RecyclerView
                        initRecyclerView(apiResultVO.getData().getRecords());

                    }

                });

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
        recyclerView.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 设置：item的分割线
        recyclerView.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 设置：元素点击事件
        recyclerAdapter.setOnItemClickListener(myViewHolder -> {

        });

    }

}

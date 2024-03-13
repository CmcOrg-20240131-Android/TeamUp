package com.cmcorg20230301.teamup.activity.home.contact;

import java.util.ArrayList;
import java.util.List;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.api.http.SysImSessionApplyApi;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.dto.NotNullId;
import com.cmcorg20230301.teamup.model.dto.SysImSessionApplyPrivateChatFindNewPageDTO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatFindNewPageVO;
import com.cmcorg20230301.teamup.util.common.ToastUtil;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.hutool.core.util.StrUtil;

/**
 * 联系人申请页
 */
public class HomeContactFindNewActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private HomeContactFindNewRecycleAdapter recyclerAdapter;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.home_contact_find_new);

        EditText homeContactFindNewSearchNickname = findViewById(R.id.homeContactFindNewSearchNickname);

        TextView homeContactFindNewSearch = findViewById(R.id.homeContactFindNewSearch);

        homeContactFindNewSearch.setOnClickListener(v -> {

            String searchNickname = homeContactFindNewSearchNickname.getText().toString();

            if (StrUtil.isBlank(searchNickname)) {

                ToastUtil.makeText("操作失败：请输入昵称");

            } else {

                SysImSessionApplyPrivateChatFindNewPageDTO sysImSessionApplyPrivateChatFindNewPageDTO =
                    new SysImSessionApplyPrivateChatFindNewPageDTO();

                sysImSessionApplyPrivateChatFindNewPageDTO.setNickname(searchNickname);

                SysImSessionApplyApi.privateChatFindNewPage(sysImSessionApplyPrivateChatFindNewPageDTO,
                    new IHttpHandle<Page<SysImSessionApplyPrivateChatFindNewPageVO>>() {

                        @Override
                        public void success(ApiResultVO<Page<SysImSessionApplyPrivateChatFindNewPageVO>> apiResultVO) {

                            doInitRecyclerView(apiResultVO.getData().getRecords());

                        }

                    });

            }

        });

    }

    /**
     * 执行：初始化 RecyclerView
     */
    private void doInitRecyclerView(List<SysImSessionApplyPrivateChatFindNewPageVO> dataList) {

        runOnUiThread(() -> {

            // 初始化：RecyclerView
            initRecyclerView(dataList);

        });

    }

    /**
     * 初始化：RecyclerView
     */
    private void initRecyclerView(List<SysImSessionApplyPrivateChatFindNewPageVO> dataList) {

        // 获取：RecyclerView
        recyclerView = findViewById(R.id.homeChatSessionRecyclerView);

        // 创建：adapter
        recyclerAdapter = new HomeContactFindNewRecycleAdapter(this, dataList);

        // 给：RecyclerView设置adapter
        recyclerView.setAdapter(recyclerAdapter);

        // 设置：layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 设置：item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 设置：元素点击事件
        recyclerAdapter.setOnItemClickListener(myViewHolder -> {

            SysImSessionApplyPrivateChatFindNewPageVO data = myViewHolder.data;

            SysImSessionApplyApi.privateChatApply(new NotNullId(data.getUserId()), new IHttpHandle<String>() {

                @Override
                public void success(ApiResultVO<String> apiResultVO) {

                    ToastUtil.makeText("申请成功");

                    // 刷新页面
                    doInitRecyclerView(new ArrayList<>());

                }

            });

        });

    }

}

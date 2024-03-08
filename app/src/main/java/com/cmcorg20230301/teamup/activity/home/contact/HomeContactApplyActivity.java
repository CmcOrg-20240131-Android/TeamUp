package com.cmcorg20230301.teamup.activity.home.contact;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.api.http.SysImSessionApplyApi;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.dto.SysImSessionApplyPrivateChatApplySelfPageDTO;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatApplySelfPageVO;
import com.cmcorg20230301.teamup.util.MyLocalStorage;
import com.cmcorg20230301.teamup.util.common.MyThreadUtil;
import java.util.List;

/**
 * 联系人申请页
 */
public class HomeContactApplyActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private HomeContactApplyRecycleAdapter recyclerAdapter;


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.home_contact_apply);

        String imContactListJsonStr = MyLocalStorage.getItem(
            LocalStorageKeyEnum.IM_CONTACT_APPLY_LIST);

        if (StrUtil.isNotBlank(imContactListJsonStr)) {

            // 初始化：RecyclerView
            initRecyclerView(JSONUtil.toList(imContactListJsonStr,
                SysImSessionApplyPrivateChatApplySelfPageVO.class));

        }

        MyThreadUtil.execute(() -> {

            SysImSessionApplyApi.privateChatApplyPageSelf(
                new SysImSessionApplyPrivateChatApplySelfPageDTO(),
                new IHttpHandle<Page<SysImSessionApplyPrivateChatApplySelfPageVO>>() {

                    @Override
                    public void success(
                        ApiResultVO<Page<SysImSessionApplyPrivateChatApplySelfPageVO>> apiResultVO) {

                        // 初始化：RecyclerView
                        initRecyclerView(apiResultVO.getData().getRecords());

                    }

                });

        });

    }

    /**
     * 初始化：RecyclerView
     */
    private void initRecyclerView(List<SysImSessionApplyPrivateChatApplySelfPageVO> dataList) {

        // 获取：RecyclerView
        recyclerView = findViewById(R.id.homeChatSessionRecyclerView);

        // 创建：adapter
        recyclerAdapter = new HomeContactApplyRecycleAdapter(this, dataList);

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

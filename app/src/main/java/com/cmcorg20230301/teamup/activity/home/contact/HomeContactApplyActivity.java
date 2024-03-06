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
import com.cmcorg20230301.teamup.api.http.SysImSessionApi;
import com.cmcorg20230301.teamup.model.base.BaseFragment;
import com.cmcorg20230301.teamup.model.base.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.dto.SysImSessionSelfPageDTO;
import com.cmcorg20230301.teamup.model.entity.SysImSessionDO;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.util.MyLocalStorage;
import com.cmcorg20230301.teamup.util.MyThreadUtil;

import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * 联系人申请页
 */
public class HomeContactApplyActivity extends BaseFragment {

    private RecyclerView recyclerView;

    private HomeContactApplyRecycleAdapter recyclerAdapter;

    @Override
    public Integer getLayoutId() {
        return R.layout.home_contact_apply;
    }

    @Override
    public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String imContactListJsonStr = MyLocalStorage.getItem(LocalStorageKeyEnum.IM_CONTACT_APPLY_LIST);

        if (StrUtil.isNotBlank(imContactListJsonStr)) {

            // 初始化：RecyclerView
            initRecyclerView(JSONUtil.toList(imContactListJsonStr, SysImSessionDO.class));

        }

        MyThreadUtil.execute(() -> {

            SysImSessionApi.myPageSelf(new SysImSessionSelfPageDTO(), new IHttpHandle<Page<SysImSessionDO>>() {

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
    private void initRecyclerView(List<SysImSessionDO> dataList) {

        FragmentActivity fragmentActivity = getActivity();

        // 获取：RecyclerView
        recyclerView = view.findViewById(R.id.homeChatSessionRecyclerView);

        // 创建：adapter
        recyclerAdapter = new HomeContactApplyRecycleAdapter(fragmentActivity, dataList);

        // 给：RecyclerView设置adapter
        recyclerView.setAdapter(recyclerAdapter);

        // 设置：layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentActivity, LinearLayoutManager.VERTICAL, false));

        // 设置：item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(fragmentActivity, DividerItemDecoration.VERTICAL));

        // 设置：元素点击事件
        recyclerAdapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view) {

            }

        });

    }

}

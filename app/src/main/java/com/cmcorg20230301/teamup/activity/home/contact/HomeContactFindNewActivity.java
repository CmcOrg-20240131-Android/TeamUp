package com.cmcorg20230301.teamup.activity.home.contact;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatFindNewPageVO;
import java.util.List;

/**
 * 联系人申请页
 */
public class HomeContactFindNewActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private HomeContactFindNewRecycleAdapter recyclerAdapter;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.home_contact_find_new);

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

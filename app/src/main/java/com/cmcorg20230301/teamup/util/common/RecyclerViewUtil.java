package com.cmcorg20230301.teamup.util.common;

import com.cmcorg20230301.teamup.layout.BaseRecycleAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewUtil {

    /**
     * 是否不能上滑了
     */
    public static boolean notCanScrollUp(RecyclerView recyclerView) {

        if (recyclerView == null) {
            return false;
        }

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();

        // 第一个可以看到元素下标
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        return firstVisibleItemPosition <= 1;

    }

    /**
     * 是否不能下滑了
     */
    public static boolean notCanScrollDown(RecyclerView recyclerView) {

        if (recyclerView == null) {
            return false;
        }

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();

        // 最后一个可以看到元素下标
        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

        BaseRecycleAdapter<?, ?> baseRecycleAdapter = (BaseRecycleAdapter<?, ?>)recyclerView.getAdapter();

        int itemCount = baseRecycleAdapter.getItemCount();

        return lastVisibleItemPosition >= itemCount - 2;

    }

}
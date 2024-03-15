package com.cmcorg20230301.teamup.util.common;

import com.cmcorg20230301.teamup.layout.BaseRecycleAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewUtil {

    public static final int UP_LIMIT_NUMBER = 1;

    public static final int DOWN_LIMIT_NUMBER = 2;

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

        BaseRecycleAdapter<?, ?> baseRecycleAdapter = (BaseRecycleAdapter<?, ?>)recyclerView.getAdapter();

        int itemCount = baseRecycleAdapter.getItemCount();

        LogUtil.debug("第一个可以看到元素下标：{}，总数：{}", firstVisibleItemPosition, itemCount);

        return firstVisibleItemPosition <= UP_LIMIT_NUMBER;

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

        LogUtil.debug("最后一个可以看到元素下标：{}，总数：{}", lastVisibleItemPosition, itemCount);

        if (lastVisibleItemPosition == -1) {
            return true;
        }

        return lastVisibleItemPosition >= itemCount - DOWN_LIMIT_NUMBER;

    }

}

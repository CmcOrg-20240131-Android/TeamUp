package com.cmcorg20230301.teamup.util.common;

import com.cmcorg20230301.teamup.layout.BaseRecycleAdapter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewUtil {

    public static final int UP_LIMIT_NUMBER = 1;

    public static final int DOWN_LIMIT_NUMBER = 2;

    /**
     * 刷新页面：上拉加载
     */
    public static void updateLinearLayoutManagerForUp(Activity activity, RecyclerView recyclerView, int preNumber) {

        if (recyclerView == null) {
            return;
        }

        LinearLayoutManager linearLayoutManager =
            new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);

        linearLayoutManager.setStackFromEnd(true); // 从最后一个开始滚动

        activity.runOnUiThread(() -> {

            recyclerView.setLayoutManager(linearLayoutManager); // 刷新页面

            recyclerView.scrollToPosition(preNumber + UP_LIMIT_NUMBER);

        });

    }

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

        LogUtil.debug("第一个可以看到元素下标：{}", firstVisibleItemPosition);

        if (firstVisibleItemPosition < 0) {
            return false;
        }

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

        if (lastVisibleItemPosition < 0) {
            return false;
        }

        return lastVisibleItemPosition >= itemCount - DOWN_LIMIT_NUMBER;

    }

}

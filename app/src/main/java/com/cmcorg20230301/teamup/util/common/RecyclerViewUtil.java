package com.cmcorg20230301.teamup.util.common;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewUtil {

    /**
     * 是否不能上滑了
     */
    public static boolean notCanScrollUp(RecyclerView recyclerView) {

        if (recyclerView == null) {
            return false;
        }

        // -1代表顶部，返回：true表示没到顶，还可以滑
        // 1代表底部，返回：true表示没到底部，还可以滑
        return !recyclerView.canScrollVertically(-1);

    }

    /**
     * 是否不能下滑了
     */
    public static boolean notCanScrollDown(RecyclerView recyclerView) {

        if (recyclerView == null) {
            return false;
        }

        return !recyclerView.canScrollVertically(1);

    }

}

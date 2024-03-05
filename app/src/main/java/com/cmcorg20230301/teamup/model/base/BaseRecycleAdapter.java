package com.cmcorg20230301.teamup.model.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * 基础：RecyclerView适配器
 */
public abstract class BaseRecycleAdapter<T extends RecyclerView.ViewHolder, D> extends RecyclerView.Adapter<T> {

    protected Context context;

    protected List<D> dataList;

    protected OnItemClickListener onItemClickListener;

    public abstract @LayoutRes Integer getItemViewId();

    public abstract T getViewHolder(View itemView);

    public BaseRecycleAdapter(Context context, List<D> dataList) {

        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 创建自定义布局
        View itemView = View.inflate(context, getItemViewId(), null);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        itemView.setLayoutParams(layoutParams);

        return getViewHolder(itemView);

    }

    @Override
    public int getItemCount() {

        return dataList.size();

    }

    /**
     * 设置：item的监听事件的接口
     */
    public interface OnItemClickListener {

        void onItemClick(View view);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}

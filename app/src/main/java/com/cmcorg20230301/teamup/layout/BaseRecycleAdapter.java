package com.cmcorg20230301.teamup.layout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础：RecyclerView适配器
 */
public abstract class BaseRecycleAdapter<T extends BaseRecycleAdapter.MyViewHolder<D>, D> extends
    RecyclerView.Adapter<T> {

    protected Context context;

    protected List<D> dataList;

    protected OnItemClickListener onItemClickListener;

    public abstract @LayoutRes Integer getItemViewId();

    public abstract T getViewHolder(View itemView);

    public BaseRecycleAdapter(Context context, @Nullable List<D> dataList) {

        this.context = context;
        this.dataList = dataList == null ? new ArrayList<>() : dataList;

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

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        itemView.setLayoutParams(layoutParams);

        T viewHolder = getViewHolder(itemView);

        // 在adapter中设置点击事件
        viewHolder.itemView.setOnClickListener(v -> {

            if (onItemClickListener != null) {

                onItemClickListener.onItemClick(viewHolder);

            }

        });

        return viewHolder;

    }

    @Override
    public int getItemCount() {

        return dataList == null ? 0 : dataList.size();

    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {

        holder.data = dataList.get(position);

        if (holder.data != null) {

            onBindViewHolderData(holder, position, holder.data);

        }

    }

    public abstract void onBindViewHolderData(@NonNull T holder, int position, D data);

    /**
     * 设置：item的监听事件的接口
     */
    public interface OnItemClickListener<D> {

        void onItemClick(MyViewHolder<D> myViewHolder);

    }

    public void setOnItemClickListener(OnItemClickListener<D> onItemClickListener) {

        this.onItemClickListener = onItemClickListener;

    }

    public static class MyViewHolder<D> extends RecyclerView.ViewHolder {

        public D data;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

        }

    }

}

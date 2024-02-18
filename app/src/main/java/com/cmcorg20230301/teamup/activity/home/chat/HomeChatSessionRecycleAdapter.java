package com.cmcorg20230301.teamup.activity.home.chat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cmcorg20230301.teamup.R;

import cn.hutool.core.date.DateUtil;

public class HomeChatSessionRecycleAdapter extends RecyclerView.Adapter<HomeChatSessionRecycleAdapter.ViewHolder> {

    private Context context;

    public HomeChatSessionRecycleAdapter(Context context) {

        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 创建自定义布局
        View itemView = View.inflate(context, R.layout.home_chat_session_item, null);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.homeChatSessionItemTime.setText(DateUtil.now());

    }

    @Override
    public int getItemCount() {

        return 50;

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView homeChatSessionItemAvatar;

        TextView homeChatSessionItemUserName;

        TextView homeChatSessionItemContent;

        TextView homeChatSessionItemTime;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            homeChatSessionItemAvatar = itemView.findViewById(R.id.homeChatSessionItemAvatar);

            homeChatSessionItemUserName = itemView.findViewById(R.id.homeChatSessionItemUserName);

            homeChatSessionItemContent = itemView.findViewById(R.id.homeChatSessionItemContent);

            homeChatSessionItemTime = itemView.findViewById(R.id.homeChatSessionItemTime);

            // 在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v);
                    }

                }

            });

        }

    }

    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {

        void onItemClick(View view);

    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}

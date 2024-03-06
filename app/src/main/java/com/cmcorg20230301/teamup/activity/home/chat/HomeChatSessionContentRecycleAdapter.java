package com.cmcorg20230301.teamup.activity.home.chat;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.model.base.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.entity.SysImSessionContentDO;

import java.util.List;

import cn.hutool.core.date.DateUtil;

public class HomeChatSessionContentRecycleAdapter extends BaseRecycleAdapter<HomeChatSessionContentRecycleAdapter.ViewHolder, SysImSessionContentDO> {

    public HomeChatSessionContentRecycleAdapter(Context context, List<SysImSessionContentDO> dataList) {
        super(context, dataList);
    }

    @Override
    public Integer getItemViewId() {
        return R.layout.home_chat_session_content_item;
    }

    @Override
    public ViewHolder getViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.homeChatSessionContentItemTime.setText(DateUtil.now());

        Glide.with(context).load(CommonConstant.FIXED_AVATAR_URL).into(holder.homeChatSessionContentItemAvatar);

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView homeChatSessionContentItemAvatar;

        TextView homeChatSessionContentItemUserName;

        TextView homeChatSessionContentItemContent;

        TextView homeChatSessionContentItemTime;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            homeChatSessionContentItemAvatar = itemView.findViewById(R.id.homeChatSessionContentItemAvatar);

            homeChatSessionContentItemUserName = itemView.findViewById(R.id.homeChatSessionContentItemUserName);

            homeChatSessionContentItemContent = itemView.findViewById(R.id.homeChatSessionContentItemContent);

            homeChatSessionContentItemTime = itemView.findViewById(R.id.homeChatSessionContentItemTime);

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

}

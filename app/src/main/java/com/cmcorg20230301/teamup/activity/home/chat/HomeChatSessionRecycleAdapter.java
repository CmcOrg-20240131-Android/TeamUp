package com.cmcorg20230301.teamup.activity.home.chat;

import java.util.Date;
import java.util.List;

import com.bumptech.glide.Glide;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.layout.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.entity.SysImSessionDO;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cn.hutool.core.date.DateUtil;

public class HomeChatSessionRecycleAdapter
    extends BaseRecycleAdapter<HomeChatSessionRecycleAdapter.MyViewHolder, SysImSessionDO> {

    public HomeChatSessionRecycleAdapter(Context context, List<SysImSessionDO> dataList) {
        super(context, dataList);
    }

    @Override
    public Integer getItemViewId() {
        return R.layout.home_chat_session_item;
    }

    @Override
    public MyViewHolder getViewHolder(View itemView) {
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolderData(@NonNull MyViewHolder holder, int position, SysImSessionDO data) {

        Glide.with(context).load(CommonConstant.FIXED_AVATAR_URL).into(holder.homeChatSessionItemAvatar);

        holder.homeChatSessionItemUserName.setText(data.getName());

        holder.homeChatSessionItemContent.setText(data.getLastContent());

        holder.homeChatSessionItemTime.setText(DateUtil.formatDateTime(new Date(data.getLastContentCreateTs())));

    }

    class MyViewHolder extends BaseRecycleAdapter.MyViewHolder<SysImSessionDO> {

        ImageView homeChatSessionItemAvatar;

        TextView homeChatSessionItemUserName;

        TextView homeChatSessionItemContent;

        TextView homeChatSessionItemTime;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            homeChatSessionItemAvatar = itemView.findViewById(R.id.homeChatSessionItemAvatar);

            homeChatSessionItemUserName = itemView.findViewById(R.id.homeChatSessionItemUserName);

            homeChatSessionItemContent = itemView.findViewById(R.id.homeChatSessionItemContent);

            homeChatSessionItemTime = itemView.findViewById(R.id.homeChatSessionItemTime);

        }

    }

}

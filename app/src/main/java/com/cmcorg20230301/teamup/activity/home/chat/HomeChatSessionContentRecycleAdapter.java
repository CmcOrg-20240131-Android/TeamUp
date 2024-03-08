package com.cmcorg20230301.teamup.activity.home.chat;

import java.util.Date;
import java.util.List;

import com.bumptech.glide.Glide;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.layout.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.entity.SysImSessionContentDO;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cn.hutool.core.date.DateUtil;

public class HomeChatSessionContentRecycleAdapter
    extends BaseRecycleAdapter<HomeChatSessionContentRecycleAdapter.MyViewHolder, SysImSessionContentDO> {

    public HomeChatSessionContentRecycleAdapter(Context context, List<SysImSessionContentDO> dataList) {
        super(context, dataList);
    }

    @Override
    public Integer getItemViewId() {
        return R.layout.home_chat_session_content_item;
    }

    @Override
    public MyViewHolder getViewHolder(View itemView) {
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolderData(@NonNull MyViewHolder holder, int position, SysImSessionContentDO data) {

        Glide.with(context).load(CommonConstant.FIXED_AVATAR_URL).into(holder.homeChatSessionContentItemAvatar);

        holder.homeChatSessionContentItemUserName.setText("");

        holder.homeChatSessionContentItemContent.setText(data.getContent());

        holder.homeChatSessionContentItemTime.setText(DateUtil.formatDateTime(new Date(data.getCreateTs())));

    }

    class MyViewHolder extends BaseRecycleAdapter.MyViewHolder<SysImSessionContentDO> {

        ImageView homeChatSessionContentItemAvatar;

        TextView homeChatSessionContentItemUserName;

        TextView homeChatSessionContentItemContent;

        TextView homeChatSessionContentItemTime;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            homeChatSessionContentItemAvatar = itemView.findViewById(R.id.homeChatSessionContentItemAvatar);

            homeChatSessionContentItemUserName = itemView.findViewById(R.id.homeChatSessionContentItemUserName);

            homeChatSessionContentItemContent = itemView.findViewById(R.id.homeChatSessionContentItemContent);

            homeChatSessionContentItemTime = itemView.findViewById(R.id.homeChatSessionContentItemTime);

        }

    }

}

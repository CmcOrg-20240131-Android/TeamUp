package com.cmcorg20230301.teamup.activity.home.chat;

import java.util.Date;
import java.util.List;

import com.bumptech.glide.Glide;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.layout.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.entity.SysImSessionDO;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cn.hutool.core.date.DateUtil;

public class HomeChatSessionRecycleAdapter
    extends BaseRecycleAdapter<HomeChatSessionRecycleAdapter.MyViewHolder, SysImSessionDO> {

    public HomeChatSessionRecycleAdapter(Activity activity, List<SysImSessionDO> dataList) {
        super(activity, dataList);
    }

    @Override
    public Integer getItemViewId(SysImSessionDO data) {
        return R.layout.home_chat_session_item;
    }

    @Override
    public MyViewHolder getViewHolder(View itemView, SysImSessionDO data) {
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolderData(@NonNull MyViewHolder holder, int position, SysImSessionDO data) {

        Glide.with(activity).load(CommonConstant.FIXED_AVATAR_URL).into(holder.homeChatSessionItemAvatar);

        holder.homeChatSessionItemUserName.setText(data.getName());

        holder.homeChatSessionItemContent.setText(data.getLastContent());

        Long lastContentCreateTs = data.getLastContentCreateTs();

        if (lastContentCreateTs != null) {

            holder.homeChatSessionItemTime.setText(DateUtil.formatDateTime(new Date(lastContentCreateTs)));

        }

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

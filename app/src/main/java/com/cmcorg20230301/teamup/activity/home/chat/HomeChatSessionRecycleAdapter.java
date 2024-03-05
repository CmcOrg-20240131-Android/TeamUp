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
import com.cmcorg20230301.teamup.model.entity.SysImSessionDO;

import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateUtil;

public class HomeChatSessionRecycleAdapter extends BaseRecycleAdapter<HomeChatSessionRecycleAdapter.ViewHolder, SysImSessionDO> {

    public HomeChatSessionRecycleAdapter(Context context, List<SysImSessionDO> dataList) {
        super(context, dataList);
    }

    @Override
    public Integer getItemViewId() {
        return R.layout.home_chat_session_item;
    }

    @Override
    public ViewHolder getViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SysImSessionDO sysImSessionDO = dataList.get(position);

        Glide.with(context).load("https://gw.alipayobjects.com/zos/antfincdn/efFD%24IOql2/weixintupian_20170331104822.jpg").into(holder.homeChatSessionItemAvatar);

        holder.homeChatSessionItemUserName.setText(sysImSessionDO.getName());

        holder.homeChatSessionItemContent.setText(sysImSessionDO.getLastContent());

        holder.homeChatSessionItemTime.setText(DateUtil.formatDateTime(new Date(sysImSessionDO.getLastContentCreateTs())));

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

}

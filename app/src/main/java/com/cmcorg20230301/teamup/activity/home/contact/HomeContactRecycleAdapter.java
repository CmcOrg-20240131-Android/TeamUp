package com.cmcorg20230301.teamup.activity.home.contact;

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
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatSelfPageVO;

import java.util.List;

import cn.hutool.core.util.StrUtil;

public class HomeContactRecycleAdapter extends BaseRecycleAdapter<HomeContactRecycleAdapter.ViewHolder, SysImSessionApplyPrivateChatSelfPageVO> {

    public HomeContactRecycleAdapter(Context context, List<SysImSessionApplyPrivateChatSelfPageVO> dataList) {
        super(context, dataList);
    }

    @Override
    public Integer getItemViewId() {
        return R.layout.home_contact_item;
    }

    @Override
    public ViewHolder getViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SysImSessionApplyPrivateChatSelfPageVO sysImSessionApplyPrivateChatSelfPageVO = dataList.get(position);

        String avatarUrl = sysImSessionApplyPrivateChatSelfPageVO.getAvatarUrl();

        if (StrUtil.isBlank(avatarUrl)) {

            avatarUrl = CommonConstant.FIXED_AVATAR_URL;

        }

        Glide.with(context).load(avatarUrl).into(holder.homeContactItemAvatar);

        holder.homeContactItemNickname.setText(sysImSessionApplyPrivateChatSelfPageVO.getNickname());

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView homeContactItemAvatar;

        TextView homeContactItemNickname;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            homeContactItemAvatar = itemView.findViewById(R.id.homeContactItemAvatar);

            homeContactItemNickname = itemView.findViewById(R.id.homeContactItemNickname);

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
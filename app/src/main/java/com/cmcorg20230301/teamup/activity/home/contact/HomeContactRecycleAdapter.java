package com.cmcorg20230301.teamup.activity.home.contact;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.model.base.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatSelfPageVO;

import java.util.List;

import cn.hutool.core.util.StrUtil;

public class HomeContactRecycleAdapter extends BaseRecycleAdapter<HomeContactRecycleAdapter.MyViewHolder, SysImSessionApplyPrivateChatSelfPageVO> {

    public HomeContactRecycleAdapter(Context context, List<SysImSessionApplyPrivateChatSelfPageVO> dataList) {
        super(context, dataList);
    }

    @Override
    public Integer getItemViewId() {
        return R.layout.home_contact_item;
    }

    @Override
    public MyViewHolder getViewHolder(View itemView) {
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolderData(@NonNull MyViewHolder holder, int position, SysImSessionApplyPrivateChatSelfPageVO data) {

        String avatarUrl = data.getAvatarUrl();

        if (StrUtil.isBlank(avatarUrl)) {

            avatarUrl = CommonConstant.FIXED_AVATAR_URL;

        }

        Glide.with(context).load(avatarUrl).into(holder.homeContactItemAvatar);

        holder.homeContactItemNickname.setText(data.getNickname());

    }

    class MyViewHolder extends BaseRecycleAdapter.MyViewHolder<SysImSessionApplyPrivateChatSelfPageVO> {

        ImageView homeContactItemAvatar;

        TextView homeContactItemNickname;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            homeContactItemAvatar = itemView.findViewById(R.id.homeContactItemAvatar);

            homeContactItemNickname = itemView.findViewById(R.id.homeContactItemNickname);

        }

    }

}
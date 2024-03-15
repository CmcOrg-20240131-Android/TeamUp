package com.cmcorg20230301.teamup.activity.home.contact;

import java.util.List;

import com.bumptech.glide.Glide;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.layout.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatSelfPageVO;
import com.cmcorg20230301.teamup.util.UserUtil;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class HomeContactRecycleAdapter
    extends BaseRecycleAdapter<HomeContactRecycleAdapter.MyViewHolder, SysImSessionApplyPrivateChatSelfPageVO> {

    public HomeContactRecycleAdapter(BaseActivity baseActivity, List<SysImSessionApplyPrivateChatSelfPageVO> dataList) {
        super(baseActivity, dataList);
    }

    @Override
    public Integer getItemViewId(SysImSessionApplyPrivateChatSelfPageVO data) {
        return R.layout.home_contact_item;
    }

    @Override
    public MyViewHolder getViewHolder(View itemView, SysImSessionApplyPrivateChatSelfPageVO data) {
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolderData(@NonNull MyViewHolder holder, int position,
        SysImSessionApplyPrivateChatSelfPageVO data) {

        String avatarUrl = UserUtil.getAvatarUrl(data.getAvatarUrl());

        Glide.with(baseActivity).load(avatarUrl).into(holder.homeContactItemAvatar);

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
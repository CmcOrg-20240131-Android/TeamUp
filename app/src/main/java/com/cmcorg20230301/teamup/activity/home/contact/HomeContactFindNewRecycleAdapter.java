package com.cmcorg20230301.teamup.activity.home.contact;

import java.util.List;

import com.bumptech.glide.Glide;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.layout.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatFindNewPageVO;
import com.cmcorg20230301.teamup.util.UserUtil;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class HomeContactFindNewRecycleAdapter
    extends BaseRecycleAdapter<HomeContactFindNewRecycleAdapter.ViewHolder, SysImSessionApplyPrivateChatFindNewPageVO> {

    public HomeContactFindNewRecycleAdapter(Activity activity,
        List<SysImSessionApplyPrivateChatFindNewPageVO> dataList) {
        super(activity, dataList);
    }

    @Override
    public Integer getItemViewId(SysImSessionApplyPrivateChatFindNewPageVO data) {
        return R.layout.home_contact_find_new_item;
    }

    @Override
    public ViewHolder getViewHolder(View itemView, SysImSessionApplyPrivateChatFindNewPageVO data) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolderData(@NonNull ViewHolder holder, int position,
        SysImSessionApplyPrivateChatFindNewPageVO data) {

        String avatarUrl = UserUtil.getAvatarUrl(data.getAvatarUrl());

        Glide.with(activity).load(avatarUrl).into(holder.homeContactFindNewItemAvatar);

        holder.homeContactFindNewItemNickname.setText(data.getNickname());

    }

    class ViewHolder extends BaseRecycleAdapter.MyViewHolder<SysImSessionApplyPrivateChatFindNewPageVO> {

        ImageView homeContactFindNewItemAvatar;

        TextView homeContactFindNewItemNickname;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            homeContactFindNewItemAvatar = itemView.findViewById(R.id.homeContactFindNewItemAvatar);

            homeContactFindNewItemNickname = itemView.findViewById(R.id.homeContactFindNewItemNickname);

        }

    }

}
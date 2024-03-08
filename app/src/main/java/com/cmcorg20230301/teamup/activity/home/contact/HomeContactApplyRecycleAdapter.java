package com.cmcorg20230301.teamup.activity.home.contact;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.model.base.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatApplySelfPageVO;
import com.cmcorg20230301.teamup.util.UserUtil;
import java.util.List;

public class HomeContactApplyRecycleAdapter extends
    BaseRecycleAdapter<HomeContactApplyRecycleAdapter.ViewHolder, SysImSessionApplyPrivateChatApplySelfPageVO> {

    public HomeContactApplyRecycleAdapter(Context context,
        List<SysImSessionApplyPrivateChatApplySelfPageVO> dataList) {
        super(context, dataList);
    }

    @Override
    public Integer getItemViewId() {
        return R.layout.home_contact_apply_item;
    }

    @Override
    public ViewHolder getViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolderData(@NonNull ViewHolder holder, int position,
        SysImSessionApplyPrivateChatApplySelfPageVO data) {

        String avatarUrl = UserUtil.getAvatarUrl(data.getAvatarUrl());

        Glide.with(context).load(avatarUrl).into(holder.homeContactApplyItemAvatar);

        holder.homeContactApplyItemNickname.setText(data.getNickname());

    }

    class ViewHolder extends
        BaseRecycleAdapter.MyViewHolder<SysImSessionApplyPrivateChatApplySelfPageVO> {

        ImageView homeContactApplyItemAvatar;

        TextView homeContactApplyItemNickname;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            homeContactApplyItemAvatar = itemView.findViewById(R.id.homeContactApplyItemAvatar);

            homeContactApplyItemNickname = itemView.findViewById(R.id.homeContactApplyItemNickname);

        }

    }

}

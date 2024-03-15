package com.cmcorg20230301.teamup.activity.home.chat;

import java.util.Date;
import java.util.List;

import com.bumptech.glide.Glide;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.layout.BaseRecycleAdapter;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.entity.SysImSessionContentDO;
import com.cmcorg20230301.teamup.model.vo.SysImSessionRefUserQueryRefUserInfoMapVO;
import com.cmcorg20230301.teamup.model.vo.UserSelfInfoVO;
import com.cmcorg20230301.teamup.util.UserUtil;
import com.cmcorg20230301.teamup.util.common.MyDateUtil;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class HomeChatSessionContentRecycleAdapter
    extends BaseRecycleAdapter<HomeChatSessionContentRecycleAdapter.MyViewHolder, SysImSessionContentDO> {

    private final UserSelfInfoVO userSelfInfoVO = UserUtil.getUserSelfInfoFromStorage();

    public HomeChatSessionContentRecycleAdapter(BaseActivity baseActivity, List<SysImSessionContentDO> dataList) {

        super(baseActivity, dataList);

    }

    @Override
    public Integer getItemViewId(SysImSessionContentDO data) {

        if (data.getCreateId().equals(userSelfInfoVO.getId())) {

            return R.layout.home_chat_session_content_item_self;

        } else {

            return R.layout.home_chat_session_content_item;

        }

    }

    @Override
    public MyViewHolder getViewHolder(View itemView, SysImSessionContentDO data) {
        return new MyViewHolder(itemView, data);
    }

    @Override
    public void onBindViewHolderData(@NonNull MyViewHolder holder, int position, SysImSessionContentDO data) {

        String avatarUrl;

        String nickname;

        SysImSessionRefUserQueryRefUserInfoMapVO sysImSessionRefUserQueryRefUserInfoMapVO =
            HomeChatSessionContentActivity.USER_INFO_MAP.get(data.getCreateId());

        if (sysImSessionRefUserQueryRefUserInfoMapVO == null) {

            avatarUrl = CommonConstant.FIXED_AVATAR_URL;

            nickname = "";

        } else {

            avatarUrl = sysImSessionRefUserQueryRefUserInfoMapVO.getSessionAvatarUrl();

            nickname = sysImSessionRefUserQueryRefUserInfoMapVO.getSessionNickname();

        }

        Glide.with(baseActivity).load(avatarUrl).into(holder.homeChatSessionContentItemAvatar);

        if (holder.homeChatSessionContentItemUserName != null) {

            holder.homeChatSessionContentItemUserName.setText(nickname);

        }

        holder.homeChatSessionContentItemContent.setText(data.getContent());

        holder.homeChatSessionContentItemTime
            .setText(MyDateUtil.formatDateTimeForCurrentDay(new Date(data.getCreateTs())));

    }

    class MyViewHolder extends BaseRecycleAdapter.MyViewHolder<SysImSessionContentDO> {

        ImageView homeChatSessionContentItemAvatar;

        TextView homeChatSessionContentItemUserName;

        TextView homeChatSessionContentItemContent;

        TextView homeChatSessionContentItemTime;

        public MyViewHolder(@NonNull View itemView, SysImSessionContentDO data) {

            super(itemView);

            if (userSelfInfoVO.getId().equals(data.getCreateId())) {

                homeChatSessionContentItemAvatar = itemView.findViewById(R.id.homeChatSessionContentItemAvatarSelf);

                homeChatSessionContentItemContent = itemView.findViewById(R.id.homeChatSessionContentItemContentSelf);

                homeChatSessionContentItemTime = itemView.findViewById(R.id.homeChatSessionContentItemTimeSelf);

            } else {

                homeChatSessionContentItemAvatar = itemView.findViewById(R.id.homeChatSessionContentItemAvatar);

                homeChatSessionContentItemUserName = itemView.findViewById(R.id.homeChatSessionContentItemUserName);

                homeChatSessionContentItemContent = itemView.findViewById(R.id.homeChatSessionContentItemContent);

                homeChatSessionContentItemTime = itemView.findViewById(R.id.homeChatSessionContentItemTime);

            }

        }

    }

}

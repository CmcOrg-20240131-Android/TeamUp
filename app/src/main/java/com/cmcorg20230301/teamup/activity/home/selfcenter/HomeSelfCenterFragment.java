package com.cmcorg20230301.teamup.activity.home.selfcenter;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.layout.BaseFragment;
import com.cmcorg20230301.teamup.util.UserUtil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 我的
 */
public class HomeSelfCenterFragment extends BaseFragment {

    @Override
    public Integer getLayoutId() {
        return R.layout.home_self_center;
    }

    @Override
    public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {

        TextView homeSelfCenterSignOut = findViewById(R.id.homeSelfCenterSignOut);

        homeSelfCenterSignOut.setOnClickListener(v -> {

            UserUtil.signOut(null);

        });

    }

}

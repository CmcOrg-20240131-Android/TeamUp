package com.cmcorg20230301.teamup.activity.home;

import android.os.Bundle;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.home.chat.HomeChatSessionFragment;

import org.jetbrains.annotations.Nullable;

/**
 * 主页
 */
public class HomeActivity extends BaseActivity {

    @Override
    public @ColorRes Integer getStatusBarColorId() {
        return R.color.black2;
    }

    @Override
    public boolean getStatusBarLightFlag() {
        return true;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.home);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.homeFragment, HomeChatSessionFragment.class, null)
                .commit();

    }

}

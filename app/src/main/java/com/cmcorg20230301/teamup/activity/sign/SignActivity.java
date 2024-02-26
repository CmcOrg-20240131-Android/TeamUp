package com.cmcorg20230301.teamup.activity.sign;

import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;

/**
 * 登录/注册页
 */
public class SignActivity extends BaseActivity {

    @Override
    public @ColorRes Integer getStatusBarColorId() {
        return R.color.white1;
    }

    @Override
    public boolean getStatusBarLightFlag() {
        return true;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

}

package com.cmcorg20230301.teamup;

import android.content.Intent;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.activity.sign.in.SignInActivity;

public class MainActivity extends BaseActivity {

    @Override
    public @ColorRes Integer getStatusBarColorId() {
        return R.color.black2;
    }

    @Override
    public boolean getStatusBarLightFlag() {
        return true;
    }

    @Override
    public void initView() {

        startActivity(new Intent(this, SignInActivity.class));

        finish();

    }

}
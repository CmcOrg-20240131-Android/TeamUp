package com.cmcorg20230301.teamup;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.activity.sign.in.SignInActivity;

import org.jetbrains.annotations.Nullable;

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
    public void initView(@Nullable Bundle savedInstanceState) {

        startActivity(new Intent(this, SignInActivity.class));

        finish();

    }

}
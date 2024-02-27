package com.cmcorg20230301.teamup;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.activity.home.HomeActivity;
import com.cmcorg20230301.teamup.activity.sign.SignActivity;
import com.cmcorg20230301.teamup.model.enums.SharedPreferencesKeyEnum;
import com.cmcorg20230301.teamup.util.SharedPreferencesUtil;

import org.jetbrains.annotations.Nullable;

import cn.hutool.core.util.StrUtil;

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

        String jwt = SharedPreferencesUtil.getSharedPreferences().getString(SharedPreferencesKeyEnum.JWT.name(), null);

        if (StrUtil.isBlank(jwt)) {

            startActivity(new Intent(this, SignActivity.class));

        } else {

            startActivity(new Intent(this, HomeActivity.class));

        }

        finish();

    }

}
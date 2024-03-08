package com.cmcorg20230301.teamup;

import android.os.Bundle;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.activity.home.HomeActivity;
import com.cmcorg20230301.teamup.activity.sign.SignActivity;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.util.MyLocalStorage;
import com.cmcorg20230301.teamup.util.common.MyDateUtil;

import org.jetbrains.annotations.Nullable;

import cn.hutool.core.convert.Convert;
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

        String jwt = MyLocalStorage.getItem(LocalStorageKeyEnum.JWT);

        String jwtExpireTsStr = MyLocalStorage.getItem(LocalStorageKeyEnum.JWT_EXPIRE_TS);

        Long jwtExpireTs = Convert.toLong(jwtExpireTsStr);

        if (jwtExpireTs != null) {

            long serverTimestamp = MyDateUtil.getServerTimestamp();

            if (jwtExpireTs > serverTimestamp) { // 判断：jwt是否过期

                jwt = null;

            }

        }

        if (StrUtil.isBlank(jwt)) {

            BaseActivity.getAppNav(SignActivity.class);

        } else {

            BaseActivity.getAppNav(HomeActivity.class);

        }

        finish();

    }

}
package com.cmcorg20230301.teamup;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.activity.home.HomeActivity;
import com.cmcorg20230301.teamup.activity.sign.SignActivity;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.util.MyLocalStorage;
import com.cmcorg20230301.teamup.util.common.LogUtil;
import com.cmcorg20230301.teamup.util.common.MyDateUtil;

import android.os.Bundle;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

public class MainActivity extends BaseActivity {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        String jwt = MyLocalStorage.getItem(LocalStorageKeyEnum.JWT);

        String jwtExpireTsStr = MyLocalStorage.getItem(LocalStorageKeyEnum.JWT_EXPIRE_TS);

        Long jwtExpireTs = Convert.toLong(jwtExpireTsStr);

        if (jwtExpireTs != null) {

            long serverTimestamp = MyDateUtil.getServerTimestamp();

            if (serverTimestamp > jwtExpireTs) { // 判断：jwt是否过期

                LogUtil.debug("jwt过期");

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
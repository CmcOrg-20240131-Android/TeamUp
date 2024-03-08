package com.cmcorg20230301.teamup;

import android.os.Bundle;
import androidx.annotation.ColorRes;
import cn.hutool.core.util.StrUtil;
import com.cmcorg20230301.teamup.activity.home.HomeActivity;
import com.cmcorg20230301.teamup.activity.sign.SignActivity;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.util.MyLocalStorage;
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

        String jwt = MyLocalStorage.getItem(LocalStorageKeyEnum.JWT);

        if (StrUtil.isBlank(jwt)) {

            BaseActivity.getAppNav(SignActivity.class);

        } else {

            BaseActivity.getAppNav(HomeActivity.class);

        }

        finish();

    }

}
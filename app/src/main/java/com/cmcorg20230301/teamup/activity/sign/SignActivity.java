package com.cmcorg20230301.teamup.activity.sign;

import android.os.Bundle;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.sign.in.SignInFragment;
import com.cmcorg20230301.teamup.layout.BaseActivity;

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

        setContentView(R.layout.sign);

        getSupportFragmentManager().beginTransaction()
            .add(R.id.signFragment, SignInFragment.class, null)
            .commit();

    }

}

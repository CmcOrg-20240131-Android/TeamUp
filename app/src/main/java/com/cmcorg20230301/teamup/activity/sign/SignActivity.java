package com.cmcorg20230301.teamup.activity.sign;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.sign.in.SignInFragment;
import com.cmcorg20230301.teamup.layout.BaseActivity;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * 登录/注册页
 */
public class SignActivity extends BaseActivity {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.sign);

        getSupportFragmentManager().beginTransaction().add(R.id.signFragment, SignInFragment.class, null).commit();

    }

}

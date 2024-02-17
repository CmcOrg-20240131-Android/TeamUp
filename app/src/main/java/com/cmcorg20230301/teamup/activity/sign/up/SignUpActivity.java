package com.cmcorg20230301.teamup.activity.sign.up;

import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.sign.in.SignInActivity;

/**
 * 登录页
 */
public class SignUpActivity extends BaseActivity {

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

        setContentView(R.layout.sign_up);

        TextView signUpGoSignIn = findViewById(R.id.signUpGoSignIn);

        signUpGoSignIn.setOnClickListener(v -> {

            startActivity(new Intent(this, SignInActivity.class));

        });

    }

}

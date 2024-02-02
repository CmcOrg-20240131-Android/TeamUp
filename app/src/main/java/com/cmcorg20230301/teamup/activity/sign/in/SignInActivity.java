package com.cmcorg20230301.teamup.activity.sign.in;

import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.sign.up.SignUpActivity;

/**
 * 注册页
 */
public class SignInActivity extends BaseActivity {

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

        setContentView(R.layout.sign_in);

        TextView signUp = findViewById(R.id.signUp);

        signUp.setOnClickListener(v -> {

            startActivity(new Intent(this, SignUpActivity.class));

        });

    }

}

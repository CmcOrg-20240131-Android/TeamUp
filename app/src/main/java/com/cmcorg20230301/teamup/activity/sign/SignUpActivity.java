package com.cmcorg20230301.teamup.activity.sign;

import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;

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

        TextView signIn = findViewById(R.id.signIn);

        signIn.setOnClickListener(v -> {

            startActivity(new Intent(this, SignInActivity.class));

        });

    }

}

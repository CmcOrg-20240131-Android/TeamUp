package com.cmcorg20230301.teamup.activity.sign.in;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.sign.up.SignUpActivity;
import com.cmcorg20230301.teamup.model.constant.BaseRegexConstant;
import com.google.android.material.textfield.TextInputEditText;

import cn.hutool.core.util.ReUtil;

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

        TextView signInGoSignUp = findViewById(R.id.signInGoSignUp);

        signInGoSignUp.setOnClickListener(v -> {

            startActivity(new Intent(this, SignUpActivity.class));

        });

        Button signInSubmit = findViewById(R.id.signInSubmit);

        signInSubmit.setOnClickListener(v -> {

            TextInputEditText signInAccount = findViewById(R.id.signInAccount);

            TextInputEditText signInPassword = findViewById(R.id.signInPassword);

            CharSequence signInAccountHint = signInAccount.getHint();

            boolean passFlag = true;

            if (!ReUtil.isMatch(BaseRegexConstant.SIGN_IN_NAME_REGEXP, signInAccountHint)) {

                passFlag = false;

                signInAccount.setError("登录名限制：只能包含中文，数字，字母，下划线，横杠，长度2-20");

            }

            CharSequence signInPasswordHint = signInPassword.getHint();

            if (!ReUtil.isMatch(BaseRegexConstant.PASSWORD_REGEXP, signInPasswordHint)) {

                passFlag = false;

                signInPassword.setError("密码限制：必须包含大小写字母和数字，可以使用特殊字符，长度8-20");

            }

            if (passFlag) {

            }

        });

    }

}

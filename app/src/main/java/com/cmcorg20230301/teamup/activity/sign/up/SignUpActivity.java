package com.cmcorg20230301.teamup.activity.sign.up;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.sign.in.SignInActivity;
import com.cmcorg20230301.teamup.api.http.SignSignInNameApi;
import com.cmcorg20230301.teamup.model.constant.BaseRegexConstant;
import com.cmcorg20230301.teamup.model.dto.SignSignInNameSignUpDTO;
import com.cmcorg20230301.teamup.util.MyRsaUtil;
import com.cmcorg20230301.teamup.util.ToastUtil;
import com.google.android.material.textfield.TextInputEditText;

import cn.hutool.core.util.ReUtil;

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

        Button signUpSubmit = findViewById(R.id.signUpSubmit);

        signUpSubmit.setOnClickListener(v -> {

            TextInputEditText signUpAccount = findViewById(R.id.signUpAccount);

            TextInputEditText signUpPassword = findViewById(R.id.signUpPassword);

            CharSequence signUpAccountHint = signUpAccount.getHint();

            boolean passFlag = true;

            if (!ReUtil.isMatch(BaseRegexConstant.SIGN_IN_NAME_REGEXP, signUpAccountHint)) {

                passFlag = false;

                signUpAccount.setError("登录名限制：只能包含中文，数字，字母，下划线，横杠，长度2-20");

            }

            CharSequence signUpPasswordHint = signUpPassword.getHint();

            if (!ReUtil.isMatch(BaseRegexConstant.PASSWORD_REGEXP, signUpPasswordHint)) {

                passFlag = false;

                signUpPassword.setError("密码限制：必须包含大小写字母和数字，可以使用特殊字符，长度8-20");

            }

            if (passFlag) {

                String password = MyRsaUtil.passwordRsaEncrypt((String) signUpPasswordHint);

                String originPassword = MyRsaUtil.rsaEncryptPro((String) signUpPasswordHint);

                SignSignInNameSignUpDTO signSignInNameSignUpDTO = new SignSignInNameSignUpDTO();

                signSignInNameSignUpDTO.setPassword(password);
                signSignInNameSignUpDTO.setOriginPassword(originPassword);
                signSignInNameSignUpDTO.setSignInName((String) signUpAccountHint);

                SignSignInNameApi.signUp(signSignInNameSignUpDTO, apiResultVO -> {

                    ToastUtil.makeText(apiResultVO.getMsg());

                    startActivity(new Intent(this, SignInActivity.class)); // 跳转到：登录页面

                });

            }

        });

    }

}

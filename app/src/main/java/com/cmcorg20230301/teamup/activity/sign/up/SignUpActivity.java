package com.cmcorg20230301.teamup.activity.sign.up;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.sign.in.SignInActivity;
import com.cmcorg20230301.teamup.api.http.SignSignInNameApi;
import com.cmcorg20230301.teamup.model.constant.BaseRegexConstant;
import com.cmcorg20230301.teamup.model.dto.SignSignInNameSignUpDTO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.util.MyRsaUtil;
import com.cmcorg20230301.teamup.util.ToastUtil;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.Nullable;

import cn.hutool.core.util.ReUtil;

/**
 * 登录页
 */
public class SignUpActivity extends BaseActivity {

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

        setContentView(R.layout.sign_up);

        TextView signUpGoSignIn = findViewById(R.id.signUpGoSignIn);

        SignUpActivity that = this;

        signUpGoSignIn.setOnClickListener(v -> {

            startActivity(new Intent(that, SignInActivity.class));

        });

        Button signUpSubmit = findViewById(R.id.signUpSubmit);

        signUpSubmit.setOnClickListener(v -> {

            TextInputEditText signUpAccount = findViewById(R.id.signUpAccount);

            TextInputEditText signUpPassword = findViewById(R.id.signUpPassword);

            String signUpAccountText = signUpAccount.getText().toString();

            boolean passFlag = true;

            if (!ReUtil.isMatch(BaseRegexConstant.SIGN_IN_NAME_REGEXP, signUpAccountText)) {

                passFlag = false;

                signUpAccount.setError("登录名限制：只能包含中文，数字，字母，下划线，横杠，长度2-20");

            }

            String signUpPasswordText = signUpPassword.getText().toString();

            if (!ReUtil.isMatch(BaseRegexConstant.PASSWORD_REGEXP, signUpPasswordText)) {

                passFlag = false;

                signUpPassword.setError("密码限制：必须包含大小写字母和数字，可以使用特殊字符，长度8-20");

            }

            if (passFlag) {

                String password = MyRsaUtil.passwordRsaEncrypt(signUpPasswordText);

                String originPassword = MyRsaUtil.rsaEncryptPro(signUpPasswordText);

                SignSignInNameSignUpDTO signSignInNameSignUpDTO = new SignSignInNameSignUpDTO();

                signSignInNameSignUpDTO.setPassword(password);
                signSignInNameSignUpDTO.setOriginPassword(originPassword);
                signSignInNameSignUpDTO.setSignInName(signUpAccountText);

                SignSignInNameApi.signUp(signSignInNameSignUpDTO, new IHttpHandle<String>() {

                    @Override
                    public void success(ApiResultVO<String> apiResultVO) {

                        ToastUtil.makeText(apiResultVO.getMsg());

                        startActivity(new Intent(that, SignInActivity.class)); // 跳转到：登录页面

                    }

                });

            }

        });

    }

}

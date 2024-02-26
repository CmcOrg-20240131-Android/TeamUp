package com.cmcorg20230301.teamup.activity.sign.in;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.home.HomeActivity;
import com.cmcorg20230301.teamup.activity.sign.up.SignUpActivity;
import com.cmcorg20230301.teamup.api.http.SignSignInNameApi;
import com.cmcorg20230301.teamup.model.constant.BaseRegexConstant;
import com.cmcorg20230301.teamup.model.dto.SignSignInNameSignInPasswordDTO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.SignInVO;
import com.cmcorg20230301.teamup.util.MyRsaUtil;
import com.cmcorg20230301.teamup.util.UserUtil;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.Nullable;

import cn.hutool.core.util.ReUtil;

/**
 * 注册页
 */
public class SignInActivity extends BaseActivity {

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

        setContentView(R.layout.sign_in);

        TextView signInGoSignUp = findViewById(R.id.signInGoSignUp);

        SignInActivity that = this;

        signInGoSignUp.setOnClickListener(v -> {

            startActivity(new Intent(that, SignUpActivity.class));

        });

        Button signInSubmit = findViewById(R.id.signInSubmit);

        signInSubmit.setOnClickListener(v -> {

            TextInputEditText signInAccount = findViewById(R.id.signInAccount);

            TextInputEditText signInPassword = findViewById(R.id.signInPassword);

            String signInAccountText = signInAccount.getText().toString();

            boolean passFlag = true;

            if (!ReUtil.isMatch(BaseRegexConstant.SIGN_IN_NAME_REGEXP, signInAccountText)) {

                passFlag = false;

                signInAccount.setError("登录名限制：只能包含中文，数字，字母，下划线，横杠，长度2-20");

            }

            String signInPasswordText = signInPassword.getText().toString();

            if (passFlag) {

                String password = MyRsaUtil.passwordRsaEncrypt(signInPasswordText);

                SignSignInNameSignInPasswordDTO signSignInNameSignInPasswordDTO = new SignSignInNameSignInPasswordDTO();

                signSignInNameSignInPasswordDTO.setPassword(password);
                signSignInNameSignInPasswordDTO.setSignInName(signInAccountText);

                SignSignInNameApi.signInPassword(signSignInNameSignInPasswordDTO, new IHttpHandle<SignInVO>() {

                    @Override
                    public void success(ApiResultVO<SignInVO> apiResultVO) {

                        UserUtil.signInSuccess(apiResultVO, true);

                        startActivity(new Intent(that, HomeActivity.class)); // 跳转到：主页

                    }

                });

            }

        });

    }

}

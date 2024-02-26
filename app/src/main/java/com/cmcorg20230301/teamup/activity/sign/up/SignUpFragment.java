package com.cmcorg20230301.teamup.activity.sign.up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cmcorg20230301.teamup.BaseFragment;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.sign.in.SignInFragment;
import com.cmcorg20230301.teamup.api.http.SignSignInNameApi;
import com.cmcorg20230301.teamup.model.constant.BaseRegexConstant;
import com.cmcorg20230301.teamup.model.dto.SignSignInNameSignUpDTO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.util.MyRsaUtil;
import com.cmcorg20230301.teamup.util.ToastUtil;
import com.google.android.material.textfield.TextInputEditText;

import cn.hutool.core.util.ReUtil;

/**
 * 注册页
 */
public class SignUpFragment extends BaseFragment {

    @Override
    public Integer getLayoutId() {
        return R.layout.sign_up;
    }

    @Override
    public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView signUpGoSignIn = findViewById(R.id.signUpGoSignIn);

        signUpGoSignIn.setOnClickListener(v -> {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.signFragment, SignInFragment.class, null)
                    .commit();

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

                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.signFragment, SignInFragment.class, null)
                                .commit();

                    }

                });

            }

        });

    }

}

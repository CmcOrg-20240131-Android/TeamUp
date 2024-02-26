package com.cmcorg20230301.teamup.activity.sign.in;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cmcorg20230301.teamup.BaseFragment;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.home.HomeActivity;
import com.cmcorg20230301.teamup.activity.sign.up.SignUpFragment;
import com.cmcorg20230301.teamup.api.http.SignSignInNameApi;
import com.cmcorg20230301.teamup.model.constant.BaseRegexConstant;
import com.cmcorg20230301.teamup.model.dto.SignSignInNameSignInPasswordDTO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.SignInVO;
import com.cmcorg20230301.teamup.util.MyRsaUtil;
import com.cmcorg20230301.teamup.util.UserUtil;
import com.google.android.material.textfield.TextInputEditText;

import cn.hutool.core.util.ReUtil;

/**
 * 登录页
 */
public class SignInFragment extends BaseFragment {

    @Override
    public Integer getLayoutId() {
        return R.layout.sign_in;
    }

    @Override
    public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView signInGoSignUp = findViewById(R.id.signInGoSignUp);

        signInGoSignUp.setOnClickListener(v -> {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.signFragment, SignUpFragment.class, null)
                    .commit();

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

                        startActivity(new Intent(getActivity(), HomeActivity.class)); // 跳转到：主页

                    }

                });

            }

        });

    }

}

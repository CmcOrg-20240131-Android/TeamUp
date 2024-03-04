package com.cmcorg20230301.teamup.api.http;

import com.cmcorg20230301.teamup.model.dto.SignSignInNameSignInPasswordDTO;
import com.cmcorg20230301.teamup.model.dto.SignSignInNameSignUpDTO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.SignInVO;
import com.cmcorg20230301.teamup.util.MyHttpUtil;

import org.jetbrains.annotations.Nullable;

import cn.hutool.core.lang.TypeReference;

/**
 * 基础-登录注册-登录名
 */
public class SignSignInNameApi {

    /**
     * 注册
     */
    public static void signUp(SignSignInNameSignUpDTO dto, @Nullable IHttpHandle<String> iHttpHandle) {

        MyHttpUtil.post("/sign/signInName/sign/up", dto, iHttpHandle, new TypeReference<String>() {
        });

    }

    /**
     * 账号密码登录
     */
    public static void signInPassword(SignSignInNameSignInPasswordDTO dto, @Nullable IHttpHandle<SignInVO> iHttpHandle) {

        MyHttpUtil.post("/sign/signInName/sign/in/password", dto, iHttpHandle, new TypeReference<SignInVO>() {
        });

    }

}

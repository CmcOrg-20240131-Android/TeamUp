package com.cmcorg20230301.teamup.api.http;

import com.cmcorg20230301.teamup.model.dto.SignSignInNameSignInPasswordDTO;
import com.cmcorg20230301.teamup.model.dto.SignSignInNameSignUpDTO;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.SignInVO;
import com.cmcorg20230301.teamup.util.MyHttpUtil;

import org.jetbrains.annotations.Nullable;

import cn.hutool.core.lang.func.VoidFunc1;

/**
 * 基础-登录注册-登录名
 */
public class SignSignInNameApi {

    /**
     * 注册
     */
    public static void signUp(SignSignInNameSignUpDTO dto, @Nullable VoidFunc1<ApiResultVO<String>> voidFunc1) {

        MyHttpUtil.post("/sign/signInName/sign/up", dto, voidFunc1, String.class);

    }

    /**
     * 账号密码登录
     */
    public static void signInPassword(SignSignInNameSignInPasswordDTO dto, @Nullable VoidFunc1<ApiResultVO<SignInVO>> voidFunc1) {

        MyHttpUtil.post("/sign/signInName/sign/in/password", dto, voidFunc1, SignInVO.class);

    }

}

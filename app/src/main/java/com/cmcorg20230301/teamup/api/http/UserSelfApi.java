package com.cmcorg20230301.teamup.api.http;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.UserSelfInfoVO;
import com.cmcorg20230301.teamup.util.MyHttpUtil;

import cn.hutool.core.lang.TypeReference;

/**
 * 基础-用户-自我-管理
 */
public class UserSelfApi {

    /**
     * 获取：当前用户，基本信息
     */
    public static void userSelfInfo(@Nullable IHttpHandle<UserSelfInfoVO> iHttpHandle) {

        MyHttpUtil.post("/user/self/info", null, iHttpHandle, new TypeReference<UserSelfInfoVO>() {});

    }

}

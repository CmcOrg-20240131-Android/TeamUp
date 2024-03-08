package com.cmcorg20230301.teamup.util;

import cn.hutool.core.util.StrUtil;
import com.cmcorg20230301.teamup.activity.sign.SignActivity;
import com.cmcorg20230301.teamup.model.base.BaseActivity;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.SignInVO;
import org.jetbrains.annotations.Nullable;

/**
 * 用户工具类
 */
public class UserUtil {

    /**
     * 退出登录
     */
    public static void signOut(@Nullable String msg) {

        MyLocalStorage.clear();

        if (StrUtil.isNotBlank(msg)) {

            ToastUtil.makeText(msg);

        }

        // 跳转到：登录页面
        BaseActivity.getAppNav(SignActivity.class);

    }

    /**
     * 登录成功之后的处理
     */
    public static void signInSuccess(@Nullable ApiResultVO<SignInVO> apiResultVO, boolean showMsg) {

        if (apiResultVO == null) {
            return;
        }

        MyLocalStorage.clear();

        SignInVO signInVO = apiResultVO.getData();

        MyLocalStorage.setItem(LocalStorageKeyEnum.JWT, signInVO.getJwt());

        MyLocalStorage.setItem(LocalStorageKeyEnum.JWT_EXPIRE_TS, signInVO.getJwtExpireTs());

        if (showMsg) {

            ToastUtil.makeText("欢迎回来~");

        }

    }

    /**
     * 获取：头像
     */
    public static String getAvatarUrl(String avatarUrl) {

        if (StrUtil.isBlank(avatarUrl)) {

            return CommonConstant.FIXED_AVATAR_URL;

        }

        return avatarUrl;

    }

}

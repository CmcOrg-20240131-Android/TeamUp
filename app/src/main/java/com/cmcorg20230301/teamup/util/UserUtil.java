package com.cmcorg20230301.teamup.util;

import java.util.Date;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.activity.sign.SignActivity;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.SignInVO;
import com.cmcorg20230301.teamup.util.common.ToastUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

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
     * 获取：用户信息
     */
    public static void getUserSelfInfo() {


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

    /**
     * 获取：默认的昵称
     */
    public static String getRandomNickname() {
        return getRandomNickname("用户昵称");
    }

    /**
     * 根据前缀获取：默认的昵称 备注：不使用邮箱的原因，因为邮箱不符合 用户昵称的规则：只能包含中文，数字，字母，下划线，长度2-20
     */
    public static String getRandomNickname(@Nullable String preStr) {

        if (preStr == null) {
            preStr = "";
        }

        return preStr + RandomUtil.randomStringUpper(6);

    }

    /**
     * 获取：日期类型的昵称
     */
    public static String getDateTimeNickname(@Nullable String preStr) {

        if (preStr == null) {
            preStr = "";
        }

        String formatDateStr = DateUtil.formatDateTime(new Date());

        return preStr + formatDateStr;

    }

}

package com.cmcorg20230301.teamup.util;

import android.content.Intent;
import android.content.SharedPreferences;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.activity.sign.SignActivity;
import com.cmcorg20230301.teamup.model.enums.SharedPreferencesKeyEnum;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.model.vo.SignInVO;

import org.jetbrains.annotations.Nullable;

import cn.hutool.core.util.StrUtil;

/**
 * 用户工具类
 */
public class UserUtil {

    /**
     * 退出登录
     */
    public static void signOut(@Nullable String msg) {

        SharedPreferencesUtil.clear();

        if (StrUtil.isNotBlank(msg)) {

            ToastUtil.makeText(msg);

        }

        // 跳转到：登录页面
        BaseActivity.CURRENT_ACTIVITY.startActivity(new Intent(BaseActivity.CURRENT_ACTIVITY, SignActivity.class));

    }

    /**
     * 登录成功之后的处理
     */
    public static void signInSuccess(@Nullable ApiResultVO<SignInVO> apiResultVO, boolean showMsg) {

        if (apiResultVO == null) {
            return;
        }

        SharedPreferencesUtil.clear();

        SignInVO signInVO = apiResultVO.getData();

        SharedPreferences.Editor editor = SharedPreferencesUtil.getEditor();

        editor.putString(SharedPreferencesKeyEnum.JWT.name(), signInVO.getJwt());

        editor.putLong(SharedPreferencesKeyEnum.JWT_EXPIRE_TS.name(), signInVO.getJwtExpireTs());

        editor.apply();

        if (showMsg) {

            ToastUtil.makeText("欢迎回来~");

        }

    }

}

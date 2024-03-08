package com.cmcorg20230301.teamup.util.common;

import com.cmcorg20230301.teamup.layout.BaseActivity;

import android.view.View;

import androidx.annotation.ColorRes;

/**
 * 状态栏工具类
 */
public class StatusBarUtil {

    /**
     * 设置：颜色，备注：状态栏默认为：白色字体
     */
    public static void setColor(@ColorRes int id) {

        setColor(id, false);

    }

    /**
     * 设置：颜色
     *
     * @param lightFlag true 状态栏的字体和图标设置为黑色 false 设置为白色，备注：状态栏默认为：白色字体
     */
    public static void setColor(@ColorRes int id, boolean lightFlag) {

        BaseActivity.WINDOW.setStatusBarColor(BaseActivity.CONTEXT.getColor(id));

        if (lightFlag) {

            BaseActivity.WINDOW.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        }

    }

}

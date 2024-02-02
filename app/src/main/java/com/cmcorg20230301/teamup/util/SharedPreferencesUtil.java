package com.cmcorg20230301.teamup.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.cmcorg20230301.teamup.BaseActivity;

/**
 * SharedPreferences 工具类
 */
public class SharedPreferencesUtil {

    /**
     * 获取：SharedPreferences
     */
    public static SharedPreferences getSharedPreferences() {

        return BaseActivity.CURRENT_ACTIVITY.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);

    }

    /**
     * 获取：SharedPreferences的 Editor，备注：请使用 .apply()
     */
    public static SharedPreferences.Editor getEditor() {

        return getSharedPreferences().edit();

    }

    /**
     * 清除：SharedPreferences
     */
    public static void clear() {

        getEditor().clear().apply();

    }

}

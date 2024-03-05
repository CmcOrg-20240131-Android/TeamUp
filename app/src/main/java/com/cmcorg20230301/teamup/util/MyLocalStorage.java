package com.cmcorg20230301.teamup.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;

import cn.hutool.json.JSONUtil;

public class MyLocalStorage {

    /**
     * 获取：SharedPreferences
     */
    private static SharedPreferences getSharedPreferences() {

        return BaseActivity.CURRENT_ACTIVITY.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);

    }

    /**
     * 获取：SharedPreferences的 Editor，备注：请使用 .apply()
     */
    private static SharedPreferences.Editor getEditor() {

        return getSharedPreferences().edit();

    }

    private static void clear() {

        getEditor().clear().apply();

    }

    public static String getItem(LocalStorageKeyEnum localStorageKeyEnum) {

        return getSharedPreferences().getString(localStorageKeyEnum.name(), null);

    }

    public static void removeItem(LocalStorageKeyEnum localStorageKeyEnum) {

        getEditor().remove(localStorageKeyEnum.name()).apply();

    }

    public static void setItem(LocalStorageKeyEnum localStorageKeyEnum, Object object) {

        getEditor().putString(localStorageKeyEnum.name(), JSONUtil.toJsonStr(object)).apply();

    }

}

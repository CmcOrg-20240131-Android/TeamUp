package com.cmcorg20230301.teamup.util;

import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;

import android.content.Context;
import android.content.SharedPreferences;

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

    public static void clear() {

        getEditor().clear().apply();

    }

    public static String getItem(LocalStorageKeyEnum localStorageKeyEnum) {

        return getItem(localStorageKeyEnum.name());

    }

    public static String getItem(String key) {

        return getSharedPreferences().getString(key, null);

    }

    public static void removeItem(LocalStorageKeyEnum localStorageKeyEnum) {

        removeItem(localStorageKeyEnum.name());

    }

    public static void removeItem(String key) {

        getEditor().remove(key).apply();

    }

    public static void setItem(LocalStorageKeyEnum localStorageKeyEnum, Object object) {

        setItem(localStorageKeyEnum.name(), object);

    }

    public static void setItem(String key, Object object) {

        String dataStr;

        if (object instanceof Long) {

            dataStr = object.toString();

        } else {

            dataStr = JSONUtil.toJsonStr(object);

        }

        getEditor().putString(key, dataStr).apply();

    }

}

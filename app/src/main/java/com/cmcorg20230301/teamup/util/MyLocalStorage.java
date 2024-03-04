package com.cmcorg20230301.teamup.util;

import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;

import cn.hutool.json.JSONUtil;

public class MyLocalStorage {

    public static void clear() {

        SharedPreferencesUtil.clear();

    }

    public static String getItem(LocalStorageKeyEnum localStorageKeyEnum) {

        return SharedPreferencesUtil.getSharedPreferences().getString(localStorageKeyEnum.name(), null);

    }

    public static void removeItem(LocalStorageKeyEnum localStorageKeyEnum) {

        SharedPreferencesUtil.getEditor().remove(localStorageKeyEnum.name()).apply();

    }

    public static void setItem(LocalStorageKeyEnum localStorageKeyEnum, Object object) {

        SharedPreferencesUtil.getEditor().putString(localStorageKeyEnum.name(), JSONUtil.toJsonStr(object)).apply();

    }

}

package com.cmcorg20230301.teamup.util;

import cn.hutool.json.JSONUtil;
import com.cmcorg20230301.teamup.model.constant.BaseConstant;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.util.common.MyTimedCache;

public class MySessionStorage {

    private static final long TIMEOUT = BaseConstant.SECOND_20_EXPIRE_TIME;

    // 本地缓存：超时缓存，默认永不过期
    private static final MyTimedCache<String, String> LOCAL_CACHE = new MyTimedCache<>(-1);

    static {

        // 定时清理 map，过期的条目
        LOCAL_CACHE.schedulePrune(TIMEOUT + BaseConstant.SECOND_3_EXPIRE_TIME);

    }

    public static void clear() {

        LOCAL_CACHE.clear();

    }

    public static String getItem(LocalStorageKeyEnum localStorageKeyEnum) {

        return LOCAL_CACHE.get(localStorageKeyEnum.name());

    }

    public static void removeItem(LocalStorageKeyEnum localStorageKeyEnum) {

        LOCAL_CACHE.remove(localStorageKeyEnum.name());

    }

    public static void setItem(LocalStorageKeyEnum localStorageKeyEnum, Object object) {

        LOCAL_CACHE.put(localStorageKeyEnum.name(), JSONUtil.toJsonStr(object));

    }

}

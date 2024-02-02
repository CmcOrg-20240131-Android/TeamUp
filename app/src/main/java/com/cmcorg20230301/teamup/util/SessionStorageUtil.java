package com.cmcorg20230301.teamup.util;

import com.cmcorg20230301.teamup.model.constant.BaseConstant;

/**
 * 每次启动 App之后的数据存储
 */
public class SessionStorageUtil {

    private static final long TIMEOUT = BaseConstant.SECOND_20_EXPIRE_TIME;

    // 本地缓存：超时缓存，默认永不过期
    private static final MyTimedCache<String, Object> LOCAL_CACHE = new MyTimedCache<>(-1);

    static {

        // 定时清理 map，过期的条目
        LOCAL_CACHE.schedulePrune(TIMEOUT + BaseConstant.SECOND_3_EXPIRE_TIME);

    }

}

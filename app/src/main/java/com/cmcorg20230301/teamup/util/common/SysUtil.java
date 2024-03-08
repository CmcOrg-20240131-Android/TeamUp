package com.cmcorg20230301.teamup.util.common;

import com.cmcorg20230301.teamup.BuildConfig;

/**
 * 系统工具类
 */
public class SysUtil {

    /**
     * 获取：是否是：开发环境
     */
    public static boolean devFlag() {

        return BuildConfig.DEBUG;

    }

}

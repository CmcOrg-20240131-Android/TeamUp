package com.cmcorg20230301.teamup.util;

import android.util.Log;

import cn.hutool.core.util.StrUtil;

/**
 * 日志工具类
 */
public class LogUtil {

    public static final String TAG = "TeamUp";

    public static void debug(CharSequence template, Object... params) {

        Log.d(TAG, "debug: " + StrUtil.format(template, params));

    }

}

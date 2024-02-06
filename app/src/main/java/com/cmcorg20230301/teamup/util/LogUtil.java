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

    public static void debug(String tag, CharSequence template, Object... params) {

        Log.d(tag, "debug: " + StrUtil.format(template, params));

    }

    public static void error(CharSequence template, Object... params) {

        Log.e(TAG, "error: " + StrUtil.format(template, params));

    }

    public static void error(String tag, CharSequence template, Object... params) {

        Log.e(tag, "error: " + StrUtil.format(template, params));

    }

}

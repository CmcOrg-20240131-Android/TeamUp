package com.cmcorg20230301.teamup.util.common;

import com.cmcorg20230301.teamup.layout.BaseActivity;

import android.widget.Toast;

import cn.hutool.core.util.StrUtil;

/**
 * 提示工具类
 */
public class ToastUtil {

    private static Toast oldToast = null;

    /**
     * 短显示
     */
    public static void makeTextShort(CharSequence text) {
        makeText(text, Toast.LENGTH_SHORT);
    }

    /**
     * 长显示
     */
    public static void makeTextLong(CharSequence text) {
        makeText(text, Toast.LENGTH_LONG);
    }

    /**
     * 长显示
     */
    public static void makeText(CharSequence text) {
        makeText(text, Toast.LENGTH_LONG);
    }

    public static synchronized void makeText(CharSequence text, int duration) {

        if (StrUtil.isBlank(text)) {
            return;
        }

        if (oldToast != null) {

            oldToast.cancel();

        }

        oldToast = Toast.makeText(BaseActivity.CURRENT_ACTIVITY, text, duration);

        BaseActivity.CURRENT_ACTIVITY.runOnUiThread(() -> {

            oldToast.show();

        });

    }

}

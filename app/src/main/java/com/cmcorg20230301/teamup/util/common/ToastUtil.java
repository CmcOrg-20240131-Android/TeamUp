package com.cmcorg20230301.teamup.util.common;

import android.widget.Toast;
import cn.hutool.core.util.StrUtil;
import com.cmcorg20230301.teamup.layout.BaseActivity;

/**
 * 提示工具类
 */
public class ToastUtil {

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

    public static void makeText(CharSequence text, int duration) {

        if (StrUtil.isBlank(text)) {
            return;
        }

        BaseActivity.CURRENT_ACTIVITY.runOnUiThread(() -> {

            Toast.makeText(BaseActivity.CURRENT_ACTIVITY, text, duration).show();

        });

    }

}

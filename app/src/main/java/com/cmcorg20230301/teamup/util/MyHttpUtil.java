package com.cmcorg20230301.teamup.util;

import com.cmcorg20230301.teamup.model.vo.ApiResultVO;

import org.jetbrains.annotations.Nullable;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpGlobalConfig;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

/**
 * 网络请求工具类
 */
public class MyHttpUtil {

    static {

        // 设置：http超时时间，默认：30分钟
        HttpGlobalConfig.setTimeout(30 * 60 * 1000);

    }

    public static final String TIMEOUT_MSG = "请求超时，请重试";
    public static final String BASE_ERROR_MSG = "请求错误：";
    public static final String REQUEST_ERROR_MSG = "请求失败：服务器未启动";

    public static final String API_URL = "https://cmcopen.top/prod-api/lx-saas";

    public static final String BASE_URL = SysUtil.devFlag() ? "/api" : API_URL;

    /**
     * 发送get请求
     *
     * @param urlString 网址
     * @return 返回内容，如果只检查状态码，正常只返回 ""，不正常返回 null
     */
    @Nullable
    public static <T> ApiResultVO<T> get(String urlString) {

        if (urlString.startsWith("http")) {

            return null;

        }

        String resStr = HttpUtil.get(urlString);

        return JSONUtil.toBean(resStr, new TypeReference<ApiResultVO<T>>() {
        }, false);

    }

}

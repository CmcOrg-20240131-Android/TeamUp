package com.cmcorg20230301.teamup.util;

import com.cmcorg20230301.teamup.model.enums.SharedPreferencesKeyEnum;
import com.cmcorg20230301.teamup.model.enums.SysRequestCategoryEnum;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpGlobalConfig;
import cn.hutool.http.HttpRequest;
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
     * 执行
     */
    private static <T> ApiResultVO<T> execHttpRequest(HttpRequest httpRequest) {

        httpRequest.setUrl(BASE_URL + httpRequest.getUrl());

        httpRequest.header("Authorization", SharedPreferencesUtil.getSharedPreferences().getString(SharedPreferencesKeyEnum.JWT.name(), ""));

        httpRequest.header("category", String.valueOf(SysRequestCategoryEnum.ANDROID.getCode()));

        String resStr = httpRequest.execute().body();

        return JSONUtil.toBean(resStr, new TypeReference<ApiResultVO<T>>() {
        }, false);

    }

    /**
     * 发送get请求
     *
     * @param urlString 网址
     * @return 返回内容，如果只检查状态码，正常只返回 ""，不正常返回 null
     */
    @NotNull
    public static <T> ApiResultVO<T> get(String urlString) {

        HttpRequest httpRequest = HttpRequest.get(urlString);

        // 执行
        return execHttpRequest(httpRequest);

    }

    /**
     * 发送get请求
     *
     * @param urlString 网址
     * @param paramMap  post表单数据
     * @return 返回数据
     */
    @NotNull
    public static <T> ApiResultVO<T> get(String urlString, Map<String, Object> paramMap) {

        HttpRequest httpRequest = HttpRequest.get(urlString).form(paramMap);

        // 执行
        return execHttpRequest(httpRequest);

    }

    /**
     * 发送get请求
     *
     * @param urlString 网址
     * @return 返回内容，如果只检查状态码，正常只返回 ""，不正常返回 null
     */
    @NotNull
    public static <T> ApiResultVO<T> post(String urlString, String body) {

        HttpRequest httpRequest = HttpRequest.post(urlString).body(body);

        // 执行
        return execHttpRequest(httpRequest);

    }

}

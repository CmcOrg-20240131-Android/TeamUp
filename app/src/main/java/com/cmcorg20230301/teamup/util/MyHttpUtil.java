package com.cmcorg20230301.teamup.util;

import com.cmcorg20230301.teamup.exception.BaseBizCodeEnum;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.enums.SharedPreferencesKeyEnum;
import com.cmcorg20230301.teamup.model.enums.SysRequestCategoryEnum;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.lang.func.VoidFunc1;
import cn.hutool.core.util.StrUtil;
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
    public static final String REQUEST_ERROR_MSG = "请求失败：请稍后再试";

    public static final String API_URL = "https://cmcopen.top/prod-api/lx-saas";

    public static final String BASE_URL = SysUtil.devFlag() ? "/api" : API_URL;

    /**
     * 处理：ApiResultVO
     */
    private static <T> void handleApiResultVO(@Nullable VoidFunc1<ApiResultVO<T>> voidFunc1, ApiResultVO<T> apiResultVO) {

        if (apiResultVO != null && voidFunc1 != null) {

            TryUtil.tryCatch(() -> {

                voidFunc1.call(apiResultVO);

            });

        }

    }

    /**
     * 执行
     *
     * @param hiddenErrorMsgFlag 是否隐藏错误
     */
    @Nullable
    private static <T> ApiResultVO<T> execHttpRequest(HttpRequest httpRequest, boolean hiddenErrorMsgFlag, @Nullable VoidFunc1<ApiResultVO<T>> voidFunc1) {

        String url = httpRequest.getUrl();

        httpRequest.setUrl(BASE_URL + url);

        httpRequest.header("Authorization", SharedPreferencesUtil.getSharedPreferences().getString(SharedPreferencesKeyEnum.JWT.name(), ""));

        httpRequest.header("category", String.valueOf(SysRequestCategoryEnum.ANDROID.getCode()));

        try {

            String resStr = httpRequest.execute().body();

            ApiResultVO<T> apiResultVO = JSONUtil.toBean(resStr, new TypeReference<ApiResultVO<T>>() {
            }, false);

            Integer resCode = apiResultVO.getCode();

            if (CommonConstant.API_OK_CODE != resCode) {

                if (BaseBizCodeEnum.NOT_LOGGED_IN_YET.getCode() == resCode) { // 这个代码需要跳转到：登录页面

                    if (!hiddenErrorMsgFlag) {

                        String jwt = SharedPreferencesUtil.getSharedPreferences().getString(SharedPreferencesKeyEnum.JWT.name(), null);

                        if (StrUtil.isNotBlank(jwt)) {

                            ToastUtil.makeText(apiResultVO.getMsg());

                        }

                    }

                    UserUtil.signOut(null); // 退出登录

                } else {

                    if (!hiddenErrorMsgFlag) {

                        ToastUtil.makeText(apiResultVO.getMsg());

                    }

                }

            }

            // 处理：ApiResultVO
            handleApiResultVO(voidFunc1, apiResultVO);

            return apiResultVO;

        } catch (Exception e) {

            MyExceptionUtil.printHttpError(e, url);

            if (!hiddenErrorMsgFlag) {

                ToastUtil.makeText(REQUEST_ERROR_MSG);

            }

            return null;

        }

    }

    /**
     * 发送get请求
     *
     * @param urlString 网址
     * @return 返回内容，如果只检查状态码，正常只返回 ""，不正常返回 null
     */
    @Nullable
    public static <T> ApiResultVO<T> get(String urlString, @Nullable VoidFunc1<ApiResultVO<T>> voidFunc1) {

        HttpRequest httpRequest = HttpRequest.get(urlString);

        // 执行
        return execHttpRequest(httpRequest, false, voidFunc1);

    }

    /**
     * 发送get请求
     *
     * @param urlString 网址
     * @param paramMap  post表单数据
     * @return 返回数据
     */
    @Nullable
    public static <T> ApiResultVO<T> get(String urlString, Map<String, Object> paramMap, @Nullable VoidFunc1<ApiResultVO<T>> voidFunc1) {

        HttpRequest httpRequest = HttpRequest.get(urlString).form(paramMap);

        // 执行
        return execHttpRequest(httpRequest, false, voidFunc1);

    }

    /**
     * 发送post请求
     *
     * @param urlString 网址
     * @return 返回内容，如果只检查状态码，正常只返回 ""，不正常返回 null
     */
    @Nullable
    public static <T> ApiResultVO<T> post(String urlString, Object body, @Nullable VoidFunc1<ApiResultVO<T>> voidFunc1) {

        HttpRequest httpRequest = HttpRequest.post(urlString).body(JSONUtil.toJsonStr(body));

        // 执行
        return execHttpRequest(httpRequest, false, voidFunc1);

    }

    /**
     * 发送post请求
     *
     * @param urlString 网址
     * @return 返回内容，如果只检查状态码，正常只返回 ""，不正常返回 null
     */
    @Nullable
    public static <T> ApiResultVO<T> postHiddenError(String urlString, Object body, @Nullable VoidFunc1<ApiResultVO<T>> voidFunc1) {

        HttpRequest httpRequest = HttpRequest.post(urlString).body(JSONUtil.toJsonStr(body));

        // 执行
        return execHttpRequest(httpRequest, true, voidFunc1);

    }

}

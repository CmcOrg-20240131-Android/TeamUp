package com.cmcorg20230301.teamup.util;

import com.cmcorg20230301.teamup.exception.BaseBizCodeEnum;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.enums.SharedPreferencesKeyEnum;
import com.cmcorg20230301.teamup.model.enums.SysRequestCategoryEnum;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpGlobalConfig;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
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

//    public static final String BASE_URL = SysUtil.devFlag() ? "http://192.168.1.8:10001" : API_URL;

    public static final String BASE_URL = API_URL;

    /**
     * 执行
     *
     * @param hiddenErrorMsgFlag 是否隐藏错误
     */
    private static <T> void execHttpRequest(HttpRequest httpRequest, boolean hiddenErrorMsgFlag, @Nullable IHttpHandle<T> iHttpHandle, String urlString, Class<T> clazz) {

        httpRequest.setUrl(BASE_URL + urlString);

        httpRequest.header("Authorization", SharedPreferencesUtil.getSharedPreferences().getString(SharedPreferencesKeyEnum.JWT.name(), ""));

        httpRequest.header("category", String.valueOf(SysRequestCategoryEnum.ANDROID.getCode()));

        MyThreadUtil.execute(() -> {

            try {

                String resStr = httpRequest.execute().body();

                ApiResultVO<T> apiResultVO = JSONUtil.toBean(resStr, ApiResultVO.class, false);

                Integer resCode = apiResultVO.getCode();

                if (apiResultVO.getData() != null && apiResultVO.getData() instanceof JSONObject) {

                    // 类型转换
                    apiResultVO.setData(BeanUtil.toBean(apiResultVO.getData(), clazz));

                }

                if (CommonConstant.API_OK_CODE != resCode) {

                    // 处理：错误代码
                    handleErrorCode(hiddenErrorMsgFlag, iHttpHandle, apiResultVO, resCode);
                    return;

                }

                // 处理：ApiResultVO
                handleSuccessApiResultVO(iHttpHandle, apiResultVO);

            } catch (Exception e) {

                MyExceptionUtil.printHttpError(e, httpRequest.getUrl());

                if (!hiddenErrorMsgFlag) {

                    ToastUtil.makeText(REQUEST_ERROR_MSG);

                }

                // 处理：ApiResultVO
                handleErrorApiResultVO(iHttpHandle, null);

            }

        });

    }

    /**
     * 处理：错误代码
     */
    private static <T> void handleErrorCode(boolean hiddenErrorMsgFlag, @Nullable IHttpHandle<T> iHttpHandle, ApiResultVO<T> apiResultVO, Integer resCode) {

        if (BaseBizCodeEnum.NOT_LOGGED_IN_YET.getCode() == resCode) { // 这个代码需要跳转到：登录页面

            if (!hiddenErrorMsgFlag) {

                String jwt = SharedPreferencesUtil.getSharedPreferences().getString(SharedPreferencesKeyEnum.JWT.name(), null);

                if (StrUtil.isNotBlank(jwt)) {

                    ToastUtil.makeText(apiResultVO.getMsg());

                }

            }

            // 处理：ApiResultVO
            handleErrorApiResultVO(iHttpHandle, apiResultVO);

            UserUtil.signOut(null); // 退出登录

        } else {

            if (!hiddenErrorMsgFlag) {

                ToastUtil.makeText(apiResultVO.getMsg());

            }

            // 处理：ApiResultVO
            handleErrorApiResultVO(iHttpHandle, apiResultVO);

        }

    }

    /**
     * 处理：ApiResultVO
     */
    private static <T> void handleSuccessApiResultVO(@Nullable IHttpHandle<T> iHttpHandle, ApiResultVO<T> apiResultVO) {

        if (apiResultVO != null && iHttpHandle != null) {

            TryUtil.tryCatch(() -> {

                iHttpHandle.success(apiResultVO);

            });

        }

    }

    /**
     * 处理：ApiResultVO
     */
    private static <T> void handleErrorApiResultVO(@Nullable IHttpHandle<T> iHttpHandle, @Nullable ApiResultVO<T> apiResultVO) {

        if (iHttpHandle != null) {

            TryUtil.tryCatch(() -> {

                iHttpHandle.error(apiResultVO);

            });

        }

    }

    /**
     * 发送get请求
     */
    public static <T> void get(String urlString, @Nullable IHttpHandle<T> iHttpHandle, Class<T> clazz) {

        HttpRequest httpRequest = HttpRequest.get(urlString).method(Method.GET);

        // 执行
        execHttpRequest(httpRequest, false, iHttpHandle, urlString, clazz);

    }

    /**
     * 发送get请求
     */
    public static <T> void get(String urlString, Map<String, Object> paramMap, @Nullable IHttpHandle<T> iHttpHandle, Class<T> clazz) {

        HttpRequest httpRequest = HttpRequest.get(urlString).form(paramMap);

        // 执行
        execHttpRequest(httpRequest, false, iHttpHandle, urlString, clazz);

    }

    /**
     * 发送post请求
     */
    public static <T> void post(String urlString, Object body, @Nullable IHttpHandle<T> iHttpHandle, Class<T> clazz) {

        HttpRequest httpRequest = HttpRequest.post(urlString).body(JSONUtil.toJsonStr(body));

        // 执行
        execHttpRequest(httpRequest, false, iHttpHandle, urlString, clazz);

    }

    /**
     * 发送post请求
     */
    public static <T> void postHiddenError(String urlString, Object body, @Nullable IHttpHandle<T> iHttpHandle, Class<T> clazz) {

        HttpRequest httpRequest = HttpRequest.post(urlString).body(JSONUtil.toJsonStr(body));

        // 执行
        execHttpRequest(httpRequest, true, iHttpHandle, urlString, clazz);

    }

}

package com.cmcorg20230301.teamup.util;

import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.exception.BaseBizCodeEnum;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.model.enums.SysRequestCategoryEnum;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.util.common.LogUtil;
import com.cmcorg20230301.teamup.util.common.MyThreadUtil;
import com.cmcorg20230301.teamup.util.common.ToastUtil;
import com.cmcorg20230301.teamup.util.common.TryUtil;

import cn.hutool.core.lang.TypeReference;
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

    // public static final String BASE_URL = SysUtil.devFlag() ? "http://192.168.1.8:10001" : API_URL;

    public static final String BASE_URL = API_URL;

    /**
     * 执行
     *
     * @param hiddenErrorMsgFlag 是否隐藏错误
     */
    private static <T> void execHttpRequest(HttpRequest httpRequest, boolean hiddenErrorMsgFlag,
        @Nullable IHttpHandle<T> iHttpHandle, String urlString, TypeReference<T> typeReference) {

        httpRequest.setUrl(BASE_URL + urlString);

        String jwt = MyLocalStorage.getItem(LocalStorageKeyEnum.JWT);

        httpRequest.header("Authorization", jwt);

        httpRequest.header("category", String.valueOf(SysRequestCategoryEnum.ANDROID.getCode()));

        MyThreadUtil.execute(() -> {

            try {

                String resStr = httpRequest.execute().body();

                // 获取：请求的内容
                String requestStr = getRequestStr(httpRequest);

                LogUtil.debug("发送请求，页面：{}\nurl：{}\njwt：{}\n请求体：{}\n响应体：{}",
                    BaseActivity.CURRENT_ACTIVITY.getClass().getName(), httpRequest.getUrl(), jwt, requestStr, resStr);

                ApiResultVO<T> apiResultVO = JSONUtil.toBean(resStr, ApiResultVO.class, false);

                Integer resCode = apiResultVO.getCode();

                if (apiResultVO.getData() != null && apiResultVO.getData() instanceof JSONObject) {

                    // 类型转换
                    apiResultVO.setData(JSONUtil.toBean((JSONObject)apiResultVO.getData(), typeReference, false));

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
     * 获取：请求的内容
     */
    private static String getRequestStr(HttpRequest httpRequest) {

        String requestStr = "";

        if (Method.GET.equals(httpRequest.getMethod())) {

            requestStr = JSONUtil.toJsonStr(httpRequest.form());

        } else if (Method.POST.equals(httpRequest.getMethod())) {

            byte[] byteArr = httpRequest.bodyBytes();

            requestStr = StrUtil.utf8Str(byteArr);

        }

        return requestStr;

    }

    /**
     * 处理：错误代码
     */
    private static <T> void handleErrorCode(boolean hiddenErrorMsgFlag, @Nullable IHttpHandle<T> iHttpHandle,
        ApiResultVO<T> apiResultVO, Integer resCode) {

        if (BaseBizCodeEnum.NOT_LOGGED_IN_YET.getCode() == resCode) { // 这个代码需要跳转到：登录页面

            if (!hiddenErrorMsgFlag) {

                String jwt = MyLocalStorage.getItem(LocalStorageKeyEnum.JWT);

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
    private static <T> void handleErrorApiResultVO(@Nullable IHttpHandle<T> iHttpHandle,
        @Nullable ApiResultVO<T> apiResultVO) {

        if (iHttpHandle != null) {

            TryUtil.tryCatch(() -> {

                iHttpHandle.error(apiResultVO);

            });

        }

    }

    /**
     * 发送get请求
     */
    public static <T> void get(String urlString, @Nullable IHttpHandle<T> iHttpHandle, TypeReference<T> typeReference) {

        HttpRequest httpRequest = HttpRequest.get(urlString).method(Method.GET);

        // 执行
        execHttpRequest(httpRequest, false, iHttpHandle, urlString, typeReference);

    }

    /**
     * 发送get请求
     */
    public static <T> void get(String urlString, Map<String, Object> paramMap, @Nullable IHttpHandle<T> iHttpHandle,
        TypeReference<T> typeReference) {

        HttpRequest httpRequest = HttpRequest.get(urlString).form(paramMap);

        // 执行
        execHttpRequest(httpRequest, false, iHttpHandle, urlString, typeReference);

    }

    /**
     * 发送post请求
     */
    public static <T> void post(String urlString, Object body, @Nullable IHttpHandle<T> iHttpHandle,
        TypeReference<T> typeReference) {

        HttpRequest httpRequest = HttpRequest.post(urlString).body(JSONUtil.toJsonStr(body));

        // 执行
        execHttpRequest(httpRequest, iHttpHandle != null && iHttpHandle.getHiddenErrorMsgFlag(), iHttpHandle, urlString,
            typeReference);

    }

    /**
     * 发送post请求
     */
    public static <T> void postHiddenError(String urlString, Object body, @Nullable IHttpHandle<T> iHttpHandle,
        TypeReference<T> typeReference) {

        HttpRequest httpRequest = HttpRequest.post(urlString).body(JSONUtil.toJsonStr(body));

        // 执行
        execHttpRequest(httpRequest, true, iHttpHandle, urlString, typeReference);

    }

}

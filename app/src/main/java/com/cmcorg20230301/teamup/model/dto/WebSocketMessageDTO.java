package com.cmcorg20230301.teamup.model.dto;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.exception.BaseBizCodeEnum;
import com.cmcorg20230301.teamup.exception.IBizCode;
import com.cmcorg20230301.teamup.model.interfaces.IWebSocketUri;

import cn.hutool.core.util.StrUtil;

public class WebSocketMessageDTO<T> {

    /**
     * 路径
     */
    private String uri;

    /**
     * 数据
     */
    private T data;

    /**
     * 响应代码，成功返回：200
     */
    private Integer code;

    /**
     * 响应描述
     */
    private String msg;

    /**
     * 服务名
     */
    private String service;

    public WebSocketMessageDTO(String uri) {
        this.uri = uri;
    }

    private WebSocketMessageDTO(String uri, Integer code, String msg, @Nullable T data) {

        this.uri = uri;
        this.msg = msg;
        this.code = code;
        this.data = data;

    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getService() {
        return service;
    }

    private void setService(String service) {
        // 不允许修改 service的值
    }

    /**
     * 系统异常
     */
    public static void sysError(IWebSocketUri iWebSocketUri) {
        error(iWebSocketUri, BaseBizCodeEnum.API_RESULT_SYS_ERROR);
    }

    /**
     * 操作失败
     */
    public static <T> WebSocketMessageDTO<T> error(IWebSocketUri iWebSocketUri, IBizCode iBizCode) {
        return new WebSocketMessageDTO<>(iWebSocketUri.getUri(), iBizCode.getCode(), iBizCode.getMsg(), null);
    }

    public static <T> WebSocketMessageDTO<T> error(IWebSocketUri iWebSocketUri, IBizCode iBizCode, @Nullable T data) {
        return new WebSocketMessageDTO<>(iWebSocketUri.getUri(), iBizCode.getCode(), iBizCode.getMsg(), data);
    }

    public static <T> WebSocketMessageDTO<T> error(IWebSocketUri iWebSocketUri, String msg, @Nullable T data) {
        return new WebSocketMessageDTO<>(iWebSocketUri.getUri(), BaseBizCodeEnum.API_RESULT_SYS_ERROR.getCode(), msg,
            data);
    }

    public static <T> WebSocketMessageDTO<T> errorMsg(IWebSocketUri iWebSocketUri, String msgTemp, Object... paramArr) {
        return new WebSocketMessageDTO<>(iWebSocketUri.getUri(), BaseBizCodeEnum.API_RESULT_SYS_ERROR.getCode(),
            StrUtil.format(msgTemp, paramArr), null);
    }

    public static <T> WebSocketMessageDTO<T> errorCode(String uri, Integer code) {
        return new WebSocketMessageDTO<>(uri, code, null, null);
    }

    /**
     * 操作成功
     */
    public static <T> WebSocketMessageDTO<T> ok(IWebSocketUri iWebSocketUri, String msg, @Nullable T data) {
        return new WebSocketMessageDTO<>(iWebSocketUri.getUri(), BaseBizCodeEnum.API_RESULT_OK.getCode(), msg, data);
    }

    public static <T> WebSocketMessageDTO<T> okData(IWebSocketUri iWebSocketUri, @Nullable T data) {
        return new WebSocketMessageDTO<>(iWebSocketUri.getUri(), BaseBizCodeEnum.API_RESULT_OK.getCode(), null, data);
    }

    public static <T> WebSocketMessageDTO<T> okMsg(IWebSocketUri iWebSocketUri, String msg) {
        return new WebSocketMessageDTO<>(iWebSocketUri.getUri(), BaseBizCodeEnum.API_RESULT_OK.getCode(), msg, null);
    }

}

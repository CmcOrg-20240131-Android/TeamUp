package com.cmcorg20230301.teamup.model.vo;

import com.cmcorg20230301.teamup.exception.BaseBizCodeEnum;
import com.cmcorg20230301.teamup.exception.BaseException;
import com.cmcorg20230301.teamup.exception.IBizCode;

import org.jetbrains.annotations.Contract;

import cn.hutool.core.util.StrUtil;

/**
 * 统一响应实体类
 */
public class ApiResultVO<T> {

    /**
     * 响应代码，成功返回：200
     */
    private Integer code;

    /**
     * 响应描述
     */
    private String msg;

    /**
     * 服务器是否收到请求，只会返回 true
     */
    private Boolean successFlag;

    /**
     * 数据
     */
    private T data;

    /**
     * 服务名
     */
    private String service;

    private ApiResultVO(Integer code, String msg, T data) {

        this.msg = msg;
        this.code = code;
        this.data = data;
        this.successFlag = true;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getService() {
        return service;
    }

    private void setSuccessFlag(boolean successFlag) {
        // 不允许修改 success的值
    }

    private void setService(String service) {
        // 不允许修改 service的值
    }

    /**
     * Contract注解，目的：让 IDEA知道这里会抛出异常
     */
    @Contract(" -> fail")
    public ApiResultVO<T> error() {
        throw new BaseException(this);
    }

    /**
     * 系统异常
     */
    public static void sysError() {
        error(BaseBizCodeEnum.API_RESULT_SYS_ERROR);
    }

    /**
     * 操作失败
     */
    @Contract("_ -> fail")
    public static <T> ApiResultVO<T> error(IBizCode iBizCode) {
        return new ApiResultVO<T>(iBizCode.getCode(), iBizCode.getMsg(), null).error();
    }

    @Contract("_,_ -> fail")
    public static <T> ApiResultVO<T> error(IBizCode iBizCode, T data) {
        return new ApiResultVO<>(iBizCode.getCode(), iBizCode.getMsg(), data).error();
    }

    @Contract("_,_ -> fail")
    public static <T> ApiResultVO<T> error(String msg, T data) {
        return new ApiResultVO<>(BaseBizCodeEnum.API_RESULT_SYS_ERROR.getCode(), msg, data).error();
    }

    @Contract("_,_ -> fail")
    public static <T> ApiResultVO<T> errorMsg(String msgTemp, Object... paramArr) {
        return new ApiResultVO<T>(BaseBizCodeEnum.API_RESULT_SYS_ERROR.getCode(),
            StrUtil.format(msgTemp, paramArr),
            null).error();
    }

    /**
     * 操作成功
     */
    public static <T> ApiResultVO<T> ok(String msg, T data) {
        return new ApiResultVO<>(BaseBizCodeEnum.API_RESULT_OK.getCode(), msg, data);
    }

    public static <T> ApiResultVO<T> okData(T data) {
        return new ApiResultVO<>(BaseBizCodeEnum.API_RESULT_OK.getCode(),
            BaseBizCodeEnum.API_RESULT_OK.getMsg(), data);
    }

    public static <T> ApiResultVO<T> okMsg(String msg) {
        return new ApiResultVO<>(BaseBizCodeEnum.API_RESULT_OK.getCode(), msg, null);
    }

}

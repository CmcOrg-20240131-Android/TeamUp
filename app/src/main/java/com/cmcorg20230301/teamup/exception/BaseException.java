package com.cmcorg20230301.teamup.exception;

import com.cmcorg20230301.teamup.model.vo.ApiResultVO;

import cn.hutool.json.JSONUtil;

public class BaseException extends RuntimeException {

    private ApiResultVO<?> apiResultVO;

    public BaseException(ApiResultVO<?> apiResult) {

        super(JSONUtil.toJsonStr(apiResult)); // 把信息封装成json格式

        setApiResultVO(apiResult);

    }

    public ApiResultVO<?> getApiResultVO() {
        return apiResultVO;
    }

    public void setApiResultVO(ApiResultVO<?> apiResultVO) {
        this.apiResultVO = apiResultVO;
    }

}

package com.cmcorg20230301.teamup.exception;

import com.cmcorg20230301.teamup.model.vo.ApiResultVO;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private ApiResultVO<?> apiResultVO;

    public BaseException(ApiResultVO<?> apiResult) {

        super(JSONUtil.toJsonStr(apiResult)); // 把信息封装成json格式

        setApiResultVO(apiResult);

    }

}

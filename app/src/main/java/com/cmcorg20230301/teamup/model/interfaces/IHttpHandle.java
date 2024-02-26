package com.cmcorg20230301.teamup.model.interfaces;

import com.cmcorg20230301.teamup.model.vo.ApiResultVO;

import org.jetbrains.annotations.Nullable;

public interface IHttpHandle<T> {

    default void success(ApiResultVO<T> apiResultVO) {

    }

    /**
     * @param apiResultVO 如果为 null，则表示是请求异常
     */
    default void error(@Nullable ApiResultVO<T> apiResultVO) {

    }

}

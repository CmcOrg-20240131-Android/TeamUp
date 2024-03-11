package com.cmcorg20230301.teamup.model.interfaces;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.model.vo.ApiResultVO;

public interface IHttpHandle<T> {

    default void success(ApiResultVO<T> apiResultVO) throws Exception {

    }

    /**
     * @param apiResultVO 如果为 null，则表示是请求异常
     */
    default void error(@Nullable ApiResultVO<T> apiResultVO) {

    }

}

package com.cmcorg20230301.teamup.api.http;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.model.dto.NotEmptyIdSet;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.LongObjectMapVO;
import com.cmcorg20230301.teamup.util.MyHttpUtil;

import cn.hutool.core.lang.TypeReference;

/**
 * 基础-文件-管理
 */
public class SysFileApi {

    /**
     * 批量获取：公开文件的 url
     */
    public static void getPublicUrl(NotEmptyIdSet dto, @Nullable IHttpHandle<LongObjectMapVO<String>> iHttpHandle) {

        MyHttpUtil.post("/sys/file/getPublicUrl", dto, iHttpHandle, new TypeReference<LongObjectMapVO<String>>() {});

    }

}

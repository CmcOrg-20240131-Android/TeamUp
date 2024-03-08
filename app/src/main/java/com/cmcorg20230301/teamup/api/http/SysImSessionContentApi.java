package com.cmcorg20230301.teamup.api.http;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.model.dto.SysImSessionContentListDTO;
import com.cmcorg20230301.teamup.model.entity.SysImSessionContentDO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.util.MyHttpUtil;

import cn.hutool.core.lang.TypeReference;

/**
 * 基础-即时通讯-会话-内容-管理
 */
public class SysImSessionContentApi {

    /**
     * 查询会话内容-用户自我
     */
    public static void scrollPageUserSelf(SysImSessionContentListDTO dto,
        @Nullable IHttpHandle<Page<SysImSessionContentDO>> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/content/scrollPage/userSelf", dto, iHttpHandle,
            new TypeReference<Page<SysImSessionContentDO>>() {});

    }

}

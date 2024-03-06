package com.cmcorg20230301.teamup.api.http;

import com.cmcorg20230301.teamup.model.dto.SysImSessionSelfPageDTO;
import com.cmcorg20230301.teamup.model.entity.SysImSessionDO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.util.MyHttpUtil;

import org.jetbrains.annotations.Nullable;

import cn.hutool.core.lang.TypeReference;

/**
 * 基础-即时通讯-会话-管理
 */
public class SysImSessionApi {

    /**
     * 分页排序查询-会话列表-自我
     */
    public static void myPageSelf(SysImSessionSelfPageDTO dto,
        @Nullable IHttpHandle<Page<SysImSessionDO>> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/page/self", dto, iHttpHandle,
            new TypeReference<Page<SysImSessionDO>>() {
            });

    }

}

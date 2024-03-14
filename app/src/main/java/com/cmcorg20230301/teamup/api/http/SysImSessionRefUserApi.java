package com.cmcorg20230301.teamup.api.http;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.model.dto.NotNullIdAndLongSet;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.LongObjectMapVO;
import com.cmcorg20230301.teamup.model.vo.SysImSessionRefUserQueryRefUserInfoMapVO;
import com.cmcorg20230301.teamup.util.MyHttpUtil;

import cn.hutool.core.lang.TypeReference;

/**
 * 基础-即时通讯-会话-用户-管理
 */
public class SysImSessionRefUserApi {

    /**
     * 查询：当前会话的用户信息，map
     */
    public static void queryRefUserInfoMap(NotNullIdAndLongSet dto,
        @Nullable IHttpHandle<LongObjectMapVO<SysImSessionRefUserQueryRefUserInfoMapVO>> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/refUser/query/refUserInfoMap", dto, iHttpHandle,
            new TypeReference<LongObjectMapVO<SysImSessionRefUserQueryRefUserInfoMapVO>>() {});

    }

}

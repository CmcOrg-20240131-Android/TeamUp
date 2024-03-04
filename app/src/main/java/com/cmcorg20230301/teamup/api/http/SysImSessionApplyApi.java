package com.cmcorg20230301.teamup.api.http;

import com.cmcorg20230301.teamup.model.dto.NotEmptyIdSet;
import com.cmcorg20230301.teamup.model.dto.NotNullId;
import com.cmcorg20230301.teamup.model.dto.SysImSessionApplyPrivateChatSelfPageDTO;
import com.cmcorg20230301.teamup.model.entity.SysImSessionApplyDO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.util.MyHttpUtil;

import org.jetbrains.annotations.Nullable;

import cn.hutool.core.lang.TypeReference;

/**
 * 基础-即时通讯-会话-申请-管理
 */
public class SysImSessionApplyApi {

    /**
     * 分页排序查询-私聊申请列表-自我
     */
    public static void privateChatMyPageSelf(SysImSessionApplyPrivateChatSelfPageDTO dto, @Nullable IHttpHandle<Page<SysImSessionApplyDO>> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/page/self", dto, iHttpHandle, new TypeReference<Page<SysImSessionApplyDO>>() {
        });

    }

    /**
     * 私聊：申请添加
     */
    public static void privateChatApply(NotNullId dto, @Nullable IHttpHandle<String> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/apply", dto, iHttpHandle, new TypeReference<String>() {
        });

    }

    /**
     * 私聊：同意添加
     */
    public static void privateChatAgree(NotEmptyIdSet dto, @Nullable IHttpHandle<String> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/agree", dto, iHttpHandle, new TypeReference<String>() {
        });

    }

}

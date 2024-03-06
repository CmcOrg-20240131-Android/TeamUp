package com.cmcorg20230301.teamup.api.http;

import com.cmcorg20230301.teamup.model.dto.NotEmptyIdSet;
import com.cmcorg20230301.teamup.model.dto.NotNullId;
import com.cmcorg20230301.teamup.model.dto.SysImSessionApplyPrivateChatApplySelfPageDTO;
import com.cmcorg20230301.teamup.model.dto.SysImSessionApplyPrivateChatFindNewPageDTO;
import com.cmcorg20230301.teamup.model.dto.SysImSessionApplyPrivateChatRejectDTO;
import com.cmcorg20230301.teamup.model.dto.SysImSessionApplyPrivateChatSelfPageDTO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.Page;
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatApplySelfPageVO;
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatFindNewPageVO;
import com.cmcorg20230301.teamup.model.vo.SysImSessionApplyPrivateChatSelfPageVO;
import com.cmcorg20230301.teamup.util.MyHttpUtil;

import org.jetbrains.annotations.Nullable;

import cn.hutool.core.lang.TypeReference;

/**
 * 基础-即时通讯-会话-申请-管理
 */
public class SysImSessionApplyApi {

    /**
     * 分页排序查询-搜索新的朋友列表
     */
    public static void privateChatFindNewPage(SysImSessionApplyPrivateChatFindNewPageDTO dto, @Nullable IHttpHandle<Page<SysImSessionApplyPrivateChatFindNewPageVO>> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/findNew/page", dto, iHttpHandle, new TypeReference<Page<SysImSessionApplyPrivateChatFindNewPageVO>>() {
        });

    }

    /**
     * 分页排序查询-私聊申请列表-自我
     */
    public static void privateChatApplyPageSelf(SysImSessionApplyPrivateChatApplySelfPageDTO dto, @Nullable IHttpHandle<Page<SysImSessionApplyPrivateChatApplySelfPageVO>> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/apply/page/self", dto, iHttpHandle, new TypeReference<Page<SysImSessionApplyPrivateChatApplySelfPageVO>>() {
        });

    }

    /**
     * 分页排序查询-好友列表-自我
     */
    public void privateChatPageSelf(SysImSessionApplyPrivateChatSelfPageDTO dto, @Nullable IHttpHandle<Page<SysImSessionApplyPrivateChatSelfPageVO>> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/page/self", dto, iHttpHandle, new TypeReference<Page<SysImSessionApplyPrivateChatSelfPageVO>>() {
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

    /**
     * 私聊：拒绝添加
     */
    public static void privateChatReject(SysImSessionApplyPrivateChatRejectDTO dto, @Nullable IHttpHandle<String> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/reject", dto, iHttpHandle, new TypeReference<String>() {
        });

    }

    /**
     * 私聊：拉黑
     */
    public static void privateChatBlock(NotNullId dto, @Nullable IHttpHandle<String> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/block", dto, iHttpHandle, new TypeReference<String>() {
        });

    }

    /**
     * 私聊：拉黑取消
     */
    public static void privateChatBlockCancel(NotEmptyIdSet dto, @Nullable IHttpHandle<String> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/block/cancel", dto, iHttpHandle, new TypeReference<String>() {
        });

    }

    /**
     * 私聊：申请取消
     */
    public static void privateChatApplyCancel(NotNullId dto, @Nullable IHttpHandle<String> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/apply/cancel", dto, iHttpHandle, new TypeReference<String>() {
        });

    }

    /**
     * 私聊：申请隐藏
     */
    public static void privateChatApplyHidden(NotNullId dto, @Nullable IHttpHandle<String> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/apply/hidden", dto, iHttpHandle, new TypeReference<String>() {
        });

    }

    /**
     * 私聊：删除
     */
    public static void privateChatDelete(NotNullId dto, @Nullable IHttpHandle<String> iHttpHandle) {

        MyHttpUtil.post("/sys/im/session/apply/privateChat/delete", dto, iHttpHandle, new TypeReference<String>() {
        });

    }

}

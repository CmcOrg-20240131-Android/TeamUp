package com.cmcorg20230301.teamup.model.constant;

/**
 * 通用常量
 */
public interface CommonConstant {

    String TAG = "TeamUp";

    String EXTRA = "extra";

    String FIXED_AVATAR_URL = "https://gw.alipayobjects.com/zos/antfincdn/efFD%24IOql2/weixintupian_20170331104822.jpg"; // 固定头像的地址

    int API_OK_CODE = 200; // api请求成功的 code

    int SECOND_3_EXPIRE_TIME = 3 * 1000; // 3秒过期

    int SECOND_5_EXPIRE_TIME = 5 * 1000; // 5秒过期

    int SECOND_10_EXPIRE_TIME = 10 * 1000; // 10秒过期

    int MINUTE_30_EXPIRE_TIME = 30 * 60 * 1000; // 30分钟过期

    int SHORT_DELAY = 80; // 短的延迟

    int MEDIUM_DELAY = 180; // 中等延迟

    int MAX_SHOW_TOTAL = 99; // 当显示数量时，最大显示的数字

    String MAX_SHOW_TOTAL_STR = "99+"; // 当显示数量时，超过最大显示数字时，显示的内容

    int SCROLL_REDUCE_HEIGHT = 30; // 滚动加载完成之后时，滚动条位置，需要增加或减少的高度

    int SCROLL_CHECK_HEIGHT = 80; // 滚动加载时，判断的高度

    String IMAGE_FILE_ACCEPT_TYPE = ".png,.jpeg,.jpg"; // 图片文件的类型

    String EXCEL_FILE_ACCEPT_TYPE = ".xlsx"; // excel文件的类型

    String TXT_FILE_ACCEPT_TYPE = ".txt"; // txt文件的类型

    String PDF_FILE_ACCEPT_TYPE = ".pdf"; // pdf文件的类型

    String DOCX_FILE_ACCEPT_TYPE = ".docx"; // docx文件的类型

    String DOCUMENT_FILE_ACCEPT_TYPE = ".txt,.pdf,.doc,.docx,.java,.python,.sql,.cs,.html"; // 文档文件的类型

    int MOBILE_WIDTH = 768; // 小于这个数值，就是移动端

    int TOOLTIP_STR_LENGTH = 500; // tooltip字符串的长度

    String TOP_PARENT_ID_STR = "0"; // 顶层 parentId

    String TOP_TENANT_ID_STR = "0"; // 默认租户 id

    String DEFAULT_TENANT_NAME = "默认"; // 默认租户名

    String TENANT_USER_ID_STR = "-2"; // 租户的用户 id

}

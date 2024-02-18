package com.cmcorg20230301.teamup.util;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * 加密工具类
 */
public class MyRsaUtil {

    public static final String SEPARATOR = SeparatorUtil.SEMICOLON_SEPARATOR;

    public static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDadmaCaffN63JC5QsMK/+le5voCB4DzOsV9xOBZgGJyqnizh9/UcFkIoRae5rebdWUtnPO4CTgdJbuSvu/TtIIPj9De5/wiJilFAWd1Ve7qGaxxTxqWwFNp7p/FLr0YpMeBjOylds9GyA1cnjIqruNdYv+qRZnseE0Sq2WEZus9QIDAQAB";

    /**
     * 统一的 password加密
     */
    public static String passwordRsaEncrypt(String passwordTemp) {

        String password = DigestUtil.sha256Hex((DigestUtil.sha512Hex(passwordTemp)));

        return rsaEncryptPro(password);

    }

    /**
     * 非对称加密
     */
    public static String rsaEncrypt(String str) {

        RSA rsa = new RSA(null, RSA_PUBLIC_KEY);

        return rsa.encryptBase64(str, KeyType.PublicKey);

    }

    /**
     * 非对称加密：增强版，加入时间戳
     */
    public static String rsaEncryptPro(String str) {

        str = str + SEPARATOR + MyDateUtil.getServerTimestamp(); // 需要加上当前时间戳

        return rsaEncrypt(str);

    }

}

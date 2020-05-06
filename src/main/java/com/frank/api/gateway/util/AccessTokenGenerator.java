package com.frank.api.gateway.util;

import com.frank.api.gateway.auth.model.AppInfo;
import lombok.NonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AccessTokenGenerator {

    /**
     * 生成32位 token
     * @param appInfo 应用信息
     * @return
     */
    public static String create(@NonNull final AppInfo appInfo) {

        try {
            final String waitDealContent = appInfo.getAppId()+appInfo.getSecret()+System.currentTimeMillis();
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(waitDealContent.getBytes());
            StringBuffer buffer = new StringBuffer();
            for (byte b : result) {
                int number = b & 0xab;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}

package com.bluesky.admin.api.common.client.util;

import com.bluesky.admin.api.common.client.exception.ApiClientException;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * @author Lijinchun
 * @Package cn.wywk.yvip.duiba.client.util
 * @date 2019/7/15 19:40
 */
@Slf4j
public class ApiSignUtil {
    private ApiSignUtil(){}
    /**
     * 验证签名
     *
     * @param signature
     * @param bizId
     * @param timestamp
     * @param bizData
     * @param sysCode
     * @return
     */
    public static boolean checkSignature(String signature, String bizId, String timestamp, String bizData, String sysCode, String appSecret) {
        return signature.equals(signature(bizId, timestamp, bizData, sysCode, appSecret));
    }

    /**
     * 参数签名
     * @param bizId
     * @param timestamp
     * @param bizData  base64值 - encodeBizData(bizData)
     * @param sysCode
     * @return
     */
    public static String signature(String bizId, String timestamp, String bizData, String sysCode, String appSecret){
        return md5(appSecret + "bizData" + bizData + "bizId" + bizId + "sysCode" + sysCode + "timestamp" + timestamp + appSecret);
    }

    /**
     * 将bizData 转base64
     * @param bizData
     * @return
     */
    public static String encodeBizData(String bizData){
        return new String(Base64.getUrlEncoder().encode(bizData.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }


    /**
     * 将base64 bizData 解码
     * @param base64BizData
     * @return
     */
    public static String decodeBizData(String base64BizData){
        return new String(Base64.getUrlDecoder().decode(base64BizData.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    /**
     * md5签名
     * @param content
     * @return
     */
    public static String md5(String content) {
        StringBuilder sb = new StringBuilder();
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(content.getBytes("UTF-8"));
            byte[] tmpFolder = md5.digest();

            for (byte aTmpFolder : tmpFolder) {
                sb.append(Integer.toString((aTmpFolder & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        }catch(Exception ex){
            log.error("md5 签名失败：{}", ex);
            throw new ApiClientException("无法生成指定内容的MD5签名");
        }
    }
}

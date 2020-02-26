package com.bluesky.admin.common.client;

import com.bluesky.admin.api.common.client.dto.req.BaseAppAipReq;
import com.bluesky.admin.api.common.client.dto.req.NotifyReq;
import com.bluesky.admin.api.common.client.dto.resp.NotifyResp;
import com.bluesky.admin.api.common.consts.RedisConstant;
import com.bluesky.admin.api.modules.sys.service.IRedisConfig;
import com.bluesky.admin.api.common.utils.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 网鱼app服务端接口调用
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.client
 * @date 2019/7/25 13:57
 */
@Component
@Slf4j
public class AppApiClient implements IRedisConfig {
    @Autowired
    private RestTemplate restTemplate;
    private static final String HEADER_KEY_SIGN = "X-Sign";
    @Autowired
    private RedisUtil redisUtil;
    private static String salt = "wywk2018";
    @Override
    public boolean initConfig(String key, String value) {
        if(RedisConstant.AppSys.CFG_BASE_API_URL_KEY.equals(key) && value != null){
            redisUtil.set(key, value);
            return true;
        }
        if(RedisConstant.AppSys.CFG_BASE_API_MD5_SALT_KEY.equals(key) && value != null){
            redisUtil.set(key, value);
            return true;
        }
        if(RedisConstant.AppSys.CFG_BASE_API_TEMPLATE_ID_KEY.equals(key) && value != null){
            redisUtil.set(key, value);
            return true;
        }
        return false;
    }


    /**
     * 消息推送
     * @param notifyReq
     * @return
     */
    public NotifyResp expiredScoreNotify(NotifyReq notifyReq){
        notifyReq.setTemplateId(getNotifyTemplateId());
        return restTemplate.exchange(this.getBaseApiUrl() + "send", HttpMethod.POST, parseReqHttpEntity(notifyReq), NotifyResp.class).getBody();
    }

    private String getBaseApiUrl() {
        return redisUtil.get(RedisConstant.AppSys.CFG_BASE_API_URL_KEY);
    }

    public  String getSalt() {
        String salt1 = redisUtil.get(RedisConstant.AppSys.CFG_BASE_API_MD5_SALT_KEY);
        if(salt1 == null){
            return salt;
        }
        return salt1;
    }

    public  String getNotifyTemplateId() {
        return redisUtil.get(RedisConstant.AppSys.CFG_BASE_API_TEMPLATE_ID_KEY);
    }

    protected HttpEntity parseReqHttpEntity(BaseAppAipReq req) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String sign = null;
        String body = null;
        try {
            body = new ObjectMapper().writeValueAsString(req);
            sign = sign(body);
        } catch (JsonProcessingException e) {
            log.error("", e);
        }

        headers.add(HEADER_KEY_SIGN, sign);
        return new HttpEntity(body, headers);
    }



    private String sign(String body) throws JsonProcessingException {
        Map<String, String> preBody = new LinkedHashMap<>();
        preBody.put("body", body);
        preBody.put("salt", getSalt());
        return md5(new ObjectMapper().writeValueAsString(preBody));
    }

    private static String md5(String message) {
        String md5str = "";
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 2 将消息变成byte数组
            byte[] input = (message).getBytes();
            // 3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);
            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = bytesToHex(buff);
        } catch (Exception e) {
            log.error("", e);
        }
        return md5str;
    }

    /**
     * 二进制转十六进制
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder md5str = new StringBuilder();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (byte aByte : bytes) {
            digital = aByte;
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }
}

package com.rdc_wechat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdc_wechat.pojo.ResultMessage;


/**
 * 把字符串转成json格式的字符串
 * @author 86178
 */
public class MessageUtils {
    /**
     * 将字符串转换为json格式
     * @param isSystemMessage
     * @param fromName
     * @param message
     * @return
     */
    public static String getMessage(boolean isSystemMessage, String fromName, Object message) {
        try {
            ResultMessage result = new ResultMessage();
            result.setSystem(isSystemMessage);
            result.setMessage(message);
            if (fromName != null) {
                result.setFromName(fromName);
            }
            //把字符串转成json格式的字符串
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

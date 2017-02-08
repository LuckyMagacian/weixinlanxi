package com.lanxi.weixin.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanxi.weixin.bean.SmsBean;
import com.lanxi.weixin.manager.ParamManager;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by shenzhiqiang on 16-10-10.
 */
public class SmsSendUtils {

    private static Logger log = Logger.getLogger(SmsSendUtils.class);

    /**
     * 发送短信
     * @param
     * @return
     */
    public static boolean sendSms(String mobile, String content) {

        try {
            SmsBean bean = new SmsBean();
            bean.setMchtId("10");
            bean.setOrderId("10" + DateTimeUtil.getCurrentyyyyMMddHHmmss() + RandomStringUtils.randomAlphanumeric(4));
            bean.setMobile(mobile);
            bean.setContent(content);
            bean.setTradeDate(DateTimeUtil.getCurrentyyyyMMdd());
            bean.setTradeTime(DateTimeUtil.getCurrentHHmmss());
            // send
            bean.setTdId("2");
            signSms(bean);
            String queryStr = bean.getSendQueryStr();
            log.info("SMS queryStr: " + queryStr);
            String respStr = HttpUtils.sendPostReq(ParamManager.smsUrl, queryStr, "utf-8", "30000");
            log.info("SMS respStr: " + respStr);
            if (!respStr.equals("") && !respStr.equals("ConnectTimeout") && !respStr.equals("SocketTimeout")) {
                Map<String, Object> jsonMap = new Gson().fromJson(respStr,
                        new TypeToken<Map<String, Object>>() {}.getType());
                respStr = (String) jsonMap.get("retCode");
            }
            // resend
            if (!respStr.equals("0000") && !respStr.equals("SocketTimeout")) {
                bean.setTdId("2");
                signSms(bean);
                queryStr = bean.getSendQueryStr();
                log.info("SMS queryStr: " + queryStr);
                respStr = HttpUtils.sendPostReq(ParamManager.smsUrl, queryStr, "utf-8", "30000");
                log.info("SMS respStr: " + respStr);
                if (!respStr.equals("") && !respStr.equals("ConnectTimeout") && !respStr.equals("SocketTimeout")) {
                    Map<String, Object> jsonMap = new Gson().fromJson(respStr,
                            new TypeToken<Map<String, Object>>() {}.getType());
                    respStr = (String) jsonMap.get("retCode");
                }
            }

            // return
            if (respStr.equals("") || respStr.equals("ConnectTimeout")) {
                return false;
            } else if (respStr.equals("SocketTimeout")){
                log.error("短信发送, 响应超时. ");
                return false;
            } else if (respStr.equals("0000")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("短信发送失败. ", e);
            return false;
        }
    }

    /**
     * 短信签名
     * @param bean
     */
    public static void signSms(SmsBean bean) {
        Field[] fields=bean.getClass().getDeclaredFields();
        Map<String, String> map=new HashMap<String, String>();
        try{
            for(Field each:fields){
                each.setAccessible(true);
                if(!each.getName().equals("sign"))
                    map.put(each.getName(), each.get(bean).toString());
            }

            String sign = MD5Util.md5LowerCaseWithCharset(getParamStr(map) + ParamManager.smsKey, "utf-8");
            bean.setSign(sign);
        } catch (Exception e) {
            log.error("短信签名失败. ", e);
        }
    }

    /**
     * 将map形式的参数 转换为字符串
     * @param params
     * @return
     */
    public static String getParamStr(Map<String, String> params){
        List<String> keys=new ArrayList<String>();
        for(Map.Entry<String, String> each:params.entrySet())
            keys.add(each.getKey());
        Collections.sort(keys);
        StringBuilder temp=new StringBuilder();
        for(String each:keys){
            temp.append(each+"=");
            temp.append(params.get(each));
            temp.append("&");
        }
        return temp.toString().substring(0,temp.length()-1);
    }

    public static void main(String[] args) {
        ParamManager.readConfig();
        System.out.println(sendSms("17710458772", "test"));
    }
}

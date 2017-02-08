package com.lanxi.weixin.bean;

import com.lanxi.weixin.utils.SmsSendUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenzhiqiang on 16-10-10.
 */
public class SmsBean {

    private String mchtId;  // "10"
    private String orderId;  // 长度20
    private String mobile;
    private String content;
    private String tradeDate;
    private String tradeTime;
    private String sign;
    private String tdId;// 1-lanxi 2-wodong

    public String getMchtId() {
        return mchtId;
    }

    public void setMchtId(String mchtId) {

        if (mchtId != null) {
            this.mchtId = mchtId.trim();
        } else {
            this.mchtId = "";
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {

        if (orderId != null) {
            this.orderId = orderId.trim();
        } else {
            this.orderId = "";
        }
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {

        if (mobile != null) {
            this.mobile = mobile.trim();
        } else {
            this.mobile = "";
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {

        if (content != null) {
            this.content = content.trim();
        } else {
            this.content = "";
        }
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {

        if (tradeDate != null) {
            this.tradeDate = tradeDate.trim();
        } else {
            this.tradeDate = "";
        }
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {

        if (tradeTime != null) {
            this.tradeTime = tradeTime.trim();
        } else {
            this.tradeTime = "";
        }
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {

        if (sign != null) {
            this.sign = sign.trim();
        } else {
            this.sign = "";
        }
    }

    public String getTdId() {
        return tdId;
    }

    public void setTdId(String tdId) {

        if (tdId != null) {
            this.tdId = tdId.trim();
        } else {
            this.tdId = "";
        }
    }

    @Override
    public String toString() {
        return "SmsBean{" +
                "mchtId='" + mchtId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", content='" + content + '\'' +
                ", tradeDate='" + tradeDate + '\'' +
                ", tradeTime='" + tradeTime + '\'' +
                ", sign='" + sign + '\'' +
                ", tdId='" + tdId + '\'' +
                '}';
    }

    public String getSendQueryStr() {

        return "mchtId=" + mchtId +
                "&orderId=" + orderId +
                "&mobile=" + mobile +
                "&content=" + content +
                "&tradeDate=" + tradeDate +
                "&tradeTime=" + tradeTime +
                "&sign=" + sign +
                "&tdId=" + tdId;
    }
}

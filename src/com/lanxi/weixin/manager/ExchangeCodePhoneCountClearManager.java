package com.lanxi.weixin.manager;


import com.lanxi.weixin.controller.ExchangeCodeController;
import org.apache.log4j.Logger;

public class ExchangeCodePhoneCountClearManager {

    private static Logger log = Logger.getLogger(ExchangeCodePhoneCountClearManager.class);

    public void clearCount() {
        try {
            log.info("每日清除手机号兑换次数");

            ExchangeCodeController.phoneCount.clear();

        } catch (Exception e) {
            log.error(e.getClass().getName()+":"+e.getMessage(),e);
        }
    }
}

package com.lanxi.weixin.controller;

import com.lanxi.weixin.bean.ExchangeCodeBean;
import com.lanxi.weixin.manager.ParamManager;
import com.lanxi.weixin.service.ExchangeCodeService;
import com.lanxi.weixin.utils.RegexUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/exchangeCode")
public class ExchangeCodeController {

    private static Logger log = Logger.getLogger(ExchangeCodeController.class);
    public static ConcurrentHashMap<String, AtomicInteger> phoneCount = new ConcurrentHashMap<String, AtomicInteger>();

    @Autowired
    ExchangeCodeService exchangeCodeService;


    /**
     * 跳转至串码兑换页面
     * @return
     */
    @RequestMapping("/exchangeCode.do")
    public String exchangeCodeIndex() {

        return "CodeCheck/exchangeCode";
    }


    /**
     * 串码兑换
     * @param cmid 串码id
     * @param dhsjhm 手机号码
     * @return
     */
    @RequestMapping("/exchange.do")
    public String exchange(String cmid, String dhsjhm, Model model, HttpServletResponse response) {

        log.info("串码兑换, code id: " + cmid + ", phone num: " + dhsjhm);

        if (null == cmid || !RegexUtil.matches(cmid, "^[0-9]{12}$")) {
            model.addAttribute("retMsg", "串码格式错误");
            return "CodeCheck/exchangeFail";
        }
        if (null == dhsjhm || dhsjhm.equals("") || !RegexUtil.matches(dhsjhm,
                                                    "^(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$")) {
            model.addAttribute("retMsg", "手机号格式错误");
            return "CodeCheck/exchangeFail";
        }

        AtomicInteger count = null;
        if (ParamManager.ifUseExchangeLimitForDay) {
            count = phoneCount.putIfAbsent(dhsjhm, new AtomicInteger(0));
            if (null == count) {
                count = phoneCount.get(dhsjhm);
            }
            if (count.getAndIncrement() >= ParamManager.exchangeLimitForDay) {
                model.addAttribute("retMsg", "手机号已达当天的兑换码充值上限");
                count.getAndDecrement();
                return "CodeCheck/exchangeFail";
            }
        }

        try {
            Map<String,Object> retMap = exchangeCodeService.exchangeByCmidDhsjhm(cmid, dhsjhm);
            if (retMap.get("status").equals("300")) {
                if (ParamManager.ifUseExchangeLimitForDay) {
                    count.getAndDecrement();
                }
                model.addAttribute("retMsg", retMap.get("msg"));
                return "CodeCheck/exchangeFail";
            }
            model.addAttribute("spsl", retMap.get("spsl"));
            model.addAttribute("spmc", retMap.get("spmc"));
            model.addAttribute("phone", retMap.get("phone"));
        } catch (Exception e) {
            log.error("ExchangeCodeController->exchange: " + e.getClass().getName()+":"+e.getMessage(),e);
            if (ParamManager.ifUseExchangeLimitForDay) {
                count.getAndDecrement();
            }
            model.addAttribute("retMsg", "兑换码兑换失败");
            return "CodeCheck/exchangeFail";
        }

        return "CodeCheck/exchangeSuccess";
    }
}

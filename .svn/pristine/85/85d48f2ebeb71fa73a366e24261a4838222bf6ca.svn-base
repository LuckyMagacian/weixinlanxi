package com.lanxi.weixin.serviceImpl;

import com.lanxi.weixin.bean.BranchActInfoBean;
import com.lanxi.weixin.bean.ExchangeCodeBean;
import com.lanxi.weixin.bean.OrderBean;
import com.lanxi.weixin.codeMapper.BranchActInfoMapper;
import com.lanxi.weixin.codeMapper.ExchangeCodeMapper;
import com.lanxi.weixin.codeMapper.SmsModMapper;
import com.lanxi.weixin.manager.ParamManager;
import com.lanxi.weixin.manager.SendOrderManager;
import com.lanxi.weixin.service.ExchangeCodeService;
import com.lanxi.weixin.utils.DateTimeUtil;
import com.lanxi.weixin.utils.ParamInterface;
import com.lanxi.weixin.utils.SmsSendUtils;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;


@Service
public class ExchangeCodeServiceImpl implements ExchangeCodeService{

    private static Logger log = Logger.getLogger(ExchangeCodeServiceImpl.class);

    @Autowired
    ExchangeCodeMapper exchangeCodeMapper;
    @Autowired
    BranchActInfoMapper branchActInfoMapper;
    @Autowired
    SmsModMapper smsModMapper;


    /**
     * 串码兑换
     * @param cmid 串码id
     * @param dhsjhm 手机号码
     * @return
     */
    @Override
    @Transactional//(isolation= Isolation.READ_COMMITTED)
    public Map<String,Object> exchangeByCmidDhsjhm(String cmid, String dhsjhm) throws Exception {
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("status", "300");

        ExchangeCodeBean exchangeCode = exchangeCodeMapper.getByCmid(cmid);
        if (null == exchangeCode) {
            log.info("ExchangeCodeServiceImpl->exchangeByCmidDhsjhm, 串码兑换, 串码不存在: " + cmid);
            retMap.put("msg", "兑换码错误");
            return retMap;
        }
        if ( !exchangeCode.getCmzt().equals(ParamInterface.CODE_STATUS_VALID) ||
                (!exchangeCode.getYxq().equals("") && exchangeCode.getYxq().compareTo(DateTimeUtil.getCurrentyyyyMMdd())<0) ) {
            log.info("ExchangeCodeServiceImpl->exchangeByCmidDhsjhm, 串码兑换, 串码已失效, cmid: " + cmid +
                    ", cmzt: " + exchangeCode.getCmzt() + ", yxq: " + exchangeCode.getYxq());
            retMap.put("msg", "兑换码已失效");
            return retMap;
        }

        // 配置文件 limit 不区分机构
        if (ParamManager.ifUseExchangeLimit && !ParamManager.exWhiteList.contains(dhsjhm)) {
            int count = exchangeCodeMapper.getExchangeCountByDhsjDhsjhm(dhsjhm,
                                                            ParamManager.limitBeginDate, ParamManager.limitEndDate);
            if (count >= ParamManager.exchangeLimit) {
                log.info("ExchangeCodeServiceImpl->exchangeByCmidDhsjhm, 串码兑换, 手机号已达充值上限, 手机号: " + dhsjhm +
                        ", 已兑换次数: " + count + ", 兑换上限限制: " + ParamManager.exchangeLimit);
                retMap.put("msg", "手机号已达兑换码充值上限");
                return retMap;
            }
        }
        // t_branch_act_info 表中数据
        String dhsj = DateTimeUtil.getCurrentyyyyMMddHHmmss();
//        if (ParamManager.ifUseExchangeBranchActInfo && !ParamManager.exWhiteList.contains(dhsjhm)) {
//            BranchActInfoBean branchActInfo = branchActInfoMapper.getBranchActInfoByBranchid(exchangeCode.getBranchid());
//            if (null == branchActInfo) {
//                log.info("机构活动信息null, 不限制, Branchid: " + exchangeCode.getBranchid());
//            } else {
//                if (null != branchActInfo.getActend() && !branchActInfo.getActend().equals("")) {
//                    branchActInfo.setActend(branchActInfo.getActend() + "235959");
//                }
//                // 兑换活动日期 ?
//                if (null != branchActInfo.getActbegin() && !branchActInfo.getActbegin().equals("") &&
//                        dhsj.compareTo(branchActInfo.getActbegin()) < 0) {
//                    log.info("ExchangeCodeServiceImpl->exchangeByCmidDhsjhm, 串码兑换, 兑换活动未开始, 兑换时间: " + dhsj +
//                            ", 活动开始日期: " + branchActInfo.getActbegin() + ", branchid: " + exchangeCode.getBranchid());
//                    retMap.put("msg", "兑换活动未开始");
//                    return retMap;
//                }
//                if (null != branchActInfo.getActend() && !branchActInfo.getActend().equals("") &&
//                        dhsj.compareTo(branchActInfo.getActend() + "235959") > 0) {
//                    log.info("ExchangeCodeServiceImpl->exchangeByCmidDhsjhm, 串码兑换, 兑换活动已过期, 兑换时间: " + dhsj +
//                            ", 活动结束日期: " + branchActInfo.getActend() + ", branchid: " + exchangeCode.getBranchid());
//                    retMap.put("msg", "兑换活动已过期");
//                    return retMap;
//                }
//                // 兑换上限
//                int count = exchangeCodeMapper.getExchangeCountByDhsjBranchidDhsjhm(exchangeCode.getBranchid(), dhsjhm,
//                        branchActInfo.getActbegin(), branchActInfo.getActend());
//                if ( null != branchActInfo.getLimit() && !branchActInfo.getLimit().equals("") &&
//                        count >= Integer.valueOf(branchActInfo.getLimit()) ) {
//                    log.info("ExchangeCodeServiceImpl->exchangeByCmidDhsjhm, 串码兑换, 手机号已达充值上限, 手机号: " + dhsjhm +
//                            ", 已兑换次数: " + count + ", 兑换上限限制: " + branchActInfo.getLimit());
//                    retMap.put("msg", "手机号已达兑换码充值上限");
//                    return retMap;
//                }
//            }
//        }

        exchangeCode.setNewCmzt(ParamInterface.CODE_STATUS_INVALID);
        exchangeCode.setDhsj(dhsj);
        exchangeCode.setDhsjhm(dhsjhm);
        int status = exchangeCodeMapper.updateCmztByCmidCmzt(exchangeCode);
        if (status < 1) {
            log.info("ExchangeCodeServiceImpl->exchangeByCmidDhsjhm, 串码兑换, 串码兑换失败, cmid: " + cmid);
            retMap.put("msg", "兑换码兑换失败");
            return retMap;
        }

        // 发送兑换请求
        OrderBean retOrderBean;
        try {
            OrderBean orderBean = new OrderBean();
            orderBean.setSpbh(exchangeCode.getSpbh());
            orderBean.setSpsl(exchangeCode.getSpsl());
            orderBean.setPhone(exchangeCode.getDhsjhm());

            retOrderBean = SendOrderManager.sendOrderToXjfByBranchid(orderBean, exchangeCode.getBranchid(),
                                                                        exchangeCode.getSpbh().substring(0, 2));

            if (!retOrderBean.getRetCode().equals("0000")) {
                log.error("ExchangeCodeServiceImpl->exchangeByCmidDhsjhm, 发送兑换请求失败, retCode: " +
                        retOrderBean.getRetCode() + ", retMsg: " + retOrderBean.getRetMsg());
                throw new RuntimeException("发送兑换请求失败, retCode: " + retOrderBean.getRetCode());
            }

            // 发送短信
            try {
                String smsMod = smsModMapper.getSmsmodBySmsname(exchangeCode.getSmsname());
                if (null == smsMod || smsMod.equals("")) {
                    log.error("ERROR, 兑换码兑换, 获取短信发送模板失败, Smsname: " + exchangeCode.getSmsname() +
                            ", 手机号: " + orderBean.getPhone() + ", 串码id: " + cmid +
                            ", Branchid: " + exchangeCode.getBranchid());
                } else {
                    if (!SmsSendUtils.sendSms(dhsjhm, smsMod)) {
                        log.error("ERROR, 兑换码兑换, 短信发送失败, Smsname: " + exchangeCode.getSmsname() +
                                ", 手机号: " + orderBean.getPhone() + ", 串码id: " + cmid +
                                ", Branchid: " + exchangeCode.getBranchid());
                    }
                }
            } catch (Exception e) {
                log.error("ERROR, 兑换码兑换, 短信发送失败" +", 系统日期: " + dhsj +
                        ", 手机号: " + orderBean.getPhone() + ", 串码id: " + cmid +
                        ", Branchid: " + exchangeCode.getBranchid() +
                        " : " + e.getClass().getName()+":"+e.getMessage(),e);
            }

        } catch(SocketTimeoutException e){
            log.error("ERROR, 响应超时: cmid: " + cmid + ", dhsjhm: " +  dhsjhm
                    + ", Spbh: " + exchangeCode.getSpbh() + ", Spsl: " + exchangeCode.getSpsl()
                    + e.getClass().getName()+":"+e.getMessage(),e);
        }catch(ConnectTimeoutException e){
            log.error("ERROR, 请求超时: " + e.getClass().getName()+":"+e.getMessage(),e);
            throw new RuntimeException(e.getClass().getName()+":"+e.getMessage(),e);
        } catch (Exception e) {
            log.error("ExchangeCodeServiceImpl->exchangeByCmidDhsjhm, 发送兑换请求失败, " +
                    e.getClass().getName()+":"+e.getMessage(),e);
            throw new RuntimeException(e.getClass().getName()+":"+e.getMessage(),e);
        }

        retMap.put("status", "200");
        retMap.put("spsl", exchangeCode.getSpsl());
        retMap.put("spmc", exchangeCode.getSpmc());
        retMap.put("phone", exchangeCode.getDhsjhm());

        return retMap;
    }

}

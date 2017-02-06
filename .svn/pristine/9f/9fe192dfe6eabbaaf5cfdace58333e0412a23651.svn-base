package com.lanxi.weixin.serviceImpl;

import com.lanxi.weixin.bean.ExchangeCodeBean;
import com.lanxi.weixin.bean.OrderBean;
import com.lanxi.weixin.codeMapper.ExchangeCodeMapper;
import com.lanxi.weixin.manager.ParamManager;
import com.lanxi.weixin.manager.SendOrderManager;
import com.lanxi.weixin.service.ExchangeCodeService;
import com.lanxi.weixin.utils.DateTimeUtil;
import com.lanxi.weixin.utils.ParamInterface;
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

        if (ParamManager.ifUseExchangeLimit) {
            int count = exchangeCodeMapper.getExchangeCountByDhsjDhsjhm(dhsjhm,
                                                            ParamManager.limitBeginDate, ParamManager.limitEndDate);
            if (count >= ParamManager.exchangeLimit) {
                log.info("ExchangeCodeServiceImpl->exchangeByCmidDhsjhm, 串码兑换, 手机号已达充值上限, 手机号: " + dhsjhm +
                        ", 已兑换次数: " + count + ", 兑换上限限制: " + ParamManager.exchangeLimit);
                retMap.put("msg", "手机号已达兑换码充值上限");
                return retMap;
            }
        }

        exchangeCode.setNewCmzt(ParamInterface.CODE_STATUS_INVALID);
        exchangeCode.setDhsj(DateTimeUtil.getCurrentyyyyMMddHHmmss());
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

            retOrderBean = SendOrderManager.sendOrderToXjfSrcNH(orderBean, exchangeCode.getSpbh().substring(0, 2));

            if (!retOrderBean.getRetCode().equals("0000")) {
                log.error("ExchangeCodeServiceImpl->exchangeByCmidDhsjhm, 发送兑换请求失败, retCode: " +
                        retOrderBean.getRetCode() + ", retMsg: " + retOrderBean.getRetMsg());
                throw new RuntimeException("发送兑换请求失败, retCode: " + retOrderBean.getRetCode());
            }

            // 发送短信 插入短信发送表(JFDH_SMS_SEND)
            try {
                String fsxx = "尊敬的客户，您参加农行“掌银分期送话费”活动，获赠的10元话费已充值成功。本充值服务由蓝喜信息提供，关注公众号“蓝喜微管家”获取更多精彩活动。【杭州蓝喜】";
                exchangeCodeMapper.insertSMSSend(DateTimeUtil.getCurrentyyyyMMdd(), // 系统日期
                        RandomStringUtils.randomNumeric(8), // 发送序号
                        "0", // 处理标志 0:未处理或失败 1:处理成功
                        orderBean.getPhone(), // 手机号
                        3, // 最大次数
                        0, // 已发次数
                        DateTimeUtil.getCurrentyyyyMMdd(), // 发送日期
                        DateTimeUtil.getCurrentHHmmss(), // 发送时间
                        fsxx); // 发送信息
            } catch (Exception e) {
                log.error("ERROR, 插入短信发送表失败" +", 系统日期: " + DateTimeUtil.getCurrentyyyyMMdd() +
                        ", 手机号: " + orderBean.getPhone() + ", 串码id: " + cmid +
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

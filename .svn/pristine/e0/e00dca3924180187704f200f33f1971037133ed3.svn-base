package com.lanxi.weixin.manager;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.dom4j.Document;

import com.lanxi.weixin.bean.OrderBean;
import com.lanxi.weixin.bean.XjfBuyBean;
import com.lanxi.weixin.utils.HttpUtils;
import com.lanxi.weixin.utils.MD5Util;
import com.lanxi.weixin.utils.XmlParser;

public class SendOrderManager {

	private static Logger log = Logger.getLogger(SendOrderManager.class);
	
	private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");
	
	private static AtomicInteger jyxhAtomic = new AtomicInteger(ParamManager.jyxhStart);

	private static Properties exchangeKeyPro = new Properties();
	static {
		try {
			String s = Thread.currentThread().getContextClassLoader()
					.getResource("signparam.properties").toURI().getPath();
			exchangeKeyPro.load(new InputStreamReader(new FileInputStream(s), "utf-8"));
		} catch (Exception e) {
			log.error("[SendOrderManager] 加载 signparam.properties 失败. ", e);
		}
	}

	/**
	 * 发送订单到小积分
	 * @param orderBean
	 */
	public static OrderBean sendOrderToXjf(OrderBean orderBean,String splb){
		log.info("发送订单到小积分...");
		try{
			int jyxh = jyxhAtomic.incrementAndGet();
			orderBean.setDealId(String.valueOf(jyxh));
			Date date = new Date();
			XjfBuyBean xjfBuyBean = new XjfBuyBean();
			xjfBuyBean.setVer(ParamManager.ver);
			xjfBuyBean.setMsgNo(ParamManager.msgNo_buy);
			xjfBuyBean.setChkDate(sdfDate.format(date));
			xjfBuyBean.setWorkDate(sdfDate.format(date));
			xjfBuyBean.setWorkTime(sdfTime.format(date));
			xjfBuyBean.setAdd(ParamManager.add);
			xjfBuyBean.setSrc(ParamManager.src);
			xjfBuyBean.setDes(ParamManager.des);
			xjfBuyBean.setApp(ParamManager.app);
			xjfBuyBean.setMsgID(orderBean.getDealId());
			xjfBuyBean.setPhone(orderBean.getPhone());
			xjfBuyBean.setType(splb);
			xjfBuyBean.setSkuCode(orderBean.getSpbh());
			xjfBuyBean.setCount(orderBean.getSpsl());
			xjfBuyBean.setNeedSend(ParamManager.needSend);
			String sign = getSign(xjfBuyBean);
			
			xjfBuyBean.setSign(sign);
			String headXml = xjfBuyBean.getHeadXml();
			String bodyXml = xjfBuyBean.getBodyXml();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>")
			.append("<JFDH>")
			.append(headXml).append(bodyXml)
			.append("</JFDH>");
			log.info("发送报文："+sb.toString());
			String result = HttpUtils.sendPost(ParamManager.xjfUrl, sb.toString(), "GBK", "GBK", "30000");
			log.info("小积分返回报文:"+result);
			Document doc_req = XmlParser.getDocument(result);
			
			String retCode = XmlParser.getNodeString(doc_req, "/JFDH/HEAD/ResCode").trim();
			String retMsg = XmlParser.getNodeString(doc_req, "/JFDH/HEAD/ResMsg").trim();
			log.info("返回retCode："+retCode+"，返回retMsg："+retMsg);
			orderBean.setRetCode(retCode);
			orderBean.setRetMsg(retMsg);
			if("0000".equals(retCode)){
				orderBean.setOrderStatus("1");
			}else{
				orderBean.setOrderStatus("2");
			}
			orderBean.setSendNum("1");
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			throw new RuntimeException(e.getClass().getName()+":"+e.getMessage(),e);
		}
		return orderBean;
	}

	/**
	 * 发送订单到小积分 src_nh 交易发起机构号 农行
	 * @param orderBean
	 */
	public static OrderBean sendOrderToXjfSrcNH(OrderBean orderBean,String splb) throws Exception{
		log.info("发送订单到小积分...");

		int jyxh = jyxhAtomic.incrementAndGet();
		orderBean.setDealId(String.valueOf(jyxh));
		Date date = new Date();
		XjfBuyBean xjfBuyBean = new XjfBuyBean();
		xjfBuyBean.setVer(ParamManager.ver);
		xjfBuyBean.setMsgNo(ParamManager.msgNo_buy);
		xjfBuyBean.setChkDate(sdfDate.format(date));
		xjfBuyBean.setWorkDate(sdfDate.format(date));
		xjfBuyBean.setWorkTime(sdfTime.format(date));
		xjfBuyBean.setAdd(ParamManager.add);
		xjfBuyBean.setSrc(ParamManager.src_nh); // 交易发起机构号 农行
		xjfBuyBean.setDes(ParamManager.des);
		xjfBuyBean.setApp(ParamManager.app);
		xjfBuyBean.setMsgID(orderBean.getDealId());
		xjfBuyBean.setPhone(orderBean.getPhone());
		xjfBuyBean.setType(splb);
		xjfBuyBean.setSkuCode(orderBean.getSpbh());
		xjfBuyBean.setCount(orderBean.getSpsl());
		xjfBuyBean.setNeedSend(ParamManager.needSend);
		String sign = getSignNH(xjfBuyBean);

		xjfBuyBean.setSign(sign);
		String headXml = xjfBuyBean.getHeadXml();
		String bodyXml = xjfBuyBean.getBodyXml();
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>")
				.append("<JFDH>")
				.append(headXml).append(bodyXml)
				.append("</JFDH>");
		log.info("发送报文："+sb.toString());
		String result = HttpUtils.sendPost(ParamManager.xjfUrl, sb.toString(), "GBK", "GBK", "30000");
		log.info("小积分返回报文:"+result);
		Document doc_req = XmlParser.getDocument(result);

		String retCode = XmlParser.getNodeString(doc_req, "/JFDH/HEAD/ResCode").trim();
		String retMsg = XmlParser.getNodeString(doc_req, "/JFDH/HEAD/ResMsg").trim();
		log.info("返回retCode："+retCode+"，返回retMsg："+retMsg);
		orderBean.setRetCode(retCode);
		orderBean.setRetMsg(retMsg);
		if("0000".equals(retCode)){
			orderBean.setOrderStatus("1");
		}else{
			orderBean.setOrderStatus("2");
		}
		orderBean.setSendNum("1");

		return orderBean;
	}

	/**
	 * 根据交易发起机构号 发送订单到小积分
	 * @param orderBean
	 */
	public static OrderBean sendOrderToXjfByBranchid(OrderBean orderBean, String branchid, String splb) throws Exception{
		log.info("发送订单到小积分... branchid:" + branchid);

		int jyxh = jyxhAtomic.incrementAndGet();
		orderBean.setDealId(String.valueOf(jyxh));
		Date date = new Date();
		XjfBuyBean xjfBuyBean = new XjfBuyBean();
		xjfBuyBean.setVer(ParamManager.ver);
		xjfBuyBean.setMsgNo(ParamManager.msgNo_buy);
		xjfBuyBean.setChkDate(sdfDate.format(date));
		xjfBuyBean.setWorkDate(sdfDate.format(date));
		xjfBuyBean.setWorkTime(sdfTime.format(date));
		xjfBuyBean.setAdd(ParamManager.add);
		xjfBuyBean.setSrc(branchid); // 交易发起机构号 branchid
		xjfBuyBean.setDes(ParamManager.des);
		xjfBuyBean.setApp(ParamManager.app);
		xjfBuyBean.setMsgID(orderBean.getDealId());
		xjfBuyBean.setPhone(orderBean.getPhone());
		xjfBuyBean.setType(splb);
		xjfBuyBean.setSkuCode(orderBean.getSpbh());
		xjfBuyBean.setCount(orderBean.getSpsl());
		xjfBuyBean.setNeedSend(ParamManager.needSend);
		String sign = getSignExchange(xjfBuyBean);

		xjfBuyBean.setSign(sign);
		String headXml = xjfBuyBean.getHeadXml();
		String bodyXml = xjfBuyBean.getBodyXml();
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>")
				.append("<JFDH>")
				.append(headXml).append(bodyXml)
				.append("</JFDH>");
		log.info("发送报文："+sb.toString());
		String result = HttpUtils.sendPost(ParamManager.xjfUrl, sb.toString(), "GBK", "GBK", "30000");
		log.info("小积分返回报文:"+result);
		Document doc_req = XmlParser.getDocument(result);

		String retCode = XmlParser.getNodeString(doc_req, "/JFDH/HEAD/ResCode").trim();
		String retMsg = XmlParser.getNodeString(doc_req, "/JFDH/HEAD/ResMsg").trim();
		log.info("返回retCode："+retCode+"，返回retMsg："+retMsg);
		orderBean.setRetCode(retCode);
		orderBean.setRetMsg(retMsg);
		if("0000".equals(retCode)){
			orderBean.setOrderStatus("1");
		}else{
			orderBean.setOrderStatus("2");
		}
		orderBean.setSendNum("1");

		return orderBean;
	}

	/**
	 * 获得秘钥
	 * @param xjfBuyBean
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getSign(XjfBuyBean xjfBuyBean) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		StringBuffer sb=new StringBuffer();
		sb.append(xjfBuyBean.getVer()).append(xjfBuyBean.getMsgNo()).append(xjfBuyBean.getChkDate().trim())
		.append(xjfBuyBean.getWorkDate().trim()).append(xjfBuyBean.getWorkTime()).append(xjfBuyBean.getAdd())
		.append(xjfBuyBean.getSrc()).append(xjfBuyBean.getDes()).append(xjfBuyBean.getApp())
		.append(xjfBuyBean.getMsgID()).append(xjfBuyBean.getReserve())
		.append(xjfBuyBean.getPhone()).append(xjfBuyBean.getType()).append(xjfBuyBean.getSkuCode())
		.append(xjfBuyBean.getCount()).append(xjfBuyBean.getNeedSend()).append(xjfBuyBean.getRemark());
		sb.append(ParamManager.xjfKey);
		
		log.info("加密字符串："+sb.toString());
		String sign = MD5Util.md5LowerCase(sb.toString());
		log.info("加密得到sign："+sign);
		return sign;
	}

	/**
	 * 获得秘钥
	 * @param xjfBuyBean
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getSignNH(XjfBuyBean xjfBuyBean) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		StringBuffer sb=new StringBuffer();
		sb.append(xjfBuyBean.getVer()).append(xjfBuyBean.getMsgNo()).append(xjfBuyBean.getChkDate().trim())
				.append(xjfBuyBean.getWorkDate().trim()).append(xjfBuyBean.getWorkTime()).append(xjfBuyBean.getAdd())
				.append(xjfBuyBean.getSrc()).append(xjfBuyBean.getDes()).append(xjfBuyBean.getApp())
				.append(xjfBuyBean.getMsgID()).append(xjfBuyBean.getReserve())
				.append(xjfBuyBean.getPhone()).append(xjfBuyBean.getType()).append(xjfBuyBean.getSkuCode())
				.append(xjfBuyBean.getCount()).append(xjfBuyBean.getNeedSend()).append(xjfBuyBean.getRemark());
		sb.append(ParamManager.xjfKey_nh);

		log.info("加密字符串："+sb.toString());
		String sign = MD5Util.md5LowerCase(sb.toString());
		log.info("加密得到sign："+sign);
		return sign;
	}

	/**
	 * 获得秘钥 duihuan
	 * @param xjfBuyBean
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getSignExchange(XjfBuyBean xjfBuyBean) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		StringBuffer sb=new StringBuffer();
		sb.append(xjfBuyBean.getVer()).append(xjfBuyBean.getMsgNo()).append(xjfBuyBean.getChkDate().trim())
				.append(xjfBuyBean.getWorkDate().trim()).append(xjfBuyBean.getWorkTime()).append(xjfBuyBean.getAdd())
				.append(xjfBuyBean.getSrc()).append(xjfBuyBean.getDes()).append(xjfBuyBean.getApp())
				.append(xjfBuyBean.getMsgID()).append(xjfBuyBean.getReserve())
				.append(xjfBuyBean.getPhone()).append(xjfBuyBean.getType()).append(xjfBuyBean.getSkuCode())
				.append(xjfBuyBean.getCount()).append(xjfBuyBean.getNeedSend()).append(xjfBuyBean.getRemark());
		sb.append(exchangeKeyPro.getProperty(xjfBuyBean.getSrc()));

		log.info("加密字符串："+sb.toString());
		String sign = MD5Util.md5LowerCase(sb.toString());
		log.info("加密得到sign："+sign);
		return sign;
	}
}

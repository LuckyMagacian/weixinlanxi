package com.lanxi.weixin.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.lanxi.weixin.bean.BaseReturnMsgBean;
import com.lanxi.weixin.bean.menu.ButtonBean;
import com.lanxi.weixin.bean.menu.ClickBean;
import com.lanxi.weixin.bean.menu.SubButtonBean;
import com.lanxi.weixin.bean.menu.ViewBean;
import com.lanxi.weixin.manager.ParamManager;
import com.lanxi.weixin.utils.AccessTokenUtil;
import com.lanxi.weixin.utils.HttpUtils;

public class MenuTest {

	private static Logger log = Logger.getLogger(MenuTest.class);
	
	public static void main(String[] args) {
		//String result = "{\"errcode\":0,\"errmsg\":\"ok\"}";
		//BaseReturnMsgBean bean = JSON.parseObject(result, BaseReturnMsgBean.class);
		//System.out.println(bean.getErrcode()+","+bean.getErrmsg());
		ParamManager.readConfig();
		getMenu();
//		createMenu();
		//String jsonText = getMenuJson();
		//System.out.println(jsonText);
	}
	
	public static void createMenu(){
		String accessToken = AccessTokenUtil.getAccessToken();
		//2016-02-29 14:05:32,积分微管家<凭证：G642ewwqkBMKmwYpJdRasbKiYKjkOaxfOLYjOd6-4fzSuZ7yxUuiZDGRGx1Q4_Q0xb4wfZMqg556Tv325wXqWG7sHBqLtOgPYC6xrNXM5loWKMjAFARFH>
		//String accessToken = "ZAzq6Vew3dsJyfK2dEQ3ijecOLEOFjCJaml6lRyEbdHPaQlMZ83QLsxTyr3VSJwmQD-uN-B83kqJtTBu1jFCXg7afS_6FmpRyAUJ66pscl-uSwYBcNSFlLuzTkHL4aRPYYAgAAAOOQ";
		log.info("凭证：" + accessToken);
		String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		createMenuUrl = createMenuUrl.replace("ACCESS_TOKEN", accessToken);
		log.info("创建自定义菜单的地址："+createMenuUrl);
		String createMenuJson = getMenuJson();
		log.info("创建自定义菜单的json："+createMenuJson);
		String createMenuResult = HttpUtils.sendPostJson(createMenuUrl, createMenuJson, "utf-8", "utf-8", "3000");
		log.info("创建自定义菜单返回结果："+createMenuResult);
		BaseReturnMsgBean bean = JSON.parseObject(createMenuResult, BaseReturnMsgBean.class);
		log.info("返回码："+bean.getErrcode()+",返回信息："+bean.getErrmsg());
		if("0".equals(bean.getErrcode())){
			log.info("创建自定义菜单成功");
		}
	}
	
	public static void getMenu(){
		String accessToken = AccessTokenUtil.getAccessToken();
		String url="https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";
		url.replace("ACCESS_TOKEN", accessToken);
		System.out.println(HttpUtils.sendGet(url, "utf-8",6000+""));
	}	
	
	/**
	 * 得到创建自定义菜单的json
	 * @return
	 */
	public static String getMenuJson(){
//		ViewBean leftFirst = new ViewBean();
//		leftFirst.setName("蓝喜生活");
//		leftFirst.setType("view");
//		leftFirst.setUrl("http://www.188lanxi.com/index/index.do");
		
//		ClickBean leftThird = new ClickBean();
//		leftThird.setName("蓝喜简介");
//		leftThird.setType("click");
//		leftThird.setKey("LEFT_THIRD");
		ViewBean leftThird = new ViewBean();
		leftThird.setName("蓝喜简介");
		leftThird.setType("view");
		leftThird.setUrl("http://u.eqxiu.com/s/QBqidnf7");
		
		ClickBean leftSecond = new ClickBean();
		leftSecond.setName("产品介绍");
		leftSecond.setType("click");
		leftSecond.setKey("LEFT_SECOND");
		
//		ClickBean leftFirst = new ClickBean();
//		leftFirst.setName("诚聘英才");
//		leftFirst.setType("click");
//		leftFirst.setKey("LEFT_FIRST");
		ViewBean leftFirst = new ViewBean();
		leftFirst.setName("诚聘英才");
		leftFirst.setType("view");
		leftFirst.setUrl("http://eqxiu.com/s/Y1mgFM2c");
		
		//微信网页授权部分
//		String redirect_uri = "http://www.188lanxi.com/weixinlanxi/OAuthServlet";
//		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
//		url = url.replace("APPID", ParamManager.appid).replace("REDIRECT_URI", redirect_uri)
//				.replace("SCOPE", "snsapi_userinfo").replace("STATE", "188lanxi");
//		log.info("用户同意授权，获取code的回调url："+url);
//		ViewBean middleThird = new ViewBean();
//		middleThird.setType("view");
//		middleThird.setName("网页授权测试");
//		middleThird.setUrl(url);
		ViewBean middleThird = new ViewBean();
		middleThird.setType("view");
		middleThird.setName("话费兑换");
		middleThird.setUrl("http://www.188lanxi.com/weixinlanxi/exchangeCode/exchangeCode.do");
		
		ViewBean middleSecond = new ViewBean();
		middleSecond.setType("view");
		middleSecond.setName("流量兑换");
		middleSecond.setUrl("http://www.188lanxi.com/weixinlanxi/codeCheck/toCodeCheck.do");
		
		ClickBean middleFirst = new ClickBean();
		middleFirst.setType("click");
		middleFirst.setName("客户服务");
		middleFirst.setKey("MIDDLE_FIRST");
		
//		ClickBean rightSecond = new ClickBean();
//		rightSecond.setType("click");
//		rightSecond.setName("玩个游戏");
//		rightSecond.setKey("RIGHT_SECOND");
		
		ViewBean rightSecond = new ViewBean();
		rightSecond.setType("view");
		rightSecond.setName("玩个游戏");
//		rightSecond.setUrl("http://www.188lanxi.com/weixinlanxi/planeGame/toPlaneGameIndex.do");
		String redirect_uri = "http://www.188lanxi.com/weixinlanxi/oauth/authorization.do";
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		url = url.replace("APPID", ParamManager.appid).replace("REDIRECT_URI", redirect_uri)
				.replace("SCOPE", "snsapi_userinfo").replace("STATE", "188lanxi");
		log.info("用户同意授权，获取code的回调url："+url);
		rightSecond.setUrl(url);
		
//		ClickBean rightFirst = new ClickBean();
//		rightFirst.setType("click");
//		rightFirst.setName("积分查询");
//		rightFirst.setKey("RIGHT_FIRST");
		
		List<Object> leftList = new ArrayList<Object>();
		leftList.add(leftThird);
		leftList.add(leftSecond);
		leftList.add(leftFirst);
		
		List<Object> middleList = new ArrayList<Object>();
		middleList.add(middleThird);
		middleList.add(middleSecond);
		middleList.add(middleFirst);
		
		List<Object> rightList = new ArrayList<Object>();
		rightList.add(rightSecond);
		//rightList.add(rightFirst);
		
		SubButtonBean leftSubButton = new SubButtonBean();
		leftSubButton.setName("蓝喜生活");
		leftSubButton.setSub_button(leftList);
		
		SubButtonBean middleSubButton = new SubButtonBean();
		middleSubButton.setName("消费服务");
		middleSubButton.setSub_button(middleList);
		
		SubButtonBean rightSubButton = new SubButtonBean();
		rightSubButton.setName("点我点我");
		rightSubButton.setSub_button(rightList);
		
		List<Object> subButtonList = new ArrayList<Object>();
		subButtonList.add(leftSubButton);
		subButtonList.add(middleSubButton);
		subButtonList.add(rightSubButton);
		
		ButtonBean buttonBean = new ButtonBean();
		buttonBean.setButton(subButtonList);
		String jsonText = JSON.toJSONString(buttonBean, true);
		return jsonText;
	}
}

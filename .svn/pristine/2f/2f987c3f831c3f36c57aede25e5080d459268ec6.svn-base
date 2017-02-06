package com.lanxi.weixin.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lanxi.weixin.bean.menu.ButtonBean;
import com.lanxi.weixin.bean.menu.ClickBean;
import com.lanxi.weixin.bean.menu.SubButtonBean;
import com.lanxi.weixin.bean.menu.ViewBean;


/**
 * 阿里巴巴的json包
 * <p>Title:FastJsonTest </p>
 * <p>Description: </p>
 * @author 任飞虎
 * @date 2016-1-19 上午10:49:02
 *
 */
public class FastJsonTest {

	public static void main(String[] args) {
		//左边按钮：一级菜单，有三个二级菜单情况
		ClickBean leftThird = new ClickBean();
		leftThird.setType("click");
		leftThird.setName("商城");
		leftThird.setKey("LEFT_THIRD");
		ClickBean leftSecond = new ClickBean();
		leftSecond.setType("click");
		leftSecond.setName("会员");
		leftSecond.setKey("LEFT_SECOND");
		ViewBean leftFirst = new ViewBean();
		leftFirst.setName("蓝喜");
		leftFirst.setType("view");
		leftFirst.setUrl("http://www.188lanxi.com/index/index.do");
		
		List<Object> leftList = new ArrayList<Object>();
		leftList.add(leftThird);
		leftList.add(leftSecond);
		leftList.add(leftFirst);
		
		SubButtonBean leftSubButton = new SubButtonBean();
		leftSubButton.setName("蓝喜生活");
		leftSubButton.setSub_button(leftList);
		
		//中间按钮：一级菜单，有三个二级菜单的情况
		ClickBean middleThird = new ClickBean();
		middleThird.setType("click");
		middleThird.setName("影券选座");
		middleThird.setKey("MODDLE_THIRD");
		ClickBean middleSecond = new ClickBean();
		middleSecond.setType("click");
		middleSecond.setName("水果门店");
		middleSecond.setKey("MODDLE_SECOND");
		ClickBean middleFirst = new ClickBean();
		middleFirst.setType("click");
		middleFirst.setName("客户服务");
		middleFirst.setKey("MODDLE_FIRST");
		
		List<Object> middleList = new ArrayList<Object>();
		middleList.add(middleThird);
		middleList.add(middleSecond);
		middleList.add(middleFirst);
		
		SubButtonBean middleSubButton = new SubButtonBean();
		middleSubButton.setName("消费服务");
		middleSubButton.setSub_button(middleList);
		
		//右边按钮：只有一级菜单的情况
		ClickBean rightFirst = new ClickBean();
		rightFirst.setType("click");
		rightFirst.setName("手机充值");
		rightFirst.setKey("RIGHT_FIRST");
		
		List<Object> subButtonList = new ArrayList<Object>();
		subButtonList.add(leftSubButton);
		subButtonList.add(middleSubButton);
		subButtonList.add(rightFirst);
		
		ButtonBean buttonBean = new ButtonBean();
		buttonBean.setButton(subButtonList);
		System.out.println(JSON.toJSONString(buttonBean, true));
	}
}

package com.lanxi.weixin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxi.weixin.bean.OrderBean;
import com.lanxi.weixin.mapper.OrderMapper;
import com.lanxi.weixin.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public void insertOrder(OrderBean orderBean) {
		orderMapper.insertOrder(orderBean);
	}

	@Override
	public void updateOrder(OrderBean orderBean) {
		orderMapper.updateOrder(orderBean);
	}

}

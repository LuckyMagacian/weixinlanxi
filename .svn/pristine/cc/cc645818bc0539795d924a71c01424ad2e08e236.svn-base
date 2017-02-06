package com.lanxi.weixin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxi.weixin.mapper.TestMapper;
import com.lanxi.weixin.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestMapper tsetMapper;
	
	@Override
	public int test() {

		return tsetMapper.test();
	}

}

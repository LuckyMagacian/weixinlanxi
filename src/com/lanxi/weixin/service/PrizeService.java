package com.lanxi.weixin.service;

public interface PrizeService {

	/**
	 * 根据商品编号获得商品类别
	 * @param spbh		商品编号
	 * @return
	 */
	public abstract String getSplbBySpbh(String spbh);
}

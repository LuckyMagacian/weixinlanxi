package com.lanxi.weixin.bean;

public class GameResultBean {

	/**微信编号*/
	private String openid;
	/**游戏类型*/
	private String gameType;
	/**游戏随机值*/
	private String probability;
	/**游戏结果*/
	private String result;
	/**创建时间	yyyyMMddHHmmss*/
	private String createTime;
	/**备用*/
	private String beiy;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getBeiy() {
		return beiy;
	}
	public void setBeiy(String beiy) {
		this.beiy = beiy;
	}
	
}

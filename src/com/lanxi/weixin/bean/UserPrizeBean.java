package com.lanxi.weixin.bean;

/**
 * 用户奖品表
 * <p>Title:UserPrizeBean </p>
 * <p>Description: </p>
 * @author 任飞虎
 * @date 2016-3-2 下午2:50:13
 *
 */
public class UserPrizeBean {

	/**微信id*/
	private String openid;
	/**手机号码*/
	private String phone;
	/**游戏类型*/
	private String gameType;
	/**商品编号*/
	private String spbh;
	/**奖品等级	0-特等奖	1-一等奖	2-二等奖	3-三等奖*/
	private String prizeGrade;
	/**商品数量*/
	private String spsl;
	/**奖品状态	1-未领取	2-已领取	3-已过期*/
	private String prizeStatus;
	/**得到的游戏积分*/
	private String points;
	/**积分状态	1-未发放	2-已发放	3-已过期*/
	private String pointsStatus;
	/**创建日期*/
	private String createDate;
	/**创建时间*/
	private String createTime;
	/**订单号*/
	private String orderId;
	/**截止日期*/
	private String deadline;
	/**备用*/
	private String beiy;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getSpbh() {
		return spbh;
	}
	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}
	public String getPrizeGrade() {
		return prizeGrade;
	}
	public void setPrizeGrade(String prizeGrade) {
		this.prizeGrade = prizeGrade;
	}
	public String getSpsl() {
		return spsl;
	}
	public void setSpsl(String spsl) {
		this.spsl = spsl;
	}
	public String getPrizeStatus() {
		return prizeStatus;
	}
	public void setPrizeStatus(String prizeStatus) {
		this.prizeStatus = prizeStatus;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getPointsStatus() {
		return pointsStatus;
	}
	public void setPointsStatus(String pointsStatus) {
		this.pointsStatus = pointsStatus;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getBeiy() {
		return beiy;
	}
	public void setBeiy(String beiy) {
		this.beiy = beiy;
	}
}

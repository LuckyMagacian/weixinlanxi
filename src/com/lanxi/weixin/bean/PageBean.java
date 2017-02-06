package com.lanxi.weixin.bean;

public class PageBean {

	/**微信编号*/
	private String openid;
	/**第几页*/
	private int pageNum;
	/**每页记录数*/
	private int numPerPage;
	
	private int start;
	

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
}

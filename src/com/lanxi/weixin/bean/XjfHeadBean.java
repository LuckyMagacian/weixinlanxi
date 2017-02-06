package com.lanxi.weixin.bean;

public class XjfHeadBean {

	private String ver = "";
	private String msgNo = "";
	private String chkDate = "";
	private String workDate = "";
	private String workTime = "";
	private String add = "";
	private String src = "";
	private String des = "";
	private String app = "";
	private String msgID = "";
	private String reserve = "";
	private String sign = "";
	private String resCode = "";
	private String resMsg = "";
	
	public String getHeadXml(){
		StringBuffer sb = new StringBuffer();
		sb.append("<HEAD>")
		.append("<VER>").append(this.ver).append("</VER>")
		.append("<MsgNo>").append(this.msgNo).append("</MsgNo>")
		.append("<CHKDate>").append(this.chkDate).append("</CHKDate>")
		.append("<WorkDate>").append(this.workDate).append("</WorkDate>")
		.append("<WorkTime>").append(this.workTime).append("</WorkTime>")
		.append("<ADD>").append(this.add).append("</ADD>")
		.append("<SRC>").append(this.src).append("</SRC>")
		.append("<DES>").append(this.des).append("</DES>")
		.append("<APP>").append(this.app).append("</APP>")
		.append("<MsgID>").append(this.msgID).append("</MsgID>")
		.append("<Reserve>").append(this.reserve).append("</Reserve>")
		.append("<Sign>").append(this.sign).append("</Sign>")
		.append("<ResCode>").append(this.resCode).append("</ResCode>")
		.append("<ResMsg>").append(this.resMsg).append("</ResMsg>")
		.append("</HEAD>");
		return sb.toString();
	}
	
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(String msgNo) {
		this.msgNo = msgNo;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getChkDate() {
		return chkDate;
	}

	public void setChkDate(String chkDate) {
		this.chkDate = chkDate;
	}
	
}

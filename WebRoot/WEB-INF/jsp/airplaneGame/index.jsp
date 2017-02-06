<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>

	<head>
		<meta name="viewport" content="initial-scale=1, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0, width=device-width">
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../css/airplaneGameIndex.css" />
		<title>飞机大战</title>
	</head>

	<body>
		<div id="logo"></div>
		<div id="welcome">欢迎，<span id="userName">${opBean.nickname }</span></div>
		<div id="start">
			<div style="height: 1px;"></div>
			<%-- <a href="../planeGame/toPlaneGamePlay.do?openid=${opBean.openid }&nickname=${opBean.nickname }" onclick="start()"></a> --%>
			<a href="../planeGame/toPlaneGamePlay.do" onclick="start()"></a>
		</div>
		<!-- 排行榜 -->
		<div id="rankList">
			<div style="height: 1px;"></div>
			<a href="javascript:rankList();"></a>
		</div>
		<!-- 活动信息 -->
		<div id="activeInfo">
			<div style="height: 1px;"></div>
			<a href="javascript:activeInfo();"></a>
		</div>
		<!-- 领取奖品 -->
		<div id="prizeInfo">
			<div style="height: 1px;"></div>
			<a href="javascript:prizeInfo();"></a>
		</div>
		<!-- 弹出框 -->
		<!--//排行榜弹出框-->
		<div id="listModel" class="hide">
			<div class="modelHeader">本周飞机大战排行榜</div>
			<div id="list">
				<c:forEach items="${rankingList }" var="bean" varStatus="status">
					<div class="modelLi">
						<div class="listRank">${status.index+1 }</div>
						<div class="listImg"><img src="${bean.headimgurl }"/></div>
						<div class="listScore"><span class="name">${bean.nickname }</span></div>
						<span class="score">${bean.gameScore }</span>
					</div>
				</c:forEach>
			</div>
			<div class="modelFooter">
				<button class="modelBtn" onclick="window.location.href='toPlaneGamePlay.do';">开始游戏</button>
				<button class="modelBtn" onclick="closeModel('listModel')">关闭</button>
			</div>
		</div>
		
		<!--//活动说明弹出框-->
		<div id="infoModel" class="hide">
			<div class="modelHeader">活动说明</div>
			<div class="modelContent">
				<p>1.活动时间：2016年1月1日起至2016年12月31日。</p>
				<p>2.开奖时间为每周一凌晨1点，届时平台会将中奖信息发布至中奖玩家的获奖列表中，玩家可在一周内领取。</p>
				<p>3.第一名：50元必胜客代金券1张+500积分；<br/>
					第二名：10元水果通兑券1张+300积分；<br/>
					第三名：联通50M流量或移动/电信30M流量+100积分；<br/>
					鼓励奖若干名：联通20M流量或移动/电信10M流量+50积分；</p>
				<p>4.电子券类将品将在玩家领取后24小时之内发送至用户指定的手机上。</p>
				<p>5.流量类奖品将在玩家领取后的24小时之内充值至用户指定的手机号中。</p>
				<p>6.若有疑问请在公众号中留言或联系客服<a href="tel:4000552797">400-055-2797</a>。<br/>
					本活动最终解释权归杭州蓝喜信息技术有限公司所有。
				</p>
			</div>
			<div class="modelFooter">
				<button class="modelBtn" onclick="closeModel('infoModel')">关闭</button>
			</div>
		</div>

		<!--//领取奖品弹出框-->
		<div id="prizeModel" class="hide">
			<div class="modelHeader">领取奖品</div>
			<div class="center">
				<p id="prizeSorry">抱歉，${opBean.nickname }<br/><label id="sorryText"></label></p>
				<p id="prizeBack" class="hide"></p>
				<div id="prizeYesContent" class="hide">
				<p>恭喜，${opBean.nickname } <label id="prizeText"></label><br></p>
				<p><label>领奖手机号：</label><input type="text" id="prizePhone" name="prizePhone" onblur="validatePhone(this.id);"/></p>
				<!-- <p class="prizeYes"><label>身份证号码：</label><input type="text" id="prizeIdcard" name="prizeIdcard" onblur="validateIdcard(this.id);"/></p> -->
				<label id="prizeModelHint">&nbsp;</label>
				</div>
			</div>
			<div class="modelFooter">
				<button class="modelBtn" id="prizeYesBtn" onclick="prize()">确认兑换</button>
				<button class="modelBtn" onclick="closeModel('prizeModel')">关闭</button>
			</div>
		</div>
		
		<div id="fullBlack" class="hide"></div>
		
		<footer>
			<div class="copyright">
				<p>copyright &copy; <a href="http://m.188lanxi.com"><strong>188lanxi.com</strong></a> @蓝喜微管家</p>
			</div>
		</footer>

		<script type="text/javascript" src="../js/airplaneGameMyFJ.js"></script>
	</body>

</html>

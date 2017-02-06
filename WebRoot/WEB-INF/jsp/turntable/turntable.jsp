<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1,maximum-scale=1" />
		<title>转盘</title>
		<link rel="stylesheet" type="text/css" href="../css/turntable.css" />
	</head>

	<body>
		<div id="lottery">
			<div id="turntable">
				<div id="pointer"></div>
			</div>
			<div id="activeInfo">
			<p>您还有<label id="integral">${opBean.points}</label>积分，消耗10积分可以再转一次。</p>
			<input type="hidden" id="turnTime" value="${number}">
			</div>
		</div>
		<div id="prizeInfo">
			<label>游戏说明：</label>
			<p>1.活动时间：2016年1月1日起至2016年12月31日。</p>
			<p>2.每位用户每天拥有一次免积分启动大转盘的机会；若将“蓝喜微管家”公众号分享给朋友可额外获得一次机会；每消耗10个积分也可获得一次启动机会。</p>
			<p>3.奖品设置：特等奖：50元水果通兑券1张；</p>
			<p>一等奖：移动70M流量或联通/电信100M流量；</p>
			<p>二等奖：5元水果通兑券1张；</p>
			<p>三等奖：联通20M流量或移动/电信10M流量；</p>
			<p>4.电子券类将品将在开奖后24小时之内通过短信发送兑换串码至用  ,户指定的手机上。</p>
			<p>5.流量类奖品将在开奖后的24小时之内充值至用户指定的手机号中       兑奖记录将显示在兑奖列表。</p>
			<p>6.数量有限，先到先得，发完为止。</p>
			<p>7.若有疑问请在公众号中留言反应或联系客服<a href="tel:4000552797">400-055-2797</a>。</p>
			<p>本活动解释权归杭州蓝喜信息技术有限公司所有。</p>
		</div>
		<footer>
			<img src="../img/turntable/footer.jpg" id="footerBg"/>
			<p class="copyright">copyright &copy; 2016 <a href="http://m.188lanxi.com"><strong>188lanxi.com</strong></a> @蓝喜微管家</p>
		</footer>
		
		<!--//转盘结果&领取奖品弹出框-->
		<div id="prizeModel" class="hide">
			<div class="modelHeader">转盘结果</div>
			<div class="center modelContent">
				<input type="hidden" id="openId"/>
				 <p class="sorry hide"><label class="retMsg"></label></p> 
				<p class="prost">恭喜，${opBean.nickname } <br>获得<label class="retMsg"></label></p>
				<p class="prost"><label for="prizePhone" style="vertical-align: middle;">领奖手机号：</label>
					<input type="text" id="prizePhone" name="prizePhone" maxlength="11" onblur="validatePhone(this.id);"/></p>
				<!--<p class="prost"><label>身份证号码：</label><input type="text" id="prizeIdcard" name="prizeIdcard"/></p>-->
				<label id="dialogHint">&nbsp;</label>
				<p class="prost"><label id="prizeHint">请确认手机号正确填写，以免影响奖品兑换</label></p>
			</div>
			<div class="modelFooter">
				<a class="modelBtn prost hide" onclick="getPrizeInfo()" style="color:#0BB20C;border-right:solid 1px #e8e8ea;">确认兑换</a>
				<a class="modelBtn prost" onclick="closeModel('prizeModel')">放弃兑换</a>
				<a class="modelBtn sorry" onclick="closeModel('prizeModel')" style="color:#0BB20C;">确定</a>
			</div>
		</div>
		<div id="fullBlack" class="hide"></div>

		<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="../js/jQueryRotate.2.2.js"></script>
		<script type="text/javascript" src="../js/jquery.easing.min.js"></script>
		<script type="text/javascript" src="../js/turntable.js"></script>
	</body>

</html>

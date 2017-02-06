<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>刮刮乐</title>

		<link rel="stylesheet" type="text/css" href="../css/happyScratch.css" />
	</head>

	<body>
		<div id="skySize">
			<header>
				<input type="hidden" id="number" value="${number}"/>
				<div id="text">您还有<label id="integral">${opBean.points}</label>积分</div>
				<div id="scratch"></div>
				<a class="button btn-orange hide" id="getPrize" href="javascript:void(0);" onclick="showDialog('prizeModel');">领奖</a>
				<a class="button btn-orange hide" id="reload" href="javascript:location.reload();">再刮一次</a>
			</header>
			<div id="content">
				<div id="blackBg"></div>
				<div id="activeInfo">
					<label>活动规则</label>
					<p>1.活动时间：2016年1月1日起至2016年12月31日。</p>
					<p>2.每位用户每天拥有一次刮奖的机会；若将“蓝喜微管家”公众号分享给朋友可额外获得一次机会；每消耗10个积分也可获得一次刮奖机会。</p>
					<p>3.奖品设置：本游戏所包含的奖品有水果通兑券、必胜客代金券、哈根达斯代金券、流量加油包以及不同额度积分均将随机赠送。</p>
					<p>4.电子券类将品将在开奖后24小时之内通过短信发送兑换串码至用户指定的手机上。</p>
					<p>5.流量类奖品将在开奖后的24小时之内充值至用户指定的手机号中，兑奖记录将显示在兑奖列表。</p>
					<p>6.积分类奖品将在开奖后实时加入用户的平台积分账户。</p>
					<p>7.奖品有限，先到先得，发完为止。</p>
					<p>8.若有疑问请在公众号中留言或联系客服<a href="tel:4000552797">400-055-2797</a>。<br />本活动解释权归杭州蓝喜信息技术有限公司所有。</p>
				</div>

				<footer id="copyright">
					<p>copyright &copy; <a href="http://m.188lanxi.com"><strong>188lanxi.com</strong></a> @蓝喜微管家</p>
				</footer>
			</div>
		</div>
		<!--//刮刮乐领取奖品弹出框-->
		<div id="prizeModel" class="hide">
			<div class="modelHeader">领取奖品</div>
			<div class="center modelContent">
				<input type="hidden" id="openId" />
				<p><label for="prizePhone" style="vertical-align: middle;">领奖手机号：</label>
					<input type="text" id="prizePhone" name="prizePhone" maxlength="11" onblur="validatePhone(this.id);" /></p>
				<label id="dialogHint">&nbsp;</label>
				<p><label id="prizeHint">请确认手机号正确填写，以免影响奖品兑换</label></p>
			</div>
			<div class="modelFooter">
				<a class="modelBtn" onclick="getPrizeInfo()" style="color:#0BB20C;border-right:solid 1px #e8e8ea;">确认兑换</a>
				<a class="modelBtn" onclick="closeModel('prizeModel')">放弃兑换</a>
			</div>
		</div>
		<div id="fullBlack" class="hide"></div>
		
		<script src="../js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/wScratchPad.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/happyScratch.js" type="text/javascript" charset="utf-8"></script>
	</body>

</html>

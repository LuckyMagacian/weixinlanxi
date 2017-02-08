<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" href="../css/codeCheckIndex.css" />

<title>兑换成功——礼品兑换平台</title>
</head>

<body>
	<header>
		<div style="height: 1px;"></div>
		<div id="headerBg"></div>
<div id="headerTitle"><span style="font-size: 0.8em">欢迎使用</span><br/><span>礼品兑换平台</span></div>
	</header>
	<div class="content">
		<h2 class="successColor center">兑换成功！</h2>
		<!-- <p class="successColor">恭喜您，串码验证成功，验证商品为||，系统将会在24小时内充值至尾号为||的手机中。如有疑问，请联系xxxxxxxx。</p> -->
		<p class="successColor">系统将会在两个工作日内将${spmc}发送/充值至${phone}中，请注意查收。如有疑问，请致电客服<a href="tel:4000552797">400-055-2797</a>。</p>
		<p style="text-indent: 0em;text-align: center;">
			<a href="../exchangeCode/exchangeCode.do">点击这里</a>返回至首页
		</p>
	
<!-- 	<div id="attention">
		<p>以下情况会造成话费充值失败：</p>
		<p>1.充值的手机号码为虚拟号段。</p>
		<p>2.该手机号今日充值次数超过5次。</p>
	</div> -->
	</div>
	<footer>
		<hr style="width: 90%;border: 1px solid #ccc;text-align: center;" />
		<div class="copyright">
			<span>Copyright &copy; 2016 <strong>188lanxi</strong>.com All
				Right Reserved</span> <br /> <span><font style="font-weight: bold;">杭州<strong>蓝喜</strong>信息技术有限公司</font>(浙ICP备14031205号)</span>
		</div>
	</footer>

	<script src="../js/jquery.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script>
		$(function() {
			var height = $(window).height(), width = $(window).width();
			if (width < 380)
				height -= 280; //170px header + 110px footer
			else if (width < 768)
				height -= 268; //170px header + 95px footer
			else
				height -= 215; //43px header + 95px footer
			$(".content").css("min-height", height + "px");
		});
	</script>

</body>

</html>
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

<title>验证成功——流量兑换平台</title>
</head>

<body>
	<header>
		<div style="height: 1px;"></div>
		<div id="headerBg"></div>
<div id="headerTitle"><span style="font-size: 0.8em">欢迎使用</span><br/><span>流量兑换平台</span></div>
	</header>
	<div class="content">
		<h2 class="successColor center">流量兑换成功！</h2>
		<!-- <p class="successColor">恭喜您，串码验证成功，验证商品为||，系统将会在24小时内充值至尾号为||的手机中。如有疑问，请联系xxxxxxxx。</p> -->
		<p class="successColor">恭喜您，兑换码验证成功，系统将会在48小时之内将xx流量xxM充值至xxxx中，请注意查收。如有疑问，请致电客服400-055-2797。</p>
		<p style="text-indent: 0em;text-align: center;">
			<a href="../codeCheck/toCodeCheck.do">点击这里</a>返回至首页
		</p>
	
	<div id="attention">
		<p>以下情况会造成流量充值失败：</p>
		<p>1.充值号码为虚拟号段；</p>
		<p>2.流量加油包与充值号码已有套餐冲突。</p>
		<!-- <p>3.请确认您输入的手机号码无误。</p> -->
	</div>
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
				height -= 138; //43px header + 95px footer
			$(".content").css("min-height", height + "px");
		});
	</script>

</body>

</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="../css/codeCheckIndex.css" />

		<title>验证失败————话费充值平台</title>
	</head>

	<body>
		<header>
			<div style="height: 1px;"></div>
			<div id="headerBg"></div>
<div id="headerTitle"><span style="font-size: 0.8em">欢迎使用</span><br/><span>话费充值平台</span></div>
		</header>
		<div class="content">
			<h2 class="failColor center">话费充值失败！</h2>
			<p class="failColor">很抱歉，您输入的${retMsg}，请重新输入。如有疑问，请致电客服<a href="tel:4000552797">400-055-2797</a>。</p>
			<p><a href="../exchangeCode/exchangeCode.do">点击这里</a>返回至首页</p>
		</div>
		<footer>
			<hr style="width: 90%;border: 1px solid #ccc;text-align: center;" />
			<div class="copyright">
				<span>Copyright &copy; 2016 <strong>188lanxi</strong>.com  All Right Reserved</span>
				<br />
				<span><font style="font-weight: bold;">杭州<strong>蓝喜</strong>信息技术有限公司</font>(浙ICP备14031205号)</span>
			</div>
		</footer>

		<script src="../js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script>
			$(function() {
				var height = $(window).height(),
					width = $(window).width();
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
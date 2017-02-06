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

		<title>流量兑换平台</title>
	</head>

	<body>
		<header>
			<div style="height: 1px;"></div>
			<div id="headerBg"></div>
			<div id="headerTitle"><span style="font-size: 0.8em">欢迎使用</span><br/><span>流量兑换平台</span></div>
		</header>
		<div class="content">
			<form action="../codeCheck/checkCode.do" method="post" id="form" onsubmit="javascript:return control('formBtn');">
				<div class="formGroup">
					<label for="code"> 请输入您的兑换码：</label>
					<input type="text" id="code" name="code" onblur="getCodePhone(this.id);" placeholder="请输入12位兑换码" maxlength="12"/><span class="icon" id="codeHint"></span></div>
				<div style="margin-bottom: 5px;">
					<!-- <label for="phone"> 匹配手机号码：</label> -->
					<input type="hidden" id="phone" name="phone" onchange="validatePhone(this.id)" placeholder="自动匹配手机号" readonly="readonly"/><!-- <span class="icon" id="phoneHint"></span> --></div>
				<div id="text">
					<label>&nbsp;</label>
				</div>
				<div id="attention">
					<p>尊敬的用户您好：</p>
					<p>1.请输入您收到农行短信中的流量兑换码，系统会自动为您参与活动的手机号进行充值。</p>
					<p>2.若您遇到兑换码无法成功使用等异常情况，请致电客服400-055-2797。</p>
					<!-- <p>3.请确认您输入的手机号码无误。</p> -->
				</div>
				<div id="btn">
					<button style="border: 0px;background-color: #e5e5e5;"><img src="../img/codeCheck/btn.png" style="width:260px"></button>
				</div>
			</form>
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
		<script src="../js/codeCheckIndex.js" type="text/javascript" charset="utf-8"></script>

	</body>

</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>OAuth2.0网页授权</title>
  </head>
  
  <body>
	<c:if test="${!empty odBean }">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr><td width="20%">属性</td><td width="80%">值</td></tr>
		<tr><td>OpenID</td><td>${odBean.openid }</td></tr>
		<tr><td>昵称</td><td>${odBean.nickname }</td></tr>
		<tr><td>性别</td><td>${odBean.sex }</td></tr>
		<tr><td>国家</td><td>${odBean.city }</td></tr>
		<tr><td>省份</td><td>${odBean.province }</td></tr>
		<tr><td>城市</td><td>${odBean.country }</td></tr>
		<tr><td>头像</td><td>${odBean.headimgurl }</td></tr>
	</table>
	</c:if>
	<c:if test="${empty odBean }">
		用户未授权
	</c:if>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta name="viewport" content="initial-scale=1, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0, width=device-width">
        <meta charset="utf-8" />
        <title>飞机大战</title>
        <link rel="stylesheet" type="text/css" href="../css/airplaneGameGlobal.css" />
        <!--[if IE]>
        <script type="text/javascript" src="../js/airplaneGameExcanvas.js"></script>
        <![endif]-->
        <script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
    </head>
    <body>
        <div id="main">
            <div id="modal" class="hide">
                <div class="header">飞机大战分数</div>
                <div class="content">
                	<div id="content">0</div>
                	<div style="margin-bottom: 4px;"><label>&emsp;历史最高：</label><span id="best">${maxScore }</span></div>
                </div>
                <div class="footer">
                	<input type="hidden" name="nickname" id="userName" value="${opBean.nickname }" />
                	<input type="hidden" name="openid" id="userId" value="${opBean.openid }" />
                    <button class="modelBtn" onclick="upScore(1)">返回首页</button><button class="modelBtn playAgain" onclick="upScore(0)">再玩一次</button>
                </div>
            </div>
            
            <div id="sureModel" class="hide">
                <div class="modelHeader">提交分数</div>
                <div id="upText">
                	分数上传中...
                </div>
                <div class="modelFooter">
                    <button class="modelBtn" onclick="window.location.href='toPlaneGameIndex.do'">返回首页</button>
                    <button class="modelBtn playAgain">再玩一次</button>
                </div>
            </div>
            
            <canvas id="game-box"></canvas>
        </div>
    </body>
    <script type="text/javascript" src="../js/airplaneGamePlain.js"></script>
    <script src="../js/airplaneGameMyFJ.js" type="text/javascript" charset="utf-8"></script>
</html>

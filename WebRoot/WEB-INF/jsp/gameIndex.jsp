<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<link rel="stylesheet" type="text/css" href="../css/gameIndex.css" />
<title>游戏列表</title>
</head>
<body>
	<div style="height:12px;"></div>
	<header>
		<div id="headerPic">
			<img src="${opBean.headimgurl }" />
		</div>
		<div id="text">
			<p id="nickname">${opBean.nickname }</p>
			<p class="color999">
				我的积分： <label id="integral">${opBean.points }</label>
			</p>
		</div>
		<div id="codePic" onclick="show2DCode();">
			<img src="../img/gameIndex/codePic.jpg" alt="2dCode"></img>
		</div>
	</header>
	<div id="contant">
		<div id="btnList">
			<div onclick="focusList('game','prize');"><label class="focusList" id="gameMenu">游戏菜单</label></div>
			<div id="listLine"></div>
			<div onclick="focusList('prize','game');"><label id="prizeMenu">获奖列表</label></div>
		</div>
		<!-- 游戏列表块 -->
		<div id="gameListContent">
			<div class="region">
				<div id="hotGame">
				<img src="../img/gameIndex/hotGame.png"></img>
				</div>
				<div class="row">
					<div class="gamePic">
						<img src="../img/gameIndex/airplane.jpg" />
					</div>
					<div class="gameText">
						<p>飞机大战</p>
						<p class="introduce">消灭更多的敌机，赢取更高的分数，好礼周周发！</p>
					</div>
					<a href="../planeGame/toPlaneGameIndex.do?openid=${opBean.openid }"
						class="button btn-red">进入游戏</a>
				</div>
			</div>
			<div class="region">
				<div id="hotActive">
				<img src="../img/gameIndex/hotList.png"></img>
				</div>
				<div class="row">
					<div class="gamePic">
						<img src="../img/gameIndex/turntable.jpg" />
					</div>
					<div class="gameText">
						<p>幸运大转盘</p>
						<p class="introduce">在无聊的时候可以试试运气，说不定大奖就是你的！</p>
					</div>
					<a href="../turnTable/goTurnTable.do?openid=${opBean.openid }"
						class="button btn-blue">进入游戏</a>
				</div>
				<div class="row">
					<div class="gamePic">
						<img src="../img/gameIndex/scratch.jpg" />
					</div>
					<div class="gameText">
						<p>刮刮乐</p>
						<p class="introduce">今天手气不错？来玩刮刮卡吧，让您开心乐不停！</p>
					</div>
					<a href="../happyScratch/getHappyScratch.do?openid=${opBean.openid }" class="button btn-blue">进入游戏</a>
				</div>
			</div>
		</div>
		<!-- 获奖列表块 -->
		<div id="prizeListContent" class="hidden">
			<!-- <div class="prizeRow">
				<div class="prizeInfo">
					<p>参与游戏：<label>幸运的转盘</label></p>
					<p>获奖级别：<label>三等奖</label></p>
					<p>奖品名称：<label style="color:#af0000;">代金券</label></p>
					<p>领奖截至：<label>2016-10-10</label></p>
				</div>
				<div class="prizeBtn btn-yes" onclick="getPrize();">领奖</div>
			</div>
			<div class="shadow"></div>	 -->	 
		</div>
	</div>
	<footer>
		<!-- <label style="color:#9d9d9d;position:absolute;left:0;right:0;bottom:80px;text-align: center;">更多精彩敬请期待</label> -->
		<div class="copyright"></div>
	</footer>
	<!-- dialog -->
	<!-- 二维码 -->
	<div id="codeDialog" class="hidden">
		<img id="codePicSrc" src="../img/default.jpg">
		<label id="codeHint">扫扫上面的二维码，分享给好友</label>
	</div>
	<!-- 奖品领取 -->
	<div id="prizeDialog" class="hidden">
	<input type="hidden" id="gameType">
	<input type="hidden" id="createDate">
	<input type="hidden" id="createTime">
	<input type="hidden" id="range">
		<div class="modelHeader">领取奖品</div>
		<p style="margin:12px 0px 2px 0px;"><label for="prizePhone" style="vertical-align: middle;">领奖手机号：</label>
		<input type="text" id="prizePhone" name="prizePhone" maxlength="11" onblur="validatePhone(this.id);"/></p>
		<label id="dialogHint">&nbsp;</label>
		<label id="prizeHint">请确认手机号正确填写，以免影响奖品兑换</label>
		<div class="modelFooter">
			<a class="modelBtn" onclick="getPrizeInfo()" style="color:#0BB20C;border-right:solid 1px #e8e8ea;">确认兑换</a>
			<a class="modelBtn" onclick="closeModel('prizeDialog')">暂不兑换</a>
		</div>
	</div>
	<div id="fullBlack" class="hidden"></div>
	
	<script type="text/javascript" src="../js/jquery-1.12.0.min.js"></script>
	<script type="text/javascript" src="../js/gameIndex.js"></script>
</body>

</html>
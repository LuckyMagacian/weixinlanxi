$(document).ready(function(){
	getPrizeList();
	$("#fullBlack").bind("click",removeClick=function(){
		$("#codeDialog").addClass("hidden");
		$("#fullBlack").addClass("hidden");
		$("#prizeDialog").addClass("hidden");
	});
});

list=new Array();
list["TurnTable"]=new Array('50元水果通用券','联通电信100M/移动70M流量','5元水果通用券','联通20M/电信移动10M流量','');
list["AirPlaneGame"]=new Array('','50元必胜客代金券','10元水果通用券','联通50M/移动电信30M流量','联通20M/电信移动10M流量');
list["HappyScratch"]=new Array('','10积分','20积分','50积分','联通20M或移动/电信10M流量','必胜客30元代金券','5元水果通兑券','10元水果通兑券','30元水果通兑券');

/* 获取用户获奖列表 */
function getPrizeList(){
	var block1='<div class="prizeRow"><div class="prizeInfo"><p>参与游戏：<label>',
		block2='</label></p><p>获奖级别：<label>',
		block3='</label></p><p>奖品名称：<label style="color:#af0000;">',
		block4='</label></p><p>领奖截至：<label>',
		block5='</label></p></div>',
		block6=null,
		block6_1='<div class="prizeBtn btn-yes" onclick="getPrize(',block6_4=');">领奖</div></div><div class="shadow"></div>',
		block6_2='<div class="prizeBtn">',block6_3='</div></div><div class="shadow"></div>';
	$.ajax({
		type: "post",
		url: "getUserPrizeList.do",
		//cache: false,
		dataType: 'json',
		data:{
			pageNum:"1",
			numPerPage:"10"
		},
		contentType: 'application/x-www-form-urlencoded',
		success: function(jsonStr) {
			var i=0,obj,
			gameType=null,
			range=null,
			rangeName=null,
			prizeName=null,
			deadline=null,
			createDate=null,
			createTime=null,
			status=0;
		$("#prizeListContent").html("");
		while(obj=jsonStr[i++]){
			gameType=obj.gameType;//游戏名称（TurnTable、AirPlaneGame、HappyScratch）
			range=obj.prizeGrade;//奖品级别
			prizeName=list[gameType][range];//奖品名称
			deadline=obj.deadline,//截止日期
			status=obj.prizeStatus,//领奖状态
			createDate=obj.createDate,
			createTime=obj.createTime;
			//gameType=(gameType=="TurnTable"?'幸运大转盘':'飞机大战');
			if(gameType=="TurnTable"){
				gameType='幸运大转盘';
				switch(range){
				case "0":rangeName="特等奖";break;
				case "1":rangeName="一等奖";break;
				case "2":rangeName="二等奖";break;
				case "3":rangeName="三等奖";break;
				case "4":rangeName="鼓励奖";break;
				}
			}
			else if(gameType=="AirPlaneGame"){
				gameType='飞机大战';
				switch(range){
				case "0":rangeName="特等奖";break;
				case "1":rangeName="一等奖";break;
				case "2":rangeName="二等奖";break;
				case "3":rangeName="三等奖";break;
				case "4":rangeName="鼓励奖";break;
				}	
			}
			else if(gameType=="HappyScratch"){
				gameType='刮刮乐';
				rangeName="无等级";
				//rangeName=range;
			}
			else
				alert("gameType error! "+gameType);
			
			
			deadline=deadline.substring(0,4)+"-"+deadline.substring(4,6)+"-"+deadline.substring(6,8);
			switch(status){
				case "1":
					if(gameType=="幸运大转盘"){
						block6=block6_1+"'TurnTable','"+range+"','"+createDate+"','"+createTime+"'"+block6_4;
					}else if(gameType=="飞机大战"){
						block6=block6_1+"'AirPlaneGame','"+range+"','"+createDate+"','"+createTime+"'"+block6_4;
					}else if(gameType=="刮刮乐"){
						block6=block6_1+"'HappyScratch','"+range+"','"+createDate+"','"+createTime+"'"+block6_4;
					}
					else{
						alert("gameType error!");
					}
					break;
				case "2":
					block6=block6_2+"已领"+block6_3;
					break;
				case "3":
					block6=block6_2+"过期"+block6_3;
					break;
			}
			$("#prizeListContent").append(block1+gameType+block2+rangeName+block3+prizeName+block4+deadline+block5+block6);
			
		}
		},
		error: function() {
			alert("请求提交失败！请重试");
			
		}
	});
	
	
}

/* 切换焦点列表 */
function focusList(focusId,removeId){
	$("#"+focusId+"Menu").addClass("focusList");
	$("#"+removeId+"ListContent").addClass("hidden");
	$("#"+removeId+"Menu").removeClass("focusList");
	$("#"+focusId+"ListContent").removeClass("hidden");
}

/* 显示二维码 */
function show2DCode(){
	$("#codeDialog").removeClass("hidden");
	$("#fullBlack").removeClass("hidden");
	$.ajax({
		type: "get",
		url: "../ewCode/getErWeiCodeByAjax.do",
		//cache: false,
		dataType: 'html',
		contentType: 'application/x-www-form-urlencoded',
		success: function(str) {
			$("#codeDialog").html('<img id="codePicSrc" src="../img/default.jpg" alt="二维码显示失败"><label id="codeHint">扫扫上面的二维码，分享给好友</label>');
			$("#codePicSrc").attr("src",str);
		},
		error: function() {
			$("#codeHint").html("<a href='javascript:show2DCode();'>重新获取二维码</a>");
		}
	});
}
function getPrize(gameType,range,createDate,createTime){
	$("#prizeDialog").removeClass("hidden");
	$("#fullBlack").removeClass("hidden");
	$("#gameType").val(gameType);
	$("#range").val(range);
	$("#createDate").val(createDate);
	$("#createTime").val(createTime);
}

/** 通用验证函数 
regStr:正则式
vailStr:待验证字符串
id:效果显示在该id上
*/
function pubValidate(regStr, valiStr) {
var flag = 0;
if (!regStr.test(valiStr)) flag = 1; //test()方法搜索字符串指定的值，根据结果并返回真或假。
else flag = 0;
if (flag) //匹配不正确
	return false;
else //匹配正确
	return true;
}

/** 手机号码验证 */
function validatePhone(id) {
var regStr = /^(13[0-9]|15[012356789]|17[01678]|18[0-9]|14[57])[0-9]{8}$/, //匹配手机号
	idObj = $("#" + id),
	valiStr = idObj.val(),
	result = pubValidate(regStr, valiStr);
if (result) {
	idObj.removeClass("inputError");
	idObj.addClass("inputSussess");
	$("#dialogHint").html("&nbsp;");
} else {
	idObj.removeClass("inputSussess");
	idObj.addClass("inputError");
	$("#dialogHint").text("您输入的手机号码有误！");
}
return result;
}

function closeModel(id) {
	$("#" + id).addClass("hidden");
	$("#fullBlack").addClass("hidden");
	clearDialog();
}
/* 清除用户输入痕迹 */
function clearDialog() {
	$("#prizePhone").val("");
	$("#dialogHint").html("&nbsp;");
	$("#prizePhone").removeClass("inputError");
	$("#prizePhone").removeClass("inputSussess");
}

/* 提交中奖数据  turntable */
function getPrizeInfo() {
	var phoneResult = validatePhone('prizePhone'),
		phone = $("#prizePhone").val(),
		range = $("#range").val(),
		createTime=$("#createTime").val(),
		createDate=$("#createDate").val(),
		gameType=$("#gameType").val(),
		openId = $("#openId").val(),
		urlStr=null;
	if(gameType=="TurnTable"){
		urlStr="../turnTable/getPrizeFromGameIndex.do";
	}
	else if(gameType=="AirPlaneGame"){
		urlStr="../planeGame/getPrize.do";
	}
	else if(gameType=="HappyScratch"){
		urlStr="../happyScratch/getPrize.do";
	}
	if (phoneResult) {
		$.ajax({
			type: "post",
			url: urlStr,
			cache: false,
			dataType: 'json',
			data: {
				"openId": openId,
				"phone": phone,
				"prizeGrade":range,
				"createTime":createTime,
				"createDate":createDate
			},
			contentType: 'application/x-www-form-urlencoded',
			success: function(jsonStr) {
				var msg=jsonStr.retMsg,
					code=jsonStr.retCode;
				switch(code){
				case "0000"://兑换成功
					alert("奖品已通过短信下发至您的手机中，请注意查收。");
					break;
				case "9001":
					alert("兑奖失败！您未中奖。");
					break;
				case "9002":
					alert("兑奖失败！您已领取过奖励。");
					break;
				case "9003":
					alert("兑奖失败！您的奖项已过期。");
					break;
				case "9999":
					alert("系统错误，请联系客服400-055-2797");
					break;
				default:alert("系统维护中，请联系客服400-055-2797");
					break;
				}
				closeModel("prizeDialog");
				getPrizeList();
			},
			error: function() {
				$("#dialogHint").text("兑换请求提交失败，请重试！");

			}
		});
	}
}






submit = false;

function upScore(flag) {
	var score = $("#content").text(), 
		userName = $("#userName").val(), 
		userId = $("#userId").val(),
		best=$("#best").text();
	$.ajax({
		url : "sendGameScore.do",
		type : 'post',
		data : {
			"score" : score,
			"nickname" : userName,
			"openid" : userId
		},
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded',
		cache : false,
		async:false,
		success : function(json) {
			if(parseInt(score)>parseInt(best)){
				$("#best").text(score);
			}
		},
		error : function() {
			alert("请求超时！");
		}
	});
	if (flag) {
		window.location.href = "toPlaneGameIndex.do";
	}
}
function submit() {
	return submit;
}
function start() {
	// alert("start");
}
/** 排行榜 */
function rankList() {
	document.getElementById("fullBlack").setAttribute("class", "");
	document.getElementById("listModel").setAttribute("class", "");
}
/** 关闭弹出框 */
function closeModel(id) {
	document.getElementById(id).setAttribute("class", "hide");
	document.getElementById("fullBlack").setAttribute("class", "hide");
	clearDialog();
}
/** 活动信息 */
function activeInfo() {
	document.getElementById("fullBlack").setAttribute("class", "");
	document.getElementById("infoModel").setAttribute("class", "");
}
/** 仅适用于prizeInfo函数 */
function alertPrizeInfo(info){
	document.getElementById("prizeYesContent").setAttribute("class", "hide");
	document.getElementById("prizeYesBtn").setAttribute("style", "display:none;");
	document.getElementById("prizeBack").setAttribute("class", "hide");
	document.getElementById("sorryText").innerHTML=info;
	document.getElementById("prizeSorry").setAttribute("class", "");
}
/** 领取奖品 */
function prizeInfo() {
	document.getElementById("fullBlack").setAttribute("class", "");
	document.getElementById("prizeModel").setAttribute("class", "");
	var xmlhttp;
	xmlhttp = new XMLHttpRequest();// IE7+
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var jsonStr=JSON.parse(xmlhttp.responseText),
				code=jsonStr.retCode,
				msg=jsonStr.retMsg,
				info="错误的奖项";
			switch(code){
			case "0000"://获奖
				document.getElementById("prizeSorry").setAttribute("class", "hide");
				document.getElementById("prizeBack").setAttribute("class", "hide");
				document.getElementById("prizeYesContent").setAttribute("class", "");
				document.getElementById("prizeYesBtn").setAttribute("style", "");
				switch(msg){
					case "1":info="获得一等奖";break;
					case "2":info="获得二等奖";break;
					case "3":info="获得三等奖";break;
					case "4":info="获得鼓励奖";break;
					default:break;
				}
				document.getElementById("prizeText").innerHTML=info;
				break;
			case "9001":
				alertPrizeInfo("上周未参加游戏");
				break;
			case "9002":
				alertPrizeInfo("您已经领取过奖励，请注意短信查收");
				break;
			case "9003":
				alertPrizeInfo("上周奖励已过期");
				break;
			default:
				alertPrizeInfo("系统维护，请稍后重试");
				break;
			}
		}
	};
	xmlhttp.open("GET", "checkUserPrize.do", true);
	xmlhttp.send();
}

function prize() {
	var userName = document.getElementById("userName").innerText, 
	//prizeIdcard = document.getElementById("prizeIdcard").value,
		prizePhone = document.getElementById("prizePhone").value;
	if(validatePhone("prizePhone")){
		// js原生ajax
	var xmlhttp;
	xmlhttp = new XMLHttpRequest();// IE7+
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var jsonStr=JSON.parse(xmlhttp.responseText),
			code=jsonStr.retCode,
			msg=jsonStr.retMsg;
			document.getElementById("prizeYesContent").setAttribute("class", "hide");
			document.getElementById("prizeYesBtn").setAttribute("style", "display:none;");
			document.getElementById("prizeSorry").setAttribute("class", "hide");
			document.getElementById("prizeBack").setAttribute("class", "");
			switch(code){
			case "0000"://成功兑奖
				document.getElementById("prizeBack").innerHTML="您已成功兑奖，奖励将以短信的形式发送到您填写的手机号中，请注意查收";
				break;
			case "9001":
				document.getElementById("prizeBack").innerHTML="您上周未参加游戏";
				break;
			case "9002":
				document.getElementById("prizeBack").innerHTML="您已经兑换过奖项";
				break;
			case "9003":
				document.getElementById("prizeBack").innerHTML="您上周的奖励已过期";
				break;
			case "9999":
				document.getElementById("prizeBack").innerHTML='请求出错，请联系客服<a href="tel:4000552797">400-055-2797</a>！';
				break;
			default:
				document.getElementById("prizeBack").innerHTML='系统维护，请联系客服<a href="tel:4000552797">400-055-2797</a>！';
				break;
			}
		}
	};
	xmlhttp.open("GET", "../planeGame/getPrize.do?phone=" + prizePhone
			/*+ "&idCard=" + prizeIdcard*/, true);
	xmlhttp.send();
	}
	
}

/** 通用验证函数 
regStr:正则式
vailStr:待验证字符串
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
var regStr = /^(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/, //匹配手机号
	idDom = document.getElementById(id),
	valiStr = idDom.value,
	result = pubValidate(regStr, valiStr);
if (result) {//正确
	idDom.setAttribute("class", "inputSuccess");
	document.getElementById("prizeModelHint").innerHTML="&nbsp;";
} else {
	idDom.setAttribute("class", "inputError");
	document.getElementById("prizeModelHint").innerHTML="您输入的手机号码有误，请重新输入!";
}
return result;
}

/** 15位或18位身份证验证*/
function validateIdcard(id) {
    var regStr = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-2]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    var valiStr = $("#" + id).val();
    var flag = 0;
    if (!regStr.test(valiStr)) {//test()方法搜索字符串指定的值，根据结果并返回真或假。
        flag = 1;//匹配不正确
    }
    else {
        if (valiStr.length == 18) {
            var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2),
            	idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2),
            	idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和
            for (var i = 0; i < 17; i++) {
                idCardWiSum += valiStr.substring(i, i + 1) * idCardWi[i];
            }

            var idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置
            var idCardLast = valiStr.substring(17);//得到最后一位身份证号码
            if (idCardMod == 2) {
                if (idCardLast == "X" || idCardLast == "x") {
                    flag = 0;
                } else {
                    flag = 1;
                }
            } else {
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                if (idCardLast == idCardY[idCardMod]) {
                    flag = 0;
                } else {
                    flag = 1;
                }
            }
            //判断性别位
            var sexBit=valiStr.substring(16, 17);
        }
        else {
        	flag = 0;
            //判断性别位
            var sexBit=valiStr.substring(14);
        }
    }
    if (flag) {//匹配不正确
        return false;
    }
    else {//匹配正确

        return true;
    }
}

/** 清除用户输入痕迹 */
function clearDialog() {
	document.getElementById("prizeModelHint").innerHTML="&nbsp;";
	document.getElementById("prizePhone").setAttribute("class", "");
	document.getElementById("prizePhone").value="";
}
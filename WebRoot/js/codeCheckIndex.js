$(function() {
	var height = $(window).height(),
		width = $(window).width();
	if (width < 380)
		height -= 250; //170px header + 80px footer
	else if (width < 768)
		height -= 245; //180px header + 65px footer
	else
		height -= 194; //129px header + 65px footer
	$(".content").css("min-height", height + "px");
});

/** 倒计时 */
function countDown(time, id) {
	idStr = "#" + id;
	if (time > 0) {
		$(idStr).text("重新充值(" + time + "s)");
		setTimeout(function() {
			countDown(time - 1, id);
		}, 1000);
	} else {
		$("#form").attr("onsubmit", "return control('formBtn');");
		$(idStr).text("确认充值");
	}
}

/** 充值申请次数控制并进行二次验证*/
function control(id) {
	var flag = false,
		time = 25, //重新申请间隔
		valiCodeResult = validateCode("code");
		valiPhoneResult = validatePhone("phone");
	if (valiCodeResult && valiPhoneResult) {
		$("#form").attr("onsubmit", "return false;");
		countDown(time, id);
		flag = true;
	} else {
		if (!valiCodeResult){
			$("#text label").text("您输入的兑换码有误！请验校后重试");
			$("#code").select();
		}
		else{
			$("#text label").text("您输入的手机号有误！请验校后重试");
			$("#phone").select();
		}
			
		$("#codeHint").removeClass("icon-ok");
		$("#codeHint").addClass("icon-no");
	}
	return flag;
}



/** 通用验证函数 
    regStr:正则式
    vailStr:待验证字符串
    id:效果显示在该id上
*/
function pubValidate(regStr, valiStr, id) {
	var flag = 0;
	if (!regStr.test(valiStr)) flag = 1; //test()方法搜索字符串指定的值，根据结果并返回真或假。
	else flag = 0;
	if (flag) { //匹配不正确
		$("#" + id + "Hint").removeClass("icon-ok");
		$("#" + id + "Hint").addClass("icon-no");
		return false;
	} else { //匹配正确
		$("#" + id + "Hint").removeClass("icon-no");
		$("#" + id + "Hint").addClass("icon-ok");
		return true;
	}
}
/** 兑换码长度验证 */
function validateCode(id) {
	var regStr = /^\d{12}$/,
		valiStr = $("#" + id).val(),
		result = pubValidate(regStr, valiStr, id);//验证结果
	return result;
}
/** 手机号码验证 */
function validatePhone(id) {
	var regStr = /^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/, //匹配手机号
		valiStr = $("#" + id).val(),
		result = pubValidate(regStr, valiStr, id);
	return result;
}

function getCodePhone(id) {
	var result,
		code=$("#"+id).val(),
		regStr = /^\d{12}$/,
		valiStr = $("#" + id).val(),
		flag = 0;
	if (!regStr.test(valiStr)) result=false; //test()方法搜索字符串指定的值，根据结果并返回真或假。
	else result=true;
	$("#phone").val("");
	if(result){
		$.ajax({//提交兑换码请求手机号
	        type: 'post',
	        url:'../codeCheck/getPhoneByCode.do',
	        contentType:'application/x-www-form-urlencoded',
	        dataType: 'json',
	        data:{
	        	"code":code
	        	},
	        cache: false,
	        success: function (jsonStr) {
		        	if(jsonStr.phone==""){
		        		$("#text label").text("兑换码校验失败！请重试");
		        		$("#codeHint").removeClass("icon-ok");
		        		$("#codeHint").addClass("icon-no");
		        	}
		        	else{
		        		$("#phone").val(jsonStr.phone);
		        		$("#text label").html("&nbsp;");
		        		$("#codeHint").removeClass("icon-no");
		        		$("#codeHint").addClass("icon-ok");
		        	}
	        },
	        error: function () {
	            alert('请求超时，请重试！');
	        }
	    });
	}
	else{
		$("#text label").text("兑换码有误！请验校后重试");
	}
	
}
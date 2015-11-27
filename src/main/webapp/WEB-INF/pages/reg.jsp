<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>

<!-- style start-->
<jsp:include page="./master/commStyle.jsp" />
<!-- style end-->

</head>

<body class="login-body">

	<div class="container">

		<form class="form-signin" action="${path}/reg" method="post">
			<h2 class="form-signin-heading"><a href="/" style="color:white;">淡香雅</a></h2>
			
			<div class="login-wrap">
				<p>填写您的个人信息</p>
				
            	<input type="text" class="form-control" id="e" name="e" value="${e}" placeholder="邮箱" autofocus>
				
				<div  style="width: 100%;height:40px;margin-bottom: 15px;"> 
					<input type="text" id="imgCode1" name="c"  placeholder="验证码" class="form-control" autocomplete="off" style="width: 60%;float: left;"> 
					
					<button id="sendCode"  class="btn btn-default " type="button" style="margin-left: 30px;">发送</button>
					<label id="sendCodeL" style="display: none;margin-top: 12px;margin-left: 10px;"></label>
				</div>
				
				<input type="password" class="form-control" name="p1" placeholder="密码" autofocus autocomplete="off"> 
				
				<input type="password" class="form-control" name="p2" placeholder="确认密码" autofocus> 
				
				<label class="checkbox" style="display: block;"> 
					
					<span class="pull-left"> 
						有账户，<a  href="/login">登录</a>
					</span>
				</label>
				
				<button class="btn btn-lg btn-login btn-block" type="submit">注册</button>
				
				<p id="error" style="color: red;">${error}&nbsp;</p>

			</div>
			
		</form>

	</div>



	<!-- js placed at the end of the document so the pages load faster -->
	<!--script start-->
	<script src="${path}/static/master/js/jquery.js"></script>
	<script src="${path}/static/master/js/bootstrap.min.js"></script>
	<!--script end-->
	<script type="text/javascript">
		$(function(){
			//1,邮箱验证
			$("#e").blur(function(){
				var e=$("#e").val();
				if(checkEmail(e)){
					$("#error").text("");
					$.post("/chkEPU",{"epu":e},function(json){
						if("1"==json){
							$("#error").text("该邮箱已注册！");
							$("#e").val("");
						}
					});
				}else{
					$("#error").text("邮箱格式错误！");
				}
			});
			//2,发送code
			$("#sendCode").click(function(){
				var e=$("#e").val();
				if(checkEmail(e)){
					$("#error").text("");
					$.post("/chkEPU",{"epu":e},function(json){
						if("0"==json){
							$.post("/getEmailCode",{"e":e},function(obj){
								if("1"==obj){
									$("#error").text("发送成功！");
									$("#sendCode").hide();
									$("#sendCodeL").show();
									setTimeBtn();
								}else{
									$("#error").text("邮件发送失败，请更换邮箱！");
								}
							});
						}else{
							$("#error").text("该邮箱已注册！");
							$("#e").val("");
						}
					});
				}else{
					$("#error").text("邮箱格式错误！");
				}
			});
			//
			
		});
		
		var count=60;
		var timer01=null;
		function setTimeBtn(){
			timer01 = setInterval(function(){
				$("#sendCodeL").text(count+"秒后再次点击");
				if(count<1){
					$("#sendCode").show();
					$("#sendCodeL").hide();
					clearInterval(timer01);
					count=60;
				}
				count--;
			}, 1000);
		}
		
		function checkEmail(str){
    		var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		    if(re.test(str)){
		    	return true;
		    }
		    return false;
		}
	</script>


</body>
</html>

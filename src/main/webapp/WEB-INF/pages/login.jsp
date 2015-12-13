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

		<div class="form-signin" >
			<h2 class="form-signin-heading"><a href="/" style="color:white;">淡香雅</a></h2>
			
			<div class="login-wrap">
				<p id="error" style="color: red;">${error}&nbsp;</p>
				
				<input type="text" id="u1" name="u1" value="${u}" class="form-control" placeholder="账户" autofocus> 
				
				<input type="password" id="p1" name="p1" class="form-control" placeholder="密码"> 
				
				<form  action="${path}/login" method="post">
					<input type="hidden" name="u" id="u"> 
					<input type="hidden" name="p" id="p"> 
				
					<div  style="width: 100%;height:40px;margin-bottom: 15px;"> 
						<input type="text" id="imgCode1" name="x"  placeholder="验证码" class="form-control" style="width: 50%;float: left;"> 
						<i id="xFa1" class="fa " style="margin-top: 12px;"></i>
						<img id="xCode1" src="/getImgCode" style="display:block; float: right;cursor: pointer;">
					</div>
					
					<label class="checkbox" style="display: block;"> 
						<span class="pull-left"> 
							没账户，<a href="/reg">注册</a>
						</span>
						
						<span class="pull-right"> 
							<a data-toggle="modal" href="#myModal">忘记密码?</a>
						</span>
					</label>
					
					<button class="btn btn-lg btn-login btn-block" onmouseover="chkJiami()" onkeypress="chkJiami()" type="submit">登录</button>
				</form>
				
				<div class="registration" style="text-align: center;">
					
					<!-- 
					<a class="" href="${path}/sinaloginPage"><i class="fa fa-weibo"></i>微博登录</a>
						&nbsp;
					<a class="" href="javascript:;" id="qqLoginBtn"><i class="fa  fa-qq"></i>QQ登录</a>
						&nbsp;
					<a class="" href="${path}/qqloginPage"><i class="fa  fa-qq"></i>QQ</a>
					&nbsp;
					<a class="" href=""><i class="fa fa-weixin"></i>微信</a>
					 -->	                	
            	</div>
            

			</div>

			<!-- Modal -->
			<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog"
				tabindex="-1" id="myModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title">忘记了密码?</h4>
						</div>
						<div class="modal-body">
							<p>请输入您注册使用的邮箱地址</p>
							<input type="text" id="femail" placeholder="邮箱账户" autocomplete="off" class="form-control placeholder-no-fix">
							
							<div  style="width: 100%;height:40px;margin-bottom: 15px;"> 
								<input type="text" id="FimgCode2"  placeholder="验证码" class="form-control" style="width: 50%;float: left;"> 
								<i id="xFa2" class="fa " style="margin-top: 12px;"></i>
								<img id="xCode2" src="${path}/getImgCode" style="display:block; float: right;cursor: pointer;margin-right: 10px;">
							</div>
							
							<span style="color: red;" id="fmsg"></span>
						</div>
						<div class="modal-footer">
							<button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
							<button id="fbtn"  class="btn btn-success" type="button">提交</button>
						</div>
					</div>
				</div>
			</div>
			<!-- modal -->

		</div>

	</div>



	<!-- js placed at the end of the document so the pages load faster -->
	<!--script start-->
	<script src="${path}/static/master/js/jquery.js"></script>
	<script src="${path}/static/master/js/bootstrap.min.js"></script>
	<script src="${path}/static/aatjs/Atools.js"></script>
	<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js"  charset="utf-8"></script>
	<!--script end-->
	<script type="text/javascript">
		
		function jsjiami(){
			$("#u1").bind('input propertychange',function(){
				var v=$(this).val();
				$("#u").val(jsJiaMi(v));
			});
			$("#p1").bind('input propertychange',function(){
				var v=$(this).val();
				$("#p").val(jsJiaMi(v));
			});
		}
		
		function chkJiami(){
			$("#u").val(jsJiaMi($("#u1").val()));
			
			$("#p").val(jsJiaMi($("#p1").val()));
		}
	
		$(function(){
			//
			jsjiami();
			//找回密码
			$("#fbtn").click(function(){
				var vc2=$("#FimgCode2").val();
				$.post("/chkImgCode",{"x":vc2},function(json){
					if("1"==json){
						$("#xFa2").removeClass('fa-remove');
						$("#xFa2").addClass('fa-check');
						$("#fmsg").text("");
						//
						var femail=$("#femail").val();
						if(checkEmail(femail)){
							$.post("/chkEPU",{"epu":femail},function(json){
								if("1"==json){
									$("#fmsg").text("发送成功，请登录您的邮箱去验证。");
									$.post("/getEmailLink",{"e":femail});
									setTimeout(function(){
										window.location.reload(true);
									}, 1500);
								}else{
									$("#fmsg").text("该邮箱未注册！");
								}
								
							});
						}else{
							$("#fmsg").text("邮箱格式错误！");
						}
						//
					}else{
						$("#xFa2").removeClass('fa-check');
						$("#xFa2").addClass('fa-remove');
						var r=Math.random()*1000;
						$("#xCode2").attr({"src":"${path}/getImgCode?id="+r});
						$("#fmsg").text("您输入的验证码错误！");
					}
				});
				
			});
			
			//验证code
			$("#imgCode1").blur(function(){
				$.post("/chkImgCode",{"x":$("#imgCode1").val()},function(json){
					if("1"==json){
						$("#xFa1").removeClass('fa-remove');
						$("#xFa1").addClass('fa-check');
						$("#error").text("");
					}else{
						$("#error").text("验证码错误");
						$("#xFa1").removeClass('fa-check');
						$("#xFa1").addClass('fa-remove');
						var r=Math.random()*1000;
						$("#xCode1").attr({"src":"${path}/getImgCode?id="+r});
					}
				});
			});
			
			$("#FimgCode2").blur(function(){
				$.post("/chkImgCode",{"x":$("#FimgCode2").val()},function(json){
					if("1"==json){
						$("#xFa2").removeClass('fa-remove');
						$("#xFa2").addClass('fa-check');
						$("#fmsg").text("");
					}else{
						$("#xFa2").removeClass('fa-check');
						$("#xFa2").addClass('fa-remove');
						var r=Math.random()*1000;
						$("#xCode2").attr({"src":"${path}/getImgCode?id="+r});
					}
				});
			});
			
			//改变图片
			$("#xCode1").click(function(){
				var r=Math.random()*1000;
				$("#xCode1").attr({"src":"${path}/getImgCode?id="+r});
			});
			$("#xCode2").click(function(){
				var r=Math.random()*1000;
				$("#xCode2").attr({"src":"${path}/getImgCode?id="+r});
			});
			//QQ login
			$("#qqLoginBtn").click(function(){
				QC.Login.showPopup({
					appId:"101241096",
					redirectURI:"http://test.dizena.com/qqlogin"
				});
			});
			//
			
			setInterval(function(){
				$.post("${path}/chkLoginJson",function(json){
					if(json>0){
						window.location.href="${path}/master";
					}
				});
			}, 1000);
		});
		
		//
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

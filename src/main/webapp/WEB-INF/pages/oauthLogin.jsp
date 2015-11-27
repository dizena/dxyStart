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

		<form class="form-signin" action="${path}/oauth/oauthLogin" method="post">
			<h2 class="form-signin-heading">授权登录</h2>
			<input type="hidden" name="id"  value="${oc.id}">
			<div class="login-wrap">
				<p>使用你的帐号访问:<a href="${oc.eeurl}" target="_blank" style="color:#F67A6E;">${oc.eename}</a></p>
				
				<input type="text" name="u" value="${u}" class="form-control" placeholder="账户" autofocus> 
				
				<input type="password" name="p" class="form-control" placeholder="密码"> 
				
				<button class="btn btn-lg btn-login btn-block" type="submit">登录</button>
			
				<p id="error" style="color: red;">${error}&nbsp;</p>
				
				<div class="registration" style="text-align: center;">
                	
            	</div>
            

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
			
		});
		
	</script>


</body>
</html>

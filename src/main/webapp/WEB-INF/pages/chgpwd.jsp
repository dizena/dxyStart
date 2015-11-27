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

		<form class="form-signin" action="${path}/chgpwd" method="post">
			<h2 class="form-signin-heading">设置密码</h2>
			
			<div class="login-wrap">
				<p id="error" style="color: red;">${error}&nbsp;</p>
				
				<input type="hidden" class="form-control" name="id" value="${id}" readonly="readonly">
				
				<input type="password" class="form-control" name="p1" placeholder="密码" autofocus autocomplete="off"> 
				
				<input type="password" class="form-control" name="p2" placeholder="确认密码" autofocus autocomplete="off"> 
				
				<button class="btn btn-lg btn-login btn-block" type="submit" >提交</button>
				
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

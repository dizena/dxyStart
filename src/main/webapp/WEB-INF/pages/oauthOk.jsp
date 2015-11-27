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
			<h2 class="form-signin-heading">授权访问</h2>
			<input type="hidden" name="id"  value="${oc.id}">
			<div class="login-wrap">
				<p>
					<img alt="" src="${oc.eelogo}">
				</p>
				<p>使用你的帐号访问:<a href="${oc.eeurl}" target="_blank" style="color:#F67A6E;">${oc.eename}</a></p>
				
				<p>请您确认</p>
				
				<button class="btn btn-lg btn-login btn-block" type="submit">确认</button>
			
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

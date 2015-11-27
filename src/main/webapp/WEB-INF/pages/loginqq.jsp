<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>

</head>

<body>
	<span id="qqLoginBtn"></span>
	<!--script start-->
	<script src="${path}/static/master/js/jquery.js"></script>
	<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-callback="true" data-appid="101241096" data-redirecturi="http://test.dizena.com/qqlogin" charset="utf-8"></script>
	<!--script end-->
	<script type="text/javascript">
	//输出属性  data-callback="true" data-appid="101241096" data-redirecturi="http://test.dizena.com/qqlogin"
	function echoAttr(myObject) {
		var s = "";
		for ( var property in myObject) {
			s = s + "\n " + property + ": " + myObject[property];
		}
		alert(s);
	}

		$(function(){
			//调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中
			
			QC.Login({
				btnId : "qqLoginBtn",
				scope : "all",
				size : "A_XL"
			}, function(reqData, opts) {
				var ps={};
					ps.n=reqData.nickname;
					ps.s=reqData.gender;
					ps.a=reqData.figureurl_qq_2;
					ps.l=reqData.province+","+reqData.city;
				QC.Login.getMe(function(openId, accessToken){
					ps.id=openId;
					ps.t=accessToken;
					$.post("${path}/qqlogin",ps,function(json){
						if("1"==json){
							//window.location.href="/";
						}
					});
				});
				
			});
			//
			if (QC.Login.check()) {

			}
			//

		});
	</script>
</body>
</html>

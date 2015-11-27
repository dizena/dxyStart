<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.dizena.com/api/dxyfun" prefix="dxy"%>

<!DOCTYPE html>
<html lang="cn">
<head>

<!-- style start-->
<jsp:include page="../commStyle.jsp" />
<!-- style end-->
<link href="${path}/static/keditor/themes/default/default.css" rel="stylesheet" />

</head>

<body>

	<section id="container" class="">

		<!--header start-->
		<jsp:include page="../commHeader.jsp" />
		<!--header end-->


		<!--sidebar start-->
		<jsp:include page="../commNav.jsp" />
		<!--sidebar end-->


		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<!-- page start-->
				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading">${title}</header>
							<div class="panel-body">
								<div style="color: red;text-align: center;height: 30px;line-height: 30px;">
									${error}
								</div>
								<div class=" form">
									<form class="cmxform form-horizontal tasi-form" id="commentForm" method="post" action="${path}/${action}">
										<input type="hidden" name="id" value="${user.id}">
										<div class="form-group ">
											<label for="username" class="control-label col-lg-2">账号(<b style="color: red;">*</b>)</label>
											<div class="col-lg-10">
												<input class=" form-control" id="username" name="username"  value="${user.username}" minlength="6" type="text" required />
											</div>
										</div>
										<div class="form-group ">
											<label for="passwd" class="control-label col-lg-2">密码(<b style="color: red;">*</b>)</label>
											<div class="col-lg-4">
												<input class="form-control " id="passwd" name="passwd" type="password" minlength="6" />
											</div>
										
											<label for="salt" class="control-label col-lg-1">再输入(<b style="color: red;">*</b>)</label>
											<div class="col-lg-5">
												<input class="form-control " id="salt" name="salt" type="password" minlength="6" />
											</div>
										</div>
										
										<div class="form-group ">
											<label for="roles" class="control-label col-lg-2">角色</label>
											<div class="col-lg-4">
												<select class="form-control " id="roles" name="roles" >
													<option <c:if test="${user.roles=='master'}">selected="selected"</c:if> value="master">管理员</option>
													<option <c:if test="${user.roles=='user'}">selected="selected"</c:if> value="user">用户</option>
													<option <c:if test="${user.roles=='pku'}">selected="selected"</c:if> value="pku">北大</option>
												</select>
											</div>
											
											<label for="auths" class="control-label col-lg-1">权限</label>
											<div class="col-lg-5">
												<input class="form-control " id="auths" name="auths" type="text" value="${user.auths}"/>
											</div>
										</div>
										
										<div class="form-group ">
											<label for="nickname" class="control-label col-lg-2">昵称</label>
											<div class="col-lg-4">
												<input class="form-control " id="nickname" name="nickname" type="text" value="${user.nickname}"/>
											</div>
											
											<label for="sex" class="control-label col-lg-1">性别</label>
											<div class="col-lg-5">
												<select class="form-control " id="sex" name="sex" >
													<option <c:if test="${user.sex=='男'}">selected="selected"</c:if> value="男">男</option>
													<option <c:if test="${user.sex=='女'}">selected="selected"</c:if> value="女">女</option>
												</select>
											</div>
										</div>
										
										<div class="form-group ">
											<label for="icon" class="control-label col-lg-2">头像</label>
											<div class="col-lg-8">
												<input class="form-control " id="icon" name="icon" type="text" value="${user.icon}"/>
											</div>
											<div class="col-lg-2">
												<button id="upimgUrl" type="button" class="btn btn-success">上传100*100</button>
											</div>
										</div>
										<div class="form-group ">
											<label for="location" class="control-label col-lg-2">联系地址</label>
											<div class="col-lg-10">
												<input class="form-control " id="location" name="location" type="text" value="${user.location}"/>
											</div>
											
										</div>
										
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<button class="btn btn-danger" type="submit">提交</button>
												<button class="btn btn-default" type="reset">重置</button>
												<button class="btn btn-info" type="reset" onclick="window.history.back();" >返回</button>
											</div>
										</div> 
									</form>
								</div>

							</div>
						</section>
					</div>
				</div>
				<!-- page end-->
			</section>
		</section>
		<!--main content end-->


		<!--footer start-->
		<jsp:include page="../commFooter.jsp" />
		<!--footer end-->
	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<!--script start-->
	<jsp:include page="../commScript.jsp" />
	
	<script charset="utf-8" src="${path}/static/keditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="${path}/static/keditor/zh_CN.js"></script>
	<!--script end-->
	<script type="text/javascript">
		KindEditor.ready(function(K) {
			var editor = K.editor({
				uploadJson : "${path}/upload/upload_json.do",
				fileManagerJson : "${path}/upload/file_manager_json.do",
				allowFileManager : true,
				allowImageUpload : true
			});
		
			K('#upimgUrl').click(function() {
				editor.loadPlugin('image', function() {
					editor.plugin.imageDialog({
						imageUrl : K('#icon').val(),
						clickFn : function(url, title, width, height, border, align) {
							K('#icon').val(url);
							editor.hideDialog();
						}
					});
				});
			});
			//
		});
		
		function loadRoles(){
			var r="${user.roles}";
			$.post("${path}/master/roleLists",{"p":100},function(json){
				var html='';
				$.each(json.datas,function(i,d){
					if(""!=r && r==d.role){
						html+='<option selected="selected" value="'+d.role+'">'+d.state+'</option>';
					}else{
						html+='<option  value="'+d.role+'">'+d.state+'</option>';
					}
				});
				$("#roles").html(html);
			})
		}
		
		$(function(){
			loadRoles();
		});
	</script>

</body>
</html>

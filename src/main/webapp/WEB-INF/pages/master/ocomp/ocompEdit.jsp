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
										<input type="hidden" name="id" value="${b.id}">
										
										
										<div class="form-group ">
											<label for="eename" class="control-label col-lg-2">企业(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<input class=" form-control" id="eename" name="eename" value="${b.eename}" type="text" required />
											</div>
										</div>
										
										<div class="form-group ">
											<label for="imgUrl" class="control-label col-lg-2">LOGO(<b style="color: red;">*</b>)</label>
											<div class="col-lg-5">
												<input class="form-control " id="imgUrl" name="eelogo" value="${b.eelogo}" type="text"  required/>
											</div>
											<div class="col-lg-1">
												<button id="upimgUrl" type="button" class="btn btn-success">上传</button>
											</div>
										</div>
										
										<div class="form-group ">
											<label for="eeurl" class="control-label col-lg-2">网址(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<input class=" form-control" id="eeurl" name="eeurl" value="${b.eeurl}" type="text" required />
											</div>
										</div>
										
										<div class="form-group ">
											<label for="resposeUrl" class="control-label col-lg-2">返回地址(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<input class=" form-control" id="resposeUrl" name="resposeUrl" value="${b.resposeUrl}" type="text" required />
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-6">
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
	<!-- style end-->
	<link href="${path}/static/keditor/themes/default/default.css" rel="stylesheet" />
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
						imageUrl : K('#imgUrl').val(),
						clickFn : function(url, title, width, height, border, align) {
							K('#imgUrl').val(url);
							editor.hideDialog();
						}
					});
				});
			});
			//
		});
		$(function(){
			
		});
	</script>

</body>
</html>

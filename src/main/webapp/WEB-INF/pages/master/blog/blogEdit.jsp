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
											<label for="title" class="control-label col-lg-2">标题(<b style="color: red;">*</b>)</label>
											<div class="col-lg-10">
												<input class=" form-control" id="title" name="title" value="${b.title}" type="text" required />
											</div>
										</div>
										<div class="form-group ">
											<label for="imgUrl" class="control-label col-lg-2">封面(<b style="color: red;">*</b>)</label>
											<div class="col-lg-8">
												<input class="form-control " id="imgUrl" name="imgUrl" value="${b.imgUrl}" type="text"  required/>
											</div>
											<div class="col-lg-2">
												<button id="upimgUrl" type="button" class="btn btn-success">上传</button>
											</div>
										</div>
										<div class="form-group ">
											<label for="ctype" class="control-label col-lg-2">分类(<b style="color: red;">*</b>)</label>
											<div class="col-lg-4">
												<select class="form-control " id="ctype" name="ctype">
													<option <c:if test="${b.ctype=='1'}">selected="selected"</c:if> value="1">文化</option>
													<option <c:if test="${b.ctype=='2'}">selected="selected"</c:if> value="2">科技</option>
													<option <c:if test="${b.ctype=='3'}">selected="selected"</c:if> value="3">金融</option>
													<option <c:if test="${b.ctype=='7'}">selected="selected"</c:if> value="7">养生</option>
													<option <c:if test="${b.ctype=='8'}">selected="selected"</c:if> value="8">旅行</option>
													<option <c:if test="${b.ctype=='4'}">selected="selected"</c:if> value="4">新闻</option>
													<option <c:if test="${b.ctype=='5'}">selected="selected"</c:if> value="5">视频</option>
													<option <c:if test="${b.ctype=='6'}">selected="selected"</c:if> value="6">约会</option>
												</select>
											</div>
										
											<label for="tags" class="control-label col-lg-1">标签(<b style="color: red;">*</b>)</label>
											<div class="col-lg-5">
												<input class="form-control " id="tags" name="tags" value="${b.tags}" type="text" required/>
											</div>
										</div>
										
										<div class="form-group ">
											<label for="content" class="control-label col-lg-2">内容(<b style="color: red;">*</b>)</label>
											<div class="col-lg-10">
												<textarea id="content" name="content" class=" form-control" style="width: 98%;height: 300px;" >${b.content}</textarea>
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
						imageUrl : K('#imgUrl').val(),
						clickFn : function(url, title, width, height, border, align) {
							K('#imgUrl').val(url);
							editor.hideDialog();
						}
					});
				});
			});
			//
			K.create('textarea[id="content"]', {
				uploadJson : "${path}/upload/upload_json.do",
				fileManagerJson : "${path}/upload/file_manager_json.do",
				allowFileManager : true
			});
			//
		});
		
		$(function(){
			
		});
	</script>

</body>
</html>

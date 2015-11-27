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
										<input type="hidden" name="id" value="${m.id}">
										
										<div class="form-group ">
											<label for="resName" class="control-label col-lg-2">名称(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<input class=" form-control" id="resName" name="resName"  value="${m.resName}"  type="text" required tabindex="1" />
											</div>
											
										</div>
										
										<div class="form-group ">
			
											<label for="resUrl" class="control-label col-lg-2">链接(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<input class=" form-control" id="resUrl" name="resUrl"  value="${m.resUrl}"  type="text" required tabindex="2"/>
											</div>
											
										</div>
										
										<div class="form-group ">
											<label for="resType" class="control-label col-lg-2">类型</label>
											<div class="col-lg-6">
												<select class="form-control " id="resType" name="resType" tabindex="3">
													<option <c:if test="${m.resType=='1'}">selected="selected"</c:if> value="1">菜单</option>
													<option <c:if test="${m.resType=='2'}">selected="selected"</c:if> value="2">合作单位</option>
													<option <c:if test="${m.resType=='3'}">selected="selected"</c:if> value="3">友情链接</option>
													<option <c:if test="${m.resType=='4'}">selected="selected"</c:if> value="4">广告</option>
													<option <c:if test="${m.resType=='5'}">selected="selected"</c:if> value="5">滚动图</option>
													<option <c:if test="${m.resType=='6'}">selected="selected"</c:if> value="6">首页滚动</option>
												</select>
											</div>
											
											
										</div>
										
										
										<div class="form-group " id="levelDIV1">
											<label for="resLevel" class="control-label col-lg-2">级别</label>
											<div class="col-lg-6">
												<select class="form-control " id="resLevel" name="resLevel" >
													<option <c:if test="${m.resLevel=='1'}">selected="selected"</c:if> value="1">一级</option>
													<option <c:if test="${m.resLevel=='2'}">selected="selected"</c:if> value="2">二级</option>
													<option <c:if test="${m.resLevel=='3'}">selected="selected"</c:if> value="3">三级</option>
												</select>
											</div>
										
										</div>
										
										<div class="form-group " id="levelDIV2">
										
											<label for="resPid" class="control-label col-lg-2">父级(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<select class="form-control " id="resPid" name="resPid" >
													
												</select>
											</div>
										</div>
										
										<div class="form-group ">
											
											<label for="resSort" class="control-label col-lg-2">序号(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<input class="form-control " id="resSort" name="resSort" value="${m.resSort}" type="text" tabindex="4"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" />
											</div>
											
										</div>
										
										<div class="form-group ">
											<label for="resAuth" class="control-label col-lg-2">权限</label>
											<div class="col-lg-6">
												<input class=" form-control" id="resAuth" name="resAuth"  value="${m.resAuth}"  type="text"  />
											</div>
			
											
										</div>
										
										<div class="form-group ">
			
											<label for="resIcon" class="control-label col-lg-2">图标</label>
											<div class="col-lg-6">
												<input class=" form-control" id="resIcon" name="resIcon"  value="${m.resIcon}"  type="text"  />
											</div>
											<div class="col-lg-1" id="resIconShow">
												<c:if test="${m.resType=='1'}">
													<i class="${m.resIcon}"></i>
												</c:if>
												<c:if test="${m.resType!='1'}">
													<img src="${m.resIcon}" width="81px" height="26px" />
												</c:if>
											</div>
											<div class="col-lg-1" id="upbtnDIV2">
												<button id="upimgUrl" type="button" class="btn btn-success">上传</button>
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
	<!--script end-->
	<!-- style end-->
	
	<script charset="utf-8" src="${path}/static/keditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="${path}/static/keditor/zh_CN.js"></script>
	<link href="${path}/static/keditor/themes/default/default.css" rel="stylesheet" />
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
						imageUrl : K('#resIcon').val(),
						clickFn : function(url, title, width, height, border, align) {
							K('#resIcon').val(url);
							chgIcon();
							editor.hideDialog();
						}
					});
				});
			});
			//
		});
		
		//菜单类型改变-级别
		function chgLevel(v){
			if("1"!=v){
				$("#levelDIV1").hide();
				$("#levelDIV2").hide();
				$("#upbtnDIV2").show();
			}else{
				$("#levelDIV1").show();
				$("#levelDIV2").show();
				$("#upbtnDIV2").hide();
			}
		}
		
		//级别类型改变-父级
		function chgPid(p){
			var v=$("#resType").val();
			var l=$("#resLevel").val();
			$.post("${path}/master/queryParentRes",{"t":v,"l":l},function(json){
				var html='';
				$.each(json,function(i,d){
					if(p==d.id){
						html+='<option selected="selected" value="'+d.id+'">'+d.resName+'</option>';
					}else{
						html+='<option value="'+d.id+'">'+d.resName+'</option>';
					}
				});
				$("#resPid").html(html);
				//
				chgSort();
			});
		}
		
		//父级类型改变-序变
		function chgSort(){
			var v=$("#resType").val();
			var l=$("#resLevel").val();
			var p=$("#resPid").val();
			if("1"!=v){
				l="1";
				p="0";
			}
			//序号改变
			$.post("${path}/master/getResSort",{"t":v,"l":l,"p":p},function(json){
				$("#resSort").val(json);
			});
		}
		
		//图标改变
		function chgIcon(){
			var v=$("#resType").val();
			if("1"==v){
				var v1=$("#resIcon").val();
				$("#resIconShow").html('<i class="'+v1+'"></i>');
			}else{
				var v1=$("#resIcon").val();
				$("#resIconShow").html('<img src="'+v1+'" width="81px" height="26px" />');
			}
		}
		
		$(function(){
			//0,类型默认值
			{
				var v="${m.resType}";
				var p="${m.resPid}";
				chgLevel(v);
				chgPid(p);
			}
			
			
			
			//1，类型选择变化
			$("#resType").change(function(){
				var v=$("#resType").val();
				chgLevel(v);
				chgSort();
			});
			
			//2,级别选择变化
			$("#resLevel").change(function(){
				chgPid("0");
			});
			
			//3,父级选择变化
			$("#resPid").change(function(){
				chgSort();
			});
			
			//4,获得焦点
			$("#resSort").focus(function(){
				chgSort();
			});
			
			//5,图标变化
			$("#resIcon").change(function(){
				chgIcon();
			});
			
		});
	</script>

</body>
</html>

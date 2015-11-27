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
										<input type="hidden" name="id" value="${p.id}">
										
										<div class="form-group ">
											<label for="uname" class="control-label col-lg-2">名称(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<input class=" form-control" id="uname" name="uname"  value="${p.uname}"  type="text" required tabindex="1" />
											</div>
											
										</div>
										
										<div class="form-group ">
			
											<label for="url" class="control-label col-lg-2">url路径(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<input class=" form-control" id="url" name="url"  value="${p.url}"  type="text" required tabindex="2"/>
											</div>
										</div>
										
										<div class="form-group ">
											<label for="roles" class="control-label col-lg-2">所需角色(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<input class=" form-control" id="roles" name="roles"  value="${p.roles}"  type="text" required tabindex="3" />
											</div>
											
										</div>
										
										<div class="form-group ">
			
											<label for="auths" class="control-label col-lg-2">所需权限(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<input class=" form-control" id="auths" name="auths"  value="${p.auths}"  type="text" required tabindex="4"/>
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
	
	<script type="text/javascript">
		
	</script>

</body>
</html>

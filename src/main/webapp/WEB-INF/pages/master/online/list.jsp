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

					<div class="col-lg-12 ">
						<section class="panel panel-success table-responsive">
							<header class="panel-heading">
                             	 在线用户
                          	</header>
							<table class="table table-striped table-advance table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>SID</th>
										<th>用户</th>
										<th>IP</th>
										<th>在线时间</th>
										
										<th>最后访问时间</th>
										<th>是否强制退出</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="datasTabDiv">
									
								</tbody>
							</table>
							<div class="alert-info text-center">
                                <ul class="pagination" id="splitPages">
                                  	  
                                </ul>
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
		//加载函数
		function loadTab(s){
			var url="${path}/master/online/list";
			var ps={"s":s};
			$.post(url,ps,function(json){
				
				if(json.count>0){
					//有数据
					var html="";
					$.each(json.datas,function(i,d){
						html+='<tr>';
						html+='<td>'+(i+1)+'</td>';
						html+='<td>'+(d.id)+'</td>';
						html+='<td>'+(d.uname)+'</a></td>';
						html+='<td>'+(d.host)+'</td>';
						html+='<td>'+(d.onlineTime)+'</td>';
						html+='<td>'+(d.lastTime)+'</td>';
						
						if("1"==d.isOut){
							html+='<td><span class="label label-success label-mini">是</span></td>';
						}else{
							html+='<td><span class="label label-danger  label-mini">否</span></td>';
						}
						
						html+='<td>';
						if("1"==d.isOut){
						}else{
							html+='<a href="${path}/master/online/forceLogout/'+(d.id)+'" class="btn btn-primary  btn-xs"><i class="fa fa-pencil"></i>&nbsp;强制退出</a>&nbsp;';
						}
						html+='</td>';
						html+='</tr>';
					});
					$("#datasTabDiv").html(html);
				}else{
					//没有数据
					var html='<tr>';
					html+='<td colspan="8" align="center">';
					html+='<b style="color:red;">没有数据</b>';
					html+='</td>';
					html+='</tr>';
					$("#datasTabDiv").html(html);
				}
				splitPages(json.pages,json.start);
			});
		}
		
		$(function(){
			loadTab(1);
		});
	</script>

</body>
</html>

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
						<div class="panel">
							<div class="panel-body">

								<div class="form-inline">
									<div class="form-group">
										<label class="sr-only" for="type">类型</label> 
										<select class="form-control" id="type" name="type">
											<option value="">全部</option>
											<option value="1">菜单</option>
											<option value="2">合作单位</option>
											<option value="3">友情链接</option>
											<option value="4">广告</option>
											<option value="5">滚动图</option>
											<option value="6">首页滚动</option>
										</select>
									</div>
									
									<div class="form-group">
										<label class="sr-only" for="level">级别</label> 
										<select class="form-control" id="level" name="level">
											<option value="">全部</option>
											<option value="1">一级</option>
											<option value="2">二级</option>
											<option value="3">三级</option>
										</select>
									</div>
									<div class="form-group">
										<label class="sr-only" for="name">名称</label> 
										<input class="form-control" id="name" name="name" placeholder="名称">
									</div>
									
									<div class="form-group">
										<button onclick="loadTab(1)" class="btn btn-danger " type="button"><i class="fa fa-search">&nbsp;搜索</i></button>
										
										<a class="btn btn-primary" href="/master/addRess">增加&nbsp;<i class="fa fa-plus"></i></a>
									</div>
								</div>

  							</div>
						</div>
					</div>

					<div class="col-lg-12 ">
						<section class="panel panel-success table-responsive">
							<header class="panel-heading">
                             	 资源管理
                          	</header>
							<table class="table table-striped table-advance table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>名称</th>
										<th>链接</th>
										<th>图标</th>
										<th>排序</th>
										<th>状态</th>
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
		
		//
		function del(id){
			if(confirm("确认删除吗?")){
				window.location.href="${path}/master/delRess/"+id;
			}
		}
		
		function lock(id){
			if(confirm("确认执行吗?")){
				window.location.href="${path}/master/lockRess?id="+id+"&s=1";
			}
		}
		
		function unlock(id){
			if(confirm("确认执行吗?")){
				window.location.href="${path}/master/lockRess?id="+id+"&s=0";
			}
		}
		
		//加载函数
		function loadTab(s){
			var url="${path}/master/ressLists";
			var ps={"s":s};
				ps.type=$("#type").val();
				ps.level=$("#level").val();
				ps.name=$("#name").val();
				
			$.post(url,ps,function(json){
				if(json.count>0){
					//有数据
					var html="";
					$.each(json.datas,function(i,d){
						html+='<tr>';
						html+='<td>'+(i+1)+'</td>';
						html+='<td>'+(d.resName)+'</td>';
						html+='<td>'+(d.resUrl)+'</td>';
						
						if("1"==d.resType){
							html+='<td><i class="'+(d.resIcon)+'" ></i></td>';
						}else{
							html+='<td><img src="'+(d.resIcon)+'"  width="81px" height="26px" /> </td>';
						}
						
						
						
						html+='<td>'+(d.resSort)+'</td>';
						
						if(0==d.locked){
							html+='<td><span class="label label-success label-mini">正常</span></td>';
						}else{
							html+='<td><span class="label label-danger  label-mini">锁定</span></td>';
						}
						
						html+='<td>';
						if("0"==d.locked){
							html+='<button onclick="lock(\''+(d.id)+'\')" class="btn btn-warning btn-xs"><i class="fa fa-lock"></i>&nbsp;锁定</button>&nbsp;';
						}else{
							html+='<button onclick="unlock(\''+(d.id)+'\')" class="btn btn-success btn-xs"><i class="fa fa-unlock"></i>&nbsp;解锁</button>&nbsp;';
						}
						
						html+='<a href="${path}/master/uptRess/'+(d.id)+'" class="btn btn-primary  btn-xs"><i class="fa fa-pencil"></i>&nbsp;编辑</a>&nbsp;';
						html+='<button onclick="del(\''+(d.id)+'\')" class="btn btn-danger  btn-xs"><i class="fa fa-trash "></i>&nbsp;删除</button>&nbsp;';
						html+='</td>';
						html+='</tr>';
					});
					$("#datasTabDiv").html(html);
				}else{
					//没有数据
					var html='<tr>';
					html+='<td colspan="7" align="center">';
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

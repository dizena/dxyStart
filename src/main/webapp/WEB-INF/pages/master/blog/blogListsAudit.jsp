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
											<option value="1">文化</option>
											<option value="2">科技</option>
											<option value="3">金融</option>
											<option value="7">养生</option>
											<option value="8">旅行</option>
											<option value="4">新闻</option>
											<option value="5">视频</option>
											<option value="6">约会</option>
										</select>
									</div>
									<div class="form-group">
										<label class="sr-only" for="type">状态</label> 
										<select class="form-control" id="status" name="status">
											<option value="">全部</option>
											<option value="0">未审核</option>
											<option value="1">不通过</option>
											<option value="2">审核通过</option>
											<option value="3">发布</option>
										</select>
									</div>
									<div class="form-group">
										<label class="sr-only" for="type">标题</label>
										<input type="text" class="form-control" id="title" name="title" placeholder="标题"> 
									</div>
									
									<div class="form-group">
										<button onclick="loadTab(1)" class="btn btn-danger" >查询&nbsp;<i class="fa fa-search"></i></a>
									</div>
									
  								</div>
  							</div>
						</div>
					</div>
						
					<div class="col-lg-12">
						<section class="panel panel-success table-responsive">
							<header class="panel-heading">
                             	 博文管理
                          	</header>
							<table class="table table-striped table-advance table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>标题</th>
										<th>类型</th>
										<th>封面</th>
										<th>时间</th>
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
	<script src="${path}/static/aatjs/myfun.js"></script>
	<!--script end-->
	<script type="text/javascript">
	
		//加载函数
		var TYPE={"1":"文化","2":"科技","3":"金融","4":"新闻","5":"视频","6":"约会","7":"养生","8":"旅行"};
		function loadTab(s){
			var url="${path}/master/blog/listBlogs";
			var ps={"s":s};
				ps.type=$("#type").val();
				ps.status=$("#status").val();
				ps.title=$("#title").val();
			var html="";
			$.post(url,ps,function(json){
				if(json.count>0){
					var html='';
					$.each(json.datas,function(i,d){
						html+='<tr>';
						html+='<td>'+(i+1)+'</td>';
						//阅读全文</a>
						html+='<td><a href="${path}/blog/'+(d.id)+'.html" target="_blank">'+(d.title)+'</a></td>';
						html+='<td>'+TYPE[d.ctype]+'</td>';
						html+='<td><img src="'+(d.imgUrl)+'" width="200px;" /></td>';
						html+='<td>'+num2Date(d.sysTime)+'</td>';
						html+='<td>';
						
						if("0"==d.status){
							html+='<a href="/blog/audit/'+(d.id)+'/1" class="btn btn-primary  btn-xs"><i class="fa fa-ban"></i>不通过</a>&nbsp;';
							html+='<a href="/blog/audit/'+(d.id)+'/2" class="btn btn-info  btn-xs"><i class="fa fa-circle"></i>通过</a>&nbsp;';
							html+='<a href="/blog/audit/'+(d.id)+'/3" class="btn btn-warning  btn-xs"><i class="fa fa-globe"></i>发布</a>&nbsp;';
						}
						if("1"==d.status){
							html+='<a href="/blog/audit/'+(d.id)+'/2" class="btn btn-info  btn-xs"><i class="fa fa-circle"></i>通过</a>&nbsp;';
							html+='<a href="/blog/audit/'+(d.id)+'/3" class="btn btn-warning  btn-xs"><i class="fa fa-globe"></i>发布</a>&nbsp;';
						}
						if("2"==d.status){
							html+='<a href="/blog/audit/'+(d.id)+'/3" class="btn btn-warning  btn-xs"><i class="fa fa-globe"></i>发布</a>&nbsp;';
						}
						if("3"==d.status){
							html+='<a href="/blog/audit/'+(d.id)+'/0" class="btn btn-danger  btn-xs"><i class="fa fa-times"></i>取消</a>&nbsp;';
						}
						
						
						html+='</td>';
						html+='</tr>';
					});
					$("#datasTabDiv").html(html);
				}else{
					//没有数据
					var html='<tr>';
					html+='<td colspan="6" align="center">';
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

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
   								<a class="btn btn-primary" href="/master/blog/add">增加&nbsp;<i class="fa fa-plus"></i></a>
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
		function del(id){
			if(confirm("确认删除吗?")){
				window.location.href="${path}/master/blog/delete/"+(id)+".html";
			}
		}
	
		//加载函数
		var TYPE={"1":"文化","2":"科技","3":"金融","4":"新闻","5":"视频","6":"约会","7":"养生","8":"旅行"};
		function loadTab(s){
			var url="${path}/master/blog/listBlogs";
			var ps={"s":s};
			var html="";
			$.post(url,ps,function(json){
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
					
					html+='<a href="/master/blog/update/'+(d.id)+'.html" class="btn btn-primary  btn-xs"><i class="fa fa-pencil"></i>编辑</a>&nbsp;';
					html+='<button onclick="del(\''+(d.id)+'\')"  class="btn btn-danger  btn-xs"><i class="fa fa-trash "></i>删除</button>&nbsp;';
					html+='</td>';
					html+='</tr>';
				});
				$("#datasTabDiv").html(html);
				splitPages(json.pages,json.start);
				
			});
		}
		
		$(function(){
			loadTab(1);
		});
	</script>
	
</body>
</html>

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
										<input type="hidden" id="id" name="id" value="${m.id}">
										<input type="hidden" id="s" name="s" >
										<div class="form-group ">
											<label for="r" class="control-label col-lg-2">名称(<b style="color: red;">*</b>)</label>
											<div class="col-lg-6">
												<input class=" form-control" id="r" name="r"  value="${m.role}" type="text" required tabindex="1" />
											</div>
											
										</div>
										
										<div class="form-group ">
			
											<label for="d" class="control-label col-lg-2">描述</label>
											<div class="col-lg-6">
												<input class=" form-control" id="d" name="d" value="${m.state}" type="text" required tabindex="2"/>
											</div>
											
										</div>
										
										<div class="form-group ">
											<label for="resType" class="control-label col-lg-2">资源</label>
											<div class="col-lg-6">
												<div id="treeRes"></div>
											</div>
											
											
										</div>
										
										
										
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<button class="btn btn-danger" onmouseover="setRessValue()" type="submit">提交</button>
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
	<script src="${path}/static/master/js/bootstrap-treeview.min.js"></script>
	
	<script type="text/javascript">
	
		function test(d){
			echoAttr(d);
		}
		
		function treeChecked(json,id){
			
			if(json.length>0){
				$.each(json,function(index,obj){
					if(id==obj.id){
						obj['state']={"selected":true};
						//obj['tags']=['<a  href="javascript:;" onclick="test('+obj+')">42</a>'];
					}
					try{
						return treeChecked(obj.nodes,id);
					}catch (e) {
					}
				});
			}
			
			return json;
		}
		
		
		function makeTree(){
			$.post("${path}/master/getMTree",function(json){
				var rids="${ressids}";
				if(rids.length>0){
					var ids = rids.split(",");
					$.each(ids,function(i,d){
						json = treeChecked(json,d);
					});
				}
				
				$('#treeRes').treeview({
					data: json,
					multiSelect:true,
					//showTags:true,
					selectedBackColor:"#F1F2F7",
					selectedColor:"#2A3542",
					showCheckbox:false,
					onNodeSelected:function(event,data) {
						selectFun('treeRes',data);
					},
					onNodeUnselected : function(event, data) {
						unselect('treeRes',data);
					}
					
				});		
				
				//$('#treeRes').treeview('expandAll');
			});
		}
		
	
		$(function(){
			
			makeTree();
			
		});
		
		function setRessValue(){
			 var s = getValue('treeRes');
			 $("#s").val(s);
		}
		//取值
		function getValue(id) {
			var ns = $('#'+id).treeview('getSelected');
			var v = '';
			$.each(ns, function(i, d) {
				v += d.id + ',';
			});
			if(v.length>0){
				v=v.substring(0, v.length-1);
			}
			return v;
		}
		
		//start 选中函数
		function selectFun(id,data){
			selectPFun(id,data);
			$('#'+id).treeview('selectNode', [ data.nodeId, {
				silent : true
			} ]);
			if (data.nodes.length > 0) {
				$.each(data.nodes, function(i, d) {
					$('#'+id).treeview('selectNode', [ d.nodeId, {
						silent : true
					} ]);
					try {
						selectFun(id,d);
					} catch (e) {
					}
				});
			}
		}
		
		function unselect(id,data) {
			unselectP(id,data);
			$('#'+id).treeview('unselectNode', [ data.nodeId, {
				silent : true
			} ]);
			if (data.nodes.length > 0) {
				$.each(data.nodes, function(i, d) {
					$('#'+id).treeview('unselectNode', [ d.nodeId, {
						silent : true
					} ]);
					try {
						unselect(id,d);
					} catch (e) {
					}
				});
			}
		}
		
		function selectPFun(id,data){
			try {
				var n = $('#'+id).treeview('getParent', data);
				$('#'+id).treeview('selectNode', [ n.nodeId, {
					silent : true
				} ]);
				selectPFun(id,n);
			} catch (e) {
			}
		}
		
		function unselectP(id,data) {
			try {
				var n = $('#'+id).treeview('getParent', data);
				var ss = $('#'+id).treeview('getSelected', data);
				//子循环
				var b=true;
				$.each(ss, function(j, d2) {
					$.each(n.nodes, function(i, d) {
						if (d.nodeId == d2.nodeId) {
							b=false;
						}
					});
				});
				
				if(b){
					$('#'+id).treeview('unselectNode', [ n.nodeId, {
						silent : true
					} ]);
				}
				unselectP(id,n);
			} catch (e) {
			}
		}
		
		//end 
		
		
		

		function echoAttr(myObject) {
			var s = "";
			for ( var property in myObject) {
				s = s + "\n " + property + ": " + myObject[property];
			}
			alert(s);
		}
	</script>

</body>
</html>

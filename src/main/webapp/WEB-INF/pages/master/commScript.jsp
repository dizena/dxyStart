<%@ page language="java" pageEncoding="UTF-8"%>

<script src="${path}/static/master/js/jquery.js"></script>
<script src="${path}/static/master/js/bootstrap.min.js"></script>
<script src="${path}/static/master/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="${path}/static/master/js/jquery.scrollTo.min.js"></script>
<script src="${path}/static/master/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="${path}/static/master/js/owl.carousel.js"></script>
<script src="${path}/static/master/js/jquery.customSelect.min.js"></script>

<!--common script for all pages-->
<script src="${path}/static/master/js/common-scripts.js"></script>


<script>
	$(function(){
		//
		$('#nav-accordion').dcAccordion({
	        eventType: 'click',
	        autoClose: true,
	        saveState: true,
	        disableLink: true,
	        speed: 'slow',
	        showCount: false,
	        autoExpand: true,
		//  cookie: 'dcjq-accordion-1',
	        classExpand: 'dcjq-current-parent'
    	});
		//
		
		
	});

      //分页函数
      function splitPages(pages,start){
    	  if(pages==0){
    		  $("#splitPages").html("共0页");
    		  return;
    	  }
			$("#splitPages").html("");
			var html='';
			if(pages>=5){
				var v=5;
				if(start>=v){
					var all=start+2<pages?start+2:pages;
					for(var i=start-2;i<=all;i++){
						if(start==i){
							html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')" style="background: #EC6459;">'+i+'</a></li>';
						}else{
							html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')">'+i+'</a></li>';
						}
					}
				}else{
					for(var i=1;i<=v;i++){
						if(start==i){
							html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')" style="background: #EC6459;">'+i+'</a></li>';
						}else{
							html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')">'+i+'</a></li>';
						}
					}
				}
				
			}else{
				for(var i=1;i<=pages;i++){
					if(start==i){
						html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')" style="background: #EC6459;">'+i+'</a></li>';
					}else{
						html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')">'+i+'</a></li>';
					}
					
				}
			}
			$("#splitPages").html(html);
		}
      
    	//分页函数
      	function splitPagesV2(count,pages,start){
    	  	if(pages==0){
    		  $("#splitPages").html("共0页");
    		  return;
    	  	}
			$("#splitPages").html("");
			var html='';
			html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+1+'\')" >首页</a></li>';
			if(pages>=5){
				var v=5;
				if(start>=v){
					var all=start+2<pages?start+2:pages;
					for(var i=start-2;i<=all;i++){
						if(start==i){
							html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')" style="background: #EC6459;">'+i+'</a></li>';
						}else{
							html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')">'+i+'</a></li>';
						}
					}
				}else{
					for(var i=1;i<=v;i++){
						if(start==i){
							html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')" style="background: #EC6459;">'+i+'</a></li>';
						}else{
							html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')">'+i+'</a></li>';
						}
					}
				}
				
			}else{
				for(var i=1;i<=pages;i++){
					if(start==i){
						html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')" style="background: #EC6459;">'+i+'</a></li>';
					}else{
						html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+i+'\')">'+i+'</a></li>';
					}
					
				}
			}
			html+='<li><a href="javascript:void(0);" onclick="loadTab(\''+pages+'\')" >尾页</a></li>';
			html+='<li><a href="javascript:void(0);">共<b style="color:#EC6459;">'+pages+'</b>页<b style="color:#EC6459;">'+count+'</b>条</a></li>';
			$("#splitPages").html(html);
		}

  </script>
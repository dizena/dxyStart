/**
 * @info JQuery插件
 * @use url="/xx.do"返回的是List<Map<K,V>> JSON数据Map必须有两个值<"CODE",1000><"NAME",北京>
 * @use 	param的第二个ID是页面对应inputID的显示下拉ID，但是必须隐藏一个input使其ID等于第二个inputID连接"Value"，这个是code的值
 * @like	var url3=path+"/a.do";
    		$("#inputId").autoText({
				url : url3,
				param:["后台接收参数名字","前台页面取值input的ID并且是显示下拉数据的ID","第二个参数名","第二个参数取值的inputID"]
			});
*/

!function($) {
	//高亮的索引  
	var highLightIndex = -1;
	// 超时的标识（对用户连续快速按下键盘的事件做延时处理，只对用户在500ms内最后一次请求，让其生效）
	var timeoutId = null;
	var timer=null;
	
	$.fn.autoText = function(options) {
		var url=options.url;
		var param=options.param;
		var divID="autoText_"+param[1];
		var width = this.width() + 30;
		var height = this.height();
		var pos = $(this.selector).position();//this.position();
		var left = pos.left;
		var top = pos.top + height + 18;
		var style = "width: "
				+ width
				+ "px;left:"
				+ left
				+ "px;top:"
				+ top
				+ "px;overflow: auto;position: absolute;text-align: left;border: 1px gray solid;background-color: white;z-index: 9999;padding-left:5px;height: 181px;color:black;";
		var div = "<div tabindex='0' id='"+divID+"' style='"+style+"'></div>";
		
		this.after(div);
		$("#"+divID).hide();
		var id=this.selector;
		
		//表单焦点点击的时候，加载
		$(id).bind('click',function(e){
			//关闭所有
			$.each($("[id^=autoText_]"),function(i,d){
				$(d).hide();
			});
			
			clearTimeout(timeoutId);
			var params="?";
			for(var i=0;i<param.length;i+=2){
				var k=param[i];
				var v = $.trim($("#"+param[i+1]).val());
				if(v!=''&&v!=null&& v != "null")
					params+=k+"="+v+"&";
			}
			
			var req_url = url+params;
			timeoutId = setTimeout(processAjaxRequest(id,req_url,divID), 100);
		});
		
		//隐藏，鼠标所在的input鼠标移走的时候，取值隐藏 mouseout
		 $(id).bind('blur',function(e){
        	var cxs=$("#"+divID).find("div > span.icon-check");
			var str="";
			var codes="";
			$.each(cxs,function(i,d){
				str+=$(d).parent().text()+" ";
				codes+=$(this).parent().find("input").val()+",";
			});
			$("#"+param[1]).val(str);
			$("#"+param[1]+"Value").val(codes);
			//延迟影藏
			timer=setTimeout(function(){
				$("#"+divID).hide();
			},1000);
		});
		
		//滚动的时候
		$("#"+divID).scroll(function(e){
			clearTimeout(timer);
		});
		
		//鼠标移动来的时候清除事件
		$("#"+divID).bind('mouseover',function(e){
			clearTimeout(timer);
		});
		
		//区域显示的DIV鼠标移走的时候，取值，隐藏
		$("#"+divID).bind('blur',function(e){
			var cxs=$("#"+divID).find("div > span.icon-check");
			var str="";
			var codes="";
			$.each(cxs,function(i,d){
				str+=$(d).parent().text()+" ";
				codes+=$(this).parent().find("input").val()+",";
			});
			$("#"+param[1]).val(str);
			$("#"+param[1]+"Value").val(codes);
			$("#"+divID).hide();
		});
		
		//处理事件，键盘事件
		this.keyup(function(event){
			var myEvent = event || window.event;
			var keyCode = myEvent.keyCode;
			// 输入是字母，或者退格键则需要重新请求
			if ((keyCode >= 48 && keyCode <= 57) || keyCode == 32|| keyCode == 8) {
				clearTimeout(timeoutId);
				//处理参数，数组k,vId,k,vId
				var params="?";
				for(var i=0;i<param.length;i+=2){
					var k=param[i];
					var v = $.trim($("#"+param[i+1]).val());
					if(v!=''&&v!=null&& v != "null")
						params+=k+"="+v+"&";
				}
				timeoutId = setTimeout(processAjaxRequest(id,url+params,divID), 500);
				// 处理上下键(up,down)
			} else if (keyCode == 38 || keyCode == 40) {
				$("#"+divID).hide();
				// 按下了回车键
			} else if (keyCode == 13) {
				$("#"+divID).hide();
			}
		});
		//end
	};

	/**
	 * 处理Ajax请求
	 * 
	 * @param data
	 */
	function processAjaxRequest(id,url,divID) {
		var T=id.substring(1,id.length);
		$("#"+divID).html("").show();
		//encodeURI
		$.post(encodeURI(url),function(data){
			var codes=[];
			var names=[];
			$.each(data,function(i,d){
				codes.push(d.CODE);
				names.push(d.NAME);
			});
			
			$.each(names,function(i, d) {
				var word_div = $("<div id='T"+T+i+"' style='white-space:nowrap;'><span class='icon-check-empty' ></span>"+
						"<input type='hidden' value='"+codes[i]+"' name='codes' id='Tcode'"+i+">&nbsp;"+d+"</div>");
				word_div.click(function(e){
					
					if($("#T"+T+i+"> span" ).hasClass("icon-check")){
						$("#T"+T+i+"> span" ).removeClass("icon-check");
						$("#T"+T+i+"> span" ).addClass("icon-check-empty");
					}else{
						$("#T"+T+i+"> span" ).removeClass("icon-check-empty");
						$("#T"+T+i+"> span" ).addClass("icon-check");
					}
				});
				$("#"+divID).append(word_div);
			});
		});
	}
	
	//end
}(window.jQuery);


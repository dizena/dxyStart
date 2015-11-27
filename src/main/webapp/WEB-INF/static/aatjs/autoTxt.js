//JQuery插件
(function($) {
	//高亮的索引  
	var highLightIndex = -1;
	// 超时的标识（对用户连续快速按下键盘的事件做延时处理，只对用户在500ms内最后一次请求，让其生效）
	var timeoutId = null;
	$.fn.autoText = function(options) {
		var url=options.url;
		var param=options.param;
		var divID="autotxt_"+param[1];
		var width = this.width() + 10;
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
				+ "px;overflow: auto;position: absolute;text-align: left;border: 1px gray solid;background-color: white;z-index: 999;padding-left:5px;height: 181px;color:black;";
		var div = "<div id='"+divID+"' style='"+style+"'></div>";
		this.after(div);
		$(document).on('mousedown', function (e) {
			if ($(e.target).closest("div[id='"+divID+"']").length === 0&&$(e.target).closest("input[id='"+param[1]+"']").length === 0) {
				$("#"+divID).hide();
			}
		});
		$("#"+divID).hide();
		//处理事件
		var id=this.selector;
		//获得焦点事件
		this.click(function(event){
			//处理参数，数组k,vId,k,vId
			var params="?";
			for(var i=0;i<param.length;i+=2){
				var k=param[i];
				var v = $("#"+param[i+1]).val();
				params+=k+"="+v+"&";
			}
			
			processAjaxRequest(id,url+params,divID);
				
		});
		//end
		
		//键盘事件
		this.keyup(function(event){
			var myEvent = event || window.event;
			var keyCode = myEvent.keyCode;
			// 输入是字母，或者退格键则需要重新请求
			if ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || keyCode == 32|| keyCode == 8 || (keyCode >= 97 && keyCode <= 122)) {
				
				highLightIndex = -1;
				clearTimeout(timeoutId);
				//处理参数，数组k,vId,k,vId
				var params="?";
				for(var i=0;i<param.length;i+=2){
					var k=param[i];
					var v = $("#"+param[i+1]).val();
					if(v==""){
						return;
					}
					params+=k+"="+v+"&";
				}
				timeoutId = setTimeout(function(){processAjaxRequest(id,url+params,divID);}, 500);
				// 处理上下键(up,down)
			} else if (keyCode == 38 || keyCode == 40) {
				processKeyUpAndDown(id,keyCode,divID);
				// 按下了回车键
			} else if (keyCode == 13) {
				processEnter(id,divID);
				query();
			}
		});
		//end
	};
	
	//删除左右两端的空格
	function trim(str){  
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	

	/**
	 * 处理Ajax请求
	 * 
	 * @param data
	 */
	function processAjaxRequest(id,url,divID) {
		$("#"+divID).html('').show();
		//encodeURI
		$.post(encodeURI(url),null,function(data){
			$.each(data,function(i, d) {
				var word_div = $("<div style='height: 20px;line-height: 20px;color: black;white-space:nowrap;'></div>");
				
				word_div.html(d);
				word_div.hover(function(){
					changeToWhite(divID);
					$(this).css('background-color', '#D8D8D8');
					highLightIndex = $(this).index();
					//高仿百度
					//$(id).val($('#'+divID).children().eq(highLightIndex).html());
				}, function(){
					$(this).css('background-color', 'white');
				});
				//单击
				word_div.click(function(){
					// 有高亮显示的则选中当前选中当前高亮的文本
					if (highLightIndex != -1) {
						$(id).val($(this).html());
						$("#"+divID).html('').hide();
					}
				});
				$("#"+divID).append(word_div);
			});
		});
	}

	/**如果有高亮的则把高亮变白*/
	function changeToWhite(divID) {
		if (highLightIndex != -1) {
			$("#"+divID).children().eq(highLightIndex).css('background-color', 'white');
		}
	}
	/**
	 * 处理按上下键的情况
	 */
	function processKeyUpAndDown(id,keyCode,divID) {
		var words = $("#"+divID).children();
		var num = words.length;
		if (num <= 0)
			return;
		changeToWhite(divID);
		highLightIndex = ((keyCode != 38 ? num + 1 : num - 1) + highLightIndex)% num;
		words.eq(highLightIndex).css('background-color', '#D8D8D8');
		$(id).val(words.eq(highLightIndex).html());
	}
	/** 处理按下Enter键*/
	function processEnter(id,divID) {
		if (highLightIndex != -1) {
			$(id).val($("#"+divID).children().eq(highLightIndex).html());
			$("#"+divID).html('').hide();
		}
	}
	//end
})(jQuery);


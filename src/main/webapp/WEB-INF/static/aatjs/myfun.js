//输出属性
function echoAttr(myObject) {
	var s = "";
	for ( var property in myObject) {
		s = s + "\n " + property + ": " + myObject[property];
	}
	alert(s);
}

/**
 * @info 数值格式化为日期
 * @param str
 * @returns {String}
 */
function num2Date(str) {
	if (isNotEmpty(str)) {
		var n = Number(str);
		var date = new Date(n);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		var h = date.getHours();
		var mi = date.getMinutes();
		var s = date.getSeconds();
		return y + "-" + append0(m) + "-" + append0(d) + " " + append0(h) + ":"
				+ append0(mi) + ":" + append0(s);
	} else {
		return "";
	}
}

function num2DateYMD(str) {
	if (isNotEmpty(str)) {
		var n = Number(str);
		var date = new Date(n);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		var h = date.getHours();
		var mi = date.getMinutes();
		var s = date.getSeconds();
		return y + "-" + append0(m) + "-" + append0(d);
	} else {
		return "";
	}
}

function fmtStr(s) {
	if (s == null || s == '') {
		return "";
	} else {
		return s;
	}
}

function shengyuDay(l) {
	var l1 = new Date();
	var l2 = l - l1.getTime();
	var l3 = l2 / 3600 / 24 / 1000;
	return parseInt(l3) + 1;
}

/**
 * @info 数值格式化为日期
 * @param str
 * @returns {String}
 */
function num2DateYMD(str) {
	if (isNotEmpty(str)) {
		var n = Number(str);
		var date = new Date(n);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + "-" + append0(m) + "-" + append0(d);
	} else {
		return "";
	}
}
/**
 * @info 如果数字的值小于10 前面补0
 * @param {}
 *            s
 * @return {}
 */
function append0(s) {
	if (Number(s) < 10) {
		return "0" + s;
	} else
		return s;
}

/**
 * @info 判断是否为空串
 * @param str
 * @returns {Boolean}
 */
function isNotEmpty(str) {
	if (null == str)
		return false;
	else if (typeof str == "undefined")
		return false;
	else if ("" == str)
		return false;
	else if (str.length == 0)
		return false;
	else
		return true;
}

/**
 * @info 截取日期字符串为199809形式
 * @param str
 *            1998-09,199809
 * @returns
 */
function getYyyymm(str) {
	if (str == "" || str == "null")
		return "";
	return str.substring(0, 4) + str.substring(5, 7);
}

/**
 * @info 截取日期字符串为199809形式 区别于getYyyymm（）
 * @param str
 *            1998-09,199809
 * @returns
 */
function getMonthS(str) {

	if (str == "" || str == "null")
		return "";
	return str.substring(0, 4) + str.substring(5, 7);
}

/**
 * @info 截取日期字符串为199809形式
 * @param str
 *            1998-09,1998-09-09
 * @returns
 */
function yyyymmToYM(str) {
	if (str == "" || str == "null")
		return "";
	return str.substring(0, 4) + "-" + str.substring(4, 6);
}

/**
 * @info 截取日期字符串为199809形式
 * @param str
 *            1998-09,1998-09-09
 * @returns
 */
function yyyyMMddToYMDL(str) {
	if (str == "" || str == "null")
		return "";
	return str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
			+ str.substring(6, 8);
}

/**
 * @info 截取日期字符串为19980909形式
 * @param str
 *            1998-09-09,19980909
 * @returns
 */
function getFormatDate(str) {
	if (str == "" || str == "null") {
		return "";
	} else {
		return str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
	}
}

/**
 * @info 获取当年日期
 * @returns
 */
function getEntYearS() {
	var date = new Date();
	return date.getFullYear();
}

/**
 * @info 获取当年当月日期
 * @returns
 */
function getEntMonthS() {
	var date = new Date();
	var m = (date.getMonth() + 1);
	return date.getFullYear() + "-" + (m > 10 ? m + "" : "0" + m);
}

/**
 * @info 获取当月第一天日期
 * @returns
 */
function getCurtMonthFirDay() {
	var date = new Date();
	var m = (date.getMonth() + 1);
	return date.getFullYear() + "-" + (m > 10 ? m + "" : "0" + m) + "-01";
}

/**
 * @info 获取当一天日期
 * @returns
 */
function getCurtDay() {
	var date = new Date();
	var m = (date.getMonth() + 1);
	return date.getFullYear() + "-" + (m > 10 ? m + "" : "0" + m) + "-"
			+ date.getDate();
}

// 计数加逗号
function numDouhao2XS(str) {
	if (typeof (str) == "undefined") {
		return "";
	}
	return fmoney(str, 2);
}

// 格式化数字逗号，有浮点数
function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (var i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}

/**
 * @info 保留位小数
 * @param srcNumber
 * @param n
 * @returns
 */
function format_number(srcNumber, n) {
	if (typeof srcNumber == 'undefined') {
		return 0;
	}

	var dstNumber = parseFloat(srcNumber);
	if (isNaN(dstNumber)) {
		return srcNumber;
	}
	if (dstNumber >= 0) {
		dstNumber = parseInt(dstNumber * Math.pow(10, n) + 0.5)
				/ Math.pow(10, n);
	} else {
		var tmpDstNumber = -dstNumber;
		dstNumber = parseInt(tmpDstNumber * Math.pow(10, n) + 0.5)
				/ Math.pow(10, n);
	}
	var dstStrNumber = dstNumber.toString();
	var dotIndex = dstStrNumber.indexOf('.');
	if (dotIndex < 0) {
		dotIndex = dstStrNumber.length;
		dstStrNumber += '.';
	}

	while (dstStrNumber.length <= dotIndex + n) {
		dstStrNumber += '0';
	}
	return dstStrNumber;
}

/**
 * @info 将数值转化为2位的百分数字符串
 * @param num
 * @returns {String}
 */
function parseToPrecentNum(num) {
	if (typeof (num) == "undefined" || num == "" || num == null)
		return "0%";
	num = Number(num);
	return format_number(num * 100, 2) + "%";
}
/**
 * @info 格式化未定义的对象
 * @param ss
 * @returns
 */
function formatUndefined(ss) {
	if (typeof ss == 'undefined')
		return "";
	else
		return ss;
}

/**
 * 验证账户是否合法
 */
function checkUser(str) {
	var re = /^[a-zA-z]\w{3,15}$/;
	if (re.test(str)) {
		return true;
	}
	return false;
}

/**
 * 验证手机号
 * 
 * @param str
 */
function checkMobile(str) {
	var re = /^1\d{10}$/
	if (re.test(str)) {
		return true;
	}
	return false;
}

/**
 * 验证电话号
 * 
 * @param str
 */
function checkPhone(str) {
	var re = /^0\d{2,3}-?\d{7,8}$/;
	if (re.test(str)) {
		return true;
	}
	return false;
}

/**
 * 验证邮箱
 * 
 * @param str
 */
function checkEmail(str){
	var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    if(re.test(str)){
    	return true;
    }
    return false;
}

/**
 * 
 * @param id
 * @param names
 * @param yTitle
 * @param datas
 *            ([{name: 'John',data: [5, 3, 4, 7, 2]}])
 */
function barChart(id, names, yTitle, datas) {
	$('#' + id).highcharts({
		chart : {
			type : 'bar'
		},
		title : {
			text : ''
		},
		xAxis : {
			categories : names
		},
		yAxis : {
			min : 0,
			title : {
				text : yTitle
			}
		},
		legend : {
			enabled : false,
			backgroundColor : '#FFFFFF',
			reversed : true
		},
		plotOptions : {
			series : {
				stacking : ''// normal堆叠
			}
		},
		series : [ {
			name : yTitle,
			data : datas
		} ],
		credits : {
			enabled : false
		}
	});
}

/**
 * @info 一个图，没有下面说明，鼠标移动上去有显示x,y值。
 * @param id(哪个DIV作图)
 * @param type(图的类型，line,spline,column,pie)
 * @param names(x轴的数据)
 * @param datas({name:"",data:[1,2,3,4,5]})
 */
function oneChart(id, type, names, datas) {
	$('#' + id).highcharts({
		chart : {
			type : type
		},
		title : {
			text : ''
		},
		xAxis : {
			categories : names
		},
		yAxis : {
			min : 0,
			title : {
				text : ''
			}
		},
		legend : {
			enabled : false
		},
		tooltip : {
			formatter : function() {
				return '<b>' + this.x + '</b>' + ':' + this.point.y;
			}
		},
		plotOptions : {
			column : {
				pointWidth : 30
			}
		},
		series : [ datas ],
		credits : {
			enabled : false
		}
	});
}

/**
 * @info 多个图，没有下面说明，鼠标移动上去有显示x,y值。
 * @param id(哪个DIV作图)
 * @param names(x轴的数据),(type=图的类型，line,spline,column,pie)
 * @param datas(
 *            [{type: 'column',color:'#8bbc21',name: '医院',data: [5, 3, 4]},
 *            {type: 'spline',name: '药店',data: [2, 2, 3]}] )
 */
function manyChart(id, names, datas) {
	$('#' + id).highcharts({
		title : {
			text : ''
		},
		xAxis : {
			categories : names,
			labels : {
				rotation : -45,
				align : 'right',
				style : {
					fontSize : '13px'
				}
			}
		},
		yAxis : {
			min : 0,
			title : {
				text : ''
			}
		},
		legend : {
			enabled : false
		},
		tooltip : {
			formatter : function() {
				return '<b>' + this.x + '</b>' + '：' + this.point.y;
			}
		},
		plotOptions : {
			column : {
				pointWidth : 30,
				borderPadding : 0.1,
				borderWidth : 0
			}
		},
		series : datas,
		credits : {
			enabled : false
		}
	});
}

/**
 * @info 多个图，没有下面说明，鼠标移动上去有显示x,y值。包含图例，默认在下边
 * @param id(哪个DIV作图)
 * @param names(x轴的数据),(type=图的类型，line,spline,column,pie)
 * @param datas(
 *            [{type: 'column',color:'#8bbc21',name: '医院',data: [5, 3, 4]},
 *            {type: 'spline',name: '药店',data: [2, 2, 3]}] )
 */
function manyChart2(id, names, datas) {
	$('#' + id).highcharts({
		title : {
			text : ''
		},
		xAxis : {
			categories : names,
			labels : {
				rotation : -45,
				align : 'right',
				style : {
					fontSize : '13px'
				}
			}
		},
		yAxis : {
			min : 0,
			title : {
				text : ''
			}
		},
		legend : {
			enabled : true
		},
		tooltip : {
			formatter : function() {
				return '<b>' + this.x + '</b>' + '：' + this.point.y;
			}
		},
		plotOptions : {
			column : {
				pointWidth : 30,
				borderPadding : 0.1,
				borderWidth : 0
			}
		},
		series : datas,
		credits : {
			enabled : false
		}
	});
}

/**
 * 一个饼状图
 * 
 * @param id
 * @param datas([{type:
 *            'pie',name: '浏览器市场',data:
 *            [['Firefox',45.0],['IE',26.8],['Safari',8.5],['Opera',6.2],['Others',0.7]]}])
 */
function onePie(id, datas) {
	$('#' + id).highcharts({
		credits : {
			enabled : false
		},
		title : {
			text : ''
		},
		tooltip : {
			pointFormat : '{series.name}: <b>{point.percentage:.2f}%</b>'
		},
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				depth : 35,
				dataLabels : {
					enabled : true,
					format : '{point.name}'
				}
			}
		},
		series : datas
	});
}

/**
 * 转换日期long为Date格式
 * 
 */
function formatLong2Date(cvalue) {
	var n = Number(cvalue);
	var date = new Date(n);
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();

	return y + "-" + append0(m) + "-" + append0(d);
}

/**
 * 转换日期long为DateTime格式
 * 
 */
function formatLong2DateTime(cvalue) {
	var n = Number(cvalue);
	var date = new Date(n);
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();

	return y + "-" + append0(m) + "-" + append0(d) + " " + append0(hour) + ":"
			+ append0(minute) + ":" + append0(second);
}

/**
 * 时间判断
 * 
 */

function JudgeDateTime(dataValue1, datavalue2) {
	if (dataValue1 > datavalue2) {
		alert("结束日期不能小于开始日期");
		return false;
	} else {
		return true;
	}
}

/**
 * 短日期，形如 (2008-07)
 * 
 */
function CheckMonth(dataValue) {
	var r = dataValue.match(/^(\d{1,4})(-|\/)(\d{1,2})$/);
	if (r == null) {
		alert("时间格式不正确,yyyy-mm");
		return false;
	} else {
		return true;
	}
}

/**
 * 短日期，形如 (2008-07-22)
 * 
 */
function CheckTime(dataValue) {
	var r = dataValue.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (r == null) {
		alert("时间格式不正确,yyyy-mm-dd");
		return false;
	}
	var d = new Date(r[1], r[3] - 1, r[4]);
	return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d
			.getDate() == r[4]);
}

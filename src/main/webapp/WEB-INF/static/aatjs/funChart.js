//1,散列图
function hashChart() {
	$('#hashc').highcharts({
		chart : {
			type : 'scatter',
			zoomType : 'xy',
			alignTicks:false,
			gridLineWidth:0
		},
		title : {
			text : '标题'
		},
		subtitle : {
			text : '小标题'
		},
		xAxis : {
			title : {
				enabled : true,
				text : '身高 (cm)'
			},
			min:5,
			max:18,
			startOnTick : true,
			endOnTick : true,
			showLastLabel : true
		},
		yAxis : {
			title : {
				text : '体重 (kg)'
			}
		},
		legend : {
			//layout : 'vertical',
			align : 'left',
			verticalAlign : 'top',
			x : 100,
			y : 70,
			floating : true,
			backgroundColor : '#FFFFFF',
			borderWidth : 1
		},
		plotOptions : {
			scatter : {
				marker : {
					radius : 5,
					states : {
						hover : {
							enabled : true,
							lineColor : 'rgb(100,100,100)'
						}
					}
				},
				states : {
					hover : {
						marker : {
							enabled : false
						}
					}
				},
				tooltip : {
					headerFormat : '<b>{series.name}</b><br>',
					pointFormat : '身高：{point.x} cm <br> 体重：{point.y} kg'
				}
			}
		},
		series : seriesData,
		credits : {
			enabled : false
		}
	});
}

var seriesData = [
		{
			name : '女',
			data : [[15.4,1000000],[10,30000],[10,10000],[10,6000],[9,10000],[15.4,750000],[10,6000],[14.8,500000],[9,6000],[10,10000]]

		}];

var cs=[ '#5998E0', '#3D4F61', '#A2C94D', '#A73333','#48BDD8', '#f15c80', '#e4d354'];

//2,柱状图
function columnChart(){
	$('#columnChart').highcharts({
		colors : cs,
		chart : {
			type : 'column'
		},
		title : {
			text : ''
		},
		xAxis : {
			categories : [ '85-100', '75-85', '60-75', '60以下' ],
			labels : {
				rotation : -45
			}
		},
		yAxis : {
			min : 0,
			title : {
				text : '人数 (人)'
			}
		},
		legend : {
			enabled : false
		},
		tooltip : {
			pointFormat : '2014级: <b>{point.y:.0f} 人</b>',
		},
		series : [ {
			data : [ 56, 136, 81, 33 ]
		} ],
		credits : {
			enabled : false
		}
	});
}

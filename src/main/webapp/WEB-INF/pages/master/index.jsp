<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ch">
<head>

<!-- style start-->
<jsp:include page="commStyle.jsp" />
<!-- style end-->

</head>

<body>

	<section id="container">

		<!--header start-->
		<jsp:include page="commHeader.jsp" />
		<!--header end-->


		<!--sidebar start-->
		<jsp:include page="commNav.jsp" />
		<!--sidebar end-->


		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				
				<div id="hashc" style="height: 400px;">
				
				</div>
				<div id="columnChart" style="height: 400px;">
				
				</div>
				
				
				
			</section>
		</section>
		<!--main content end-->

		<!--footer start-->
		<jsp:include page="commFooter.jsp" />
		<!--footer end-->
	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<!--script start-->
	<jsp:include page="commScript.jsp" />
	<!--script end-->
	<script type="text/javascript" src="${path}/static/aatjs/highcharts.js"></script>
	<script type="text/javascript" src="${path}/static/aatjs/funChart.js"></script>
	<script type="text/javascript">
		$(function(){
			hashChart();
			columnChart();
		});
	</script>

</body>
</html>


<%@ page language="java" pageEncoding="UTF-8"%>

<header class="header white-bg">
	<div class="sidebar-toggle-box">
		<div data-original-title="菜单 导航" data-placement="right" class="fa fa-reorder icon-reorder tooltips"></div>
	</div>
	
	
	<!--logo start-->
	<a href="${path}/master" class="logo">驾驶<span>舱</span></a>
	<!--logo end-->
	
	
	<div class="nav notify-row" id="top_menu">
		<!--  notification start -->
		<ul class="nav top-menu">
			<!-- 任务 settings start -->
			<li class="dropdown">
				<a title="淡香雅" href="${path}/blog" target="_blank"> 
					<i class="fa fa-file-word-o"></i> 
				</a>
			</li>
			<li class="dropdown">
				<a title="巧了啊" href="${path}/love" target="_blank"> 
					<i class="fa fa-heart"></i> 
				</a>
			</li>
			<li class="dropdown">
				<a title="数据库" href="${path}/druid" target="_blank"> 
					<i class="fa fa-database"></i> 
				</a>
			</li>
			<li class="dropdown">
				<a title="图标网" href="http://fontawesome.io/icons/" target="_blank"> 
					<i class="fa fa-adn"></i> 
				</a>
			</li>
		</ul>
		<!--  notification end -->
	</div>
	
	<div class="top-nav ">
		<!--search & user info start-->
		<ul class="nav pull-right top-menu">
			   
			<!-- user login dropdown start-->
			<li class="dropdown">
				<a data-toggle="dropdown" class="dropdown-toggle" href="javascript:;">
                  	<img alt="" src="${aatdxy_login_user.icon}" style="width: 35px;height: 35px;">
                    <span class="username">${aatdxy_login_user.nickname}</span>
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu extended logout">
                    <div class="log-arrow-up"></div>
                 	<li><a href="#"><i class="fa fa-user"></i>个人中心</a></li>
                    <li><a href="#"><i class="fa fa-cog"></i> 个性设置</a></li>
                    <li><a href="/uptpwd"><i class="fa fa-pencil"></i> 修改密码</a></li>
                    <li><a href="/logout"><i class="fa fa-key"></i>退出</a></li>
                 </ul>
			</li>
			<!-- user login dropdown end -->
		</ul>
		<!--search & user info end-->
	</div>
</header>
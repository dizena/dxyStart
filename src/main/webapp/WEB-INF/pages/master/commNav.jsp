<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<script type="text/javascript">
<!--
//菜单active
function lv1(i){
	$.post("${path}/setMenu?lv1="+i);
}

function lv2(i){
	$.post("${path}/setMenu?lv2="+i);
}

function lv3(i){
	$.post("${path}/setMenu?lv3="+i);
}
-->
</script>

<aside>
	
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">
			<!-- 菜单 -->
			<c:if test="${not empty menus}">
				<c:forEach items="${menus}" var="r">
					<li onclick="lv1(${r.sort})" <c:if test="${not empty r.subs}">class="sub-menu"</c:if> >
						<a <c:if test="${lv1==r.sort}">class="active"</c:if>  href="${r.url}"> 
							<i class="${r.icon}"></i> <span>${r.name}</span>
						</a>
						<!-- 2 -->
						<c:if test="${not empty r.subs}">
							<ul class="sub">
								<c:forEach items="${r.subs}" var="r2">
									<li onclick="lv2(${r2.sort})" <c:if test="${lv2==r2.sort}">class="active"</c:if> <c:if test="${not empty r2.subs}">class="sub-menu"</c:if> >
										<a href="${r2.url}">${r2.name}</a>
										<!-- 3 -->
										<c:if test="${not empty r2.subs}">
											<ul class="sub">
												<c:forEach items="${r2.subs}" var="r3">
													<li onclick="lv3(${r3.sort})" <c:if test="${lv3==r3.sort}">class="active"</c:if> >
														<a href="${r3.url}">${r3.name}</a>
													</li>
												</c:forEach>
											</ul>
										</c:if>	
										<!-- 3 end -->
									</li>
								</c:forEach>
							</ul>
						</c:if>	
						<!-- 2 end -->
					</li>
				</c:forEach>
			</c:if>
			<!-- 菜单 end -->
		</ul>
		<!-- sidebar menu end-->
	</div>
</aside>
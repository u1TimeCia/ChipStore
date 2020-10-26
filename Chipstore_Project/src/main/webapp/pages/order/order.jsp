<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>My Order</title>
<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">My order</span>
			<div>
				<%@ include file="/pages/common/login_success_menu.jsp"%>
			</div>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>dates</td>
				<td>money</td>
				<td>state</td>
				<td>detail</td>
			</tr>
			<c:forEach items="${requestScope.orderList}" var="item">
			<tr>
				<td>${item.createTime}</td>
				<td>${item.price}</td>
				<td>
					<c:if test="${item.status==0}">
						not shipped
					</c:if>
					<c:if test="${item.status==1}">
						is on the way
					</c:if>
					<c:if test="${item.status==2}">
						delivered
					</c:if>
				</td>
				<td><a href="orderServlet?action=showMyOrderItem&orderId=${item.orderId}">check for detail</a></td>
			</tr>
			</c:forEach>
		</table>
		
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
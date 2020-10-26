<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>订单管理</title>
<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">Order Management System</span>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>dates</td>
				<td>money</td>
				<td>detail</td>
				<td>state</td>
				
			</tr>		
			<tr>
				<td>2015.04.23</td>
				<td>90.00</td>
				<td><a href="#">check for detail</a></td>
				<td><a href="#">click to ship</a></td>
			</tr>	
			
			<tr>
				<td>2015.04.20</td>
				<td>20.00</td>
				<td><a href="#">check for detail</a></td>
				<td>shipped</td>
			</tr>	
			
			<tr>
				<td>2014.01.23</td>
				<td>190.00</td>
				<td><a href="#">check for detail</a></td>
				<td>delivered</td>
			</tr>		
		</table>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
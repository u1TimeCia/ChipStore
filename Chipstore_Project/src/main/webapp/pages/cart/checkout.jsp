<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Payment page</title>
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
			<div>
				<%@ include file="/pages/common/login_success_menu.jsp"%>
			</div>
	</div>
	
	<div id="main">
		
		<h1>Your order has been proceeded, order number ${sessionScope.orderId}</h1>

	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
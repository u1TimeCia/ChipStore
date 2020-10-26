<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>后台管理</title>
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
			<span class="wel_word">back-stage management</span>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<h1>welcome to back-stage management system</h1>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
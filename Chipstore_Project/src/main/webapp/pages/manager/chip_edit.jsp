<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>编辑图书</title>
<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">edit chip</span>
			<%@include file="/pages/common/manager_menu.jsp"%>
		</div>
		
		<div id="main">
			<form action="manager/chipServlet"method="get">
				<input type="hidden" name="action" value="${empty requestScope.chip?'add':'update'}">
				<input type="hidden" name="id" value="${requestScope.chip.id}">
				<input type="hidden" name="pageNo" value="${param.pageNo}">
				<table>
					<tr>
						<td>name</td>
						<td>price</td>
						<td>author</td>
						<td>sales</td>
						<td>stock</td>
						<td colspan="2">action</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.chip.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.chip.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.chip.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.chip.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.chip.stock}"/></td>
						<td><input type="submit" value="submit"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
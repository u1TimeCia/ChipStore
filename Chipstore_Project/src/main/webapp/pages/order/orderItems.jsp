<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qhr74
  Date: 2020/10/25
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>
<table>
    <tr>
        <td>chip name</td>
        <td>quantity</td>
        <td>price</td>
        <td>total price</td>
        <td>order id</td>
    </tr>
    <c:forEach items="${requestScope.orderItems}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.count}</td>
            <td>${item.price}</td>
            <td>${item.totalPrice}</td>
            <td>${item.orderId}</td>
        </tr>
    </c:forEach>
</table>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
common tag for login_success,login,register,register_success
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>Glad to see<span class="um_span">${empty sessionScope.user.username?"Guest":sessionScope.user.username}</span>come to our base!</span>
    <c:if test="${empty sessionScope.user}">
        <a href="pages/user/login.jsp">login</a> |
        <a href="pages/user/register.jsp">register</a> |
        <a href="pages/cart/cart.jsp">shopping cart</a>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <a href="pages/cart/cart.jsp">Shopping Cart</a> |
        <a href="orderServlet?action=showMyOrder">My Order</a> |
        <a href="userServlet?action=logout">Log out</a> |
        <a href="pages/manager/manager.jsp">backup management</a>
    </c:if>
</div>


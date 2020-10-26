<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping cart</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a.deleteItem").click(function () {
                return confirm("Delete " + $(this).parent().parent().find("td:first").text() + "?");
            })
            $("a.clearCart").click(function () {
                return confirm("Clear Shopping Cart???");
            })
            $(".updateCount").change(function () {
                var name = $(this).parent().parent().find("td:first").text();
                var count = this.value;
                var id = $(this).attr("chipId");
                if(confirm("Change quantity of"+name+" to "+count+"?")){
                    location.href = "http://localhost:8080/ChipStore/cartServlet?action=updateCount&count="+count+"&chipId="+id;
                }else{
                    this.value = this.defaultValue;
                }
            })
        })
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">Shopping Cart</span>
    <div>
        <%@ include file="/pages/common/login_success_menu.jsp" %>
    </div>
</div>

<div id="main">

    <table>
        <tr>
            <td>Chip name</td>
            <td>Quantity</td>
            <td>Price</td>
            <td>Total</td>
            <td>Action</td>
        </tr>
        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5"><a href="index.jsp">No items in your shopping cart, go to shopping center!</a></td>
            </tr>
        </c:if>
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>
                    <td>
                        <input class="updateCount" chipId="${entry.value.id}" style="width: 40px;" type="text" value="${entry.value.count}">
                    </td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">delete</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <!--if no item in cart-->
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">There are<span class="b_count">${sessionScope.cart.totalCount}</span>items in your shopping cart</span>
            <span class="cart_span">Total price:<span class="b_price">${sessionScope.cart.totalPrice}</span>$</span>
            <span class="cart_span"><a class="clearCart"
                                       href="cartServlet?action=clearCart">Clear shopping cart</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">Proceed to checkout</a></span>
        </div>
    </c:if>
</div>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
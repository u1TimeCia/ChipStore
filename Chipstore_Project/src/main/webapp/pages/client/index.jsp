<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>YoRHa Membership Main Page</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("button.addToCart").click(function () {
                var chipId = $(this).attr("chipId");
                location.href = "http://localhost:8080/ChipStore/cartServlet?action=addItem&id="+chipId;
            })
        })
    </script>
</head>
<body>

    <div id="header">
        <img class="logo_img" alt="" src="static/img/logo.gif">
        <span class="wel_word">YoRHa Base</span>
        <%@include file="/pages/common/login_success_menu.jsp"%>
    </div>
    <div id="main">
        <div id="chip">
            <div class="book_cond">
                <form action="client/chipServlet" method="get">
                    <input type="hidden" name="action" value="pageByPrice">
                    price:<input id="min" type="text" name="min" value="${param.min}"> dollars -
                    <input id="max" type="text" name="max" value="${param.max}"> dollars
                    <input style="width: 70px" type="submit" value="search"/>
                </form>
            </div>
            <div style="text-align: center">
                <c:if test="${empty sessionScope.cart || sessionScope.cart.totalCount == 0}">
                    <span><br/></span>
                    <div id="chipName"><span style="color: red"  >No chips in your carts!</span><br/></div>
                </c:if>
                <c:if test="${sessionScope.cart.totalCount > 0}">
                    <span>There are ${sessionScope.cart.totalCount} items in your cart</span>
                    <div >
                        You just add<span style="color: red" > ${sessionScope.chipName} </span>into your shopping cart
                    </div>
                </c:if>

            </div>
            <c:forEach items="${requestScope.page.items}" var="chip">
                <div class="b_list">
                    <div class="img_div">
                        <img class="book_img" alt="" src="${chip.imgPath}"/>
                    </div>
                    <div class="book_info">
                        <div class="book_name">
                            <span class="sp1">chip name:</span>
                            <span class="sp2">${chip.name}</span>
                        </div>
                        <div class="book_author">
                            <span class="sp1">made by:</span>
                            <span class="sp2">${chip.author}</span>
                        </div>
                        <div class="book_price">
                            <span class="sp1">price:</span>
                            <span class="sp2">${chip.price}</span>
                        </div>
                        <div class="book_sales">
                            <span class="sp1">sell</span>
                            <span class="sp2">${chip.sales}</span>
                        </div>
                        <div class="book_amount">
                            <span class="sp1">in store:</span>
                            <span class="sp2">${chip.stock}</span>
                        </div>
                        <div class="book_add">
                            <button chipId=${chip.id} class="addToCart">add into your cart</button>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>

        <div id="page_nav">
            <%--
                display page if pageNo is bigger than mainPage
            --%>
            <c:if test="${requestScope.page.pageNo > 1}">
                <a href="client/chipServlet?action=pageByPrice&pageNo=${requestScope.page.pageNo-1}&min=${param.min}&max=${param.max}">previous</a>
            </c:if>
            <c:choose>
                <%--pageTotal <= 5--%>
                <c:when test="${requestScope.page.pageTotal<=5}">
                    <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            [${i}]
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="client/chipServlet?action=pageByPrice&pageNo=${i}&min=${param.min}&max=${param.max}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--pageTotal > 5--%>
                <c:when test="${requestScope.page.pageTotal>5}">
                    <c:choose>
                        <%--current page is samller than 3--%>
                        <c:when test="${requestScope.page.pageNo <=3}">
                            <c:forEach begin="1" end="5" var="i">
                                <c:if test="${i==requestScope.page.pageNo}">
                                    [${i}]
                                </c:if>
                                <c:if test="${i!=requestScope.page.pageNo}">
                                    <a href="client/chipServlet?action=pageByPrice&pageNo=${i}&min=${param.min}&max=${param.max}">${i}</a>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <%--current page is bigger than total page -3--%>
                        <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                            <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                                <c:if test="${i==requestScope.page.pageNo}">
                                    [${i}]
                                </c:if>
                                <c:if test="${i!=requestScope.page.pageNo}">
                                    <a href="client/chipServlet?action=pageByPrice&pageNo=${i}&min=${param.min}&max=${param.max}">${i}</a>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <%--current page is samller than total page -3 and bigger than 3--%>
                        <c:otherwise>
                            <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                                <c:if test="${i==requestScope.page.pageNo}">
                                    [${i}]
                                </c:if>
                                <c:if test="${i!=requestScope.page.pageNo}">
                                    <a href="client/chipServlet?action=pageByPrice&pageNo=${i}&min=${param.min}&max=${param.max}">${i}</a>
                                </c:if>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:when>
            </c:choose>
            <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
                <a href="client/chipServlet?action=pageByPrice&pageNo=${requestScope.page.pageNo+1}&min=${param.min}&max=${param.max}">next</a>
            </c:if>|
            ${requestScope.page.pageTotal} pages，${requestScope.page.pageTotalCount} records | go to page <input value ="${param.pageNo}" name="pn" id="pn_input"/>
            <input id="searchPage" type="button" value="confirm">
            <script type="text/javascript">
                //go to indicated page
                $(function () {
                    $("#searchPage").click(function () {
                        var pageNo = $("#pn_input").val();
                        if(pageNo > ${requestScope.page.pageTotal} || pageNo <= 0){
                            alert("Invalid page No.");
                            return false;
                        }
                        location.href = "${pageContext.getAttribute("bassPath")}client/chipServlet?action=pageByPrice&min=${param.min}&max=${param.max}&pageNo="+pageNo;
                    })
                })
            </script>
        </div>


    </div>
    <%@include file="/pages/common/footer.jsp" %>


</body>
</html>
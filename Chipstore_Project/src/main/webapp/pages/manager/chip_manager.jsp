<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%@include file="/pages/common/head.jsp"%>


	<script type="text/javascript">
		//bond onclick even to a tag to confirm delete
		$(function () {
			$("a.deleteClass").click(function () {

				return confirm("Are you sure you want to delete ["+$(this).parent().parent().find("td:first").text()+"]?");

			})
		})
	</script>
<title>chip management</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">Chip Management</span>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>name</td>
				<td>price</td>
				<td>made by</td>
				<td>sales</td>
				<td>stock</td>
				<td colspan="2">action</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="chip">
				<tr>
					<td>${chip.name}</td>
					<td>${chip.price}</td>
					<td>${chip.author}</td>
					<td>${chip.sales}</td>
					<td>${chip.stock}</td>
					<td><a href="manager/chipServlet?action=getBook&id=${chip.id}&pageNo=${requestScope.page.pageNo}">update</a></td>
					<td><a class = "deleteClass" href="manager/chipServlet?action=delete&id=${chip.id}&pageNo=${requestScope.page.pageNo}">delete</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/chip_edit.jsp?pageNo=${requestScope.page.pageTotal}">add chips</a></td>
			</tr>	
		</table>


        <!--page separation-->
		<div id="page_nav">
            <%--
                display page if pageNo is bigger than mainPage
            --%>
            <c:if test="${requestScope.page.pageNo > 1}">
                <a href="manager/chipServlet?action=page&pageNo=${requestScope.page.pageNo-1}">previous</a>
            </c:if>
            <c:choose>
                <%--pageTotal <= 5--%>
                <c:when test="${requestScope.page.pageTotal<=5}">
                    <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            [${i}]
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="manager/chipServlet?action=page&pageNo=${i}">${i}</a>
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
                                    <a href="manager/chipServlet?action=page&pageNo=${i}">${i}</a>
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
                                    <a href="manager/chipServlet?action=page&pageNo=${i}">${i}</a>
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
                                    <a href="manager/chipServlet?action=page&pageNo=${i}">${i}</a>
                                </c:if>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:when>
            </c:choose>
            <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
                <a href="manager/chipServlet?action=page&pageNo=${requestScope.page.pageNo+1}">next</a>
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
                            location.href = "${pageContext.getAttribute("basePath")}manager/chipServlet?action=page&pageNo="+pageNo;
                        })
                    })
                </script>
		</div>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
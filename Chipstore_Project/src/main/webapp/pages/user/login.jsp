<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>NieR Automata Login</title>
<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	<div id="login_header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
			</div>
	<div class="login_banner">

				<div id="l_content">
					<span class="login_word">いらっしゃいませ</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit" >
								<h1>YoRuHa Membership</h1>
								<a href="pages/user/register.jsp">Register now</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<!--
								display errorMsg if username or password is wrong
								-->
								<span class="errorMsg1" >
	<%--									<%=request.getAttribute("msg") == null? "please enter username and password":request.getAttribute("msg")%>--%>
										${empty requestScope.msg? "please enter username and password":requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<!--
									hidden property for decide login or register
									-->
									<input type="hidden" value="login" name="action">
									<label>username：</label>
									<!--
									if username or password is wrong, keep username on the page
									-->
									<input class="itxt" type="text" placeholder="enter username" autocomplete="off" tabindex="1" name="username"
										   value="${requestScope.username}"/>
									<br />
									<br />
									<label>password：</label>
									<input class="itxt" type="password" placeholder="enter password" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="Login" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>
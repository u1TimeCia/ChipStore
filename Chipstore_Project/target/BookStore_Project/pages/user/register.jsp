<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>YoRuHa register</title>
		<%@include file="/pages/common/head.jsp"%>
		<script type="text/javascript">
			$(function () {
				$("#username").blur(function () {
					var username = this.value;
					var usernameText = $("#username").val();
					var usernamePatt = /^\w{5,12}$/;
					$.getJSON("http://localhost:8080/ChipStore/userServlet","action=ajaxExistUsername&username="+username,function (data) {
						if(data.existUsername){
							$("span.errorMsg").text("username has existed");
						}

					});
					if (!usernamePatt.test(usernameText)) {
						$("span.errorMsg").text("username must only contains 5-12 characters(number, letter and '_')");
					}else{
						$("span.errorMsg").text("");
					}
				});

				$("#password").blur(function () {
					var passwordPatt =  /^\S{5,18}$/;
					var password = this.value;
					if (!passwordPatt.test(password)) {
						$("span.errorMsg").text("password must be 5-18 characters!");
					}
					else{
						$("span.errorMsg").text("");
					}
				})


				$("#repwd").blur(function () {
					var pwd = $("#password").val();
					var repwd = this.value;
					if (pwd != repwd) {
						$("span.errorMsg").text("password does not match");
					}else{
						$("span.errorMsg").text("");
					}
				})

				$("#email").blur(function () {
					var email = this.value;
					var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
					if (!emailPatt.test(email)) {
						//remind user
						$("span.errorMsg").text("email is not valid！");
					}else{
						$("span.errorMsg").text("");
					}
				})



				// onclick event
				$("#sub_btn").click(function () {

					//username
					var usernameText = $("#username").val();
					var usernamePatt = /^\w{5,12}$/;
					if (!usernamePatt.test(usernameText)) {
						$("span.errorMsg").text("username is not valid");

						return false;
					}


					//password
					var passwordText = $("#password").val();
					var passwordPatt =  /([^\s])/g;
					if (!passwordPatt.test(passwordText)) {
						$("span.errorMsg").text("password is not valid");

						return false;
					}

					//confirm password
					var repwdText = $("#repwd").val();
					if (repwdText != passwordText) {
						$("span.errorMsg").text("password does not match");

						return false;
					}

					//email
					var emailText = $("#email").val();
					var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
					if (!emailPatt.test(emailText)) {
						//remind user
						$("span.errorMsg").text("email is not valid！");

						return false;
					}

					// verification code
					var codeText = $("#code").val();

					codeText = $.trim(codeText);

					if (codeText == null || codeText == "") {
						//remind user
						$("span.errorMsg").text("verification code cannot be empty！");

						return false;
					}

					$("span.errorMsg").text("");

				});

			});

		</script>
		<script type="text/javascript">
			//bond onclick event to verification jpg
			$(function () {
				$("#codeImg").click(function () {
					this.src = "${basePath}/kaptcha.jpg";
				})
			})
		</script>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
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
							<div class="tit">
								<h1>YoRuHa Membership</h1>
							</div>
							<span class="errorMsg">
<%--									<%=request.getAttribute("msg") == null?"":request.getAttribute("msg")%>--%>
									${requestScope.msg}
							</span>
							<div class="form">
								<form action="userServlet" method="post">
									<!--
									hidden property for decide login or register
									-->
									<input type="hidden" value="register" name="action">
									<label>username：</label>
									<input class="itxt" type="text" placeholder=""
										   autocomplete="off" tabindex="1" name="username" id="username"
										   value="${requestScope.username}"/>
									<br />
									<br />
									<label>password：</label>
									<input class="itxt" type="password" placeholder=""
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>confirm password：</label>
									<input class="itxt" type="password" placeholder=""
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>email：</label>
									<input class="itxt" type="text" placeholder=""
										   autocomplete="off" tabindex="1" name="email" id="email"
										   value="${requestScope.email}"/>
									<br />
									<br />
									<label>Verification code：</label>
									<input class="itxt" placeholder="" type="text" style="width: 70px;" name="code" id="code"/>
									<img alt="" id="codeImg" src="http://localhost:8080/ChipStore/kaptcha.jpg" style="width: 100px; height: 40px">
									<br />
									<br />
									<input type="submit" value="register" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
	</body>
</html>
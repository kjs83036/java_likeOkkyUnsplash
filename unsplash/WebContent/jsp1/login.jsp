<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../all.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div id="loginPage">
        <div id="loginPage_home">
            <a href="home.jsp"><span><i class="fa fa-camera fa-4x" aria-hidden="true"></i></span></a>
        </div>
        <div>
            <h1>Login</h1>
        </div>
        <div>
            <p>${param.welcome }</p>
        </div>
        <form id ="loginPage_form"action="loginProc.action" method="post">
            <div class="loginPage_form_box">
                <p>Email address</p>
                <input type="text" name="email">
            </div>
            <div class="loginPage_form_box">
                <p>Password</p>
                <input type="password" name="password">
            </div>
            <div class="loginPage_form_btn">
                <input type="submit" value="Login">
            </div>
        </form>
        <div>
            <p>
                Don't have an account?<a href="join.jsp">Join</a>
            </p>
        </div>
    </div>

</body>
</html>
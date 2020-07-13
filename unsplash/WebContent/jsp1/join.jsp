<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String fail = request.getParameter("fail");
    if (fail != null) {
        if (fail.equals("null")) {
            fail = null;
        }
    } else {
        fail = "";
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../all.css">
</head>
<body>
    <div id="joinPage">
        <div id="joinPage_home">
            <a href="home.jsp"><span><i class="fa fa-camera fa-4x" aria-hidden="true"></i></span></a>
        </div>
        <div>
            <h1>Join</h1>
        </div>
        <div>
            <p>Welcome to the UnsplashLike</p>
        </div>
        <div>
            <p><%=fail%></p>
        </div>
        <form id="joinPage_form" action="joinProc.action">
            <div class="joinPage_form_box">
                <p>First name</p>
                <input name="firstName" type="text">
            </div>
            <div class="joinPage_form_box">
                <p>Last name</p>
                <input name="lastName" type="text">
            </div>
            <div class="joinPage_form_box">
                <p>nickname</p>
                <input name="nickName" type="text">
            </div>
            <div class="joinPage_form_box">
                <p>Email address</p>
                <input name="email" type="text">
            </div>
            <div class="joinPage_form_box">
                <p>password</p>
                <input name="password" type="text">
            </div>
            <div class="joinPage_form_btn">
                <input type="submit" value="Join">
            </div>
        </form>
        <div>
            <p>
                Already joined?<a href="login.jsp">Login</a>
            </p>
        </div>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../all.css">
<link rel="stylesheet" type="text/css" href="../font-awesome/css/font-awesome.min.css">
</head>
<body>
    <div id="menu">
        <ul>
            <li class="menu_home"><a href="home.jsp"><i class="fa fa-home fa-3x" aria-hidden="true"></i></a></li>
            <li class="menu_search"><input type="text" placeholder="&#xf002 Search"></li>
            <li class="menu_link"><a href="board.jsp"> <span>Board</span>
            </a></li>
            <li class="menu_link"><a href="photoBoard.action"> <span>PhotoBoard</span>
            </a></li>
            <li class="menu_link"><a href="menu3.html"> <span>Menu3</span>
            </a></li>
            <c:if test="${empty sessionScope.nickName}">
                <li class="menu_join"><a href="join.jsp"> <span>Join</span>
                </a></li>
                <li class="menu_login"><a href="login.jsp"> <span>Login</span>
                </a></li>
            </c:if>
            <c:if test="${!(empty sessionScope.nickName)}">
                <li>
                    <div class="menu_login_success">
                        <a href="logoutProc.action"><i class="fa fa-user-circle fa-2x" aria-hidden="true"></i></a>
                        <a href=""><i class="fa fa-paper-plane fa-2x" aria-hidden="true"></i></a>
                        <a href=""><i class="fa fa-bell-o fa-2x" aria-hidden="true"></i></a>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>
    <div id="board_menu_vertical">
        <ul>
            <li class="board_menu_vertical_board1"><a
                href="board1.action"> <span><i class="fa fa-th-large fa-2x" aria-hidden="true"></i></span>
            </a></li>
        </ul>
        <ul>
            <li></li>
            <li class="board_menu_vertical_board2"><a
                href="board2.jsp"> <span><i class="fa fa-weixin fa-2x" aria-hidden="true"></i></span>
            </a></li>
        </ul>
        <ul>
            <li></li>
            <li class="board_menu_vertical_board3"><a
                href="board3.jsp"> <span><i class="fa fa-book fa-2x" aria-hidden="true"></i></span>
            </a></li>
        </ul>
    </div>
    <div id="board_content">
        <img class="board_content_img" alt="board" src="photo-1.png">
    </div>

</body>
</html>
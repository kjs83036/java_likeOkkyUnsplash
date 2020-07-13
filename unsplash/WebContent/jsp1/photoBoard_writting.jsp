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
    <div id="board1_writting">
        <h3>새 글 쓰기</h3>
        <div id="board1_writting_inner">
            <div class="board1_writting_writter">${sessionScope.nickName }
            </div>
            <div class="board1_writting_contents">
                <form action="photoBoard_writtingProc.action" method="post" enctype="multipart/form-data">
                    <div class="board1_writting_contents_title">
                        <input type="file" name="file" id="file">
                    </div>
                    <div class="board1_writting_contents_title">
                        <div id="holder"></div>
                    </div>
                    <div class="board1_writting_contents_tag">
                        <input type="text" name="tag" placeholder="태그을 입력해주세요">
                    </div>
                    <div class="board1_writting_contents_cancle">
                        <a href="photoBoard.action">취소</a>
                    </div>
                    <div class="board1_writting_contents_register">
                        <button type="submit">등록</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<script type="text/javascript">
    var upload= document.getElementById("file");
    var holder = document.getElementById("holder");
    upload.onchange=function(e){
        e.preventDefault();
        
        var file = upload.files[0];
        var reader = new FileReader();
        reader.onload=function(event){
            var img = new Image();
            img.src=event.target.result;
            if(img.width>500){
                img.width=500;
            }
            holder.innerHTML="";
            holder.appendChild(img);
            console.log(img);
        };
        reader.readAsDataURL(file);
        console.log(file);
        
        return false;
    };
    
    
    
</script>
</body>
</html>
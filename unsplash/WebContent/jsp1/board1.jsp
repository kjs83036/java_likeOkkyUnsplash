<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <div id="board1_content">
        <div>
            <div>
                <h3>게시판1</h3>
                <div id="board1_content_createWritting">
                    <a href="board1_writting.jsp"><i class="fa fa-pencil " aria-hidden="true">새 글 쓰기</i></a>
                </div>
            </div>
        </div>
        <div id="board1_content_sortAndSearch">
            <form action="board1.action" method="post">
                <ul>
                    <li><a href="board1.action?sort=seq&sort=${sort }&search=${search}">최신순</a></li>
                    <li><a href="board1.action?sort=read&sort=${sort }&search=${search}">조회순</a></li>
                    <li><a href="board1.action?sort=comment&sort=${sort }&search=${search}">댓글순</a></li>
                    <li class="board1_content_sortAndSearch_search">
                            <button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                            <input type="hidden" name="sort" value="${sort }">
                    </li>
                    <li class="board1_content_sortAndSearch_search">
                        <input type="text" name="search" placeholder="search">
                    </li>
                </ul>
            </form>
        </div>
        <div id="board1_content_boardList">
            <div class="board1_content_boardList_box">
                <div class="board1_content_boardList_box_seq">글번호</div>
                <div class="board1_content_boardList_box_tag">태그</div>
                <div class="board1_content_boardList_box_title">제목</div>
                <div class="board1_content_boardList_box_writer">글쓴이</div>
                <div class="board1_content_boardList_box_comment">댓글수</div>
                <div class="board1_content_boardList_box_read">조회수</div>
            </div>
            <c:forEach var="board" items="${boardList }" varStatus="status">
                <c:if test="${status.index<20 }">
                    <div class="board1_content_boardList_box">
                        <div class="board1_content_boardList_box_seq"><i class="fa fa-hashtag" aria-hidden="true"></i>${board.seq }</div>
                        <div class="board1_content_boardList_box_tag"><i class="fa fa-tag" aria-hidden="true"></i>${board.tag }</div>
                        <div class="board1_content_boardList_box_title"><a href="board1_detail.action?sort=${sort }&search=${search}&seq=${board.seq}">${board.title }</a></div>
                        <div class="board1_content_boardList_box_writer">${board.writer }</div>
                        <c:if test="${!empty countMap[board.seq] }">
                        <div class="board1_content_boardList_box_comment"><i class="fa fa-commenting" aria-hidden="true"></i>${countMap[board.seq]}</div>
                        </c:if>
                        <c:if test="${empty countMap[board.seq] }">
                        <div class="board1_content_boardList_box_comment"><i class="fa fa-commenting" aria-hidden="true"></i>0</div>
                        </c:if>
                        <div class="board1_content_boardList_box_read"><i class="fa fa-eye" aria-hidden="true"></i>${board.read }</div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <script type="text/javascript">
        var endless=function(){
            var count=20;
            window.onscroll = function(ev) {
                if ((window.innerHeight + window.scrollY) >= document.body.scrollHeight) {
                    console.log(window.innerHeight);
                    console.log(window.scrollY);
                    console.log(window.document.body.scrollHeight);
                    var div = document
                            .getElementById('board1_content_boardList');
                    
                    var list = new Array();
                        <c:forEach var="itemList" items="${boardList}" varStatus="listidx">
                            list.push("${itemList}")
                        </c:forEach>
                    
                    var seqList = new Array();
                        <c:forEach var="itemList" items="${boardList}" varStatus="listidx">
                            seqList.push("${itemList.seq}")
                        </c:forEach>
                    console.log(seqList);
                    
                    var tagList = new Array();
                        <c:forEach var="itemList" items="${boardList}" varStatus="listidx">
                            tagList.push("${itemList.tag}")
                        </c:forEach>
                    console.log(tagList);
                    
                    var titleList = new Array();
                        <c:forEach var="itemList" items="${boardList}" varStatus="listidx">
                            titleList.push("${itemList.title}")
                        </c:forEach>
                    console.log(titleList);
                    
                    var writerList = new Array();
                        <c:forEach var="itemList" items="${boardList}" varStatus="listidx">
                            writerList.push("${itemList.writer}")
                        </c:forEach>
                    console.log(writerList);
                    
                    var readList = new Array();
                        <c:forEach var="itemList" items="${boardList}" varStatus="listidx">
                            readList.push("${itemList.read}")
                        </c:forEach>
                    console.log(readList);
                    
                    var countMap = ${cM2};
                    console.log(countMap);
                    
                    var end = false;
                    
                    for(var i=0;i<10;i++){
                        var seq=seqList[count];
                        console.log(seqList[count]);
                        var tag=tagList[count];
                        console.log(tagList[count]);
                        var title=titleList[count];
                        console.log(titleList[count]);
                        var writer=writerList[count];
                        console.log(writerList[count]);
                        var read=readList[count];
                        console.log(readList[count]);
                        var cm=countMap[seq];
                        console.log(cm);
                        if(cm==null){
                            cm=0;
                        }
                        if(seq!=null){
                            element='<div class="board1_content_boardList_box">'
                                    +'<div class="board1_content_boardList_box_seq"><i class="fa fa-hashtag" aria-hidden="true"></i>'+seq+'</div>'
                                    +'<div class="board1_content_boardList_box_tag"><i class="fa fa-tag" aria-hidden="true"></i>'+tag+'</div>'
                                    +'<div class="board1_content_boardList_box_title"><a href="board1_detail.action?sort=${sort }&search=${search}&seq='+seq+'">'+title+'</a></div>'
                                    +'<div class="board1_content_boardList_box_writer">'+writer+'</div>'
                                    +'<div class="board1_content_boardList_box_comment"><i class="fa fa-commenting" aria-hidden="true"></i>'+cm+'</div>'
                                    +'<div class="board1_content_boardList_box_read"><i class="fa fa-eye" aria-hidden="true"></i>'+read+'</div>'
                                +'</div>';
                            div.innerHTML = div.innerHTML+element;
                            count++;
                        }else{
                            event.stopImmediatePropagation();
                        }
                    }
                    
                }
            };
        };
        endless();
        
    </script>
</body>
</html>
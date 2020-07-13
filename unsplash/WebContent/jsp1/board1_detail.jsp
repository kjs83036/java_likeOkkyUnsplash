<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <div id="board1_detail">
        <div>
            <h3 id="board1_detail_head">${board.tag }</h3>
            <div id="board1_detail_toList"><a href="board1.action?sort=${sort }&search=${search}"><i class="fa fa-list" aria-hidden="true">목록으로</i></a></div>
            <c:if test="${!empty sessionScope.nickName&&sessionScope.nickName==board.writer}">
                <div id="board1_detail_update"><a href="board1_update.action?sort=${sort }&search=${search}&seq=${board.seq}">수정</a></div>
                <div id="board1_detail_delete"><a href="board1_deleteProc.action?sort=${sort }&search=${search}&seq=${board.seq }">삭제</a></div>
            </c:if>
        </div>
        <div id="board1_detail_inner">
            <div id="board1_detail_inner_writter">${board.writer}
                <div id="board1_detail_inner_writter_comment"><i class="fa fa-commenting" aria-hidden="true"></i>${fn:length(commentList)}</div>
                <div id="board1_detail_inner_writter_read"><i class="fa fa-eye" aria-hidden="true"></i>${board.read }</div>
            </div>
            <div id="board1_detail_inner_contents">
                <div id="board1_detail_inner_contents_div">
                    <div class="board1_detail_inner_contents_seq">${board.seq}</div>
                    <div class="board1_detail_inner_contents_tag">${board.tag}</div>
                    <div class="board1_detail_inner_contents_title">${board.title}</div>
                </div>
                <div class="board1_detail_inner_contents_content">${board.content}</div>
            </div>
            <div id="board1_detail_inner_comments">
                <c:if test="${!empty commentList }">
                    <div class="board1_detail_inner_comments_count">댓글 ${fn:length(commentList)}</div>
                </c:if>
                <c:if test="${empty commentList }">
                    <div class="board1_detail_inner_comments_count">댓글 0</div>
                </c:if>
                    <c:if test="${!empty commentList }">
                        <c:forEach var="comment" items="${commentList}" varStatus="status">
                        <div class="board1_detail_inner_comments_leftAndRight">
                            <div class="board1_detail_inner_comments_left">
                                <div class="board1_detail_inner_comments_writer">${comment.writer }</div>
                                <div class="board1_detail_inner_comments_comment">${comment.text}</div>
                            </div>
                            <div class="board1_detail_inner_comments_right">
                                <c:if test="${!empty sessionScope.nickName&&sessionScope.nickName==comment.writer}">
                                    <div id="board1_detail_inner_comments_update"><a href="board1_comment_update.action?sort=${sort }&search=${search}&fSeq=${comment.fSeq}&seq=${comment.seq}">수정</a></div>
                                    <div id="board1_detail_inner_comments_delete"><a href="board1_comment_deleteProc.action?sort=${sort }&search=${search}&fSeq=${comment.fSeq }&seq=${comment.seq}">삭제</a></div>
                                </c:if>
                                <div class="board1_detail_inner_comments_like_btn"><a href="board1_comment_likeProc.action?sort=${sort }&search=${search}&fSeq=${comment.fSeq }&seq=${comment.seq}"><i class="fa fa-thumbs-o-up fa-2x" aria-hidden="true"></i></a></div>
                                <div class="board1_detail_inner_comments_like">${comment.likes }</div>
                            </div>
                       </div>
                        </c:forEach>
                    </c:if>
                <c:if test="${empty sessionScope.nickName}">
                    <div class="board1_detail_inner_comments_login"><a href="login.jsp">로그인하셈</a></div>
                </c:if>
                <c:if test="${!(empty sessionScope.nickName)}">
                    <div class="board1_detail_inner_comments_leftAndRight">
                        <form action="board1_commentProc.action" method="post">
                            <input type="hidden" name="seq" value="${board.seq }"/>
                            <input type="hidden" name="sort" value="${sort }"/>
                            <input type="hidden" name="search" value="${search }"/>
                            <div class="board1_detail_inner_comments_left">
                                <div id="board1_detail_inner_comments_myEmail" class="board1_detail_inner_comments_writer">${sessionScope.nickName }</div>
                                <div id="board1_detail_inner_comments_text" class="board1_detail_inner_comments_comment"><textarea name="text" rows="5"></textarea></div>
                            </div>
                            <div class="board1_detail_inner_comments_right">
                                <div id="board1_detail_inner_comments_reg"><button type="submit" >등록</button></div>
                            </div>
                        </form>
                   </div>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
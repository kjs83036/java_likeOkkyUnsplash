<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../jquery-3.1.1.js"></script>
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
    <div id="photoBoard_content">
        <div>
            <div >
                <h3 id="photoBoard_content_head">사진 게시판</h3>
                <div id="photoBoard_content_createWritting">
                    <a href="photoBoard_writting.jsp"><i class="fa fa-pencil " aria-hidden="true">새 글 쓰기</i></a>
                </div>
            </div>
        </div>
        <div id="photoBoard_content_boardListCount">
        </div>
        <div id="photoBoard_content_boardList">
            <div class="photoBoard_content_boardList_left">
                <c:forEach var="photo" varStatus="status" items="${photoBoardList }">
                    <c:if test="${status.index%2!=0 }">
                    <div class="photoBoard_content_boardList_box"><img alt="그림" onclick="onClick(this)" src="${photo.imageURI }"><input type="hidden" value="${photo.writer}"><input type="hidden" value="${photo.tag}"><input type="hidden" value="${photo.likes}"><input type="hidden" value="${photo.views}"><input type="hidden" value="${photo.downloads}"><input type="hidden" value="${photo.seq}"></div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="photoBoard_content_boardList_right">
                <c:forEach var="photo" varStatus="status" items="${photoBoardList }">
                    <c:if test="${status.index%2==0 }">
                    <div class="photoBoard_content_boardList_box"><img alt="그림" onclick="onClick(this)" src="${photo.imageURI }"><input type="hidden" value="${photo.writer}"><input type="hidden" value="${photo.tag}"><input type="hidden" value="${photo.likes}"><input type="hidden" value="${photo.views}"><input type="hidden" value="${photo.downloads}"><input type="hidden" value="${photo.seq}"></div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
     <div id="photoBoard_modal">
        <div id ="photoBoard_modal_img"><img alt="그림" onclick="onClickOff()"></div>
        <div id ="photoBoard_modal_home"><a href='home.jsp'><i class="fa fa-home fa-3x" aria-hidden="true"></i></a></div>
        <div id ="photoBoard_modal_writer"></div>
        <div id ="photoBoard_modal_tag"></div>
        <div id ="photoBoard_modal_like"></div>
        <div id ="photoBoard_modal_view"></div>
        <div id ="photoBoard_modal_download"></div>
     </div>
<script type="text/javascript">
    
    function onClick(element){
        var seq=element.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value;
        var uri=element.src;
        var array=uri.split("/");
        console.log(array);
        var fileName=array[5];
        console.log(array[5]);
        document.getElementById('photoBoard_modal_img').firstChild.src=element.src;
        document.getElementById('photoBoard_modal').style.display='block'
        document.getElementById("photoBoard_modal_writer").innerHTML="<i class='fa fa-user-circle-o' aria-hidden='true'></i>"+element.nextSibling.value;
        document.getElementById("photoBoard_modal_tag").innerHTML="<i class='fa fa-tag' aria-hidden='true'></i> "+element.nextSibling.nextSibling.value;
        document.getElementById("photoBoard_modal_like").innerHTML="<input id='photoBoard_modal_like_seq' type='hidden' value='"+seq+"'><i id='photoBoard_modal_like_fa' onclick='onClickLike(this)' class='fa fa-heart' aria-hidden='true'></i> "+element.nextSibling.nextSibling.nextSibling.value;
        document.getElementById("photoBoard_modal_view").innerHTML="<i id='photoBoard_modal_view_fa' class='fa fa-eye' aria-hidden='true'></i> "+element.nextSibling.nextSibling.nextSibling.nextSibling.value;
        document.getElementById("photoBoard_modal_download").innerHTML="<input id='photoBoard_modal_download_fileName' type='hidden' value='"+fileName+"'><i id='photoBoard_modal_download_fa' class='fa fa-download' aria-hidden='true'></i> "+element.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value;
        
        updateView();
    }
    
    function onClickOff(element){
        $("#photoBoard_modal").css("display","none");
    }
    
    function onClickLike(element){
        var seq = $("#photoBoard_modal_like_seq").val();
        $.ajax({
            type : "POST",
            url : "photoBoard_likeProc.action",
            dataType : "text",
            data : {
                "seq" : seq
            },
            success : function(data){
                console.log(data);
                $("#photoBoard_modal_like").html("<input id='photoBoard_modal_like_seq' type='hidden' value='"+seq+"'><i id='photoBoard_modal_like_fa' onclick='onClickLike(this)' class='fa fa-heart' aria-hidden='true'></i> "+data);
            }
        });
    }
    
    function updateView(){
        var seq = $("#photoBoard_modal_like_seq").val();
        $.ajax({
            type : "POST",
            url : "photoBoard_viewProc.action",
            dataType : "text",
            data : {
                "seq" : seq
            },
            success : function(data){
                console.log(data);
                $("#photoBoard_modal_view").html("<i id='photoBoard_modal_view_fa' class='fa fa-eye' aria-hidden='true'></i> "+data);
            }
        });
    }
    
    $(document).on("click","#photoBoard_modal_download_fa",function(){
        var seq = $("#photoBoard_modal_like_seq").val();
        var fileName=$("#photoBoard_modal_download_fileName").val();
        var string='photoBoard_downloadProc.action?seq='+seq+'&fileName='+fileName
        location.href=string;
        
        
        $.ajax({
            type : "POST",
            url : "photoBoard_downloadCountProc.action",
            dataType : "text",
            data : {
                "seq" : seq
            },
            success : function(data){
                $("#photoBoard_modal_download").html("<input id='photoBoard_modal_download_fileName' type='hidden' value='"+fileName+"'><i id='photoBoard_modal_download_fa' class='fa fa-download' aria-hidden='true'></i> "+data);
            }
        });
    })
    
    
</script>
</body>
</html>
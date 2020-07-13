<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
<link href="join.css" type="text/css" rel="stylesheet" />
<script src="../js/jquery-3.1.1.js" type="text/javascript"></script>
<script type="text/javascript">
    var idOk = 0;
    $(document).ready(function() {
        $("#btnCheckUid").on("click", function() {
            //alert("123");
            $.ajax({
                type : "POST",
                url : "idcheck.do",
                dataType : "text",
                data : {
                    "mid" : $("#mid").val()
                },
                success : function(data) {
                    if ($.trim(data) == "1") {
                        idOk = 1;
                        alert("사용 가능한 아이디입니다.");
                    } else {
                        idOk = 0;
                        alert("이미 존재하는 아이디입니다.");
                    }
                }
            });
        });

    });

    function validate() {
        if (idOk != 1) {
            alert("아이디 중복확인을 해주세요.");
            return false;
        }

        if ($("#pwd").val() != $("#pwd2").val()) {
            alert("비밀번호가 다릅니다. 확인해주세요.");
            return false;
        }

        var birth = $('#year').val() + "-" + $('#month').val() + "-"
                + $('#day').val();
        $("#birthday").val(birth);
    }
</script>
</head>
<body>
    <div id="main">
        <div class="top-wrapper clear">
            <div id="content">
                <form action="join.do" method="post" onsubmit="return validate();">
                    <h2>회원가입</h2>
                    <h3 class="hidden">방문페이지 로그</h3>
                    <p id="breadscrumb" class="block_hlist clear">
                        <img alt="Step1 개인정보 등록" src="images/step2.png" />
                    </p>
                    <h3 class="hidden">회원가입 폼</h3>

                    <div id="join-form" class="join-form margin-large">

                        <dl class="join-form-row">
                            <dt class="join-form-title">*아이디</dt>
                            <dd class="join-form-data">
                                <input type="text" id="mid" name="mid" required placeholder="아이디 입력" pattern="[a-zA-Z][a-zA-Z0-9]{3,11}" /><span> 4~12자리 영문자(단 영어로 시작)</span> <input id="btnCheckUid"
                                    class="button" type="button" value="중복확인" />
                            </dd>
                        </dl>
                        <dl class="join-form-row">
                            <dt class="join-form-title">*비밀번호</dt>
                            <dd class="join-form-data">
                                <input type="password" name="pwd" id="pwd" required />
                            </dd>
                        </dl>
                        <dl class="join-form-row">
                            <dt class="join-form-title">*비밀번호 확인</dt>
                            <dd class="join-form-data">
                                <input type="password" name="pwd2" id="pwd2" required />
                            </dd>
                        </dl>
                        <dl class="join-form-row">
                            <dt class="join-form-title">*이름</dt>
                            <dd class="join-form-data">
                                <input type="text" name="name" required placeholder="이름 입력" />
                            </dd>
                        </dl>
                        <dl class="join-form-row">
                            <dt class="join-form-title">*성별</dt>
                            <dd class="join-form-data">
                                <input type="radio" name="gender" id="male" checked value="male" />남자 <input type="radio" name="gender" id="female" value="female" />여자
                            </dd>
                        </dl>
                        <dl class="join-form-row birthday">
                            <dt class="join-form-title">*생년월일</dt>
                            <dd class="join-form-data">
                                <span> <input type="text" name="year" id="year" required />년 <input type="text" name="month" id="month" required />월 <input type="text" name="day" id="day"
                                    required />일 <input type="text" name="birthday" id="birthday" />
                                </span>
                            </dd>
                        </dl>
                        <dl class="join-form-row">
                            <dt class="join-form-title">*나이</dt>
                            <dd class="join-form-data">
                                <input type="text" name="age" required />
                            </dd>
                        </dl>
                        <dl class="join-form-row">
                            <dt class="join-form-title">*핸드폰 번호</dt>
                            <dd class="join-form-data">
                                <input type="text" name="phone" required /><span> 대시(-)를 포함할 것: 예) 010-3456-2934 </span>
                            </dd>
                        </dl>
                        <dl class="join-form-row">
                            <dt class="join-form-title">취미</dt>
                            <dd class="join-form-data habit">
                                <input type="checkbox" name="habit" id="music" value="music" /><label for="music">음악</label> <input type="checkbox" name="habit" id="movie" value="movie" /><label
                                    for="movie">영화</label> <input type="checkbox" name="habit" id="trip" value="trip" /><label for="trip">여행</label>
                            </dd>
                        </dl>
                    </div>

                    <div id="buttonLine">
                        <input class="btn-okay button" type="submit" value="가입" />
                    </div>
                </form>
            </div>

        </div>
    </div>

</body>
</html>

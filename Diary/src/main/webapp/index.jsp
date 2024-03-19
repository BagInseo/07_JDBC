<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Diary</title>

    <style>
     .titleName{font-size : 50px;
                text-align : center;
     }
    .container {margin: auto;
                text-align : center;}

    .input{ margin : 2px  2px;}
    </style>
</head>
<body>

 <div class="titleName">Diary</div>
 <hr>
<div class="container">
   
    <form action="/diary/main" method="GET" name="inputForm">
    
    <div> 제목 : <input type="text" name="diaryTitle" class="input" id="diaryTitle" font-weight=bold></div>
    <div> 오늘 날씨 : <input type="text" name = "weather" class="input"></div>
    <div> 오늘의 기분 : <input type="text" name="feel" class="input"></div>
    <div><textarea name="diaryContent"  cols="70" rows="20" placeholder="내용 입력" id="diaryContent"></textarea></div>
    
    <button class="submitBtn">작성완료</button>
    <button type=button class= "listBtn" onClick = "location.href ='diary/diaryList'">일기 목록 보러가기</button>
    </form>
</div>

<c:if test="${not empty message}">
    <script>
        alert("${message}");
    </script>
    <c:remove var="message"/>
</c:if>

<script src= "/resources/js/main.js"></script>
</body>
</html>
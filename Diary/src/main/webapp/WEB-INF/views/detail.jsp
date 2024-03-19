<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>html문서 제목</title>

    <style>
        .container{
            text-align:center;}
        table{
            border-collapse : collapse;
        }

        table{
                padding: auto;
                margin-left:auto; 
                margin-right:auto;
                width: 550px;
            }

        table, td, th {
            border-collapse : collapse;
            border : 1px solid black;
        }

        .diary-content{
            white-space: pre-wrap;
            height : 300px;
        }

        button{margin : 5px;}
    </style>
</head>
<body>
<div class="container">
      <h2>제목 : ${diary.diaryTitle}</h2>

    <table border=1>
        <tr>
        <th>날씨</th>
        <td>${diary.diaryWeather}</td>
        </tr>

        <tr>
        <th>날짜</th>
        <td>${diary.regDate}</td>
        </tr>

        <tr>
        <th>오늘의 기분</th>
        <td>${diary.diaryFeel}</td>
        </tr>

        <tr>
        <th>오늘의 일기</th>
        <td class="diary-content">${diary.diaryContent}</td>
        </tr>
    </table>
    <button type="button" onClick="location.href='/'">메인 화면으로 가기</button>
    <button type="button" onClick="location.href='/diary/diaryList'">일기 리스트 보러가기</button>
    <button type="button" id="deleteBtn" data-diary-no="${diary.diaryNo}">삭제</button>

</div>
  
    <script src="/resources/js/detail.js"></script>
</body>
</html>
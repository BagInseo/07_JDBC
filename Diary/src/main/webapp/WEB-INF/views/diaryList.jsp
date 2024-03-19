<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Diary Lsit</title>

    <style>
    .titleName{font-size : 50px;
                text-align : center;}
        .container{
            text-align:center;}
        table{
                margin-left:auto; 
                margin-right:auto;

            }

        table, td, th {
            border-collapse : collapse;
            border : 1px solid black;
        }
        a{text-decoration: none;
        color: #006400;}

        button{margin : 5px;}
    </style>
</head>
<body>

<div class="titleName">DIARY LIST</div>
    <form action="/diary/diarySearch" method="GET">
    검색어 입력 : <input type="text" name="keyword">
    <button>검색</button>
    </form>
    <div class="container">
    <form action="/diary/diaryList" method="GET">
    
    <hr>

    <table border="1" >
    
        <thead>
            <th>NO</th>
            <th>Title</th>
            <th>Weather</th>
            <th>Feeling</th>
            <th>Date</th>
            <th>Delete</th>
        </thead>

        <tbody>
        
    <c:forEach items="${diaryList}" var="diary">
        <tr>
            <td>${diary.diaryNo}</td>
            <td>
            <a href="/diary/detail?diaryNo=${diary.diaryNo}"> ${diary.diaryTitle}</a>
            </td>
            <td>${diary.diaryWeather}</td>
            <td>${diary.diaryFeel}</td>
            <td>${diary.regDate}</td>

        <th>
            <button type ="button" class="delete-btn">삭제</button>
        </th>


        </tr>

    </c:forEach>
        </tbody>
    </table>

    <button type="button" onClick= "location.href ='/'">메인 화면으로 가기</button>
    </form>

    </div>

    <script src="/resources/js/diaryList.js"></script>

</body>
</html>
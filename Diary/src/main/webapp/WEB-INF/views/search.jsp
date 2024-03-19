<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${param.keyword}검색 결과</title>

    <style>
    a{ text-decoration: none;
        color: #006400;}

    .titleName{font-size : 50px;
        text-align : center;}
    .container{
            text-align:center;}

    table{
        margin-left:auto; 
        margin-right:auto;
        width: 550px;
    }
    button{margin : 5px;}
    </style>
</head>
<body>

    <div class="titleName">DIARY LIST(Search)</div>
    <hr>
    <div class="container">
        <table border="1">
        <thead>
			<tr>
                <th>NO</th>
				<th>Title</th>
				<th>Weather</th>
				<th>Feeling</th>
				<th>Date</th>
			</tr>
		</thead>

        <tbody>
        <c:forEach items="${diaryList}" var="diary" varStatus="vs">
            <tr>
                <td>${diary.diaryNo}</td>
                <td>
                <a href="/diary/detail?diaryNo=${diary.diaryNo}"> ${diary.diaryTitle}</a>
                </td>
                                
                <td>${diary.diaryWeather}</td>					
                <td>${diary.diaryFeel}</td>
                <td>${diary.regDate}</td>
            </tr>

           
        </c:forEach>
        </tbody>
    </table>
    <button onClick="location.href='/diary/diaryList'">리스트로 가기</button>
</div>
       
</body>
</html>
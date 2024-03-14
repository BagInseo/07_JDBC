<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${dept.deptId} 부서 수정 페이지</title>
</head>
<body>
    

    <h1>${dept.deptId} 부서 수정 페이지</h1>

    <%-- 현재 주소 : /department/update(GET) --%>

    <form action="update" method="POST">
        <div>
            부서 코드(DEPT_ID) : <input type="text" name="deptId" value="${dept.deptId}" readonly>
            <%-- readonly : 읽기 전용(input 태그 값 수정 불가) --%>   
        </div>

        <div>
            부서명(DEPT_TITLE) : <input type="text" name = "deptTitle" value="${dept.deptTitle}" placeholder="수정할 부서명">
        </div>
        <div>
            지역 코드(LOCATION_ID) : <input type="text" name = locationId value="${dept.locationId}" placeholder="수정할 지역 코드">
        </div>

        <button >수정하기</button>   
    </form>
</body>
</html>
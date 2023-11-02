<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인페이지</title>
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    <style>
        /* 테이블 스타일 */
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 10px;
        }

        th {
            background-color: #f2f2f2;
        }

        /* 버튼 스타일 */
        a[type="button"] {
            display: inline-block;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            background-color: #007bff;
            color: #fff;
            border: 1px solid #007bff;
            border-radius: 5px;
            margin-right: 10px;
        }

        a[type="button"]:hover {
            background-color: #0056b3;
            border: 1px solid #0056b3;
        }
    </style>
</head>
<body>
        <tr>
            <th>아이디</th>
            <td>${euser.name }</td>
        </tr>
        <tr>
            <th>이름</th>
            <td>${euser.username }</td>
        </tr>
        <tr>
            <th>이메일</th>
            <td>${euser.email }</td>
        </tr>
        <tr>
            <th>레벨</th>
            <td>${euser.lev }</td>
        </tr>
        <tr>
            <th>활동여부</th>
            <td>${euser.act }</td>
        </tr>

    <a href="${path }/user/With.do?name=${euser.name }">탈퇴하기</a>
    <a href="${path }/user/Join.do?name=${euser.name }">휴면하기</a>
    <a href="${path }/user/Dormant.do?name=${euser.name }">활동하기</a>

</body>

</html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>userList</title>
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
            padding: 8px;
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

    <c:forEach items="${euserList }" var="userList" varStatus="status">
        <thead>
        <tr>
            <th>#</th>
            <th>아이디</th>
            <th>이름</th>
            <th>이메일</th>
            <th>레벨</th>
            <th>활동여부</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${status.count }</td>
            <td ><a href="${path }/user/Get.do?name=${userList.name }">${userList.name }</a></td>
            <td>${userList.username }</td>
            <td>${userList.email }</td>
            <td>${userList.lev }</td>
            <td>${userList.act }</td>
        </tr>
        </tbody>
    </c:forEach>
</body>
</html>
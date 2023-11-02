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
</head>
<body>
    <h1>메인 페이지요</h1>

    <a href="${path }/user/List.do">회원 리스트</a>
    <a href="${path }/user/login.do">로그인</a>
</body>
</html>
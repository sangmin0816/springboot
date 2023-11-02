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
<form action="${path }/Idlogin.do" method="post" >
<input type="text" id="name" name="name" placeholder="아이디" required>
<input type="password" id="pw" name="pw" placeholder="비밀번호" required>

    <button type="submit">로그인</button>
</form>


</body>
</html>
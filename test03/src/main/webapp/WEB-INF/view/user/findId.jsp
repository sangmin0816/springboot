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
<form action="" method="Get" onsubmit="findId(this)" >
<input type="text" id="email" name="email" placeholder="이메일" required>
<input type="password" id="tel" name="tel" placeholder="전화번호" required>

    <button type="submit" >찾기</button>
</form>
<script>
    function findId(f){
        var url ="${path }/user/FindIdp.do"
        var size ="width=500px, height=500px"
        window.open(url, 'child', size)
    }

</script>

</body>
</html>
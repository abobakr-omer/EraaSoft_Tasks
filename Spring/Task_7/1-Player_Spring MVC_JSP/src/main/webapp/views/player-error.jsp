<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>Player request error</title></head>
<body>
    <h1>Request could not be completed (<c:out value="${status}" />)</h1>
    <p><c:out value="${message}" /></p>
    <p><a href="${pageContext.request.contextPath}/player/show-players">Back to Players</a></p>
</body>
</html>


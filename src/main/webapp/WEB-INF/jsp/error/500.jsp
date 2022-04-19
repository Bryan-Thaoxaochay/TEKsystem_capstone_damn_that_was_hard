<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>500 Error</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav.css">
</head>
<body>
<jsp:include page="../include/nav.jsp" />

<h1>500 Error</h1>

<c:if test="${not empty requestUrl}">
    <p>${requestUrl}</p>
</c:if>
<h3>Stack Trace</h3>
<c:if test="${not empty message}">
    <p>${message}</p>
</c:if>
<c:if test="${not empty stackTrace}">
    <p>${stackTrace}</p>
</c:if>
<h3>Root Cause</h3>
<c:if test="${not empty rootcause}">
    <p>${rootcause}</p>
</c:if>
<c:if test="${not empty rootTrace}">
    <p>${roottrace}</p>
</c:if>

</body>
</html>
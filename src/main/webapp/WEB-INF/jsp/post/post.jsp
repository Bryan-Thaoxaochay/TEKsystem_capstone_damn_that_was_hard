<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>${post.title}</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav.css">
    <link rel="stylesheet" type="text/css" href="../../../pub/css/post.css">
    <script src="../../../pub/js/editComment.js" defer ></script>
</head>
<body>
<jsp:include page="../include/nav.jsp" />
<header>
    <h1>${postUser.username}</h1>
    <p>${post.createDate}</p>
    <sec:authorize access="isAuthenticated()">
        <c:if test="${currentUser.userId != post.userId}">
            <form action="/posts/save" method="post">
                <input type="hidden" name="id" value="${post.id}">
                <button type="submit">Save Post</button>
            </form>
        </c:if>

        <c:if test="${currentUser.userId == post.userId}">
            <a href="/posts/post/edit/${post.id}">Edit Post</a>
            <form action="/posts/delete/${post.id}" method="post">
                <input type="hidden" name="id" value="${post.id}"></label>
                <button type="submit">Delete Post</button>
            </form>
        </c:if>
    </sec:authorize>
</header>
<main>
    <h2>${post.title}</h2>
    <p>
        ${post.description}
    </p>
</main>

<sec:authorize access="isAuthenticated()">
    <section>
        <jsp:include page="../comment/create_comment.jsp" />
    </section>
</sec:authorize>

<table>
    <jsp:include page="../comment/comments.jsp" />
</table>
</body>
</html>
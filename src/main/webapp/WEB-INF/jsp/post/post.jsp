<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Damn That Was Hard</title>

    <link rel="stylesheet" type="text/css" href="../../../pub/css/posts.css">
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav_footer.css">
    <script src="../../../pub/js/editComment.js" defer ></script>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../include/nav.jsp" />

<main>
    <sec:authorize access="isAuthenticated()">
        <a href="/posts/post/edit/${post.id}">Edit Post</a>
        <form action="/posts/delete/${post.id}" method="post">
            <input type="hidden" name="id" value="${post.id}"></label>
            <button type="submit">Delete Post</button>
        </form>
    </sec:authorize>
    <form action="/posts/save" method="post">
        <input type="hidden" name="id" value="${post.id}">
        <button type="submit">Save Post</button>
    </form>
    <h1>${post.topic}</h1>
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
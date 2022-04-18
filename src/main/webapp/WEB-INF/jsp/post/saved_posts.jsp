<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Stories</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav.css">
    <link rel="stylesheet" type="text/css" href="../../../pub/css/posts.css">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
<jsp:include page="../include/nav.jsp" />
<h2>Saved Stories</h2>
<main id="recentPosts">
    <c:forEach items="${posts}" var="post">
        <section>
            <h3>${post.topic}</h3>
            <h4>${post.title}</h4>
            <a href="/posts/post/${post.id}">Read</a>
            <form action="/posts/saved_posts/${post.id}" method="post">
                <input type="hidden" name="postId" value="${post.id}">
                <button type="submit"><i class="fas fa-trash-alt"></i></button>
            </form>
        </section>
    </c:forEach>
</main>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Damn That Was Hard</title>

    <link rel="stylesheet" type="text/css" href="../../../pub/css/posts.css">
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav_footer.css">
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/nav.jsp" />

<main id="recentPosts">
    <h2>Recent Stories</h2>
    <c:forEach items="${posts}" var="post">
        <section>
            <h3>${post.topic}</h3>
            <h4>${post.title}</h4>
            <a href="/posts/post/${post.id}">Read Story</a>
            <form action="/posts/saved_posts/${post.id}" method="post">
                <input type="hidden" name="postId" value="${post.id}">
                <button type="submit">Remove Saved Story</button>
            </form>
        </section>
    </c:forEach>
</main>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Stories</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav.css">
    <link rel="stylesheet" type="text/css" href="../../../pub/css/posts.css">
</head>
<body>
<jsp:include page="../include/nav.jsp" />
<h2>Stories</h2>
<main id="recentPosts">
    <c:forEach items="${posts}" var="post">
        <section>
            <h3>${post.topic}</h3>
            <h4>${post.title}</h4>
            <a href="/posts/post/${post.id}">Read Story</a>
            <sec:authorize access="isAuthenticated()">
                <form action="/posts/saved_posts/${post.id}" method="post">
                    <input type="hidden" name="postId" value="${post.id}">
                    <button type="submit">Remove Saved Story</button>
                </form>
            </sec:authorize>
        </section>
    </c:forEach>
</main>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp" />
    <title>Stories</title>
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
            <a href="/posts/post/${post.id}">Read</a>
        </section>
    </c:forEach>
</main>

<jsp:include page="../include/footer.jsp" />
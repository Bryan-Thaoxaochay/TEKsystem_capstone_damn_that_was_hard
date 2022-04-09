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

<main>
    <h1>${post.title}</h1>
    <h2>${post.topic}</h2>
    <p>
        ${post.description}
    </p>
</main>

<section>
    <jsp:include page="../comment/comments.jsp" />
</section>

<jsp:include page="../include/footer.jsp" />

</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Write a Story</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav.css">
    <link rel="stylesheet" type="text/css" href="../../../pub/css/create_post.css">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
<jsp:include page="../include/nav.jsp" />

<main>
    <c:choose>
    <c:when test = "${not empty post}">
        <h2>Edit Post</h2>
        <form action="/posts/edit/${post.id}" id="editPostForm" method="post">
            <fieldset>
                <input type="hidden" name="id" value="${post.id}">
                <input type="hidden" name="userId" value="${post.userId}">
                <label for="editPostForm">Topic </label><input type="text" name="topic" class="topic" value="${post.topic}">
                <label for="editPostForm">Title </label><input type="text" name="title" class="title" value="${post.title}">
                <label for="editPostForm">Description </label><textarea type="text" name="description" class="description" value="${post.description}"></textarea>
                <button type="submit">Edit Post</button>
            </fieldset>
        </form>
    </c:when>
    <c:otherwise>
    <h2>Write a Post</h2>
    <form action="/posts/create/${user.userId}" id="createPostForm" method="post">
        <fieldset>
            <input type="hidden" name="userId" value="${user.userId}">
            <label for="createPostForm">Topic</label><input type="text" name="topic" class="topic">
            <label for="createPostForm">Title</label><input type="text" name="title" class="title">
            <label for="createPostForm">Story</label><textarea type="text" name="description" class="description" placeholder="Feel free to tell your story here..."></textarea>
            <button type="submit"><i class="fas fa-pen"></i> Publish</button>
        </fieldset>
    </form>
    </c:otherwise>
    </c:choose>
</main>
</body>
</html>
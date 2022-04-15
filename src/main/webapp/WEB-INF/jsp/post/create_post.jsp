<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Damn That Was Hard</title>

    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav.css">
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
                <label for="editPostForm">Topic <input type="text" name="topic" class="topic" value="${post.topic}"></label>
                <label for="editPostForm">Title <input type="text" name="title" class="title" value="${post.title}"></label>
                <label for="editPostForm">Description <input type="text" name="description" class="description" value="${post.description}"></label>
                <button type="submit">Edit Post</button>
            </fieldset>
        </form>
    </c:when>
    <c:otherwise>
    <h2>Create a Post</h2>
    <form action="/posts/create/${user.userId}" id="createPostForm" method="post">
        <fieldset>
            <input type="hidden" name="userId" value="${user.userId}">
            <label for="createPostForm">Topic <input type="text" name="topic" class="topic" placeholder="Enter a topic"></label>
            <label for="createPostForm">Title <input type="text" name="title" class="title" placeholder="Enter a title"></label>
            <label for="createPostForm">Description <input type="text" name="description" class="description" placeholder="Feel free to tell your story here"></label>
            <button type="submit">Create Post</button>
        </fieldset>
    </form>
    </c:otherwise>
    </c:choose>
</main>
</body>
</html>
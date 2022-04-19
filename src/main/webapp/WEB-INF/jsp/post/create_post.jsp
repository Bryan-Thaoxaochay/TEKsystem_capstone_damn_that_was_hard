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
                <label for="editPostForm">Topic </label>
                <select name="topic" id="editPostForm" value="${post.topic}">
                    <option value="Career">Career</option>
                    <option value="Education">Education</option>
                    <option value="Health/Wellness">Health and Wellness</option>
                    <option value="Money">Money</option>
                    <option value="Outdoors">Outdoors</option>
                    <option value="Relationship">Relationships</option>
                    <option value="Other">Other</option>
                </select>
                <c:forEach items="${bindingResult.getFieldErrors('topic')}" var="error">
                    <div class="createPostErrMsg">
                            ${error.getDefaultMessage()}
                    </div>
                </c:forEach>

                <label for="editPostForm">Title </label><input type="text" name="title" class="title" value="${post.title}">
                <c:forEach items="${bindingResult.getFieldErrors('title')}" var="error">
                    <div class="createPostErrMsg">
                            ${error.getDefaultMessage()}
                    </div>
                </c:forEach>

                <label for="editPostForm">Description </label><textarea type="text" name="description" class="description">${post.description}</textarea>
                <c:forEach items="${bindingResult.getFieldErrors('description')}" var="error">
                    <div class="createPostErrMsg">
                            ${error.getDefaultMessage()}
                    </div>
                </c:forEach>

                <button type="submit"><i class="fas fa-pen"></i> Save</button>
            </fieldset>
        </form>
    </c:when>
    <c:otherwise>
    <h2>Write a Post</h2>
    <form action="/posts/create/${user.userId}" id="createPostForm" method="post">
        <fieldset>
            <input type="hidden" name="userId" value="${user.userId}">
            <label for="createPostForm">Topic</label>
                <select name="topic" id="createPostForm">
                    <option value="Career">Career</option>
                    <option value="Education">Education</option>
                    <option value="Health/Wellness">Health and Wellness</option>
                    <option value="Money">Money</option>
                    <option value="Outdoors">Outdoors</option>
                    <option value="Relationship">Relationships</option>
                    <option value="Other">Other</option>
                </select>
            <c:forEach items="${bindingResult.getFieldErrors('topic')}" var="error">
                <div class="createPostErrMsg">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>

            <label for="createPostForm">Title</label><input type="text" name="title" class="title">
            <c:forEach items="${bindingResult.getFieldErrors('title')}" var="error">
                <div class="createPostErrMsg">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>

            <label for="createPostForm">Story</label><textarea type="text" name="description" class="description" placeholder="Feel free to tell your story here..."></textarea>
            <c:forEach items="${bindingResult.getFieldErrors('description')}" var="error">
                <div class="createPostErrMsg">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>

            <button type="submit"><i class="fas fa-pen"></i> Publish</button>
        </fieldset>
    </form>
    </c:otherwise>
    </c:choose>
</main>
</body>
</html>
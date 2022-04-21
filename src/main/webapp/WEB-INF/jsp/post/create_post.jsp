<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />
    <title>Write a Story</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/create_post.css">
</head>

<body>
<jsp:include page="../include/nav.jsp" />

<main>
    <c:choose>
    <%-- Editing a Post --%>
    <c:when test = "${not empty post}">
        <h2>Edit Post</h2>
        <form action="/posts/edit/${post.id}" id="editPostForm" method="post">
            <fieldset>
                <input type="hidden" name="id" value="${post.id}">
                <input type="hidden" name="userId" value="${post.userId}">

                <label for="editPostForm">Topic </label>
                <select name="topic" id="editPostForm">
                    <option ${post.topic}>${post.topic}</option>
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
    <%-- Writing a Post --%>
    <c:otherwise>
    <h2>Write a Post</h2>
    <form action="/posts/create/${currentUser.userId}" id="createPostForm" method="post">
        <fieldset>
            <input type="hidden" name="userId" value="${currentUser.userId}">

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

<jsp:include page="../include/footer.jsp" />
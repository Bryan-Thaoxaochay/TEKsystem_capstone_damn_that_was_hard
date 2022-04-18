<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>${post.title}</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav.css">
    <link rel="stylesheet" type="text/css" href="../../../pub/css/post.css">
    <script src="../../../pub/js/editComment.js" defer ></script>
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
<jsp:include page="../include/nav.jsp" />
<main>
    <header>
        <h1>${postUser.username}</h1>
        <sec:authorize access="isAuthenticated()">
            <c:if test="${currentUser.userId != post.userId}">
                <form action="/posts/save" method="post">
                    <input type="hidden" name="id" value="${post.id}">
                    <button type="submit">Save Post</button>
                </form>
            </c:if>

            <c:if test="${currentUser.userId == post.userId}">
                <div>
                    <a href="/posts/post/edit/${post.id}"><i class="fas fa-edit"></i></a>
                    <form action="/posts/delete/${post.id}" method="post">
                        <input type="hidden" name="id" value="${post.id}"></label>
                        <button type="submit"><i class="fas fa-trash-alt"></i></button>
                    </form>
                </div>
            </c:if>
        </sec:authorize>
    </header>
    <p>${post.createDate}</p>
    <section>
        <h2>${post.title}</h2>
        <p>
            ${post.description}
        </p>
    </section>
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
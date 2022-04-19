<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${comments}" var="comment">
    <div id="commentDiv">
        <header>
            <section>
                <h5>${postUser.username}</h5>
                <p><em>${comment.updateDate}</em></p>
            </section>
            <c:if test="${comment.userId == currentUser.userId}">
                <div id="editDeleteDiv">
                    <button type="button" class="editCommentBtn" title="Edit comment"><i class="fas fa-edit"></i></button>
                    <form action="/comment/delete/${comment.id}" method="post">
                        <input type="hidden" name="id" value="${post.id}">
                        <button title="Delete comment"><i class="fas fa-trash-alt"></i></button>
                    </form>
                </div>
            </c:if>
        </header>

        <hr>

        <p data-id="${comment.id}" data-postId="${comment.blogpostId}" data-userId="${comment.userId}">${comment.comment}</p>
    </div>
</c:forEach>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${comments}" var="comment">
    <tr>
        <td>
                ${comment.comment}
        </td>
        <c:if test="${comment.userId == user.userId}">
            <td id="editCommentTd-${comment.id}">
                <button type="button" class="editCommentBtn">edit comment</button>
            </td>
            <td>
                <form action="/comment/delete/${comment.id}" method="post">
                    <input type="hidden" name="id" value="${post.id}">
                    <button>delete comment</button>
                </form>
            </td>
        </c:if>
    </tr>
</c:forEach>

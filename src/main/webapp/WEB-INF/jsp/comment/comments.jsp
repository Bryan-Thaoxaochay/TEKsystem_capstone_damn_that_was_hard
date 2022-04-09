<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${comments}" var="comment">
    <tr>
        <td>
                ${comment.comment}
        </td>
    </tr>
</c:forEach>

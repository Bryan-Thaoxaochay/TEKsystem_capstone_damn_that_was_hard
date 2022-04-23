<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />
    <title>${currentUser.firstName} ${currentUser.lastName}'s Profile</title>
<%--    <script src="../../../pub/js/user/editUserForm.js" defer></script>--%>
</head>

<body>
<jsp:include page="../include/nav.jsp" />

<main id="userInfoContainer">
    <h1 id="name">${currentUser.firstName} ${currentUser.lastName}</h1><button id="userEditLink"><i class="fas fa-pen"></i></button>
    <p id="username">Username: ${currentUser.username}</p>
    <p id="email">Email: ${currentUser.email}</p>
</main>

<%--<section>--%>
<%--    <c:forEach items="${bindingResult.getFieldErrors('firstName')}" var="error">--%>
<%--        <div class="editUserErrMsg">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <c:forEach items="${bindingResult.getFieldErrors('lastName')}" var="error">--%>
<%--        <div class="editUserErrMsg">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <c:forEach items="${bindingResult.getFieldErrors('username')}" var="error">--%>
<%--        <div class="editUserErrMsg">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">--%>
<%--        <div class="editUserErrMsg">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--</section>--%>

<jsp:include page="../include/footer.jsp" />
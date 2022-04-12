<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<footer>
    <h2>Damn That Was Hard</h2>
    <sec:authorize access="!isAuthenticated()">
        <p>If you'd like to share or comment on a post, consider creating an account.</p>
        <p>Click <a href="/user/sign_up">here</a> to sign up.</p>
        <p>If you already have an account: click <a href="/user/sign_in">here</a> to sign in.</p>
    </sec:authorize>
</footer>
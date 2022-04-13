<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav>
    <h1><a href="/home/index">Damn That Was Hard</a></h1>
    <h3>Feel free to tell us a story of triumph in your life or someone else's life.</h3>
    <ul>
        <sec:authorize access="!isAuthenticated()">
            <li>
                <a href="/user/sign_in">Sign In</a>
            </li>
            <li>
                <a href="/user/sign_up">Sign Up</a>
            </li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li>
                <a href="/posts/create_post">Create a Post</a>
            </li>
            <li>
                <a href="/posts/my_posts">My Posts</a>
            </li>
            <li>
                <a href="/user/logout">Sign Out</a>
            </li>
        </sec:authorize>

    </ul>
</nav>
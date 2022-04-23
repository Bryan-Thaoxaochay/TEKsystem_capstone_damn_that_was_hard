<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav>
    <h1><a href="/home/index">Damn That Was Hard</a></h1>
    <h3>Feel free to tell us a story of triumph in your life or someone else's life.</h3>
    <sec:authorize access="!isAuthenticated()">
    <ul>
            <li>
                <a href="/user/sign_in">Sign In</a>
            </li>
            <li>
                <a href="/user/sign_up">Sign Up</a>
            </li>

    </ul>
    </sec:authorize>
    <sec:authorize access="hasAnyAuthority('ADMIN', 'USER')">
    <ul id="navAuthenticated">
        <li>
            <p>Posts</p>
            <div class="post-dropdown">
                <a href="/posts">All Posts</a>
                <a href="/posts/my_posts">My Posts</a>
                <a href="/posts/saved_posts">Saved Posts</a>
                <a href="/posts/create_post">Create a Post</a>
            </div>
        </li>
        <li>
            <p>Account</p>
            <div class="account-dropdown">
                <a href="/user/information">Profile</a>
                <a href="/user/logout">Sign Out</a>
            </div>
        </li>
    </ul>
    </sec:authorize>
</nav>
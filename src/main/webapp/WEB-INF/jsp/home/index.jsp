<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp" />
    <title>Damn That Was Hard</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/index.css">
</head>

<body>
<jsp:include page="../include/nav.jsp" />

<main>
    <section class="topSection">
        <h3>Everyone has a motivating story to tell.</h3>
        <a href="/posts">Click Here to Read Them</a>
        <blockquote>If you're going through hell, keep going.</blockquote>
        <p><em>~ Winston Churchill</em></p>
    </section>

    <img src="../../../pub/images/boat_sunset.webp" alt="boat sunset">
    <img src="../../../pub/images/mountain%20sunrise.jpeg" class="bottomSection" alt="mountain sunrise">

    <section class="bottomSection">
        <sec:authorize access="!isAuthenticated()">
            <p>If you'd like to share or comment on a post, consider creating an account.</p>
            <p>Click <a href="/user/sign_up">here</a> to sign up.</p>
            <p>If you already have an account: click <a href="/user/sign_in">here</a> to sign in.</p>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <p>Welcome Back ${user.username}</p>
        </sec:authorize>
    </section>
</main>

<jsp:include page="../include/footer.jsp" />
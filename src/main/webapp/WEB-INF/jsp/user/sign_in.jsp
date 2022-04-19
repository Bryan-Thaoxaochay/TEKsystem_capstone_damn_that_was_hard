<jsp:include page="../include/header.jsp" />
    <title>Sign In</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/sign_in.css">
</head>
<body>
<jsp:include page="../include/nav.jsp" />

<div id="backgroundDiv">
    <main>
        <h2>Welcome Back</h2>
        <p>Please Log In</p>
        <form action="/user/sign_in/submit" id="signInForm" method="post">
            <label for="signInForm">Username: <input type="text" name="username"></label>
            <label for="signInForm">Password: <input type="text" name="password"></label>
              <div id="signInErrorMsg">${signInErrorMsg}</div>
            <button type="submit">Sign In</button>
        </form>
        <p>If you don't have an account: <a href="/user/sign_up">Create an Account</a></p>
    </main>
</div>

<jsp:include page="../include/footer.jsp" />
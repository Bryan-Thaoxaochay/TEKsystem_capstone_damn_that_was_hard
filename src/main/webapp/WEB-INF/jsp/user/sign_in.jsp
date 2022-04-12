<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Damn That Was Hard</title>

    <link rel="stylesheet" type="text/css" href="../../../pub/css/sign-in.css">
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav_footer.css">
</head>
<body>
<jsp:include page="../include/nav.jsp" />

<main>
    <h2>Welcome Back</h2>
    <form action="/user/sign_in/submit" id="signInForm" method="post">
      <fieldset>
        <legend>Please Log In</legend>
        <label for="signInForm">Username: <input type="text" name="username" placeholder="Enter your username"></label>
        <label for="signInForm">Password: <input type="text" name="password" placeholder="Enter your password"></label>
        <button type="submit">Login</button>
      </fieldset>
    </form>
</main>

<section>
    <p>If you don't have an account: <a href="/user/sign_up">create an account</a></p>
</section>

<jsp:include page="../include/footer.jsp" />

</body>
</html>
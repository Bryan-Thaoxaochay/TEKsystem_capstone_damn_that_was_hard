<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Sign In</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav.css">
    <link rel="stylesheet" type="text/css" href="../../../pub/css/sign-in.css">
</head>
<body>
<jsp:include page="../include/nav.jsp" />
<main>
    <h2>Welcome Back</h2>
    <form action="/user/sign_in/submit" id="signInForm" method="post">
      <fieldset>
        <legend>Please Log In</legend>
        <label for="signInForm">Username: <input type="text" name="username"></label>
        <label for="signInForm">Password: <input type="text" name="password"></label>
          <div style="color: red">${signInErrorMsg}</div>
        <button type="submit">Sign In</button>
      </fieldset>
    </form>
</main>

<section>
    <p>If you don't have an account: <a href="/user/sign_up">Create an Account</a></p>
</section>
</body>
</html>
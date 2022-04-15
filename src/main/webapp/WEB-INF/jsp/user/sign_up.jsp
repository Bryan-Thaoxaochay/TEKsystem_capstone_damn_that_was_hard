<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Damn That Was Hard</title>

    <link rel="stylesheet" type="text/css" href="../../../pub/css/sign-up.css">
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav.css">
</head>
<body>
<jsp:include page="../include/nav.jsp" />

<main>
    <form action="/user/create" method="post" name="signUpForm" id="signUpForm">
        <h2>Create an Account</h2>
        <fieldset>
            <legend>Fill in the info below</legend>
            <label for="signUpForm" id="firstNameLabel">First Name: <input type="text" name="firstName" id="firstName" placeholder="Enter your first name"></label>

            <label for="signUpForm" id="lastNameLabel">Last Name: <input type="text" name="lastName" id="lastName" placeholder="Enter your last name"></label>

            <label for="signUpForm" id="emailLabel">Email: <input type="text" name="email" id="email" placeholder="Enter your email"></label>

            <label for="signUpForm" id="passwordLabel">Password: <input type="text" name="password" id="password" placeholder="Enter your password"></label>

            <label for="signUpForm" id="confirmPasswordLabel">Confirm Password: <input type="text" name="confirmPassword" id="confirmPassword" placeholder="Confirm your password"></label>

            <label for="signUpForm" id="usernameLabel">Username: <input type="text" name="username" id="username" placeholder="Enter your username"></label>

            <button type="submit">Sign Up</button>
        </fieldset>
    </form>
</main>

<section>
    <p>If you already have an account: <a href="/user/sign_in">sign in</a></p>
</section>
</body>
</html>
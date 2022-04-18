<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav.css">
    <link rel="stylesheet" type="text/css" href="../../../pub/css/sign_up.css">
</head>
<body>
<jsp:include page="../include/nav.jsp" />
<div id="signUpBackgroundDiv">
    <main>
        <h2>Create an Account</h2>
        <p>Please fill in the info below</p>
        <form action="/user/create" method="post" name="signUpForm" id="signUpForm">
            <label for="signUpForm" id="firstNameLabel">First Name: <input type="text" name="firstName" value="${form.firstName}"></label>
            <c:forEach items="${bindingResult.getFieldErrors('firstName')}" var="error">
                <div class="signUpErrMsg">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>

            <label for="signUpForm" id="lastNameLabel">Last Name: <input type="text" name="lastName" value="${form.lastName}"></label>
            <c:forEach items="${bindingResult.getFieldErrors('lastName')}" var="error">
                <div class="signUpErrMsg">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>

            <label for="signUpForm" id="emailLabel">Email: <input type="text" name="email" value="${form.email}"></label>
            <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                <div class="signUpErrMsg">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>

            <label for="signUpForm" id="passwordLabel">Password: <input type="password" name="password" value="${form.password}"></label>
            <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                <div class="signUpErrMsg">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>

            <label for="signUpForm" id="confirmPasswordLabel">Confirm Password: <input type="password" name="confirmPassword" value="${form.confirmPassword}"></label>
            <c:forEach items="${bindingResult.getFieldErrors('confirmPassword')}" var="error">
                <div class="signUpErrMsg">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
            <div id="confirmPasswordErrMsg">
                ${passwordMessage}
            </div>

            <label for="signUpForm" id="usernameLabel">Username: <input type="text" name="username" value="${form.username}"></label>
            <p>Your username is how you will be displayed on this site.</p>
            <c:forEach items="${bindingResult.getFieldErrors('username')}" var="error">
                <div class="signUpErrMsg">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>

            <button type="submit">Sign Up</button>
            <p>If you already have an account: <a href="/user/sign_in">Sign In</a></p>
        </form>
    </main>
</div>
</body>
</html>
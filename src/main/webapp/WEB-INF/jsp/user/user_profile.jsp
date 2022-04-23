<jsp:include page="../include/header.jsp" />
</head>

<body>
<jsp:include page="../include/nav.jsp" />

<main>
    <h1>${currentUser.firstName} ${currentUser.lastName}</h1><a href="/user/edit"><i class="fas fa-pen"></i></a>
    <p>Username: ${currentUser.username}</p>
    <p>Email: ${currentUser.email}</p>
</main>

<jsp:include page="../include/footer.jsp" />
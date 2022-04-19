<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Can't Find Page</title>
    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav.css">
    <style>
        div {
            align-items: center;
            display: flex;
            flex-direction: column;
        }

        img {
            height: 40vh;
            margin-top: 1rem;
            width: 30vw;
        }

        div > h1 {
            margin-bottom: 0;
        }

        div > h2 {
            font-size: medium;
            margin-top: 0;
        }

        div > p > a {
            color: yellow;;
            text-decoration: none;
        }

        div > p > a:hover {
            color: red;
        }
    </style>
</head>
<body>
<jsp:include page="../include/nav.jsp" />

<div>
    <img src="../../../pub/images/where_am_i_404.png">
    <h1>WE BELIEVE YOU CAN DO ANYTHING</h1>
    <h2>..........except go to this page.</h2>
    <p>Click <a href="/home/index">here</a> to return home.</p>
</div>

</body>
</html>
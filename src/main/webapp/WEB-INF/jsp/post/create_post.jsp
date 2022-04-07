<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Damn That Was Hard</title>

    <link rel="stylesheet" type="text/css" href="../../../pub/css/nav_footer.css">
</head>
<body>
<jsp:include page="../include/nav.jsp" />

<main>
    <h2>Create a Post</h2>
    <form action="/post/new_post" id="postForm" method="post">
        <fieldset>
            <label for="postForm">Topic <input type="text" name="topic" id="topic" placeholder="Enter a topic"></label>
            <label for="postForm">Title <input type="text" name="title" id="title" placeholder="Enter a title"></label>
            <label for="postForm">Description <input type="text" name="description" id="description" placeholder="Feel free to tell your story here"></label>
            <button type="submit">Create Post</button>
        </fieldset>
    </form>
</main>

<jsp:include page="../include/footer.jsp" />

</body>
</html>
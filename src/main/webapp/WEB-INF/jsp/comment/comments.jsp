<fieldset>
    <legend>Comment on the Story</legend>
    <form action="/posts/post/${post.id}/create_comment" method="post" id="commentForm">
        <label for="commentForm"><input type="text" name="comment" id="comment" placeholder="Enter a comment"></label>
        <button type="submit">Add Comment</button>
    </form>
</fieldset>
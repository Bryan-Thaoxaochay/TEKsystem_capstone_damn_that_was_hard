<fieldset>
    <legend>What are your thoughts?</legend>
    <form action="/comment/create/${post.id}" method="post" id="commentForm">
        <label for="commentForm"><input type="text" name="comment" id="comment" placeholder="Write a comment..."></label>
        <button type="submit"><i class="fas fa-pencil-alt"> Comment</i></button>
    </form>
</fieldset>
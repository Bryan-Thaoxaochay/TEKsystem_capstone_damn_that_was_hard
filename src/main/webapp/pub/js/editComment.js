let editCommentBtns = document.getElementsByClassName("editCommentBtn");
document.addEventListener("click", function (e) {
    if (editCommentBtns[0] === e.target) {
        showEditCommentForm(e.target);
    }
})

function showEditCommentForm(button) {
    let editBtn = button;
    let editBtnTd = editBtn.parentElement;
    let commentTd = editBtnTd.previousElementSibling;
    let pEl = commentTd.firstElementChild;

    let formEl = document.createElement("form");
    let labelEl = document.createElement("label");
    let inputEl = document.createElement("input");
    let buttonEl = document.createElement("button");

    commentTd.appendChild(formEl);
    formEl.appendChild(labelEl);
    formEl.appendChild(buttonEl);
    labelEl.appendChild(inputEl);

    let commentId = pEl.getAttribute("data-id");
    let postId = pEl.getAttribute("data-postId");
    let userId = pEl.getAttribute("data-userId");

    formEl.setAttribute("action", `/comment/edit/${commentId}/${postId}/${userId}`);
    formEl.setAttribute("method", "post");
    formEl.setAttribute("id", "editCommentForm");
    labelEl.setAttribute("for", "editCommentForm");
    inputEl.setAttribute("type", "text");
    inputEl.setAttribute("name", "comment");
    inputEl.setAttribute("value", pEl.innerText);
    buttonEl.setAttribute("type", "submit");

    buttonEl.innerHTML = "Save";
}

// <form action="/comment/create/${post.id}" method="post" id="commentForm">
//     <label htmlFor="commentForm"><input type="text" name="comment" id="comment" placeholder="Enter a comment"></label>
//     <button type="submit">Add Comment</button>
// </form>
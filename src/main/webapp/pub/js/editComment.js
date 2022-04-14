document.addEventListener("click", function (e) {
    showEditCommentForm(e.target);
})

function showEditCommentForm(button) {
    let editBtn = button;
    let editBtnTd = editBtn.parentElement;
    let commentTd = editBtnTd.previousElementSibling;

    let tdEl = document.createElement("td");
    let formEl = document.createElement("form");
    let labelEl = document.createElement("label");
    let inputEl = document.createElement("input");
    let buttonEl = document.createElement("button");

    commentTd.appendChild(tdEl);
    tdEl.appendChild(formEl);
    formEl.appendChild(labelEl);
    formEl.appendChild(buttonEl);
    labelEl.appendChild(inputEl);

}

// <form action="/comment/create/${post.id}" method="post" id="commentForm">
//     <label htmlFor="commentForm"><input type="text" name="comment" id="comment" placeholder="Enter a comment"></label>
//     <button type="submit">Add Comment</button>
// </form>
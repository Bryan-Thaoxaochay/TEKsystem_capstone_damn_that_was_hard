let editCommentBtns = document.getElementsByClassName("editCommentBtn");
document.addEventListener("click", function (e) {
    if (editCommentBtns[0] === e.target) {
        showEditCommentForm(e.target);
    }
})

function showEditCommentForm(button) {
    const editBtn = button;
    const editBtnDiv = editBtn.parentElement;
    const headerEl = editBtnDiv.parentElement;
    const commentDiv = headerEl.parentElement;
    const hrEl = headerEl.nextElementSibling;
    let pEl = hrEl.nextElementSibling;

    let formEl = document.createElement("form");
    let labelEl = document.createElement("label");
    let textAreaEl = document.createElement("textarea");
    let buttonEl = document.createElement("button");

    pEl.setAttribute("style", "display: none");

    commentDiv.appendChild(formEl);
    formEl.appendChild(labelEl);
    formEl.appendChild(buttonEl);
    labelEl.appendChild(textAreaEl);

    let commentId = pEl.getAttribute("data-id");
    let postId = pEl.getAttribute("data-postId");
    let userId = pEl.getAttribute("data-userId");

    formEl.setAttribute("action", `/comment/edit/${commentId}/${postId}/${userId}`);
    formEl.setAttribute("method", "post");
    formEl.setAttribute("id", "editCommentForm");
    labelEl.setAttribute("for", "editCommentForm");
    textAreaEl.setAttribute("type", "text");
    textAreaEl.setAttribute("name", "comment");
    buttonEl.setAttribute("type", "submit");
    buttonEl.setAttribute("style",
        "background-color: #314455;" + "border: none;" + "color: white;" +
        "font-size: small;" + "margin: 0;" + "padding: 0.5rem;");

    textAreaEl.innerHTML = pEl.innerText;
    buttonEl.innerHTML = "Save";
}
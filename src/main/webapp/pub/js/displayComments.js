let displayCommentsCheckbox = document.getElementById("displayCommentsCheckBox");
let spanEl = displayCommentsCheckbox.nextElementSibling;
let commentsContainer = document.getElementById("commentsContainer");

displayCommentsCheckbox.addEventListener("change", function() {
    if (this.checked) {
        commentsContainer.setAttribute("style", "display: none")
        spanEl.innerHTML = "Show Comments";
    } else {
        commentsContainer.removeAttribute("style");
        spanEl.innerHTML = "Hide Comments";
    }
});
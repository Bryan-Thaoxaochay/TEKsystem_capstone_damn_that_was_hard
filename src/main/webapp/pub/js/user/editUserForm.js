let editLinkEl = document.getElementById("userEditLink");

editLinkEl.addEventListener("click", displayUserEditForm);

function displayUserEditForm() {
    let userInfoEl = document.getElementById("userInfoContainer");
    let nameEl = document.getElementById("name");
    let usernameEl = document.getElementById("username");
    let emailEl = document.getElementById("email");

    let form = document.createElement("form");
    let firstNameLabel = document.createElement("label");
    let firstNameInput = document.createElement("input");
    let lastNameLabel = document.createElement("label");
    let lastNameInput = document.createElement("input");
    let usernameLabel = document.createElement("label");
    let usernameInput = document.createElement("input");
    let emailLabel = document.createElement("label");
    let emailInput = document.createElement("input");
    let submitBtn = document.createElement("button");

    nameEl.setAttribute("style", "display: none");
    usernameEl.setAttribute("style", "display: none");
    emailEl.setAttribute("style", "display: none");

    userInfoEl.appendChild(form);
    form.appendChild(firstNameLabel);
    form.appendChild(lastNameLabel);
    form.appendChild(usernameLabel);
    form.appendChild(emailLabel);
    form.appendChild(submitBtn);

    firstNameLabel.innerText = "First Name: ";
    lastNameLabel.innerText = "Last Name: ";
    usernameLabel.innerText = "Username: ";
    emailLabel.innerText = "Email: ";
    submitBtn.innerText = "Save";

    firstNameLabel.appendChild(firstNameInput);
    lastNameLabel.appendChild(lastNameInput);
    usernameLabel.appendChild(usernameInput);
    emailLabel.appendChild(emailInput);

    firstNameInput.setAttribute("value", nameEl.textContent.split(" ")[0]);
    lastNameInput.setAttribute("value", nameEl.textContent.split(" ")[1]);
    usernameInput.setAttribute("value", usernameEl.textContent.split(" ")[1]);
    emailInput.setAttribute("value", emailEl.textContent.split(" ")[1]);

    form.setAttribute("action", "/user/edit");
    form.setAttribute("method", "post");
    firstNameInput.setAttribute("name", "firstName");
    lastNameInput.setAttribute("name", "lastName");
    usernameInput.setAttribute("name", "username");
    emailInput.setAttribute("name", "email");
    submitBtn.setAttribute("type", "submit");
}
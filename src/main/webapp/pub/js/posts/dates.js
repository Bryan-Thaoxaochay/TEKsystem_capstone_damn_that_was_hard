let postDate = document.getElementById("postDate");
let commentDates = document.getElementsByClassName("commentDates");

function convertDate(dateText) {
    let postDate = new Date(dateText);
    let month = postDate.toLocaleString('default', {month: 'long'});

    return `${month} ${postDate.getDate()}, ${postDate.getFullYear()}`;
}

function getDayDiff(dateText) {
    let date = new Date(dateText);
    let currentDate = new Date();
    const msToDay = 86400000;

    let timeDiff = currentDate.getTime() - date.getTime();
    return Math.floor(timeDiff / msToDay);
}

postDate.innerText = `${convertDate(postDate.textContent)} (${getDayDiff(postDate.textContent)} days ago)`;

for (let i = 0; i < commentDates.length; i++) {
    let commentDate = commentDates[i];
    commentDate.innerText = `${getDayDiff(commentDate.textContent)} days ago (${convertDate(commentDate.textContent)})`;
}
// https://forum.freecodecamp.org/t/free-api-inspirational-quotes-json-with-code-examples/311373/19
const settings = {
    "async": true,
    "crossDomain": true,
    "url": "https://type.fit/api/quotes",
    "method": "GET"
}
function displayQuote() {
    $.ajax(settings).done(function (response) {
        const data = JSON.parse(response);
        let randomNum = Math.ceil(Math.random() * data.length);
        $("#blockquote").text(data[randomNum].text);
        $("#author").text(data[randomNum].author);
    });
}

displayQuote();
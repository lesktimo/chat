function addMessage() {
    var input = $("#message").val();
    var dataToSend = JSON.stringify({
        name: input
    });

    var urlPath = window.location.pathname;

    $.ajax({
        url: "http://localhost:8080/" + urlPath,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        type: 'post',
        data: dataToSend,
        success: showNewMessage
    });
}

function showNewMessage(message) {
    $("<p/>").text(message.content).appendTo("#content");
}

function listMessages() {
    var urlPath = window.location.pathname;

    $.ajax({
        url: "http://localhost:8080/" + urlPath,
        success: showAllMessages
    });
}

function showAllMessages(messages) {
    $("#content").empty();
    $.each(messages, function (i, item) {
        $("<p/>").text(item.content).appendTo("#content");
    });
}
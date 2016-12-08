function addMessage() {
    var input = $("#message").val();
    var time = new Date($.now());
    var dataToSend = JSON.stringify({
        content: input,
        timestamp: time 
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
    $("<p/>").text(message.timestamp + " " + message.content).appendTo("#content");
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
        $("<p/>").text(item.timestamp + " " + item.content).appendTo("#content");
    });
}
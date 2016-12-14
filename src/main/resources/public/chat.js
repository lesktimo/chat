function addMessage() {
    var input = $("#message").val();
    var time = new Timestamp($.now());
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
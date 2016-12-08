function addGroup() {
    var input = $("#group").val();
    var dataToSend = JSON.stringify({
        topic: input
    });
    
    $.ajax({
        url: "http://localhost:8080/groups",
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        type: 'post',
        data: dataToSend,
        success: showNewGroup
    });
}

function showNewGroup(group) {
    $('<a>', {
            title: group.topic,
            href: 'http://localhost:8080/groups' + group.id
        }).appendTo("#content");
}

function listGroups() {
    $.ajax({
        url: "http://localhost:8080/groups",
        success: showAllGroups
    });
}

function showAllGroups(groups) {
    $("#content").empty();
    $.each(groups, function (i, item) {
        $('<a>', {
            title: item.topic,
            href: 'http://localhost:8080/groups' + item.id
        }).appendTo("#content");
    });
}
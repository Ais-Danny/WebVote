$(document).ready(function () {
    $("#login").click(function () {
        var password = $("#password").val();
        $.ajax({
            url: "http://aisdanny.top:8080/Vote/getVoteData",
            type: "post",
            data: "password=" + password,
            success: function (msg) {
                if (msg == "error") {
                    alert("密码输入错误，请重新输入！");
                    return;
                }
                var json = JSON.parse(msg);
                var html = "";
                for (var i = 0; i < json.length; i++) {
                    if (json[i].choice == "同意") html += '<tr class="content" style="background-color: rgb(0, 248, 58);">';
                    else if(json[i].choice == "不同意") html += '<tr class="content" style="background-color: red;">';
                    else html += '<tr class="content" style="background-color: grey;">';
                    html +=
                        '<td>' + (i + 1) + '</td>' +
                        '<td>' + json[i].roomId + '</td>' +
                        '<td>' + json[i].name + '</td>' +
                        '<td>' + json[i].telephone + '</td>' +
                        '<td>' + json[i].time + '</td>' +
                        '<td>' + json[i].choice + '</td>' +
                        '</tr>'
                }
                $("#data").append(html);
                $("#data").show();
                $("#inputData").show();
                $("#inputPassword").hide();
            }
        })
    })
})
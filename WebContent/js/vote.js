$(document).ready(function () {
    $("#submit").click(function () {
        var roomId = $("#room").val();
        var telephone = $("#tp").val();
        var name = $("#nm").val();
        var choice;
        if ($("#ok").prop("checked")) choice = "同意";
        else if ($("#no").prop("checked")) choice = "不同意";
        else if ($("#undef").prop("checked")) choice = "弃权";
        else{
            alert("请将信息填写完整!");
            return;
        }

        var str = "roomId=" + roomId + "&name=" + name + "&choice=" + choice + "&telephone=" + telephone;

        var ss=localStorage.getItem("vote_uuid");
        if(ss!=null){
            alert("您已参与过投票了，谢谢参与！");
            location.href="okPage.html";
            return;
        }
        
        if(!isPoneAvailable(telephone)){
            alert("手机号格式不正确！");
            return;
        }

        $.ajax({
            url: "http://aisdanny.top:8080/Vote/add",
            type: "post",
            data: str,
            success: function (msg) {
                alert(msg);
                if(msg=="请将信息填写完整"||msg=="500 Error!") return;
                localStorage.setItem("vote_uuid",guid());
                location.href="okPage.html";
            }
        })
    })

})
function guid() {//唯一标识
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0,
            v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}

function isPoneAvailable(pone) {
    var myreg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
    if (!myreg.test(pone)) {
      return false;
    } else {
      return true;
    }
}
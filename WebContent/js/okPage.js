$(document).ready(function () {
    
    $.ajax({
        url: "http://aisdanny.top:8080/Vote/voteNumber",
        type: "post",
        data: "",
        success: function (msg) {
            var json = JSON.parse(msg);
            var okNumber= parseInt((String)(json.okNumber));
            var noNumber=parseInt((String)(json.noNumber));
            var undefNumber=parseInt((String)(json.undefNumber));
            var total = okNumber+noNumber+undefNumber;
            if(total==0) {
                $("#ok").html("0%(0票)")
                $("#no").html("0%(0票)")
                $("#undef").html("0%(0票)")
                return;
            }
            $("#okFill").css('width',Math.round(okNumber/total*10000)/100+"%")
            $("#ok").html(Math.round(okNumber/total*10000)/100+"%("+okNumber+"票)");
            $("#noFill").css('width',Math.round(noNumber/total*10000)/100+"%")
            $("#no").html(Math.round(noNumber/total*10000)/100+"%("+noNumber+"票)");
            $("#undefFill").css('width',Math.round(undefNumber/total*10000)/100+"%")
            $("#undef").html(Math.round(undefNumber/total*10000)/100+"%("+undefNumber+"票)");
        }
    })
})
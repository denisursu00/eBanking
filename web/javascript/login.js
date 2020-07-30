$(document).ready(function () {
    $('#showPassword').click(function(){
        if($("#parola").attr("type")=="text"){
            $("#parola").attr("type","password");
        }else{
            $("#parola").attr("type","text");
        }
    });
});
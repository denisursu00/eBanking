$.post("sourceAccounts.do", function (responseJson) {
    $('#selectAccount').on("change",function () {
        $.each(responseJson, function (index, account) {
            if( account["id"] == $('#selectAccount option:selected').val() ){
                $('#accountSum').val(account["sum"]);
            };
        });
        if($('#selectAccount option:selected').val()===""){
            $('#accountSum').val(null);
        };
    });
    $.each(responseJson, function (index, account) {
        $("<option>").val(account["id"]).text("Cont "+account["accountNumber"]).appendTo($('#selectAccount'));
    });
});
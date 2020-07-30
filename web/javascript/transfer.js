let destinationAccounts = [];
$.post("sourceAccounts.do", function (responseJson) {
    $.post("destinationAccounts.do", function (responseJson) {
        $.each(responseJson, function (index, account) {
            destinationAccounts[index] = account;
        });
    });
    $('#sourceAccountId').on("change",function () {
        $.each(responseJson, function (index, account) {
            if( account["id"] == $('#sourceAccountId option:selected').val() ){
                $('#accountSum').val(account["sum"]);
            };
        });
        if($('#sourceAccountId option:selected').val()===""){
            $('#accountSum').val(null);
        };
    });
    $.each(responseJson, function (index, account) {
        $("<option>").val(account["id"]).text("Cont "+account["accountNumber"]).appendTo($('#sourceAccountId'));
    });
});
$(document).ready(function () {
    $('#selectDestinationClient').on("change",function () {
        let destinationAccountId = $('#destinationAccountId');
        destinationAccountId.find('option').remove();
        $("<option>").val("").text("Alegeti contul").appendTo(destinationAccountId);
        $.each(destinationAccounts, function (index, acc) {
            if(acc["clientId"]==$('#selectDestinationClient').val()) {
                $("<option>").val(acc["id"]).text("Cont "+acc["accountNumber"]).appendTo(destinationAccountId);
            }
        });
    });
});
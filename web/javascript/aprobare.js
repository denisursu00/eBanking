$(document).ready(function () {
    $('input[type="checkbox"]').on('change', function () {
        $('input[name="' + this.name + '"]').not(this).prop('checked',false);
    });
    $('input[type="button"]').on('click', function () {
        let pairs = $('form').serializeArray();
        for(let i=0;i<pairs.length;i++) {
            $.post("aprobare.do", {accountId: pairs[i].name, state:pairs[i].value});
        };
        location.reload(true);
    });
});
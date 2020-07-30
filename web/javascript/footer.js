$(document).ready(function () {
    if($('.mainContainer').outerHeight()>500){
        $('.footerContainer').css('top', 243 + $('.mainContainer').outerHeight() + 10 + 'px');
    }
});
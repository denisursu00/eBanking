$(document).ready(function () {
    $('.dropdown').hover(function () {
        $(this).children("a").css('borderBottomLeftRadius','0px');
        $(this).children("a").css('borderBottomRightRadius','0px');
        $(this).children("ul").toggleClass("displayNone");
    },function () {
        $(this).children("a").css('borderBottomLeftRadius','7px');
        $(this).children("a").css('borderBottomRightRadius','7px');
        $(this).children("ul").toggleClass("displayNone");
    },$('.mainContainer').css('top', $('.menuDiv').position().top+$('.menuDiv').outerHeight()+50+'px'));
    $(window).on('resize',function () {
        $('.mainContainer').css('top', $('.menuDiv').position().top+$('.menuDiv').outerHeight()+50+'px');
    })
});

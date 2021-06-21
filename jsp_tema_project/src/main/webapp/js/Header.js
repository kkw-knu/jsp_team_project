$(document).ready(function(){
    $('#travle').hover(function(){
        $('#travle').css({
            color:'red'
        });
        $('#travle_menu').css({
            display:'flex'
        });
    }, function(){
        $('#travle').css({
            color:'black'
        });
        $('#travle_menu').css({
            display:'none'
        });
    });
    $('#travle_menu').hover(function(){
        $('#travle_menu').css({
            display:'flex'
        });
    }, function(){
        $('#travle_menu').css({
            display:'none'
        });
    });
});
$(document).ready(function(){
    $('#sleep').hover(function(){
        $('#sleep_menu').css({
            display:'flex'
        });
    }, function(){
        $('#sleep_menu').css({
            display:'none'
        });
    });
    $('#sleep_menu').hover(function(){
        $('#sleep_menu').css({
            display:'flex'
        });
    }, function(){
        $('#sleep_menu').css({
            display:'none'
        });
    });
});
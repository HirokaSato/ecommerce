$(function() {
	$(".image_pizza").hover(function(){
       $(this).fadeTo("2000",0.3); // マウスオーバーで透明度を30%にする
    },function(){
       $(this).fadeTo("2000",1.0); // マウスアウトで透明度を100%に戻す
    });
	
	

});
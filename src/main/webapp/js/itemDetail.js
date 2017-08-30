
pizaPrice = 0;
toppingPrice = 0;
quantity = 1;


$(function() {
	
	//初期値
		pizaPrice = $(".priceM").data("price");
	
		initialValue = $(".priceM").data("price");
		let num = String(initialValue).replace(/(\d)(?=(\d\d\d)+$)/g, "$1,");
		initialValue = num;
		
		$('#totalPrice').html("<span>" + initialValue + "</span>");

	//ピザ料金設定
	$('.priceM').on('click',function(){
		var priceM = $(".priceM").data("price");
		pizaPrice = priceM;
	});
	
	$('.priceL').on('click',function(){
		var priceL = $(".priceL").data("price");	
		pizaPrice = priceL;
	});

	
	//トッピング計算
	$('.checkboxes').on('click',function(){
		toppingPrice = $("#toppingCheck :checked").length * 200;
	});
	
	
	//枚数取得
	$('#select').on('click',function(){
		quantity = $(this).val();
	});
	
	
	//合計金額計算
	$('body').on('click',function(){
		var totalPrice = (pizaPrice + toppingPrice) * quantity;
		
		let num = String(totalPrice).replace(/(\d)(?=(\d\d\d)+$)/g, "$1,");
		totalPrice = num;
		
		$('#totalPrice').text(totalPrice);
		
	});
	
});

//ピザ本体価格
pizaPrice = 0;
// トッピング合計金額
toppingPrice = 0;
// 枚数
quantity = 1;

$(function() {
	
	//フェードイン処理
	$('#toppingCheck').css('display','none');
	$('#pizaSize').on('click',function(){
			$('#toppingCheck').fadeIn("fast");
	});
	
	$('#num').css('display','none');
	$('#toppingCheck').on('click',function(){
		$('#num').fadeIn("fast");
	});
	$('#noTopping').on('click',function(){
		$('#num').fadeIn("fast");
	});
	
	$('.total').css('display','none');
	$('#num').on('click',function(){
		$('.total').fadeIn("fast");
	});
	

	// 初期値
	pizaPrice = $(".priceM").data("price").css("color","red");

	initialValue = $(".priceM").data("price");
	let num = String(initialValue).replace(/(\d)(?=(\d\d\d)+$)/g, "$1,");
	initialValue = num;

	$('#totalPrice').html("<span>" + initialValue + "</span>");

	
	// ピザ料金設定
	$('.priceM').on('change', function() {
		var priceM = $(".priceM").data("price");
		pizaPrice = priceM;
	});

	$('.priceL').on('change', function() {
		var priceL = $(".priceL").data("price");
		pizaPrice = priceL;
	});

	
	// トッピング計算
	$('.checkbox-inline').on('change', function() {
		if ($("input:radio[name='size']:checked").val() == "M") {
			toppingPrice = $("#toppingCheck :checked").length * 200;
		}

		if ($("input:radio[name='size']:checked").val() == "L") {
			toppingPrice = $("#toppingCheck :checked").length * 300;
		}
	});

	
	// 枚数取得
	$('#select').on('change', function() {
		quantity = $(this).val();
	});

	
	// 合計金額計算
	$(window).on('change', function() {
		var totalPrice = (pizaPrice + toppingPrice) * quantity;

		let num = String(totalPrice).replace(/(\d)(?=(\d\d\d)+$)/g, "$1,");
		totalPrice = num;

		$('#totalPrice').css('color', 'red').text(totalPrice);

	});
	
});
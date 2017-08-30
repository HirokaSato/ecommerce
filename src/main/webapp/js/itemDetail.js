//ピザサイズ

totalPrice = 0;


$(function() {
	
	// 初期値
	$('#totalPrice').text("1,490");

	// ラジオボタンをチェックしたら変わる
	$('input[type="radio"]').change(function() {

		// 選択したvalue値を変数に格納
		 totalPrice = $(this).val();
		if (totalPrice === "M") {
			totalPrice = 1490;
		} else {
			totalPrice = 2570;
		}

//		// 数字をString型に変換し、3桁区切りのコンマを挿入
//		let num = String(totalPrice).replace(/(\d)(?=(\d\d\d)+$)/g, "$1,");
//		totalPrice = num;

		// 選択したvalue値を出力
		$('#totalPrice').text(totalPrice);
		$('#totalPrice').val(totalPrice);
		


	});

// トッピング
	// チェックボックスをチェックしたら発動	
	$('input[name="toppingList"]').change(function() {
		
		// prop()でチェックの状態を取得
		let checkboxes = $('#checkboxes').val('checked');
		
//		str = $('#totalPrice').val();
//		totalPrice = parseInt(totalPrice);
		
		if (checkboxes) {
			totalPrice = totalPrice + 200;
		} else {
			totalPrice = totalPrice - 200;
		}
		
		$('#totalPrice').text(totalPrice);
		
	});
		
});

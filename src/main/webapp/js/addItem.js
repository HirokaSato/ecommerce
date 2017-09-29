$(function () {
	let contextpath = $('#contextpath').val();
	$(".add_item_tbl").html('<tr>' +
		'<th  rowspan="2" class="col-lg-offset-0 col-xs-1 text-center">' + '<label>'
		+ '<input type="checkbox" value="">' + '</label>' + '</th>'
		+ '<td rowspan="2" class="col-sm-2 text-center">'
		+ '<input path="name" placeholder="商品名" class="form-control" id="inputName" />' + '</td>'
		+ '<td class="col-md-2">' + '<input path="priceM" placeholder="Mサイズの金額" class="form-control" id="inputPriceM" />'
		+ '</td>'
		+ '<td class="col-md-2">' + '<input path="priceL" placeholder="Lサイズの金額" class="form-control" id="inputPriceL" />'
		+ '</td>'
		+ '<td rowspan="2" class="col-md-3">'
		+ '<textarea path="description" rows="3" class="form-control" id="InputTextarea" placeholder="商品詳細文" />' + '</td>'
		+ '<td rowspan="2" class="col-sm-3">' + '<label for="inputAddress">' + '画像:' + '</label>'
		+ '<input type="file" name="image" />' + '<br />' + '</td>'
		+ '<td rowspan="2" class="col-xs-1 text-center">' + '<button type="submit" class="btn btn-warning  register_botton" >' + '登録' + '</button>'
		+ '<button type="reset" class="btn btn-danger deleteData" >' + '削除' + '</button>' + '</td>'
		+ '</tr>'
	);


	/* 	$(".deleteData").on("click", function () {
			$("#code").val("");
		}); */

	$('#add_item_form').on("click", function () {

		$(".add_item_tbl").append('<tr>' +
			'<th  rowspan="2" class="col-lg-offset-0 col-xs-1 text-center">' + '<label>'
			+ '<input type="checkbox" value="">' + '</label>' + '</th>'
			+ '<td rowspan="2" class="col-sm-2 text-center">'
			+ '<input path="name" placeholder="商品名" class="form-control" id="inputName" />' + '</td>'
			+ '<td class="col-md-2">' + '<input path="priceM" placeholder="Mサイズの金額" class="form-control" id="inputPriceM" />'
			+ '</td>'
			+ '<td class="col-md-2">' + '<input path="priceL" placeholder="Lサイズの金額" class="form-control" id="inputPriceL" />'
			+ '</td>'
			+ '<td rowspan="2" class="col-md-2">'
			+ '<textarea path="description" rows="3" class="form-control" id="InputTextarea" placeholder="商品詳細文" />' + '</td>'
			+ '<td rowspan="2" class="col-sm-3">' + '<label for="inputImage">' + '画像:' + '</label>'
			+ '<input type="file" name="image" />' + '<br />' + '</td>'
			+ '<td rowspan="2" class="col-xs-1 text-center">' + '<button type="submit" class="btn btn-warning register_botton" >' + '登録' + '</button>'
			+ '<button type="reset" class="btn btn-danger deleteData">' + '削除' + '</button>' + '</td>'
			+ '</tr>'
		);
	});


	$(".register_botton").on("click", function () {
		var name = $("#inputName").val();
		console.log(name);
		var priceM = $("#inputPriceM").val();
		console.log(priceM);
		var priceL = $("#inputPriceL").val();
		console.log(priceL);
		var description = $("#InputTextarea").val();
		console.log(description);
		var image = new FileReader($('#inputImage').get(0));
		console.log(image);

		$.ajax({
			type: "get",
			url: contextpath + "/addItemByAjax",
			data:{
				"name":name
				,"priceM":priceM
				,"priceL":priceL
				,"description":description
				,"image":image
			},
			dataType: "JSON"
		}).then(function () {
			var message="登録しました";
			alert(message);
		}).fail(function () {
			console.log("fail");
		})
	});

});

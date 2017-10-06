$(function () {
	let contextpath = $('#contextpath').val();

	var form = '<tr>' +
		'<th  class="col-lg-offset-0 col-xs-1 text-center">' + '<label>'
		+ '<input type="checkbox" class="check_count">' + '</label>' + '</th>'
		+ '<td  class="col-sm-2 text-center">'
		+ '<input path="name" placeholder="商品名" class="form-control" id="inputName" />' + '</td>'
		+ '<td class="col-md-2">' + '<input path="priceM" placeholder="Mサイズの金額" class="form-control" id="inputPriceM" />'
		+ '</td>'
		+ '<td class="col-md-2">' + '<input path="priceL" placeholder="Lサイズの金額" class="form-control" id="inputPriceL" />'
		+ '</td>'
		+ '<td class="col-md-3">'
		+ '<textarea path="description" rows="3" class="form-control" id="inputTextarea" placeholder="商品詳細文" />' + '</td>'
		+ '<td  class="col-sm-3">' + '<label for="inputAddress">' + '画像:' + '</label>'
		+ '<input type="file" name="image" id="inputImage"/>' + '<br />' + '</td>'
		+ '<td class="col-xs-1 text-center">'
		+ '<input type="hidden" value="0" name="popularity"/>'
		+ '<button type="reset" class="btn btn-danger delete_botton" >' + '削除' + '</button>' + '</td>'
		+ '</tr>';


	$(".add_item_tbl").html(form);

	//クリックすると商品登録フォームが追加されていく
	$('#add_item_form').on("click", function () {

		$(".add_item_tbl").append(form);
	});

	// SpringSecurityのcsrf対策を潜り抜けるためにAjax通信のヘッダにトークンを設定する
	$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		jqXHR.setRequestHeader(header, token);
	});


	//商品フォームの削除メソッド
	$(document).on("click", "button.delete_botton", function () {//動的に追加されたボタンに対してイベントを設定する
		$(this).closest("tr").remove();
	});

	$(".bulk_register").click(function () {
		var message = "登録しました";
		$('.check_count:checked').each(function () {
			var item_FormData = new FormData();
			item_FormData.append('name', $(this).closest("tr").find("#inputName").val());
			item_FormData.append('priceM', $(this).closest("tr").find("#inputPriceM").val());
			item_FormData.append('priceL', $(this).closest("tr").find("#inputPriceL").val());
			item_FormData.append('description', $(this).closest("tr").find("#inputTextarea").val());
			var image=$(this).closest("tr").find("#inputImage")[0].files[0];
			item_FormData.append('image',image);

			$.ajax({
				type: "post",
				url: contextpath + "/addItemByAjax",
				data: item_FormData,
				dataType: "JSON",
				processData: false,
				contentType: false,
			}).then(function () {
				console.log(message);
			}).fail(function () {
				message = "登録失敗しました";
				console.log("fail");
			})
		});
		alert(message);
	});




});

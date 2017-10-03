$(function () {
	let contextpath = $('#contextpath').val();
	$.ajax({
		type: "GET",
		url: contextpath + "/SearchAllItem",
		dataType: 'json'
	}).then(function (lst_item) {
		var htmlItems = toDeep(lst_item, 1, decorateItem);
		// htmlitems [[item,item,item],[item,item,item]....]
		htmlItems.forEach(function (twoPizza) {
			var row = $('<tr>');
			$('#all_item_list').append(row.append(twoPizza));
		});

		$("img.image_pizza").hover(function () {
			$(this).fadeTo("2000", 0.3); // マウスオーバーで透明度を30%にする
		}, function () {
			$(this).fadeTo("2000", 1.0); // マウスアウトで透明度を100%に戻す
		});

		$(document).on("click", ".change-btn", function () {
			let deleted = $(this).html();
			if (deleted == '販売中') { // クリックされたら、休止中にする
				$(this).replaceWith('<a class="btn btn-block btn-warning delete">休止中</a>')
			} else {
				$(this).replaceWith('<a class="btn btn-block btn-success delete">販売中</a>')
			}
		});


	}).fail(function () {
		console.log("fail");
	})

	function toDeep(arr, count, callback) {
		var length = arr.length
		newArr = [];

		for (var i = 0; i < Math.ceil(length / count); i++) {
			var j = i * count;
			var innerArr = arr.slice(j, j + count);

			if (typeof callback === "function") {
				innerArr = innerArr.map(callback);

			}
			newArr.push(innerArr);

		}
		return newArr;
	}

	//一括削除
	$(".bulk_stop").on("click", function () {
		//チェックされた数ぶんだけ以下の処理を行う
		$('.item_num:checked').each(function () {
			//販売中ボタンを休止中ボタンに切り替え

			var id = $(this).val();
			$.ajax({
				type: "get",
				url: contextpath + "/deleteItemByAjax",
				data: {
					"id": id
				},
				dataType: "JSON"
			}).then(function () {
				var message = "削除しました";
				alert(message);
			}).fail(function () {
				console.log("fail");
			})
		});

	});


	//一括更新
	$("edit-button").on("click", function () {
		$('.check_count:checked').each(function () {
			var id = $(this).val();
			var name = $(this).closest("tr").find("#edit-name").val();
			var priceM = $(this).closest("tr").find("#edit-priceM").val();
			var priceL = $(this).closest("tr").find("#edit=priceL").val();
			var image = new FileReader($(this).closest("tr").find("#edit-image").get(0));

			$.ajax({
				type: "get",
				url: contextpath + "/editItemByAjax",
				data: {
					"id": id,
					"name": name,
					"priceM": priceM,
					"priceL": priceL,
					"image": image
				},
				dataType: "JSON"
			}).then(function () {
				var message = "更新しました";
				alert(message);
			}).fail(function () {
				console.log("fail");
			})
		});
	});

	//商品情報編集フォーム
	$(document).on("click", ".edit-btn", function () {
		var text = '<tr>' + '<th  class="col-lg-offset-0 col-xs-1 text-center">' + '</th>'
			+ '<td  class="col-sm-3">' + '<label for="inputAddress">' + '画像:' + '</label>'
			+ '<input type="file" name="image" id="edit-image" />' + '<br />' + '</td>'
			+ '<td  class="col-sm-2 text-center">'
			+ '<input path="name" placeholder="商品名" class="form-control" id="edit-name" />' + '</td>'
			+ '<td class="col-md-2">' + '<input path="priceM" placeholder="Mサイズの金額" class="form-control" id="edit-priceM" />'
			+ '</td>'
			+ '<td class="col-md-2">' + '<input path="priceL" placeholder="Lサイズの金額" class="form-control" id="edit-PriceL" />'
			+ '</td>'
			+ '<tr>';

		$(this).closest("tr").after(text);
	});


	function decorateItem(item) {
		let separatePriceM = separate(item.priceM);
		let separatePriceL = separate(item.priceL);

		return '<tr>' + '<th class=" text-center col-sm-1" >'
			+ '<label>' + '<input type="checkbox" class="item_num" value="' + item.id + '">' + '</label>'
			+ '</th>'
			+ '<td  class="col-sm-2 text-center">' + '<img src="'
			+ item.imagePath
			+ '" class="img-responsive-overwrite img-rounded image_pizza" width="70%">'
			+ '</td>'
			+ '<td  class="col-sm-2 text-center">'
			+ item.name + '</td>' + '<td class="col-md-2">'
			+ '<span class="text-center priceM">&nbsp;М&nbsp;</span>' + '&nbsp;&nbsp;'
			+ separatePriceM + '円(税抜)' + '</td>'
			+ '<td class="col-md-2">'
			+ '<span class="priceL">&nbsp;Ｌ</span>' + '&nbsp;&nbsp;'
			+ separatePriceL + '円(税抜)' + '</td>'
			+ '<td class="col-xs-1 text-center">'
			+ '<button type="button" class="btn btn-success edit-btn">' + "編集"
			+ '</button>' + '</td>'
			+ '<td class="col-xs-1 text-center">'
		if (item.deleted == false) {
			+'<button type="button" class="btn btn-danger change-btn">' + '販売中' + '</button>';
		} else {
			+'<button type="button" class="btn btn-danger change-btn">' + '休止中' + '</button>';
		}
		'</td>' + '</tr>'
	}

	function separate(num) {
		return String(num).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, '$1,');
	}
});
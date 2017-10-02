$(function () {
	let contextpath = $('#contextpath').val();
	$.ajax({
		type: "GET",
		url: contextpath + "/SearchAllItem",
		dataType: 'json'
	}).then(function (lst_item) {
		var htmlItems = toDeep(lst_item, 2, decorateItem);
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
			$(this).toggleClass('active');

			if ($(this).hasClass('active')) {
				var text = $(this).data('text-clicked');
				$(this).html(text).css('background-color',"blue");
			} else {
				var text = $(this).data('text-default');
				//""にすることで、もとの色にもどる
				$(this).html(text).css('background-color',"");
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

	function decorateItem(item) {
		let separatePriceM = separate(item.priceM);
		let separatePriceL = separate(item.priceL);

		return '<th class=" text-center col-sm-1" >'
			+ '<label>' + '<input type="checkbox" value="">' + '</label>'
			+ '</th>'
			+ '<th class="text-center col-md-offset-2 col-md-2">' + '<img src="'
			+ item.imagePath
			+ '" class="img-responsive-overwrite img-rounded image_pizza" width="70%">'
			+ '<br>' + item.name + '</a>' + '<br>'
			+ '<span class="text-center price">&nbsp;М&nbsp;</span>' + '&nbsp;&nbsp;'
			+ separatePriceM + '円(税抜)' + '<br>'
			+ '<span class="price">&nbsp;Ｌ</span>' + '&nbsp;&nbsp;'
			+ separatePriceL + '円(税抜)' + '<br>' + '</th>'
			+ '<th class="text-center col-md-2">'
			+ '<button type="button" class="btn btn-warning active">'
			+ '編集</button>' + '<br>' + '<br>'
			+ '<button type="button" class="btn btn-danger change-btn" data-text-default="販売中" data-text-clicked="休止中">' + "販売中"
			+ '</button>' + '</th>'
	}

	function separate(num) {
		return String(num).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, '$1,');
	}
});
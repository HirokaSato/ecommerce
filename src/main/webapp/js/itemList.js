$(function () {
	$("#next-page").css('display', 'none');
	$("#pre-page").css('display', 'none');
	let contextpath = $('#contextpath').val();

	// 商品表示
	var offset = 0;
	var limit = 3;
	viewpage(offset, limit);
	//3件表示ボタンを押したとき
	$("#three_data").on("click", function () {
		var offset = 0;
		var limit = $(this).val();
		viewpage(offset, limit);
	});
	//6件表示ボタンを押したとき
	$("#six_data").on("click", function () {
		var offset = 0;
		var limit = $(this).val();
		viewpage(offset, limit);
	});
	//12件表示ボタンを押したとき
	$("#twelve_data").on("click", function () {
		var offset = 0;
		var limit = $(this).val();
		viewpage(offset, limit);
	});

	function viewpage(offset, limit) {
		$.ajax({
			type: "GET",
			url: contextpath + "/ajaxViewItem",
			dataType: 'json',
			data: {
				"offset": offset,
				"limit": limit
			}
		}).then(function (lst_item) {
			var htmlItems = toDeep(lst_item, 3, decorateItem);
			//htmlitems [[item,item,item],[item,item,item]....]
			var viewlist = [];
			htmlItems.forEach(function (threePizza) {
				var row = $('<tr>');
				viewlist.push(row.append(threePizza));
			});
			$('#list-table').html(viewlist);

			$("img.image_pizza").hover(function () {
				$(this).fadeTo("2000", 0.3); // マウスオーバーで透明度を30%にする
			}, function () {
				$(this).fadeTo("2000", 1.0); // マウスアウトで透明度を100%に戻す
			});
			$("#next-page").css('display', 'block');

			$('div.panel-body').css('display', 'none');
			$("button.panel-title").on("click", function () {
				$("div.panel-body").toggle();
			});
		}).fail(function () {
			console.log("fail");
		})
	}

	//全件検索
	$.ajax({
		type: "get",
		url: contextpath + "/ajaxSearchAllItem",
		dataType: 'JSON'
	}).then(function (lst_item) {
		console.log(lst_item.length);
	}).fail(function () {
		console.log("fail");
	})


	//次のページへボタンを押すと次のページが表示される
	$("#next-page").on("click", function () {
		//テーブルの行数を取得するメソッド
		if ($('#list-table').prop('rows').length == 1) {
			viewpage(3, 3);
		}
		if ($('#list-table').prop('rows').length == 2) {
			viewpage(6, 6);
		}
		if ($('#list-table').prop('rows').length == 4) {
			viewpage(12, 6);
		}
	});

	// 商品検索
	$(".searchGo").on("click", function () {
		var words = $("#code").val();
		var keikoku = "入力してください";
		if (words == "") {
			alert(keikoku)
		} else {
			$.ajax({
				url: contextpath + "/search_item",
				dataType: "json",
				type: 'GET',
				data: {
					"search_word": words
				}
			}).then(function (Result_pizzas) {
				var pizzaList = [];
				Result_pizzas.forEach(function (pizza) {
					pizzaList.push(decorateItem(pizza));
					$('#list-table').html(pizzaList);
				});
				$("img.image_pizza").hover(function () {
					$(this).fadeTo("2000", 0.3); // マウスオーバーで透明度を30%にする
				}, function () {
					$(this).fadeTo("2000", 1.0); // マウスアウトで透明度を100%に戻す
				});

			}).fail(function () {
				console.log("fail");
			})
		}
	});



	// グラフ
	$.ajax({
		type: "GET",
		url: contextpath + "/ajaxViewGraph",
		dataType: 'json'
	}).then(function (top_items) {
		var pizzaTopLst = [];
		top_items.forEach(function (item) {
			pizzaTopLst.push(item.popularity);
		});
		var pizzaTopLst_name = [];
		top_items.forEach(function (item) {
			pizzaTopLst_name.push(item.name);
		});
		var barChartData = {
			labels: pizzaTopLst_name,
			datasets: [{
				fillColor: /* "#d685b0" */"rgba(214,133,176,0.7)",
				strokeColor: /* "#d685b0" */"rgba(214,133,176,0.7)",
				highlightFill: /* "#eebdcb" */"rgba(238,189,203,0.7)",
				highlightStroke: /* "#eebdcb" */"rgba(238,189,203,0.7)",
				data: pizzaTopLst
			}]
		}
		$(document).ready(function () {
			var ctx = document.getElementById("chart").getContext("2d");
			window.myBar = new Chart(ctx).Bar(barChartData, {
				responsive: true,
			});
		});
	}).fail(function () {
		console.log("fail");
	})

	// オートコンプリート
	$.ajax({
		url: contextpath + "/ajaxSerchResult",
		dataType: "json",
		type: 'GET'
	}).then(function (Result_pizzas) {
		var pizzaName = [];
		Result_pizzas.forEach(function (pizza) {
			pizzaName.push(pizza.name);
		})
		$('.autocomplate').autocomplete({
			source: pizzaName,
			autoFocus: true,
			delay: 500,
			minLength: 1
		});

	}).fail(function () {
		console.log("fail");

	})

	$(".deleteGo").on("click", function () {
		$("#code").val("");
	});

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
		return '<th class="col-md-4">' + '<a href="'
			+ contextpath
			+ '/detailController/detail?id='
			+ item.id
			+ '">'
			+ '<img src="'
			+ item.imagePath
			+ '" class="img-responsive img-rounded image_pizza" width="200%" >'
			+ '</a>' + '<br>' + '<a href="' + contextpath
			+ '/detailController/detail?id=' + item.id + '">' + item.name
			+ '</a>' + '<br>' + '<span class="price">&nbsp;М&nbsp;</span>'
			+ '&nbsp;&nbsp;' + separatePriceM + '円(税抜)' + '<br>'
			+ '<span class="price">&nbsp;Ｌ</span>' + '&nbsp;&nbsp;'
			+ separatePriceL + '円(税抜)' + '<br>' + '</t>'
	}

	function separate(num) {
		return String(num).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, '$1,');
	}
});
/**
 * var items = htmlItems.map(function(innerArr){ return
 * innerArr.join().replace(/,/, ''); //正規表現(/,/g,
 * '')⇒gがあるとすべての「，」をなくしてくれる。gがないと、最初の「，」だけなくす処理になる。gはglobaldserchの略 });
 */

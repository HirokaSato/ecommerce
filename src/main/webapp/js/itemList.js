$(function() {

	let contextpath = $('#contextpath').val();

	// 商品一覧表示

	$.ajax({
		type : "GET",
		url : contextpath + "/ajaxSearchAllItem",
		dataType : 'json'
	}).then(function(lst_item) {
		var htmlItems = toDeep(lst_item, 3, decorateItem);// htmlitems
		// [[item,item,item],[item,item,item]....]
		htmlItems.forEach(function(threePizza) {
			var row = $('<tr>');
			$('#list-table').append(row.append(threePizza));
		});

		$("img.image_pizza").hover(function() {
			$(this).fadeTo("2000", 0.3); // マウスオーバーで透明度を30%にする
		}, function() {
			$(this).fadeTo("2000", 1.0); // マウスアウトで透明度を100%に戻す
		});

		$('div.panel-body').css('display', 'none');
		$("button.panel-title").on("click", function() {
			$("div.panel-body").toggle();
		});

	}).fail(function() {
		console.log("fail");

	})
	// グラフ表示
	$.ajax({
		type : "GET",
		url : contextpath + "/ajaxViewGraph",
		dataType : 'json'
	}).then(function(top_items) {
		var pizzaTopLst = [];
		top_items.forEach(function(item) {
			pizzaTopLst.push(item.popularity);
		});
		var pizzaTopLst_name = [];
		top_items.forEach(function(item) {
			pizzaTopLst_name.push(item.name);
		});
		var barChartData = {
			labels : pizzaTopLst_name,
			datasets : [ {
				fillColor : /* "#d685b0" */"rgba(214,133,176,0.7)",
				strokeColor : /* "#d685b0" */"rgba(214,133,176,0.7)",
				highlightFill : /* "#eebdcb" */"rgba(238,189,203,0.7)",
				highlightStroke : /* "#eebdcb" */"rgba(238,189,203,0.7)",
				data : pizzaTopLst
			} ]
		}
		$(document).ready(function() {
			var ctx = document.getElementById("chart").getContext("2d");
			window.myBar = new Chart(ctx).Bar(barChartData, {
				responsive : true,
			});
		})
	}).fail(function() {
		console.log("fail");
	})
	// 検索機能
	$(button.searchGo).on(click, function() {
		var words = $("#code").val();
		ajax({
			type : "GET",
			url : contextpath + "/ajaxSerchResult",
			dataType : 'json',
			data : {
				search_word : words
			}
		// 連想配列：search_wordというkeyでwordsというvalueを送っている。
		}).then(function(ResultWords) {
			

		}).fail(function() {
			console.log("fail");
		})
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
				+ '" class="img-responsive img-rounded image_pizza" width="190" height="590">'
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
/*
 * var items = htmlItems.map(function(innerArr){ return
 * innerArr.join().replace(/,/, ''); //正規表現(/,/g,
 * '')⇒gがあるとすべての「，」をなくしてくれる。gがないと、最初の「，」だけなくす処理になる。gはglobaldserchの略 });
 */

/*
 * var pizzaName = []; lst_item.forEach(function(pizza) {
 * pizzaName.push(pizza.name); }); $(".autocomplate").autocomplete({ source :
 * pizzaName, autoFocus : true, delay : 500, minLength : 2
 */


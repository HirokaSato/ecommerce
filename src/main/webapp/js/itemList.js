

$(function() {
	
	let contextpath = $('#contextpath').val();

	console.log("start ajax");
	$.ajax({
		type : "GET",
		url : contextpath + "/ajaxSearchAllItem",
		dataType : 'json'
	}).then(function(lst_item) {
		 var htmlItems = toDeep(lst_item, 3, decorateItem);
		 //htmlitems　[[item, item,item], [item,item,item] ....]
		/* var items = htmlItems.map(function(innerArr){
		    	return innerArr.join().replace(/,/, '');
		    	//正規表現(/,/g, '')⇒gがあるとすべての「，」をなくしてくれる。gがないと、最初の「，」だけなくす処理になる。gはglobaldserchの略
		    });*/
		 var row = $('<tr>');
		 htmlItems.forEach(function(item) {
		    var row = $('<tr>');
		    $('#list-table').append(row.append(item));
		 });
	}).fail(function() {
		console.log("fail");
	});

	$(".image_pizza").hover(function() {
		$(this).fadeTo("2000", 0.3); // マウスオーバーで透明度を30%にする
	}, function() {
		$(this).fadeTo("2000", 1.0); // マウスアウトで透明度を100%に戻す
	});


	$('div.panel-body').css('display', 'none');
	$("button.panel-title").on("click", function() {
		$("div.panel-body").toggle();
	});
	
	function toDeep(arr, count, callback){
		  var length = arr.length,
		      newArr= [];

		  for(var i = 0; i < Math.ceil(length / count); i++) {
		    var j = i * count;
		    var innerArr = arr.slice(j, j + count);
		    if(typeof callback === "function") {
		      innerArr = innerArr.map(callback);
		    }
		    newArr.push(innerArr);
		  }

		  return newArr;
		}
	
	
	function decorateItem(item) {
		let separatePriceM = separate(item.priceM);
		console.log(separatePriceM);
		let separatePriceL=separate(item.priceL);
		console.log(separatePriceL);
		return '<th class="col-md-4">'+
		'<a href="' + contextpath + '/detailController/detail?id='+ item.id + '">'
		+'<img src="'+ item.imagePath + '" class="img-responsive img-rounded image_pizza" width="190" height="590">' 
		+'</a>'
		+'<br>'
		+'<a href="'+ contextpath + '/detailController/detail?id='+ item.id +'">'
		+ item.name
		+ '</a>'
		+ '<br>'
		+'<span class="price">&nbsp;М&nbsp;</span>'
		+'&nbsp;&nbsp;'
		+ separatePriceM +'円(税抜)'
		+'<br>'
		+'<span class="price">&nbsp;Ｌ</span>'
		+'&nbsp;&nbsp;'
		+ separatePriceL + '円(税抜)'
		+'<br>'
		+'</t>'
	}
		

	
});


function separate(num){
	return String(num).replace( /(\d)(?=(\d\d\d)+(?!\d))/g, '$1,');
}




$(function() {
	//住所から郵便番号の検索
	$(window).on('load',function() {
		$.ajax({
	        url: "http://zipcoda.net/api",
	        dataType: "jsonp",
	        data: { 
	          address: $('#address').val() 
	        },
	      }
	    )
	    // 検索成功時にはページに結果を反映
	    .done(function(data) {
	      // コンソールに取得データを表示
	      console.log(data);
	      $("#zipcode").val(data.items[0].zipcode);
	    })
	  });
	
  
	
	//郵便番号から住所検索ボタンを押して検索
	$('#searchZipcode').on("click",function() {
    $.ajax({
        url: "http://zipcoda.net/api",
        dataType: "jsonp",
        data: { 
          zipcode: $('#zipcode').val() 
        },
      }
    )
    // 検索成功時にはページに結果を反映
    .done(function(data) {
      // コンソールに取得データを表示
      console.log(data);
      $("#address").val(data.items[0].address);
    })
    // 検索失敗時には、その旨をダイアログ表示
    .fail(function() {
      window.alert('正しい結果を得られませんでした。');
    });
  });
	
	
	//住所から郵便番号の検索
	$('#searchAddress').on("click",function() {
	    $.ajax({
	        url: "http://zipcoda.net/api",
	        dataType: "jsonp",
	        data: { 
	          address: $('#address').val() 
	        },
	      }
	    )
	    // 検索成功時にはページに結果を反映
	    .done(function(data) {
	      // コンソールに取得データを表示
	      console.log(data);
	      $("#zipcode").val(data.items[0].zipcode);
	    })
	    // 検索失敗時には、その旨をダイアログ表示
	    .fail(function() {
	      window.alert('正しい結果を得られませんでした。');
	    });
	  });
	
  
  
});
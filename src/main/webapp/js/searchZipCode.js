$(function () {
	
	$('#searchZipcode').on("click",function(){
	$('#zipcode').jpostal({
		postcode : [
			'#zipcode'
		],
		address : {
			'#address' : '%3%4%5'
		}
	});
	}
});
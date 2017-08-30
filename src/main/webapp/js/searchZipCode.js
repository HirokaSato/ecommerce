$(function () {
	
	$("#searchZipcode").on("click",function(){
		alert("Hellow");
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
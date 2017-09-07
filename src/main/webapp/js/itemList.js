 $("div.panel-body").hide();
$(function() {
	$(".image_pizza").hover(function() {
		$(this).fadeTo("2000", 0.3); // マウスオーバーで透明度を30%にする
	}, function() {
		$(this).fadeTo("2000", 1.0); // マウスアウトで透明度を100%に戻す
	});

	/*
	 * $("button.panel-title").click(function(){ $("div.panel-body").animate(
	 * {height: "toggle", opacity: "toggle"}, "slow" ); });
	 */
	$('div.panel-body').css('display','none');
	$("button.panel-title").on("click", function() {
		$("div.panel-body").toggle();
	});

	/*
	 * $("button.panel-title").on("click", function() {
	 * $("div.panel-body").hide(); $($(this).attr("href")).fadeToggle(); return
	 * false; });
	 */
});
<%@page import="org.springframework.security.taglibs.TagLibConfig"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ピザ屋のネット注文</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/piza.css"
	rel="stylesheet">
<link href="css/piza.css" rel="stylesheet">

<script src="jquery.elevatezoom.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/itemDetail.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="./commonNavi.jsp"%>


		<div class="row">
			<div
				class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-10 col-xs-12">
				<div class="well">
					<fieldset>
						<legend>アカウントサービス</legend>
						<div class="panel panel-default">
							<div class="panel-title text-center">アカウント情報</div>
							<label>お名前 </label>
							<sec:authentication property="principal.user.name" />
							<br> <label>お住まい </label>
							<sec:authentication property="principal.user.address" />
							<br> <label>電話番号 </label>
							<sec:authentication property="principal.user.telephone" />
							<br> <label>メールアドレス </label>
							<sec:authentication property="principal.user.email" />

						</div>

						<div class="panel panel-default">
							<div class="panel-title text-center">
								<a href="${pageContext.request.contextPath}/user_info_change">アカウント情報の変更</a>
							</div>
						</div>


						<div class="panel panel-default">
							<div class="panel-title text-center">
								<a href="${pageContext.request.contextPath}/orderHistory">注文履歴</a>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>

	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script>
		$(function() {
			$(".btn-block").on("click", function() {
				$(".btn-block").off("click", onclick).on("click", false);
			});
		});
	</script>

</body>
</html>
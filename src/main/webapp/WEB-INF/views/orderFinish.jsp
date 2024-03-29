<%@ page contentType="text/html; charset=UTF-8"%>
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
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<%@ include file="./commonNavi.jsp"%>
		<!-- table -->
		<div class="row">
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
				<h3 class="text-center">決済が完了しました！</h3>
				<p class="text-center">この度はご注文ありがとうございます。</p>
				<p class="text-center">お支払い先は、お送りしたメールに記載してありますのでご確認ください。</p>
				<p class="text-center">メールが届かない場合は「注文履歴」からご確認ください。</p>
			</div>
		</div>

		<br>
		<br>
		<div class="row">
			<div class="col-xs-offset-4 col-xs-4">
				<div class="form-group">
					<form action="${pageContext.request.contextPath}/top">
						<input class="form-control btn btn-warning btn-block"
							type="submit" value="トップ画面を表示する">
					</form>
				</div>
			</div>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
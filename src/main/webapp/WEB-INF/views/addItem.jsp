<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ネットでピザ注文</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">

</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span><span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/top"> <!-- 企業ロゴ --> <img
						alt="main log"
						src="${pageContext.request.contextPath}/img/header_logo.png"
						height="35">
					</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<%-- <p class="navbar-text navbar-right">
						<a href="${pageContext.request.contextPath}/viewCartList" class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;
					</p> --%>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>

	<!-- login form -->

	<div class="container">
	<input id="contextpath" type="hidden"
		value="${pageContext.request.contextPath}" />
		<ol class="breadcrumb">
			<li><input type="button" id="add_item_form" value="商品追加"></li>
			<li><input type="button" class="bulk_register" value="商品一括登録"></li>
			<li><a href="${pageContext.request.contextPath}/product_list">商品管理一覧に戻る</a></li>
		</ol>
		<div class="table-responsive">
			<table style="table-layout: fixed" class="table table-responsive">
					<tbody class="add_item_tbl">
					</tbody>
			</table>
		</div>
	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/addItem.js"></script>
</body>
</html>
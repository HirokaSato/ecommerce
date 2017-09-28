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
							class="icon-bar"></span> <span class="icon-bar"></span> <span
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

		<!-- login form -->
		<div class="row">
			<div
				class="col-lg-offset-3 col-lg-7 col-md-offset-2 col-md-8 col-sm-10 col-xs-12">
				<div class="row well">
					<form:form modelAttribute="itemForm"
						action="${pageContext.request.contextPath}/add_item_submit"
						method="post" id="itemForm" enctype="multipart/form-data">
						<div class="form-group">
							<label for="inputName">商品名:</label><label class="control-label"
								style="color: red" for="inputError"><form:errors
									path="name" /></label>
							<form:input path="name" placeholder="商品名"
								class="
									form-control" id="inputName" />
						</div>
						<div class="form-group">
							<label for="inputPriceM">Mサイズ金額:</label><label
								class="control-label" style="color: red" for="inputError"><form:errors
									path="description" /></label>
							<form:input path="priceM" placeholder="Mサイズの金額"
								class="form-control" id="inputPriceM" />
						</div>
						<div class="form-group">
							<label for="inputPriceL">Lサイズ金額:</label> <label
								class="control-label" style="color: red" for="inputError"></label>
							<form:input path="priceL" placeholder="Lサイズの金額"
								class="form-control" id="inputPriceL" />
						</div>
						<div class="form-group">
							<label for="InputTextarea">商品説明:</label> <label
								class="control-label" style="color: red" for="inputError"></label>
							<form:textarea path="description" rows="3" class="form-control"
								id="InputTextarea" />
						</div>
						<div class="form-group">
							<label for="inputAddress">画像:</label> <input type="file"
								name="image" /><br />
						</div>
						<form:hidden path="popularity" value="0" />
						<div class="form-group">
							<button type="submit" class="btn btn-warning" form="itemForm">登録</button>
							<button type="reset" class="btn btn-danger deleteData"
								form="itemForm">クリア</button>
						</div>
					</form:form>
				</div>
				<a href="${pageContext.request.contextPath}/product_list">商品管理一覧に戻る</a>
			</div>
		</div>

	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
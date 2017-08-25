<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ネットでピザ注文</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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
							class="icon-bar"></span> <span class="icon-bar"></span> 
							<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${pageContext.request.contextPath}/top"> <!-- 企業ロゴ --> 
					<img alt="main log" src="${pageContext.request.contextPath}/img/header_logo.png" height="35">
					</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<p class="navbar-text navbar-right">
						<a href="${pageContext.request.contextPath}/viewCartList" class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;
					</p>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		
		<!-- login form -->
		<div class="row">
			<div class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-10 col-xs-12">
				<div class="well">
					<form:form modelAttribute="userForm" action="${pageContext.request.contextPath}/registerUser/submit" method="post">
						<fieldset>
							<legend>ユーザ登録</legend>
							<div class="form-group">
								<label for="inputName">名前:</label><label class="control-label" style="color: red" for="inputError">名前を入力してください</label>
								<form:input path="name" placeholder="Name" class="form-control" id="inputName"/>
							</div>
							<div class="form-group">
								<label for="inputEmail">メールアドレス:</label><label class="control-label" style="color: red" for="inputError">メールアドレスを入力してください</label>
								<form:input path="email" class="form-control" placeholder="Email" id=""/>
							</div>
							<div class="form-group">
								<label for="inputAddress">住所:</label>
								<label class="control-label" style="color: red" for="inputError">住所を入力してください</label>
								<form:input path="address" placeholder="Address" class="form-control" id="inputAddress"/>
							</div>
							<div class="form-group">
								<label for="inputTel">電話番号:</label>
								<label class="control-label" style="color: red" for="inputError">電話番号を入力してください</label>
								<form:input path="telephone" id="inputTel" class="form-control" placeholder="Tel"/>
							</div>
							<div class="form-group">
								<label for="inputPassword">パスワード:</label>
								<label class="control-label" style="color: red" for="inputError">パスワードを入力してください</label>
								<form:input path="password" id="inputPassword" class="form-control" placeholder="Password"/>
							</div>
							<div class="form-group">
								<label for="inputConfirmationPassword">確認用パスワード:</label>
								<label
									class="control-label" style="color: red" for="inputError">確認用パスワードを入力してください</label>
								<form:input path="reInputPassword" id="inputConfirmationPassword" class="form-control" placeholder="Confirmation Password"/>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary">登録</button>
								<button type="reset" class="btn btn-primary">クリア</button>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>

	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
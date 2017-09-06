<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ピザ屋のネット注文e</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/piza.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/myPage.css"
	rel="stylesheet">
</head>
<body>
	<!-- ヘッダー部分 -->
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
					<p class="navbar-text navbar-right">
						<a href="${pageContext.request.contextPath}/viewCartList"
							class="navbar-link">ショッピングカート</a>&nbsp;&nbsp; <a
							href="${pageContext.request.contextPath}/logout"
							class="navbar-link">ログアウト</a>
					</p>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>

	<!-- ページタイトル -->
	<div class="row">
		<div
			class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
			<h1 class="text-center">マイページ</h1>

		</div>
	</div>
	<!-- 登録情報 -->
	<div class="row">
		<div
			class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
			<h2 class="text-center">
				<sec:authentication property="principal.user.name" />
				&nbsp;様の登録情報
			</h2>
		</div>
	</div>
	<div class="row">
		<br>
		<br>
		<div
			class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-10 col-xs-12">
			<div class="well">
				<div class="form-group">
					<br>
					<table class="table" id="userInfoTable">
						<tr>
							<td><span class="userItem">名前：</span></td>
							<td><span class="userInfo"><c:out
										value="${user.name}" /></span></td>
						</tr>
						<tr>
							<td><span class="userItem">メールアドレス：</span></td>
							<td><span class="userInfo"><c:out
										value="${user.email}" /></span></td>
						</tr>
						<tr>
							<td><span class="userItem">住所：</span></td>
							<td><span class="userInfo"><c:out
										value="${user.address}" /></span></td>
						</tr>
						<tr>
							<td><span class="userItem">電話番号：</span></td>
							<td><span class="userInfo"><c:out
										value="${user.telephone}" /></span></td>
						</tr>
					</table>
				</div>
				<br> <a> <input type="button"
					class="form-control btn btn-warning btn-block"
					style="background-color: #6495ED; border-color: #6495ED"
					value="登録内容を変更する">
				</a> <br>
			</div>
		</div>





	</div>


</body>
</html>
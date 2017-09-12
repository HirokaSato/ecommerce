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
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

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
					<form:form modelAttribute="userForm"
						action="${pageContext.request.contextPath}/changed_userInfo"
						method="post" id="userForm">
						<fieldset>
							<legend>アカウント情報変更</legend>
							<div class="form-group">
								<label for="inputName">名前:</label><label class="control-label"
									style="color: red" for="inputError"><form:errors
										path="name" /></label>
								<form:input path="name" placeholder="名前" class="form-control"
									id="inputName" />
							</div>
							<div class="form-group">
								<label for="inputEmail">メールアドレス:</label><label
									class="control-label" style="color: red" for="inputError"><form:errors
										path="email" /></label>
								<form:input path="email" class="form-control"
									placeholder="メールアドレス" id="" />
							</div>
							<div class="form-group">
								<label for="inputAddress">住所:</label> <label
									class="control-label" style="color: red" for="inputError"><form:errors
										path="address" /></label>
								<form:input path="address" placeholder="住所" class="form-control"
									id="inputAddress" />
							</div>
							<div class="form-group">
								<label for="inputTel">電話番号:</label> <label class="control-label"
									style="color: red" for="inputError"><form:errors
										path="telephone" /></label>
								<form:input path="telephone" id="inputTel" class="form-control"
									placeholder="ハイフンなしで入力してください" />
							</div>
							<div class="form-group">
								<label for="inputPassword">パスワード:</label> <label
									class="control-label" style="color: red" for="inputError"><form:errors
										path="password" /></label>
								<form:input path="password" id="inputPassword"
									class="form-control" placeholder="8文字以上で登録してください"
									type="password" />
							</div>
							<div class="form-group">
								<label for="inputConfirmationPassword">確認用パスワード:</label> <label
									class="control-label" style="color: red" for="inputError"><form:errors
										path="reInputPassword" /></label>
								<form:input path="reInputPassword"
									id="inputConfirmationPassword" class="form-control"
									placeholder="パスワード再入力" type="password" />
							</div>
							<div class="form-group">
						
								<button type="submit" class="btn btn-primary" form="userForm">変更</button>
								<button type="reset" class="btn btn-primary" form="userForm">クリア</button>
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
	<script>
		$(function() {
			$(".btn-block").on("click", function() {
				$(".btn-block").off("click", onclick).on("click", false);
			});
		});
	</script>

</body>
</html>

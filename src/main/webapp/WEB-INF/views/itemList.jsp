<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ピザ屋のネット注文</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/piza.css" rel="stylesheet">
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
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${pageContext.request.contextPath}/top"> <!-- 企業ロゴ --> <img
						alt="main log" src="${pageContext.request.contextPath}/img/header_logo.png" height="35">
					</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<p class="navbar-text navbar-right">
						<a href="${pageContext.request.contextPath}/viewCartList" class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/orderhistory" class="navbar-link">注文履歴</a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/login" class="navbar-link">ログイン</a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/logout" class="navbar-link">ログアウト</a>
					</p>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>

		<!-- search form -->
		<div class="row">
			<div
				class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-10 col-xs-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">商品を検索する</div>
					</div>
					<div class="panel-body">
						<form:form method="post" action="${pageContext.request.contextPath}/searchItem" class="form-horizontal">
							<div class="form-group">
								<label for="code" class="control-label col-sm-2">商品名</label>								
								<div class="col-sm-9">
									<input type="text" name="keyword" id="code"
										class="form-control input-sm" />
								</div>
							</div>
							<div class="text-center">
								<c:out value="${sessionId}"/>								
								<button type="submit" value="検索" class="btn btn-primary">検索</button>
								<button type="reset" value="クリア" class="btn btn-default">クリア</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<!-- table -->
		<div class="row">
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
				<table class="table table-striped">
					<tbody>	
						 <c:forEach var="item" items="${itemList}" varStatus="status">
							<c:if test="${status.count + 3 % 3 == 1}">
								<tr>							
							</c:if>
									<th>
										<a href="${pageContext.request.contextPath}/detailController/detail?id=${item.id}">
											<img src="${item.imagePath}" class="img-responsive img-rounded" width="200" height="600">
										</a><br>	
										<a href="${pageContext.request.contextPath}/detailController/detail?id=${item.id}">
										<c:out value="${item.name}"/></a><br>
											<span class="price">&nbsp;М&nbsp;</span>&nbsp;&nbsp;<fmt:formatNumber value="${item.priceM}" pattern="###,###"/>円(税抜)<br>
											<span class="price">&nbsp;Ｌ</span>&nbsp;&nbsp;<fmt:formatNumber value="${item.priceL}" pattern="###,###"/>円(税抜)<br>
									</th> 
							<c:if test="${status.count % 3 == 0}">
								</tr>				
							</c:if>
						</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>		
	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}js/bootstrap.min.js"></script>
</body>
</html>
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
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/itemList.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="./commonNavi.jsp"%>
		<!-- search form -->
		<div class="row">
			<div
				class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-12 col-xs-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">商品を検索する</div>
					</div>
					<div class="panel-body">
						<form:form method="post"
							action="${pageContext.request.contextPath}/searchItem"
							class="form-horizontal">
							<div class="form-group">
								<div class="text-center" style="color: red">
									<c:out value="${error}" />
								</div>
								<label for="code" class="control-label col-sm-2">商品名</label>
								<div class="col-sm-9">
									<input type="text" name="keyword" id="code"
										class="form-control input-sm" />
								</div>
							</div>
							<div class="text-center">
								<c:out value="${sessionId}" />
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
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-12 col-xs-12">
				<table class="table table-striped" >
					<tbody>
						<c:forEach var="item" items="${itemList}" varStatus="status">
							<c:if test="${status.count + 3 % 3 == 1}">
								<tr>
							</c:if>
							<th class="col-md-4"><a
								href="${pageContext.request.contextPath}/detailController/detail?id=${item.id}">
									<img src="${item.imagePath}"
									class="img-responsive img-rounded image_pizza" width="190"
									height="590">
							</a><br> <a
								href="${pageContext.request.contextPath}/detailController/detail?id=${item.id}">
									<c:out value="${item.name}" />
							</a><br> <span class="price">&nbsp;М&nbsp;</span>&nbsp;&nbsp;<fmt:formatNumber
									value="${item.priceM}" pattern="###,###" />円(税抜)<br> <span
								class="price">&nbsp;Ｌ</span>&nbsp;&nbsp;<fmt:formatNumber
									value="${item.priceL}" pattern="###,###" />円(税抜)<br></th>
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
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
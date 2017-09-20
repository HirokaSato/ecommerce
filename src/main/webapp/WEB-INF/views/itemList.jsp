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
<link type="text/css" rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/cupertino/jquery-ui.min.css" />


</head>

<body>
	<input id="contextpath" type="hidden"
		value="${pageContext.request.contextPath}" />

	<div class="container">
		<%@ include file="./commonNavi.jsp"%>
		<!-- search form -->
		<div class="row">
			<div
				class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-12 col-xs-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<%-- <form:form method="post"
							action="${pageContext.request.contextPath}/searchItem"
							class="form-horizontal">  --%>
							<div class="form-group">
								<div class="text-center" style="color: red">
									<c:out value="${error}" />
								</div>
								<label for="code" class="control-label col-sm-2">商品名</label>
								<div class="col-sm-9 ">
									<input type="text" name="keyword" id="code"
										class="form-control input-sm autocomplate" />
								</div>
							</div>
							<div class="text-center">
								<c:out value="${sessionId}" />
								<br>
								<button type="submit" value="検索" class="btn btn-primary searchGo">検索</button>
								<input type="reset" value="クリア" class="btn btn-default deleteGo"/>
							</div>
						<%--  </form:form> --%>
					</div>
				</div>
			</div>
		</div>
		<!-- table -->

		<div class="row">
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-12 col-xs-12">
				<table class="table table-striped pizzaList">
					<tbody id="list-table">
					</tbody>

				</table>
			</div>
		</div>

		<div>
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-12 col-xs-12"
				style="width: 70%" class="text-center">
				<strong>シュガーピザ人気ランキング</strong>
				<canvas id="chart" height="450" width="600"></canvas>
			</div>
		</div>
	</div>
	<!-- end container -->


	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript"
		src="http://code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/itemList.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
</body>
</html>
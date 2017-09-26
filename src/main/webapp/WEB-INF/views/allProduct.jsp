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
<link href="${pageContext.request.contextPath}/css/pagination.css"
	rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/cupertino/jquery-ui.min.css" />

</head>
<body>
	<input id="contextpath" type="hidden"
		value="${pageContext.request.contextPath}" />
	<div class="container">
		<%@ include file="./commonNavi.jsp"%>
		<div class="row">
			<div
				class="table-responsive col-lg-offset-0 col-lg-11.5 col-md-offset-0 col-md-11.5 col-sm-12 col-xs-12">
				<ol class="breadcrumb">
					<li><a href="http://bootstrap3.cyberlab.info">商品追加</a></li>
					<li><a href="http://bootstrap3.cyberlab.info/components/">一括追加</a></li>
					<li class="active">一括削除</li>
					<li class="active">更新</li>
				</ol>

				<table class="table table-striped pizzaList">
					<tbody id="all_item_list">
					</tbody>
				</table>


			</div>
		</div>
	</div>
	<!-- end container -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript"
		src="http://code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/allProduct.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
</body>
</html>
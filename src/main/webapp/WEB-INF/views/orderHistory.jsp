<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>ピザ屋のネット注文</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/piza.css"
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

		<%-- 注文履歴がない場合 --%>
		<c:if test="${fn:length(orderList) == 0}">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<h3 class="text-center text-danger">注文履歴がありません</h3>
						<br> <br>


						<div class="row">
							<div class="form-group">
								<div class="col-xs-offset2 col-xs3">
									<form:form action="${pageContext.request.contextPath}/top">
										<button type="submit" class="btn btn-lg btn-block btn-warning">商品一覧へ戻る</button>
									</form:form>
								</div>
								<div class="col-xs-offset1 col-xs3">
									<form:form action="${pageContext.request.contextPath}/mypage">
										<button type="submit" class="btn btn-lg btn-block btn-warning">マイページに戻る</button>
									</form:form>

								</div>
							</div>
						</div>



						<%-- <form:form action="${pageContext.request.contextPath}/top">
							<button type="submit" class="btn btn-lg btn-block btn-warning">商品一覧へ戻る</button>
						</form:form>

						<form:form action="${pageContext.request.contextPath}/mypage">
							<button type="submit" class="btn btn-lg btn-block btn-warning">マイページに戻る</button>
						</form:form> --%>

					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</c:if>


		<%-- 注文履歴を表示 --%>
		<c:if test="${fn:length(orderList) != 0}">
			<!-- table -->
			<div class="row">
				<div
					class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
					<h1 class="text-center">注文履歴</h1>
					<br>
				</div>
			</div>
			<div class="row">
				<div
					class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
					<table class="table ">
						<c:forEach var="order" items="${orderList}">
							<tr bgcolor="#cccccc">
								<th>
									<div class="text-center">注文日</div>
								</th>
								<th>
									<div class="text-center">
										<fmt:formatDate value="${order.orderDate}"
											pattern="yyyy年MM月dd日" />
									</div>
								</th>
								<th>
									<div class="text-center">合計金額</div>
								</th>
								<th>
									<div class="text-center">
										<fmt:formatNumber value="${order.totalPrice}"
											pattern="###,###" />
										円
									</div>
								</th>
							<tbody>
								<tr>
									<th>
										<div class="text-center">商品</div>
									</th>
									<th>
										<div class="text-center">サイズ、価格(税抜)、数量</div>
									</th>
									<th>
										<div class="text-center">トッピング、価格(税抜)</div>
									</th>
									<th>
										<div class="text-center">小計(税抜き)</div>
									</th>
								</tr>

								<c:forEach var="orderItem" items="${order.orderItemList}">
									<tr>

										<td>
											<div class="center">
												<img src="${orderItem.item.imagePath}"
													class="img-responsive img-rounded" width="100" height="300"><br>
												<c:out value="${orderItem.item.name}" />
												<br>
											</div>
										</td>
										<td><span class="price"> <c:out
													value="${orderItem.size}" />&nbsp;
										</span>&nbsp;&nbsp;&nbsp; <c:if test="${orderItem.size=='M'}">
												<fmt:formatNumber value="${orderItem.item.priceM}"
													pattern="###,###" />
											</c:if> <c:if test="${orderItem.size=='L'}">
												<fmt:formatNumber value="${orderItem.item.priceL}"
													pattern="###,###" />
											</c:if>円 &nbsp;&nbsp; <c:out value="${orderItem.quantity}" />個</td>
										<td>
											<ul>
												<%-- トッピング無の場合 --%>
												<c:if test="${fn:length(orderItem.orderToppingList) == 0}">トッピング無し</c:if>
												<%-- トッピング有の場合 --%>
												<c:forEach var="orderTopping"
													items="${orderItem.orderToppingList}">
													<c:out value="${orderTopping.topping.name}" />
													<c:if test="${orderItem.size=='M'}">
														<c:out value="${orderTopping.topping.priceM}" />円
												&nbsp;<c:out value="${orderItem.quantity}" />個
											</c:if>
													<c:if test="${orderItem.size=='L'}">
														<c:out value="${orderTopping.topping.priceL}" />円
												&nbsp;<c:out value="${orderItem.quantity}" />個
											</c:if>
													<br>
												</c:forEach>
											</ul>
										</td>
										<td>
											<div class="text-center">
												<fmt:formatNumber value="${orderItem.subTotalPrice}"
													pattern="###,###" />
												円
											</div>
										</td>
									</tr>
								</c:forEach>
								<tr>
									<th>&nbsp;</th>
									<th>&nbsp;</th>
									<th>&nbsp;</th>
									<th>&nbsp;</th>
								</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<hr>
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<a href="${pageContext.request.contextPath}/top"> <input
							type="button" class="btn btn-lg btn-block btn-warning"
							value="商品一覧へ戻る">
						</a><br> <br> <br>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>
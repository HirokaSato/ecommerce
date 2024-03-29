<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
</head>
<body>
	<div class="container">
		<%@ include file="./commonNavi.jsp"  %>
	
	<%-- カートに商品がない場合 --%>
	<c:if test="${orderListSize == 0}">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<h3 class="text-center text-danger">カートに商品がありません</h3><br><br>
					<form:form action="${pageContext.request.contextPath}/top">
						<button type="submit" class="btn btn-lg btn-block btn-warning">商品一覧へ戻る</button>
					</form:form>
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
	</c:if>			
		
		
	<%-- カート内商品を表示 --%>
	<c:if test="${orderListSize != 0}">
		<!-- table -->
		<div class="row">
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
				<h3 class="text-center">ショッピングカート</h3>
				<table class="table table-striped">
					<tbody>
						<tr>
							<th>
								<div class="text-center">商品名</div>
							</th>
							<th>
								<div class="text-center">サイズ、価格(税抜)、数量</div>
							</th>
							<th>
								<div class="text-center">トッピング、価格(税抜)</div>
							</th>
							<th>
								<div class="text-center">小計</div>
							</th>
							<th></th>
						</tr>

						<c:forEach var="orderItem" items="${order.orderItemList}">
							<tr>
								<td>
									<div class="center">
										<img src="${orderItem.item.imagePath}" class="img-responsive img-rounded" width="100" height="300"><br>
										<c:out value="${orderItem.item.name}" />
										<br>
									</div>
								</td>
								<td><span class="price"> <c:out value="${orderItem.size}" />&nbsp;</span>
								&nbsp;&nbsp;
								<c:if test="${orderItem.size == 'M'}">
									<fmt:formatNumber value="${orderItem.item.priceM}" pattern="###,###"/>
								</c:if>
								<c:if test="${orderItem.size == 'L'}">
									<fmt:formatNumber value="${orderItem.item.priceL}" pattern="###,###"/>
								</c:if>円 
								&nbsp;&nbsp;
								<fmt:formatNumber value="${orderItem.quantity}" pattern="###,###"/>個</td>
								
								<td>								
									<ul>
									<%-- トッピング無の場合 --%>	
										<c:if test="${fn:length(orderItem.orderToppingList) == 0}">トッピング無し</c:if>
									<%-- トッピング有の場合 --%>
										<c:forEach var="orderTopping" items="${orderItem.orderToppingList}">		
											<li>
												<c:out value="${orderTopping.topping.name}" />
												<c:if test="${orderItem.size=='M'}">
													<fmt:formatNumber value="${orderTopping.topping.priceM}" pattern="###,###"/>
												</c:if> 
												<c:if test="${orderItem.size=='L'}">
													<fmt:formatNumber value="${orderTopping.topping.priceL}" pattern="###,###"/>
												</c:if>
												円
											</li>
										</c:forEach>
									</ul>										
								</td>
								<td>
									<div class="text-center">
										<fmt:formatNumber value="${orderItem.subTotalPrice}" pattern="###,###"/>
										円
									</div>
								</td>
								<td>
									<div class="text-center">
										<form:form action="${pageContext.request.contextPath}/delteCartItem/delete" method="post">
											<button type="submit" class="btn btn-primary">削除</button>
											<input type="hidden" name="userId" value="${order.userId}"/>											
											<input type="hidden" name="id" value="${orderItem.id}"/>
											<input type="hidden" name="subTotalPrice" value="${orderItem.subTotalPrice}"/>
										</form:form>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-offset-2 col-xs-8">
				<div class="form-group text-center">
					<span id="total-price">
					消費税：<fmt:formatNumber value="${tax}" pattern="###,###"/>円</span><br>
					<span id="total-price">ご注文金額合計：<fmt:formatNumber value="${totalPrice}" pattern="###,###"/>円(税込)</span>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-offset-5 col-xs-3">
				<div class="form-group">
					<a href="${pageContext.request.contextPath}/OrderConfirmationController/">
						<input class="form-control btn btn-warning btn-block"type="button" value="注文に進む">
					</a><br>
					<a href="${pageContext.request.contextPath}/top">
						<input class="form-control btn btn-warning btn-block"type="button" style="background-color: #6495ED; border-color: #6495ED" value="商品一覧へ戻る">
					</a>
				</div>
			</div>
		</div>	
		
	</c:if>

	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/ajaxzip3.js" charset="UTF-8"></script>
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
							class="icon-bar"></span> <span class="icon-bar"></span> 
							<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="item_list.html"> <!-- 企業ロゴ --> <img
						alt="main log" src="${pageContext.request.contextPath}/img/header_logo.png" height="35">
					</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<p class="navbar-text navbar-right">
						<a href="cart_list.html" class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;
						<a href="order_history.html" class="navbar-link">注文履歴</a>&nbsp;&nbsp;
						<a href="login.html" class="navbar-link">ログイン</a>&nbsp;&nbsp;
						<a href="item_list.html" class="navbar-link">ログアウト</a>
					</p>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>


		<!-- table -->
		<div class="row">
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
				<h3 class="text-center">注文内容確認</h3>
				<table class="table table-striped">
					<tbody>
						<tr>
							<th>
								<div class="text-center">
									商品名
								</div>
							</th>
							<th>
								<div class="text-center">
									サイズ、価格(税抜)、数量
								</div>
							</th>
							<th>
								<div class="text-center">
									トッピング、価格(税抜)
								</div>
							</th>
							<th>
								<div class="text-center">
									小計
								</div>
							</th>
						</tr>
						<c:forEach var="orderItem" items="${orderItemList}">
							<tr>
								<td>
									<div class="center">
										<img src="${orderItem.item.imagePath}" class="img-responsive img-rounded" width="100" height="300"><br>
										<c:out value="${orderItem.item.name}" />
										<br>
									</div>
								</td>
								<td><span class="price"> <c:out value="${orderItem.size}" />&nbsp;</span>
								&nbsp;&nbsp;&nbsp;
								<c:if test="${orderItem.size == 'M'}"><c:out value="${orderItem.item.priceM}" /></c:if>
								<c:if test="${orderItem.size == 'L'}"><c:out value="${orderItem.item.priceL}" /></c:if>
								円 &nbsp;&nbsp; <c:out value="${orderItem.quantity}" />個</td>
								<td>
									<ul>
										<c:forEach var="orderTopping" items="${orderItem.orderToppingList}">
											<li><c:out value="${orderTopping.topping.name}" />
											<c:if test="${orderItem.size=='M'}">
												<c:out value="${orderTopping.topping.priceM}" />
											</c:if> 
											<c:if test="${orderItem.size=='L'}">
												<c:out value="${orderTopping.topping.priceL}" />
											</c:if>
											円
											</li>
										</c:forEach>
									</ul>
								</td>
		
								<td>
									<div class="text-center">
										<c:out value="${order.totalPrice}" />
										円
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
					消費税：<c:out value="${tax}" />円</span><br>
					<span id="total-price">ご注文金額合計：<c:out value="${taxIncludedAmount}" />円(税込)</span>
				</div>
			</div>
		</div>
		
		<!-- table -->
	<form:form modelAttribute="orderForm" action="${pageContext.request.contextPath}/doOrderController/order">
	<form:hidden path="userId" value="${order.userId}"/>
	<form:hidden path="id" value="${order.id}"/> 
	<form:hidden path="totalPrice" value="${taxIncludedAmount}"/>
		<div class="row">
			<div
				class="table-responsive col-lg-offset-3 col-lg-6 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
				<h3 class="text-center">お届け先情報</h3>
				<table class="table table-striped">
					<tbody>
						<tr>
							<td>
								<div class="text-center">
									お名前
								</div>
							</td>
							<td>
								<form:errors path="name" cssStyle="color:red" element="div"/>
								<form:input path="name"/>
							</td>
						</tr>
						<tr>
							<td>
								<div class="text-center">
									メールアドレス
								</div>
							</td>
							<td>
								<form:errors path="email" cssStyle="color:red" element="div"/>
								<form:input path="email"/>
							</td>
						</tr>
						<tr>
							<td>
								<div class="text-center">
									郵便番号(ハイフンなし)
								</div>
							</td>
							<td>
								<form:errors path="zipcode" cssStyle="color:red" element="div"/>
								<form:input path="zipcode" size="10" maxlength="8" onKeyUp="AjaxZip3.zip2addr(this,'','addr11','addr11');"/>
								&nbsp;&nbsp;<button id="searchZipcode" >住所検索</button>
							</td>
						</tr>
						<tr>
							<td>
								<div class="text-center">
									住所
								</div>
							</td>
							<td>
								<form:errors path="address" cssStyle="color:red" element="div"/>
								<form:input  path="address"/>
							</td>
						</tr>
						<tr>
							<td>
								<div class="text-center">
									電話番号
								</div>
							</td>
							<td>
								<form:errors path="telNumber" cssStyle="color:red" element="div"/>
								<form:input path="telNumber"/>
							</td>
						</tr>
						<tr>
							<td>
								<div class="text-center">
									希望配達日時
								</div>
							</td>
							<td>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-12">
										</div>
										<div class="col-sm-5">
											<form:errors path="deliveryDate" cssStyle="color:red" element="div"/>
											<form:errors path="deliveryTime" cssStyle="color:red" element="div"/>
											<form:input type="date" path="deliveryDate"/>
										</div>
										
									</div>
									<div class="row">
										<div class="col-sm-12">
											<label class="radio-inline"> 
												<form:radiobutton path="deliveryTime" value="10:00:00"/>
												10時
											</label>
											<label class="radio-inline"> 
												<form:radiobutton path="deliveryTime" value="11:00:00"/>
												11時
											</label>
											<label class="radio-inline"> 
												<form:radiobutton path="deliveryTime" value="12:00:00"/>
												12時
											</label><br>
											<label class="radio-inline"> 
												<form:radiobutton path="deliveryTime" value="13:00:00"/>
												13時
											</label>
											<label class="radio-inline"> 
												<form:radiobutton path="deliveryTime" value="14:00:00"/>
												14時
											</label>
											<label class="radio-inline"> 
												<form:radiobutton path="deliveryTime" value="15:00:00"/>
												15時
											</label><br>
											<label class="radio-inline"> 
												<form:radiobutton path="deliveryTime" value="16:00:00"/>
												16時
											</label>
											<label class="radio-inline"> 
												<form:radiobutton path="deliveryTime" value="17:00:00"/>
												17時
											</label>
											<label class="radio-inline"> 
												<form:radiobutton path="deliveryTime" value="18:00:00"/>
												18時
											</label><br>
										</div>
									</div>
								</div>
								
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- table -->
		<div class="row">
			<div
				class="table-responsive col-lg-offset-3 col-lg-6 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
				<h3 class="text-center">お支払い方法</h3>
				<form:errors path="paymentMethod" cssStyle="color:red" element="div"/>
				<table class="table table-striped">
					<tbody>
						<tr>
							<td>
								<div class="text-center">
									代金引換
								</div>
							</td>
							<td align="center">
								<div class="row">
									<div class="col-sm-12">
										<label class="radio-inline"> 
												<form:radiobutton path="paymentMethod" value="1"/>
										</label><br><br>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="text-center">
									クレジットカード決済
								</div>
							</td>
							<td align="center">
								<div class="row">
									<div class="col-sm-12">
										<label class="radio-inline"> 
												<form:radiobutton path="paymentMethod" value="2"/>
										</label><br><br>
									</div>
								</div>
								<%---<script src="https://checkout.webpay.jp/v3/" class="webpay-button" data-key="test_public_87Aa3Pe1r1XffA3dwB8TH690" data-lang="ja" data-partial="true"/>--%>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-offset-4 col-xs-4">
				<div class="form-group">
						<input  class="form-control btn btn-warning btn-block"
							type="submit" value="この内容で注文する">
				</div>
			</div>
		</div>
		</form:form>
		<br><br><br><br><br><br>

	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
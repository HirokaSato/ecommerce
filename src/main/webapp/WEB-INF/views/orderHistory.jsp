<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>ピザ屋のネット注文</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/piza.css" rel="stylesheet">
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
						<a href="${pageContext.request.contextPath}/logout" class="navbar-link">ログアウト</a>
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
				<h3 class="text-center">注文履歴</h3>
				
				<table class="table table-striped">
					<tbody>
						<c:forEach var="orderHistory" items="${orderHistory}">
						<tr>
							<th>
								<div class="text-center">
									注文日
								</div>
							</th>
							<th>
								<div class="text-center">
									<c:out value="${nothing}"/>
									<fmt:formatDate value="${orderHistory.orderDate}" pattern="yyyy年MM月dd日"/>								
								</div>
							</th>							
						</tr>
						</c:forEach>							
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
			
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
									合計(税込み)
								</div>
							</th>
						</tr>
						
						
						
						
						
						
						
						
						
						
						
						<c:forEach var="orderHistory" items="${orderHistory}">
							<tr>
								<td>
									<div class="center">
										<img src="${orderItem.item.imagePath}" class="img-responsive img-rounded" width="100" height="300"><br>
										<c:out value="${item.name}" />
									
										<br>
									</div>
								</td>
								
								
								
								
								<td><span class="price">
								<c:out value="${orderItemHistory.size}" />&nbsp;</span>
								&nbsp;&nbsp;&nbsp;
								
								円 &nbsp;&nbsp; <c:out value="${orderItem.quantity}" />個
								</td>
								
								
								
								
								
								<td>
									<ul>
										<c:forEach var="orderToppingHistory" items="${orderToppingHistory}">
											<c:out value="${orderToppingHistory.topping}"/>
										</c:forEach>
									</ul>
								</td>
		
								<td>
									<div class="text-center">
										<c:out value="${orderHistory.totalPrice}" />
										円
									</div>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


</div>
</body>
</html>
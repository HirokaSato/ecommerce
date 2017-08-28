<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
						<tr>
							<th>
								<div class="text-center">
									注文日
								</div>
							</th>
							<th>
								<div class="text-center">
									~~.orderDate
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
								<td>
									<div class="text-center">
										<button type="submit" class="btn btn-primary">削除</button>
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
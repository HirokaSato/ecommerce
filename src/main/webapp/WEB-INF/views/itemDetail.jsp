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
<link href="css/piza.css" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]--> 


<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/itemDetail.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="./commonNavi.jsp"  %>
		<form:form action="${pageContext.request.contextPath}/addToCart" modelAttribute="addToCartForm">
		<div class="row">
			<div class="col-xs-offset-2 col-xs-8">
				<h3 class="text-center">商品詳細</h3>
				<div class="row">
					<div class="col-xs-5">
						<img src="${item.imagePath}" class="img-responsive img-rounded">
					</div>
					<div class="col-xs-5">
						<div class="bs-component">
							<!-- 商品のidをhiddenで送る -->
							<form:hidden path="itemId" value="${item.id}"/>
							<h4><c:out value="${item.name}"/></h4> <br>
							<br>
							<p><c:out value="${item.description}"/></p>
						</div>
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-offset-2 col-xs-8">
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<label class="control-label" style="color: red" for="inputError">サイズを選択してください</label>
								<%--　<label for="inputResponsibleCompany">サイズ</label>  --%>
								</div>
								<div class="col-sm-12" id="pizaSize">
									<label class="radio-inline priceM" data-price="<c:out value="${item.priceM}"/>"> 
										<input type="radio" name="size" value="M">
										<span class="price">&nbsp;М&nbsp;</span>&nbsp;&nbsp;
										<fmt:formatNumber value="${item.priceM}" pattern="###,###"/>円(税抜)						
									</label>									
									<label class="radio-inline priceL" data-price="<c:out value="${item.priceL}"/>"> 
										<input type="radio" name="size" value="L" > 
										<span class="price">&nbsp;L&nbsp;</span>&nbsp;&nbsp;<fmt:formatNumber value="${item.priceL}" pattern="###,###"/>円(税抜)
									</label>
								</div>
							</div>
						</div>
					</div>
				</div><br>
				
				<div class="row">
					<div class="col-xs-offset-2 col-xs-8" id="toppingCheck">
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<label class="control-label" style="color: red" for="inputError">トッピングはいかがですか?</label>
									<label for="inputResponsibleCompany">
									トッピング：&nbsp;1つにつき
									<span>&nbsp;М&nbsp;</span>&nbsp;&nbsp;200円(税抜)
									<span>&nbsp;Ｌ</span>&nbsp;&nbsp;300円(税抜)
									</label>
										<div class="col-sm-12">
											<c:forEach items="${toppingList}" var="topping" varStatus="status">
												<label class="checkbox-inline">
													<input type="checkbox" name="toppingList" value="${topping.id}"><c:out value="${topping.name}"/>		
												</label>
												<c:if test="${(status.count+4)%4 == 0}"><br></c:if>
											</c:forEach>
										</div>									
								</div>
							</div>
						</div>
					</div>
				<div class="row" id="num">
					<div class="col-xs-offset-2 col-xs-8">
						<div class="form-group">
							<div class="row">
								<div class="col-xs-5 col-sm-5">
									<label class="control-label" style="color: red" for="inputError">数量を選択してください</label>
									<select name="quantity" class="form-control" id="select">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
									</select>	
								</div>
							</div>
						</div>
					</div>
				</div>
				<br>

				<div class="row" class="total">
					<div class="col-xs-offset-2 col-xs-8">
						<div class="form-group">
 						<span id="total-price"  class="total">この商品金額：
							<span id="totalPrice"></span>円(税抜)</span>
						</div>
						<p id="numofpizza"></p>
					</div>
				</div>
			<div class="row" class="total">
					<div class="col-xs-offset-4 col-xs-4">
						<div class="form-group">
							<p  class="total">
								<input class="form-control btn btn-warning btn-block" type="submit" value="カートに入れる">
							</p>							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form:form>
	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script>
		$(function(){
			$(".btn-block").on("click",function(){
				$(".btn-block").off("click", onclick).on("click", false);
			});
		});
	</script>
	
</body>
</html>
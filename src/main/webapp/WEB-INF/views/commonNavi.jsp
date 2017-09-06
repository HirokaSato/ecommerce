<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link type="text/css" rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/cupertino/jquery-ui.min.css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>
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
			<a class="navbar-brand" href="${pageContext.request.contextPath}/top">
				<!-- 企業ロゴ --> <img alt="main log"
				src="${pageContext.request.contextPath}/img/header_logo.png"
				height="35">
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<p class="navbar-text navbar-right">
				<sec:authorize access="isAuthenticated()">
					<strong><sec:authentication property="principal.user.name" />&nbsp;さん、いらっしゃいませ！</strong>&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="navbar-link" href="${pageContext.request.contextPath}/myPage/">マイページ</a>&nbsp;&nbsp;
						</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<strong>ゲスト さんいらっしゃいませ！</strong>&nbsp;&nbsp;&nbsp;&nbsp;
						</sec:authorize>
				<a href="${pageContext.request.contextPath}/viewCartList"
					class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;
				<sec:authorize access="isAnonymous()">
					<a href="${pageContext.request.contextPath}/login"
						class="navbar-link">ログイン</a>&nbsp;&nbsp; 
						</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<a href="${pageContext.request.contextPath}/orderHistory"
						class="navbar-link">注文履歴</a>&nbsp;&nbsp;
							<a href="${pageContext.request.contextPath}/logout"
						class="navbar-link">ログアウト</a>
				</sec:authorize>
			</p>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
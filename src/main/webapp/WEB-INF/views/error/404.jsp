<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file ="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">
		요청 URL 문제
	</div>
	<div class="card-body">
		<p>요청 URL이 잘못 구성 되었습니다.</p>
		<a href="${pageContext.request.contextPath}/" class="btn btn-danger btn-sms">홈으로 가기</a>
	</div>
</div>
<%@ include file ="/WEB-INF/views/common/footer.jsp"%>
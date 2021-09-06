<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file ="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">
		서버 실행 오류
	</div>
	<div class="card-body">
		<p>데이터 타입 일치하지 않습니다.</p>
		<a href="${pageContext.request.contextPath}/" class="btn btn-danger btn-sms">홈으로 가기</a>
	</div>
</div>
<%@ include file ="/WEB-INF/views/common/footer.jsp"%> 
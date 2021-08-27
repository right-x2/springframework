<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file ="/WEB-INF/views/common/header.jsp"%>
<div class="card m-2">
	<div class="card-header">

	</div>
	<div class="card-body">
		<form method="post" action="${pageContext.request.contextPath}/ch02/boardwrite">
		  <div class="mb-3">
		    <label for="title">제목</label>
		    <input type="text" class="form-control" id="title">
		  </div>
		  <div class="mb-3">
		    <label for="content">내용</label>
		    <input type="text" class="form-control" id="content">
		  </div>
		  <button type="submit" class="btn btn-primary btn-sm">저장</button>
		</form>
	</div>
</div>
<%@ include file ="/WEB-INF/views/common/footer.jsp"%>
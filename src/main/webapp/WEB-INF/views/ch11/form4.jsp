<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file ="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">
		DTO 객체의 필드값을 양식의 드롭다운 리스트(radio 태그)로 세팅
	</div>
	<div class="card-body">
	
		<form>
			<c:forEach var="job" items= "${jobList}" varStatus="status">
				<div class="form-check form-check-inline ml-2">
				  <input class="form-check-input" type="radio" id="lang${status.count}" name="mjob" value="${job}"
				  	<c:forEach var="temp" items="${member.mjob}">
				  		<c:if test="${temp==job}">checked</c:if>
				  	</c:forEach>
				  	>
				  <label class="form-check-label" for="lang${status.count}">${job}</label>
				</div>	
			</c:forEach>
			<button class="btn btn-info btn-sm">제출</button>
		</form>

		<form:form modelAttribute="member" class="mt-3">
			<div class="form-check form-check-inline">
				<form:radiobuttons items="${jobList}" path="mjob" class="ml-2 mr-1 mt-3"></form:radiobuttons>
			</div>
			<button class="btn btn-info btn-sm">제출</button>
		</form:form>
		
		
		<form:form modelAttribute="member">
			<div class="form-check form-check-inline">
				<form:radiobuttons  path="mcity" items="${cityList}" itemValue="code" itemLabel="label" class="ml-2 mr-1  mt-3"></form:radiobuttons>
			</div>
			<button class="btn btn-info btn-sm">제출</button>
		</form:form>
	</div>
</div>
<%@ include file ="/WEB-INF/views/common/footer.jsp"%>
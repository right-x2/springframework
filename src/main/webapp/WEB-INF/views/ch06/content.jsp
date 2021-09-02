<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file ="/WEB-INF/views/common/header.jsp"%>
<div class="card m-2">
	<div class="card-header">
		Controller/Forward & Redirect
	</div>
	<div class="card-body">
		<a href="forward" class="btn btn-info btn-sm">JSP 포워드</a>
		<a href="redirect" class="btn btn-info btn-sm">홈으로 리다이렉트</a>
		
		<hr>
	
		<P>[AJAX 요청은 리다이렉트를 하면 안됨]</P>
		<a href="javascript:ajax1()" class="btn btn-info btn-sm">AJAX 요청(HTML 조각)</a>
		<a href="javascript:ajax2()" class="btn btn-info btn-sm">AJAX 요청(JSON)</a>
		<a href="javascript:ajax3()" class="btn btn-info btn-sm">AJAX 요청(JSON)</a>
		<a href="javascript:ajax4()" class="btn btn-info btn-sm">AJAX 요청(redirect) - 잘못된 방법</a>
		<div id="content" class="mt-2"></div>
	</div>
	

	<script >
		function ajax1(){
			console.log("ajax1() 실행");
			
			$.ajax({
				url:"getFragmentHtml"
			})
			.done((data)=>{
				$("#content").html(data);
			});
		}
		
		
		function ajax2(){
			console.log("ajax2() 실행");
			
			$.ajax({
				url:"getJson1"
			})
			.done((data)=>{
				$("#content").html("<img src='${pagaContext.request.contextPath}/resources/images/"+data.fileName+"' width='200px'/>");
			});
		}
		
		function ajax3(){
			console.log("ajax3() 실행");
			
			$.ajax({
				url:"getJson2"
			})
			.done((data)=>{
				$("#content").html("<img src='${pagaContext.request.contextPath}/resources/images/"+data.fileName+"' width='200px'/>");
			});
		}
		
		function ajax4(){
			console.log("ajax4() 실행");
			
			$.ajax({
				url:"getJson3"
			})
			.done((data)=>{
				$("#content").html("<img src='${pagaContext.request.contextPath}/resources/images/"+data.fileName+"' width='200px'/>");
			});
		}
	</script>
</div>
<%@ include file ="/WEB-INF/views/common/footer.jsp"%>
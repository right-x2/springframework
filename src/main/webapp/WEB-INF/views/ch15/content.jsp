<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file ="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">
		AOP(관점지향프로그래밍)
	</div>
	<div class="card-body">
		<div class="card ">
			<div class="card-header">
				Advice 예제
			</div>
			<div class="card-body">
				<a href="before" class="btn btn-info btn-sm">@before 테스트</a>
				<a href="after" class="btn btn-info btn-sm">@after 테스트</a>
				<a href="afterReturning" class="btn btn-info btn-sm">@afterReturning 테스트</a>
				<a href="afterThrowing" class="btn btn-info btn-sm">@afterThrowing 테스트</a>
				<a href="around" class="btn btn-info btn-sm">@around 테스트</a>
			</div>
		</div>
		
		<div class="card ">
			<div class="card-header">
				AOP 예제
			</div>
			<div class="card-body">
				<a href="runtimeCheck" class="btn btn-info btn-sm">요청 처리 시간 측정</a>
				<a href="javascript:boardList1()" class="btn btn-info btn-sm">인증 여부 확인(HTML 응답)</a>
				<a href="javascript:boardList2()" class="btn btn-info btn-sm">인증 여부 확인(JSON 응답)</a>
				<hr>
				<div id="boardList"></div>
			</div>
			<hr/>
			<div>
				${methodName} 실행시간: ${howLong}
			</div>
			<script type="text/javascript">
				function boardList1(){
					$.ajax({
						url: "boardList1"
					}).done((data)=>{
						if(data.result=="authFail"){
							console.log("-----------");
							window.location.href="login";
						}else{
							$("#boardList").html(data);
						}
						
					});
				}
				
				
				function boardList2(){
					$.ajax({
						url: "boardList2"
					}).done((data)=>{ //{result:"success",boards:[{...},{...} ...]}
						if(data.result=="authFail"){
							console.log("----------");
							window.location.href="login";
						}else{
							//[{...},{...},{..}]
							// 하나가 게시물 하나에 해당됨
							let html = "";
							
							
							html += '<table class="table table-sm table-bordered container-fluid">';
							html +='<tr>';
							html +='<th class="col-sm-1">번호</th>';
							html +='<th class="col-sm-7">제목</th>';
							html +='<th class="col-sm-2">글쓴이</th>';
							html +='<th class="col-sm-2">날짜</th>';
							html +='</tr>';
						
						
							
							
							for(var board of data.boards){
								html +='<tr>';
								html +='	<td>'+board.bno+'</td>';
								html +='	<td><a href="boardDetail?bno='+board.bno+'">'+board.btitle+'</a></td>';
								html +='	<td>'+board.mid+'</td>';
								html +='	<td>'+board.bdate+'</td>';
								html +='</tr>';
							}
							html +='</table>';
							$("#boardList").html(html);
						}
					});
				}
			</script>
		</div>
		
		<div class="card">
			<div class="card-header">form을 통한 login 처리</div>
			<div class="card-body">
				<c:if test="${sessionMid==null}">
					<a href="login" class="btn btn-info btn-sm">로그인 폼 요청</a>
				</c:if>
				<c:if test="${sessionMid!=null}">
					<a href="logout" class="btn btn-info btn-sm">로그아웃</a>
				</c:if>
			</div>
		</div>
	</div>
</div>
<%@ include file ="/WEB-INF/views/common/footer.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">FileUpload & FileDownload</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">Form 태그를 이용한 FileUpload</div>
			<div class="card-body">
	            <form method="post" enctype="multipart/form-data" action="fileupload">
	               <div class="form-group">
	                  <label for="title">title</label> 
	                  <input type="text" class="form-control" id="title" name="title" placeholder="파일 제목">
	               </div>
	               <div class="form-group">
	                  <label for="desc">File description</label> 
	                  <input type="text" class="form-control" id="desc" name="desc" placeholder="파일 설명">
	               </div>
	               <div class="form-group">
	                   <label for="attach">Example file input</label>
	                   <input type="file" class="form-control-file" id="attach" name="attach" multiple="multiple">
	               </div>
	               <button class="btn btn-info btn-sm">form 파일 업로드</button>
	               <a href="javascript:fileUpload()" class="btn btn-info btn-sm">AJAX 파일 업로드</a>
	            </form>
			</div>
			<script>
				function fileUpload() {
					const title = $("#title").val();
					const desc = $("#desc").val();
					const attach = document.querySelector("#attach").files[0];
					
					
					
					//Multipart/form-data
					const formData = new FormData();
					formData.append("title",title);
					formData.append("desc",desc);
					formData.append("attach",attach);
					console.log(attach);
					
					//ajax 서버로 전송
					$.ajax({
						url:"fileuploadAjax",
						method:"post",
						data: formData,
						cache:false, //큰파일을 보내기떄문에 캐싱해서 저장할 필요가 없다.
						processData: false, // 파일데이터를 임의로 ajax가 가공하지 못하도록 막는 것이다.
						contentType: false // multi part기 때문에 각 파일의 contentType이 다르다. 개별적인 contenttype이 넘어가기 때문에 명시하지 않아도 
					}).done((data)=>{
						console.log(data.result);
						if(data.result==="success"){
							window.alert("파일전송이 성공됨");
						}
					});
				}	
			</script>
		</div>
		<div class="card">
			<div class="card-header">
				파일 다운로드
			</div>
			<div class="card-body">
				<a href="filedownload?fileNo=1" class="btn btn-sm">파일 다운로드</a>
				<hr>
				<img src="filedownload?fileNo=1" width="200px"/>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
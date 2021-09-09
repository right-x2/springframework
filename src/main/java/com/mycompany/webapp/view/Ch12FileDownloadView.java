package com.mycompany.webapp.view;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class Ch12FileDownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 logger.info("실행");
		// TODO Auto-generated method stub
		// fileno로 디비에서 퍼알 정보 거죠오
		// 파일 뒤의 확정명을 보고 자동적으로 mimetype이름을 리턴해준다. 아주 중요함
		 
		 
		String fileName =(String)model.get("fileName");
		String userAgent = (String)model.get("userAgent");
		String contentType = request.getServletContext().getMimeType(fileName);
		String originalFilename = fileName;
		String savedName = fileName;
		logger.info(fileName);
		// http 헤더에는 아스키 문자만 들어가지만 한글은 포함안됨 그래서 바꾸어 주어랴 한다.

		// 크롬 브라우저에서 한글 파일명을 변환

		// 응답 버다의 데이ㅣ터의 형식
		System.out.println(userAgent);
		response.setContentType(contentType);

		if (userAgent.contains("Tredent") || userAgent.contains("MSIE")) {
			// IE일 경우
			originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
		} else {
			// 크롬, 엣지, 사파리일 경우
			originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
		}
		// 응답헤더에 이 헤더가 포함이 되면 지시한대로 다운로드가 되어야 한다.
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");
		// 파일을 첨부로 다운로드하도록 설정 응답헤더에 지정하는 것이다.

		// 파일로부터 데이터를 읽는 입력스트림 생성
		String filePath = "/Users/kimjungwoo/Documents/hyundai_it/upload_files/" + savedName;
		InputStream is = new FileInputStream(filePath);

		// 응답 바디에 출력하는 출력스트임 얻기
		OutputStream os = response.getOutputStream();

		// 입력스트림 -> 출력스트림

		//FileCopyUtils.copy(is, os); // 파일사이즈가 커져도 무리가 없다. -> 1메가씩 읽고 출력하기 때문에 메모리를 많이 차지하지 않는다.
		// 바이트의 길이를 줄 수 없다.
		
		byte[] data = new byte[1024];
		int readByteNum = -1;
		while(true) {
			readByteNum = is.read(data);
			if(readByteNum==-1)
				break;
			os.write(data,0,readByteNum);
			os.flush();
		}
		
		os.close();
		is.close();
	}

}

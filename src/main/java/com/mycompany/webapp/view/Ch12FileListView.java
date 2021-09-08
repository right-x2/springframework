package com.mycompany.webapp.view;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.mycompany.webapp.controller.Ch09Controller;

@Component
public class Ch12FileListView extends AbstractView {

	private static final Logger logger = LoggerFactory.getLogger(Ch12FileListView.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 파일 총 수 잋 파일 이름 목록 얻기
		String fileDir = "/Users/kimjungwoo/Documents/hyundai_it/upload_files";
		logger.info("실행");

		File file = new File(fileDir);

		String[] fileList = file.list();
		System.out.println(fileList.length);
		int totalFileNum = fileList.length;

		response.setContentType("Application/json; charset=UTF-8");

		PrintWriter pw = response.getWriter();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("totalFileNum", totalFileNum);
		JSONArray jsonArray = new JSONArray();
		for (String fileName : fileList) {
			jsonArray.put(fileName);
		}
		jsonObject.put("fileList", jsonArray);
		String json = jsonObject.toString();
		pw.print(json);
		pw.flush();
		pw.close();

	}

}

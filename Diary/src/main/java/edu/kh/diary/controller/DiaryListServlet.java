package edu.kh.diary.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.diary.model.dto.Diary;
import edu.kh.diary.model.service.DiaryService;
import edu.kh.diary.model.service.DiaryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/diary/diaryList")
public class DiaryListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			DiaryService service = new DiaryServiceImpl();
			List<Diary> diaryList = service.selectAll();
			
			req.setAttribute("diaryList", diaryList);
			
			String path= "/WEB-INF/views/diaryList.jsp";
			
			req.getRequestDispatcher(path).forward(req, resp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}

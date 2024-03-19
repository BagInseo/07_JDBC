package edu.kh.diary.controller;

import java.io.IOException;

import edu.kh.diary.model.dto.Diary;
import edu.kh.diary.model.service.DiaryService;
import edu.kh.diary.model.service.DiaryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/diary/detail")
public class DiaryDetail extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			
			DiaryService service = new DiaryServiceImpl();
			
			int diaryNo = Integer.parseInt(req.getParameter("diaryNo"));
			
			Diary diary = service.detailDiary(diaryNo);
			
			if(diary != null) {
				req.setAttribute("diary", diary);
				String path ="/WEB-INF/views/detail.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

}

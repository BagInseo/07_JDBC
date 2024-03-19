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
import jakarta.servlet.http.HttpSession;

@WebServlet("/diary/main")
public class MainServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
		
			DiaryService service = new DiaryServiceImpl();
			
			String diaryTitle = req.getParameter("diaryTitle");
			String diaryWeather = req.getParameter("weather");
			String diaryFeel = req.getParameter("feel");
			String diaryContent = req.getParameter("diaryContent");
			
			
			int result = service.addDiary( diaryTitle, diaryWeather, diaryFeel, diaryContent);
			
			
			HttpSession session = req.getSession();
			
			String message = null;
			 if (result>0) message = "일기 저장 완료";
			 else		   message = "일기 저장 실패";
			 
			 session.setAttribute("message", message);
			 
			 
			 resp.sendRedirect("/");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	
	}

}

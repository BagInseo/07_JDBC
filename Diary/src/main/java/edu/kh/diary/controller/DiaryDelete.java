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

@WebServlet("/diary/diaryDelete")
public class DiaryDelete extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		int result=0;
		String message = null;
		
		try {
			DiaryService service = new DiaryServiceImpl();
			
			int diaryNo =Integer.parseInt(req.getParameter("diaryNo")) ;
			
			HttpSession session = req.getSession();
			result = service.deleteDiary(diaryNo);
			
			
			if(result>0) message ="삭제 성공";
			else 		 message = "삭제 실패";
			
			session.setAttribute("message", message);
			resp.sendRedirect("/diary/diaryList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	
	}

}

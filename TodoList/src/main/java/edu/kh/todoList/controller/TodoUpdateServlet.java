package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.model.service.TodoService;
import edu.kh.todoList.model.service.TodoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo/update")
public class TodoUpdateServlet extends HttpServlet {
	
	// 수정 화면 전환
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			TodoService service = new TodoServiceImpl(); // 서비스 객체 생성
			
			// 파라미터 얻어오기(+ int로 변환)
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			
			// 상세 조회 서비스 호출
			Todo todo = service.selectTodo(todoNo);
			
			if(todo != null) { // 존재하는 글 == 수정 가능
				req.setAttribute("todo", todo);
				String path = "/WEB-INF/views/update.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
			} else { // 존재하지 않는 글 == 수정 불가
				req.getSession().setAttribute("message", "존재하지 않는 할 일 입니다");
				resp.sendRedirect("/"); // 메인 페이지로 
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	// 제목, 내용 수정
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			// 서비스 객체 생성
			TodoService service = new TodoServiceImpl();
			
			// 파라미터 얻어오기
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			String todoTitle = req.getParameter("todoTitle");
			String todoContent = req.getParameter("todoContent");
			
			// 파라미터를 Todo를 이용해 묶기
			Todo todo = new Todo(todoNo, todoTitle, todoContent);
			
			
			// 서비스 메서드 호출 후 결과 반환 받기
			int result = service.updateTodo(todo);
			
			String path = null;
			String message = null;
			
			if(result > 0) { // 수정 성공 시
				path = "/todo/detail?todoNo=" + todoNo;
				message = "수정 성공";
			
			} else { // 수정 실패 시
				path = "/todo/update?todoNo=" + todoNo;
				message = "수정 실패";
			}
			
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(path);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	

}
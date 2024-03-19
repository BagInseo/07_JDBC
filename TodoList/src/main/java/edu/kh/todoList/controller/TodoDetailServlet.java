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

// 할 일 상세 조회
@WebServlet("/todo/detail")
public class TodoDetailServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			// 서비스 객체 생성
			TodoService service = new TodoServiceImpl();
			
			// 파라미터(todoNo) 얻어오기
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			
			// 서비스 호출 후 결과 반환 받기
			Todo todo = service.selectTodo(todoNo);
			
			// 조회 결과에 따라 응답 제어
			
			if(todo != null) { // 조회 결과가 있을 경우
				req.setAttribute("todo", todo);
				String path = "/WEB-INF/views/detail.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
			} else { // 조회 결과가 없을 경우 
					// -> 파라미터로 전달 받은 todoNo가 DB에 존재하지 않음
				
				req.getSession().setAttribute("message", "해당 할 일이 존재하지 않습니다");
				resp.sendRedirect("/");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
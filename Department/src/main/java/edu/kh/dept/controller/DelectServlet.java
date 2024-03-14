package edu.kh.dept.controller;

import java.io.IOException;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.service.DepartmentService;
import edu.kh.dept.model.service.DepartmentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/department/delect")
public class DelectServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		int result=0;
		String message = null;
		
		try {
			
			//서비스 객체 생성 
			DepartmentService service = new DepartmentServiceImpl();
			
			//파라미터 얻어오기
			String deptId =  req.getParameter("deptId"); //삭제할 부서 코드 
			
			// 서비스 메서드 호출 후 결과 반환 받기
		   HttpSession session = req.getSession();
		   result=service.deleteDepartment(deptId);
			
			
			// 서비스 결과에 따라서 
			//Session에 "삭제 성공", "삭제 실패" 메세지 속성을 추가
			if(result>0) message="삭제 성공";
			else		 message="삭제 실패";
			
			//전체 부서 조회를 재요청 
			session.setAttribute("message", message);
			resp.sendRedirect("/department/selectAll");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	
	}

}

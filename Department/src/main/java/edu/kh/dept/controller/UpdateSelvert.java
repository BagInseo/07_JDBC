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

@WebServlet("/department/update")
public class UpdateSelvert extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//파라미터 얻어오기
		String deptId=req.getParameter("deptId");
		int result=0;
		
		
		try {
			DepartmentService service = new DepartmentServiceImpl();
			
			//deptId가 일치하는 1행 조회
			Department dept = service.selectOne(deptId);
			
			//조회 결과가 없을 경우
			if(dept==null) {
				req.getSession().setAttribute("message", "해당 부서가 존재하지 않습니다.");
				resp.sendRedirect("/department/selectAll"); //전체 조회로 redirect
				
			}else {
				req.setAttribute("dept", dept);
				String path ="/WEB-INF/views/update.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int result;
		String message=null;
		
		try {
			DepartmentService service = new DepartmentServiceImpl();
			
		
			String deptId = req.getParameter("deptId");
			String deptTitle = req.getParameter("deptTitle");
			String locationId = req.getParameter("locationId");
			
			Department dept = new Department(deptId, deptTitle, locationId);
			
			result  = service.updateDepartment(dept);
			
			if(result>0) message="부서 수정 성공";
			else		 message="부서 수정 실패";
			
			//message를 Session에 속성으로 추가
			req.getSession().setAttribute("message", message);
			
			//전체 부서 조회 재요청
			resp.sendRedirect("/department/selectAll");
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	
	}
}

package edu.kh.dept.controller;

import java.io.IOException;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.exception.DepartmentInsertException;
import edu.kh.dept.model.service.DepartmentService;
import edu.kh.dept.model.service.DepartmentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebServlet : 현재 클래스를 서블릿으로 등록 (서버 실행 시 객체 생성)
//--- +URL 매핑
@WebServlet("/department/insert")
public class InsertServlet extends HttpServlet{
	
	//a태그, JS(location.href), 주소창 직접 작성, <form method="GET">
	// --> get방식 요청
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//JSP로 요청 위임해서 부서 추가 화면 보여주기
		
		//**JSP 파일 경로 /(wepapp폴더)를 기준으로 작성
		String path="/WEB-INF/views/insert.jsp";
		
		// 요청 발송자(RequestDispatcher)를 이용해서
		// 요청 위임(forward) 하기
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	
	
	
	
	
	
	
	//<form method="POST"> ,ajax(비동기 통신 요청 POST 방식) => POST 방식 요청
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			
		try {
			
			//service 객체 생성
			DepartmentService service = new DepartmentServiceImpl();
		
			// 요청 시 전달받은 데이터(==Parameter) 얻어오기
			String deptId=req.getParameter("deptId"); //("name 속성값")
			String deptTitle=req.getParameter("deptTitle"); 
			String locationId=req.getParameter("locationId");
			
			//Dynamic Web Project 기본 요청/응답 흐름
			
			/*클라언트느 요청 
			 * -> Controller (Servlet , 요청에 따른 응답 화면 제어)
			 * <-> Service(비즈니스 로직 처리)
			 * <-> DAO(DB 연결 객체)
			 * <-> DB
			 */ 
			
			
			//부서 추가 서비스 호출 후 결과 반환 받기
			Department dept = new Department(deptId, deptTitle, locationId);
			
			int result = service.insertDepartment(dept);
			
			String message = null; //응답 화면에서 alert()로 출력할 내용
			
			//Session : 브라우저 당 1
			// 클라이언트 접속 시 1개 생성
			// 브라우저를 닫거나 만료되었을 때 소멸
			HttpSession session = req.getSession();
			
			if(result>0) message = "부서 추가 성공!!" ;
			else 		 message = "부서 추가 실패!!" ;
			
			//추가 동작 후 모든 부서 조회하는 Servlet을 재요청 (redirect)
			// -> redirect 는 재요청 == request 객테가 다시 만들어진다.
			//							==> 기존 request 객체가 사라진다. 
			// -> 그래서 redirect 시 데이터를 전달하고 싶으면
			//     request가 아니라 session에 담아서 전달해야 한다. 
			
			session.setAttribute("message", message);
			
			
			//모든 부서 조회 페이지 재요청하기
			resp.sendRedirect("/department/selectAll");
			
		}catch (DepartmentInsertException e) {
		 //제약 조건 위배로 삽입 실패 예외가 발생한 경우
		//에러 페이지로 forward

			//request scope 객체에 에러 메세지 세팅
			req.setAttribute("errorMessage", e.getMessage());
			
			
			//에러 메세지 포워드
			String path = "/WEB-INF/views/error.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		}
		
		
		catch (Exception e) {
			e.printStackTrace();
			
			
		}
		
		
	}

}

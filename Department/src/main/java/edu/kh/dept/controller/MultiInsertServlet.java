package edu.kh.dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/department/multiInsert")
public class MultiInsertServlet extends HttpServlet{
	

	@Override
	protected void doPost(HttpServletRequest  req, HttpServletResponse  resp) throws ServletException, IOException {

		try {
			
			// 서비스 객체 생성
			DepartmentService service = new DepartmentServiceImpl();
			
			// 전달 받은 파라미터 얻어오기
			// -> name 속성 값이 같은 파라미터가 여러 개 전달된 경우
			// String[] req.getParameterValues("name속성값") 이용
			
			
			String[] deptIdArr=req.getParameterValues("deptId");
			String[] deptTitleArr=req.getParameterValues("deptTitle");
			String[] locationIdArr=req.getParameterValues("locationId");
			
			
			//같은 인덱스 데이터를 Department 객체로 만들어 List형태로 저장
			
			List<Department> deptList = new ArrayList<Department>();
			
			for(int i=0; i<deptIdArr.length ; i++) {
				deptList.add(new Department(deptIdArr[i], deptTitleArr[i], locationIdArr[i]));
			}
			
			
			
			// 서비스 메서드 호출 후 결과(삽입된 행의 개수) 반환 받기
			int result = service.multiInsert(deptList); //성공, 실패, 예외
			
			HttpSession session=req.getSession();
			
			String message = null;
			if(result == deptList.size()) {///실패
				message = result+ "개의 부서가 추가 되었습니다." ;
			}else {//실패
				message = "부서 추가 실패";
			}
			
			session.setAttribute("message", message);
			
			//redirect : 재요청
//			resp.sendRedirect("/department/selectAll");   //절대 경로
			resp.sendRedirect("selectAll");  //상대 경로
			
		 	
		}catch(DepartmentInsertException e) { 
			//PK 제약 조건 위배시
			req.setAttribute("errorMessage", e.getMessage());
			
			String path="/WEB-INF/views/error.jsp";
			
			// forward : 요청 위임 (대신 응답 화면 만드어줘)
			req.getRequestDispatcher(path).forward(req, resp);
			
			
			
		}
		
		
		catch (Exception e) {
			e.printStackTrace();
			
			//예외가 발생이 되었음을 Controller(Servlet)에 전달하기
			// -> 사용자 정의 예외 강제 발생
			
		}
		
		
	}

	
}

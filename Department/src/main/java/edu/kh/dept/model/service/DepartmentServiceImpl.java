package edu.kh.dept.model.service;

import static edu.kh.dept.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dao.DepartmentDAO;
import edu.kh.dept.model.dao.DepartmentDAOImpl;
import edu.kh.dept.model.dto.Department;

//Service
//- 비즈니스 로직 처리
//- (데이터 가공, 트랜젝션 제어 처리)

//-트랜젝션 제어 처리
//-> 하나의 Service 메서드가 여러 DAO 메서드를 호출할 수 있다!
//  -호출한 모든 DAO 메소드가 성공했을 때 Commmit
//  -하나라도 실패하면 Rollback 수행

public class DepartmentServiceImpl implements DepartmentService{
	
	//DAO 객체 생성
	private DepartmentDAO dao = new DepartmentDAOImpl();

	
	
	//모든 부서 조회
	@Override
	public List<Department> selectAll() throws SQLException {
		
		/* 1. 커넥션 얻어오기*/
		Connection conn = getConnection(); 
		
		/* 2. DAO 메서드 호출 (매개 변수로 Connection 전달) + 결과 반환*/
		List<Department> deptList = dao.selectAll(conn);
		
		/* 3. SELECT는 트렌젝션 필요 x */
		
		/* 4. 사용 완료된 Connection 닫기 */
		close(conn);
		
		/* 5. 결과반환 */
		return deptList;
		
	}
	
}

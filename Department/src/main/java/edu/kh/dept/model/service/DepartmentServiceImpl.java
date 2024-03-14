package edu.kh.dept.model.service;

import static edu.kh.dept.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dao.DepartmentDAO;
import edu.kh.dept.model.dao.DepartmentDAOImpl;
import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.exception.DepartmentInsertException;

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


	//부서 추가 서비스
	@Override
	public int insertDepartment(Department dept) throws DepartmentInsertException {
		
		int result=0;
		
		//1. 커넥션 얻어오기
		Connection conn = getConnection();
			
		try {
			
			//2. DAO 메서드 호출(매개 변수로 Connection 전달) + 결과 반환
			// (DAO 메서드 호출 시 커넥션이 필요하기 때문에 매개변수로 전달!)
			result = dao.insertDepartment(conn,dept);
			
			//3. DAO 수행 결과에 따라 트랜젝션 제어
			if(result >0 ) commit(conn);
			else 		   rollback(conn);
			
		} catch (SQLException e) {
			
			//PK 제약조건 위배 / NOT NULL 제약조건 위배
			
			e.printStackTrace();
			
			//예외 발생 시 무조건 rollback
			rollback(conn);
			
			
			/* 제약조건 위배로 인해서 정상 수행 되지 않음을 표현하기 위해 강제로 예외 발생!!
			 * => 사용자 정의 예외 이용 */
			throw new DepartmentInsertException();
		
		}finally {//예외 발생 여부 관계 없이 무조건 커넥션 close
			
			//4. 사용 완료된 Connection 닫기
			close(conn);
			
		}
		return result;
	}


	@Override
	public int multiInsert(List<Department> deptList) {
		
		//1.Connection 얻어오기
		Connection conn = getConnection();
		
		int result=0;
		String currentDeptId = null;
		
		
		try {
			
			// 2. 전달받은 deptList의 크기(길이)만큼 반복하며 
			// DB에 INSERT하는 DAO 메서드 호출
			
			for (Department dept : deptList) {
				currentDeptId = dept.getDeptId();
				
				result += dao.insertDepartment(conn, dept);
			}
			
			//3. 트랜젝션 제어 처리
			// result에 누적된 값 (== 삽입 성공한 행의 개수)과
			// deptList의 길이가 같은 경우 == 모두 삽입 성공  => commit
			
			// reslut != deptList의 길이 == 모두 삽임 실패  => rollback
			
			if(result == deptList.size()) commit(conn);
			else 						  rollback(conn);
			
			
			
		} catch (SQLException e) {
			
			// 4. SQL 수행 중 오류 발생 시 
			//(PK, NOT NULL  제약조건 위배, 데이터 크기 초과)
			e.printStackTrace();
			rollback(conn); //sql이 하나라도 실패하면 전체 rollback
			
			
			//PK 제약조건 위배만 생각하고 코드 작성
			throw new DepartmentInsertException(currentDeptId+"부서 코드가 이미 존재합니다. ");
		
		}
		
		finally {
			close(conn);
		}
		
	
		return result;
	}


	@Override
	public int deleteDepartment(String deptId) throws SQLException{
	
		Connection conn = getConnection();
		
		int result=0;
		
		try {
			
			result = dao.deleteDepartment(conn,deptId);
			
			if(result>0)	commit(conn);
			else			rollback(conn);
			
		}
		finally {
			close(conn);
		}
		return result;
	}


	@Override
	public Department selectOne(String deptId) throws SQLException {
		
		Connection conn = getConnection();
		Department dept = dao.selectOne(conn,deptId);
		
		close(conn);
		
		return dept;
	}


	@Override
	public int updateDepartment(Department dept) throws SQLException {
		
		int result=0;
		
		Connection conn = getConnection();
		result=dao.updateDepartment(conn,dept);
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		
		close(conn);
		
		return result;
	}



	
}

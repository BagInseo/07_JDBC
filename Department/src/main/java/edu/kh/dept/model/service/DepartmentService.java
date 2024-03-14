package edu.kh.dept.model.service;

import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.exception.DepartmentInsertException;

public interface DepartmentService {

	/** 모든 부서 조회하기
	 * @return deptList(부서 목록)
	 * @throws SQLException
	 */
	List<Department> selectAll() throws SQLException;

	/**부서 추가 서비스
	 * @param dept
	 * @return result(삽입된 행의 개수)
	 * @throws DepartmentInsertException
	 */
	int insertDepartment(Department dept) throws DepartmentInsertException;

	int multiInsert(List<Department> deptList);

	int deleteDepartment(String deptId) throws SQLException;

	/**부서 1행 조회
	 * @param deptId
	 * @return dept (부서 1행 데이터가 담긴 객체)
	 * @throws SQLException
	 */
	Department selectOne(String deptId) throws SQLException;

	
	
	
	/** 부서 수정
	 * @param dept
	 * @return
	 * @throws SQLException 
	 */
	int updateDepartment(Department dept) throws SQLException;





	

}

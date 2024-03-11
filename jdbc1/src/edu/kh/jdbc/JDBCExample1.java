package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {
	
	public static void main(String [] args) {
		
		/*
		 * JDBC(Java DataBase Connectivity)
		 * - 자바에서 DB에 접근할 수 있게 해주는
		 * 자바 제공 코드(class, interface)
		 * 
		 */
		
		//EMPLOYEE 테이블에서
		//사번, 이름, 부서코드, 직금코드, 급여 , 입사일 조회해서
		//이클립스 콘솔 출력
		
		
		/* 1. JDBC 객체 참조 변수 선언 */
		
		//Connection
		//- 특정 db와의 연결 정보를 저장한 객체
		//(==dbaever에서 db 연결을 위해 주소, 계정, 비밀번호)
		Connection conn = null;
		
		//Statement
		//-SQL을 String 형태로  DB에 전달하고
		// 결과를 받아오는 객체
		
		Statement stmt =null;
		
		
		//ResultSet
		//-SELECT의 결과를 저장하는 객체
		//  (조회 결과는 0행 이상)
		// (커서를 이용해서 1행씩만 접근 가능) 
		ResultSet rs =null;
		
		
		try {
			
			/* 2. DriverManage 객페를 이횰해서 Connection 샐설하기*/
			
			/* 2-1) Oracle JDBC Driver 객체를 메모리에 로드(적재)하기*/
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Class.forName("클래스명"): 해당클래스를 읽어 메모리에 적재
			
			
			/* 2-2) DB 연결을 이용해서 Connection 객체 생성*/
			String type ="jdbc:oracle:thin:@"; //드라이버 종류
			String host ="localhost"; //DB 서버 컴퓨터의 IP 주소
			String port =":1521"; // DB 서버 컴퓨터에 DB 프로그램 연결 번호
			String dbName = ":xe"; //DB 이름
			String userName="KH_PIS"; //사용자 계정
			String pw ="KH1234"; //계정 비밀번호
			
			//DriverManager : Connection을 만드는 객체
			conn = DriverManager.getConnection(type + host+ port + dbName, userName, pw);
			
			//System.out.println(conn);//중간 확인
			
			/* 3. sql 작성 */
			String sql=
					"SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SALARY, HIRE_DATE "
							+ "FROM EMPLOYEE";
			//***** JDBC에서 SQL 작성 시 ;을 작성하면 안된다.
			// -> "SQL  명령어가 올바르게 종료되지 않았습니다."\
			
			/*4. Statment 객체 생성*/
			stmt = conn.createStatement();
			//Connection에 연결된 DB로 SQL 전달하고 결과를 받아오는 
			//Statmetn  객체 생성
			
			
			/* 5. Statment 객체를 이용해서 SQL 수행 후 결과 반환 받기*/
			rs=stmt.executeQuery(sql);
			
			/* 6. 조회 결과가 담겨 있는 ResultSet을 커서를 이용해 일행실 접근해며
			 * 각 행의 컬럼 값 얻어오기 */
			
			while (rs.next()) {
				//rs.next() : 커서를 한 행씩 이동시켜 해당 행이 있었으며 t, 아니면 f
				
				//rs.get자료형 (컬럼명 | 순서)
				// -> 현재 접근한 행의 컬럼 값 얻어오기
				
				// [java]         [db]
				// String         CHAR, VARCHAR2
				// int, long      NUMBER (정수만 저장된 컬럼)
				// float, double  NUMBER (정수 + 실수)
				// java.sql.Date  DATE
				
				String empId=rs.getString("EMP_ID");
				String empName=rs.getString("EMP_NAME");
				String deptCode=rs.getString("DEPT_CODE");
				String jobCode=rs.getString("JOB_CODE");
				int salary=rs.getInt("SALARY");
				Date hireDate   = rs.getDate("HIRE_DATE");
				
				
				System.out.printf("사번 : %s / 이름 : %s / "
						+ "부서코드 : %s / 직급코드 : %s /  "
						+ "급여 : %d / 입사일 : %s \n",
					empId, empName, deptCode, jobCode, salary, hireDate.toString());
				
				
				
			}
			
			//OracleDriver : Java - Oracle 연결 시 필 요한 코드가 담긴 클래스
		
			
			
		} catch (SQLException e) {
			//SQLException : JDBC 관련 예외의 푀상위 부모
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// ClassNotFoundException : 클래스가 존재하지 않으면 발생하는 예외
			// -> 추가한 라이브러리가 없거나, 클래스 경로를 잘못 작성
			e.printStackTrace();
		} finally {
			/*7. 사용 완료된 JDBC 객체 자원 반환*/
			/*ㅁ만들어진 역순으로 반환하는 것을 권장*/
			try {
				if(rs   != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			}catch (SQLException e) {
				e.printStackTrace(); 
			}
			
			
		}
		
	}

}

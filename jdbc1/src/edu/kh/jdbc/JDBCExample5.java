package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample5 {
	public static void main(String[] args) {
		
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs =null;
		
		try {
			
			/* 2. DriverManager 객체를 이용해서 Connection 생성하기 */

			/* 2-1) Oracle JDBC Driver 객체를 메모리에 적재 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			/* 2-2) DB 연결 정보를 이용해서 Connection 생성*/
			String type     = "jdbc:oracle:thin:@"; // 드라이버 종류
			String host     = "localhost";  // DB 서버 컴퓨터의 IP 주소
			String port     = ":1521";  // DB 서버 컴퓨터에 DB 프로그램 연결 번호
			String dbName   = ":xe";    // DB 이름
			String userName = "KH_PIS"; // 사용자 계정
			String pw       = "KH1234"; // 계정 비밀번호
			
			conn = DriverManager.getConnection(type + host + port + dbName, userName, pw);
			
			/* 만들어진 커녁션으로 SQL 수행 시 자동 커밋 비활성화!! */
			conn.setAutoCommit(false);
			
			
			/* 3.sql작성 */
			Scanner sc = new Scanner(System.in);
			System.out.print("부서코드 입력 : ");
			String deptCode=sc.next();
			
			System.out.print("부서명 입력 : ");
			String deptTitle=sc.next();
			
			System.out.print("지역코드 입력 : ");
			String locationId=sc.next();
			
			
			String sql=String.format("INSERT INTO DEPARTMENT4 VALUES ('%s','%s','%s')",
											deptCode,deptTitle,locationId);
			/* 4. Statement 객체 생성 */
			stmt=conn.createStatement();
			
			/* 5. SQL 수행 후 결과 반환 받기 */
			/* DML 수행 결과는 결과 행의 수!!! (INT) */
			
			//INSERT, UPDATE, DELETE 묶어서 UPDATE로 취금
			int result=stmt.executeUpdate(sql);
			
			/* 6. 수행 결과에 따라 트랜잭셩 제어 처리 */
			if(result > 0) {
				System.err.println("삽입 성공");
				conn.commit();
			}else {
				System.out.println("삽입 실패");
				conn.rollback();
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}

package edu.kh.dept.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	/*
	 * Template : 주형, 양식, 본뜨기 위한 틀
	 * 
	 *  JDBCTemplate : JDBC 관련 작업을 위한 코드를 제공하는 클래스
	 *  
	 *  (JDBC 관련 공통 코드를 미리 작성해둘 예정)
	 *  -Connection 생성(+자동 커밋 false)
	 *  -commit / rollback 제어
	 *  -JDBC 객체 자원 반환 구문 close
	 */
	
	//필드
	private static Connection conn =null;
	//-> 필드는 왜 static?
	// static 메서드가 참조 가능한 필드는 static 밖에 없기 떄문에
	
	
	
	//메서드
	
	 /*호출 시 연결 정보가 담긴 새로운 Connetion 객체를 생성해서 반환
	  * @return conn
	  */
	public static Connection getConnection() {
		 
		try {
			//Connetion이 없거나 닫혀있는 상태의 경우 -> 새 Connetion생성
			if(conn==null || conn.isClosed()) {
				Properties prop = new Properties();
				//Properties 
				//- K:V 모두 String인 Map
				//- 파일 입출력하기 편리한 기능을 제공해줌
				
				
				
				//컴파일된 driver.xml 파일의 위치를 얻어오는 코드
				String path=JDBCTemplate.class.getResource("/edu/kh/dept/sql/driver.xml").getPath();
				
				prop.loadFromXML(new FileInputStream(path));
				//프로젝트 최상단 폴더에 존재하는
				//driver.xml 파일을 읽어와 Properties 객체에 저장(적재)
				
				String driver = prop.getProperty("driver");  // oracle.jdbc.driver.OracleDriver
				String url=prop.getProperty("url");   // jdbc:oracle:thin:@localhost:1521:xe
				String user=prop.getProperty("user");
				String pw=prop.getProperty("pw");
				
				//오라클 드라이버 클래스를 읽어와 메모리 적재
				Class.forName(driver);
				
				//커넥션 객체 생성
				conn=DriverManager.getConnection(url, user, pw);
				
				//자동 커밋 false설정
				conn.setAutoCommit(false);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return conn;
	}
	
		//-------------------------------------------------------------------------------
		/*트랜젝션 제어 코드*/
		public static void commit(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) conn.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		public static void rollback(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) conn.rollback();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		

		
		
		//-----------------------------------------------------------------
		/* JDBC 객체 자원 반환 (close) */
		public static void close(Connection conn) {
			try {
				if(conn !=null && !conn.isClosed())  conn.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
		public static void close(Statement stmt) {
			try {
				if(stmt !=null && !stmt.isClosed())  stmt.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
	

		public static void close(ResultSet rs) {
			try {
				if(rs !=null && !rs.isClosed())  rs.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
		}
				
	


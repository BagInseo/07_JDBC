package edu.kh.dept.model.exception;


//사용자 정의 예외 만드는 방법!
// -> 아무 Exception 클래스를 상속 받으면 된다. 
// + RuntimeException 또는 그 자식 예외를 상속 받으면
//   Unchecked Exception 으로 설정된다. 
public class DepartmentInsertException extends RuntimeException {
	
	
	//기본 생성자
	public DepartmentInsertException() {
		
		super("부서 추가(INSERT) 중 예외 발생 (제약 조건 위배)");
	}

	
	//매개변수 생성자
	public DepartmentInsertException(String message) {
		super(message);
	}
}

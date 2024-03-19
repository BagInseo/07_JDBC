package edu.kh.diary.model.dto;

public class Diary {
	
	private int diaryNo; //일기 번호
	private String diaryTitle; // 일기 제목
	private String diaryWeather; //날씨
	private String diaryFeel; //기분
	private String diaryContent; //작성한 일기
	private String regDate; // 작성 날짜
	
	public Diary() {} //기본 생성자

	public Diary(int diaryNo, String diaryTitle, String diaryWeather, String diaryFeel, String regDate) {
		super();
		this.diaryNo = diaryNo;
		this.diaryTitle = diaryTitle;
		this.diaryWeather = diaryWeather;
		this.diaryFeel = diaryFeel;
		this.regDate = regDate;
	} //매개변수 생성자

	public Diary( String diaryTitle, String diaryWeather, String diaryFeel, String regDate) {
		super();
		this.diaryTitle = diaryTitle;
		this.diaryWeather = diaryWeather;
		this.diaryFeel = diaryFeel;
		this.regDate = regDate;
	}
	public Diary( int diaryNo,String diaryTitle, String diaryWeather, String diaryFeel,String diaryContent, String regDate) {
		super();
		this.diaryNo=diaryNo;
		this.diaryTitle = diaryTitle;
		this.diaryWeather = diaryWeather;
		this.diaryFeel = diaryFeel;
		this.diaryContent =diaryContent;
		this.regDate = regDate;
	}
	
	
	//getter, setter
	public int getDiaryNo() {
		return diaryNo;
	}

	public void setDiaryNo(int diaryNo) {
		this.diaryNo = diaryNo;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	public String getDiaryWeather() {
		return diaryWeather;
	}

	public void setDiaryWeather(String diaryWeather) {
		this.diaryWeather = diaryWeather;
	}

	public String getDiaryFeel() {
		return diaryFeel;
	}

	public void setDiaryFeel(String diaryFeel) {
		this.diaryFeel = diaryFeel;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	
	
	public String getDiaryContent() {
		return diaryContent;
	}

	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}

	@Override
	public String toString() {
		return "Diary [diaryNo=" + diaryNo + ", diaryTitle=" + diaryTitle + ", diaryWeather=" + diaryWeather
				+ ", diaryFeel=" + diaryFeel + ", regDate=" + regDate + "]";
	}; //toString
	
	
	
	
	

}

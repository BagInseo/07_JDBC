package edu.kh.diary.model.service;

import java.sql.SQLException;
import java.util.List;

import edu.kh.diary.model.dto.Diary;

public interface DiaryService{

	/** 일기 추가
	 * @param diary
	 * @return
	 * @throws SQLException 
	 */

	int addDiary(String diaryTitle, String diaryWeather, String diaryFeel, String diaryContent)throws SQLException;

	/** 목록 조회
	 * @return
	 * @throws SQLException
	 */
	List<Diary> selectAll() throws SQLException;

	/** 일기 삭제
	 * @param diaryTitle
	 * @return
	 */
	int deleteDiary(int diaryNo) throws SQLException;

	/** 작성된 일기보기
	 * @param diaryNo
	 * @return
	 */
	Diary detailDiary(int diaryNo)throws SQLException;

	/** 일기 제목 검색
	 * @param keyword
	 * @return
	 */
	List<Diary> searchDiary(String keyword)throws SQLException;


}

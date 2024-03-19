package edu.kh.diary.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.diary.model.dto.Diary;

public interface DiaryDAO {



	/** 일기추가
	 * @param conn
	 * @param diaryTitle
	 * @param diaryWeather
	 * @param diaryFeel
	 * @param diaryContent
	 * @return
	 * @throws SQLException
	 */
	int addDiary(Connection conn, String diaryTitle, String diaryWeather, String diaryFeel, String diaryContent)throws SQLException;

	List<Diary> selectAll(Connection conn) throws SQLException;

	int deleteDiary(Connection conn, int diaryNo) throws SQLException;

	Diary detailDiary(Connection conn, int diaryNo)throws SQLException;

	List<Diary> searchDiary(Connection conn, String keyword)throws SQLException;

}

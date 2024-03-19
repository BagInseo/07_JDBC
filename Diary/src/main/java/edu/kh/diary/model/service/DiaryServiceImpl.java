package edu.kh.diary.model.service;

import static edu.kh.diary.common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.diary.model.dao.DiaryDAO;
import edu.kh.diary.model.dao.DiaryDAOImple;
import edu.kh.diary.model.dto.Diary;

public class DiaryServiceImpl implements DiaryService{

	private DiaryDAO dao =null;
	
	public DiaryServiceImpl() {
		dao = new DiaryDAOImple();
	}
	
	
@Override
	public int addDiary(String diaryTitle, String diaryWeather, String diaryFeel, String diaryContent)
			throws SQLException {
	
	Connection conn = getConnection();
	int result = dao.addDiary(conn, diaryTitle,diaryWeather,diaryFeel,diaryContent);
	
	if(result > 0 )  commit(conn);
	else 			 rollback(conn);
	
	close(conn);
	
	return result;
	}


@Override
public List<Diary> selectAll() throws SQLException {
	Connection conn = getConnection();
	
	List<Diary> diaryList = dao.selectAll(conn);
	
	close(conn);
	
	return diaryList;
}


@Override
public int deleteDiary(int diaryNo) throws SQLException {
	
	Connection conn = getConnection();
	int result =0;
	
	try {
		
		result= dao.deleteDiary(conn,diaryNo);
		
		if(result>0) commit(conn);
		else 		 rollback(conn);
		
	} finally {
		close(conn);
	}
	
	return result;
}


@Override
public Diary detailDiary(int diaryNo) throws SQLException{
	
	Connection conn = getConnection();
	Diary diary =dao.detailDiary(conn,diaryNo);
	close(conn);
	
	return diary;
}

@Override
public List<Diary> searchDiary(String keyword) throws SQLException {

	Connection conn = getConnection();
	List<Diary> diaryList = dao.searchDiary(conn,keyword);
	
	close(conn);
	
	
	return diaryList;
}

}

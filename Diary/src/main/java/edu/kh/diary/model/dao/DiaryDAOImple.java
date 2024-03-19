package edu.kh.diary.model.dao;


import static edu.kh.diary.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.diary.model.dto.Diary;

public class DiaryDAOImple implements DiaryDAO{

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public DiaryDAOImple() {
		try {
			
			prop=new Properties();
			String path = DiaryDAOImple.class.getResource("/edu/kh/diary/sql/sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(path));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	
	@Override
	public int addDiary(Connection conn, String diaryTitle, 
			String diaryWeather, String diaryFeel, String diaryContent)
					throws SQLException {
		int result =0; 
		
		try {
			
			
			String sql= prop.getProperty("addDiary");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,diaryTitle);
			pstmt.setString(2,diaryWeather);
			pstmt.setString(3,diaryFeel);
			pstmt.setString(4,diaryContent);
			
			result=pstmt.executeUpdate();		
			

			
		} finally {
			close(pstmt);
			
		}
		return result;
	}
	
	
@Override
	public List<Diary> selectAll(Connection conn) throws SQLException {
	
		List<Diary> diaryList = new ArrayList<Diary>();
	
		try {
			
			String sql = prop.getProperty("selectAll");
			
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int diaryNo = rs.getInt("DIARY_NO");
				String diaryTitle = rs.getString("DIARY_TITLE");
				String diaryWeather = rs.getString("DIARY_WEATHER");
				String diaryFeel = rs.getString("DIARY_FEEL");
				String regDate = rs.getString("REG_DATE");
				
				Diary diary = new Diary(diaryTitle, diaryWeather, diaryFeel, regDate);
				diary.setDiaryNo(diaryNo);
				diaryList.add(diary);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
	
		return diaryList;
	}



	@Override
	public int deleteDiary(Connection conn ,int diaryNo) throws SQLException {

		int result =0;
		
		try {
			String sql = prop.getProperty("deleteDiary");
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, diaryNo);
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
	
	@Override
	public Diary detailDiary(Connection conn, int diaryNo) throws SQLException {
		
		
		Diary diary = null;
		
		try {
			
			String sql = prop.getProperty("detailDiary");
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,diaryNo);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				diary= new Diary();
				
				diary.setDiaryNo(rs.getInt("DIARY_NO"));
				diary.setDiaryTitle(rs.getString("DIARY_TITLE"));
				diary.setDiaryWeather(rs.getString("DIARY_WEATHER"));
				diary.setDiaryFeel(rs.getString("DIARY_FEEL"));
				diary.setDiaryContent(rs.getString("DIARY_CONTENT"));
				diary.setRegDate(rs.getString("REG_DATE"));
			}
		} finally {
			
			close(rs);
			close(pstmt);

			
		}

		return diary;
	}
	
@Override
public List<Diary> searchDiary(Connection conn, String keyword) throws SQLException {

	List<Diary> diaryList = new ArrayList<Diary>();
	
	try {
		
	 
		
		String sql = prop.getProperty("searchDiary");
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, keyword);
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			int diaryNo = rs.getInt("DIARY_NO");
			String diaryTitle=rs.getString("DIARY_TITLE");
			String diaryWeather=rs.getString("DIARY_WEATHER");
			String diaryFeel=rs.getString("DIARY_FEEL");
			String regDate=rs.getString("REG_DATE");
			
			Diary diary = new Diary(diaryNo, diaryTitle, diaryWeather, diaryFeel, regDate);
			
			diaryList.add(diary);
		}
	}
		finally {
			close(rs);
			close(pstmt);
		}
	
	return diaryList;

}

}

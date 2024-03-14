package edu.kh.dept.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/department/search")
public class SearchServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String keyword= req.getParameter("keyword");
			
			
			
			
			//forward할 js경로
			String path = "/WEB-INF/views/search.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}

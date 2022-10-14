package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.model.vo.Person;

@WebServlet("/forEach")
public class ForEachServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Person> pList = new ArrayList<>();
		
		pList.add(new Person("백승훈", 31, "의정부시"));
		pList.add(new Person("노성찬", 29, "의정부시"));
		pList.add(new Person("김정현", 28, "구리시"));
		pList.add(new Person("김혜진", 26, "구리시"));
		pList.add(new Person("김연수", 25, "파주시"));
		
		req.setAttribute("pList", pList); // request에 값 세팅
		
		// 요청 위임
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/forEach.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	
}

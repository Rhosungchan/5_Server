package edu.kh.jsp.student.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.student.model.service.StudentService;
import edu.kh.jsp.student.model.vo.Student;

@WebServlet("/student/SelectDepartment")
public class SelectDepartmentServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		StudentService service = new StudentService();
		
		
		try {
			String dpt = req.getParameter("inputDept");
			
			List<Student> dptList = service.selectDpt(dpt);
			
			req.setAttribute("dptList", dptList);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/student/selectDpt.jsp");
			
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
		
	
	
}

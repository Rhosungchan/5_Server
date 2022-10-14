package edu.kh.jsp.student.model.dao;

//JDBCTemplate의 static 필드/메서드 호출 시 클래스명 생략
import static edu.kh.jsp.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jsp.student.model.vo.Student;

public class StudentDAO {

	private Statement stmt; 
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	// 기본 생성자
	public StudentDAO() {
		try {
			
			String filePath = StudentDAO.class.getResource("/edu/kh/jsp/sql/student-sql.xml").getPath();
			
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}

	/** 학생 전체 조회 DAO
	 * @param conn
	 * @return stdList
	 * @throws Exception
	 */
	public List<Student> selectAll(Connection conn) throws Exception{
		
		// 결과 저장용 변수 선언
		List<Student> stdList = new ArrayList<>();
		
		try {
			// SQL 작성하기
			String sql = prop.getProperty("selectAll");
			
			// Statement 객체 생성
			stmt = conn.createStatement();
			
			// SQL 수행 후 결과(ResultSet) 반환 받기
			rs = stmt.executeQuery(sql);
			
			
			// 조회 결과를 1행씩 조회해서 결과에 옮겨담기
			while(rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				
				stdList.add((new Student(studentNo, studentName, studentAddress, departmentName)));
			}
				
		} finally {
			// 사용한 JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		
		// 결과 반환 
		return stdList;
	}

	/** 학과명 검색 DAO
	 * @param conn
	 * @param dpt 
	 * @return
	 * @throws Exception
	 */
	public List<Student> selectDpt(Connection conn, String dpt) throws Exception{
		
		List<Student> dptList = new ArrayList<>();
		
		try {
			
			String sql = prop.getProperty("selectDpt");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dpt);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				
				dptList.add((new Student(studentNo, studentName, studentAddress, departmentName)));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return dptList;
	}
	
	
	
}

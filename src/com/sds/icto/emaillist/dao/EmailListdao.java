package com.sds.icto.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.sds.icto.emaillist.*;

import com.sds.icto.emaillist.vo.EmailListvo;

@Repository
public class EmailListdao {
	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection conn = null;

		// 1 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2 connection 생성
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dbURL, "webdb", "webdb");

		return conn;
	}

	public void insert(EmailListvo vo) {
		try{
		// 1 connection 생성
		Connection conn = getConnection();

		// 2 Statement 준비
		String sql = "insert into email_list values(email_list_no_seq.nextval, ?, ?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		// 3 바인딩
		pstmt.setString(1, vo.getFirstName());
		pstmt.setString(2, vo.getLastName());
		pstmt.setString(3, vo.getEmail());

		// 4 쿼리실행
		pstmt.executeUpdate();
	
		}catch (ClassNotFoundException ex){
			ex.printStackTrace();
		}catch (SQLException ex){
			ex.printStackTrace();
		}
		
	}

	
	public void delete() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();

		// statement 생성
		Statement stmt = conn.createStatement();
		
		// query 실행
		String sql = "delete from Email_List";
		
		// 자원정리
		
		try {
			if (stmt != null) {
				stmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
		}
		
	}

	public List<EmailListvo> fetchList() {
		List<EmailListvo> list = new ArrayList<EmailListvo>();
		try {
		

		Connection conn = getConnection();

		// 3 statement 생성
		Statement stmt = conn.createStatement();

		// 4 SQL문 실행
		String sql = "select * from Email_List";

		ResultSet rs = stmt.executeQuery(sql);

		// 5 결과 처리
		while (rs.next()) {
			Long no = rs.getLong(1);
			String firstName = rs.getString(2);
			String lastName = rs.getString(3);
			String email = rs.getString(4);

			EmailListvo vo = new EmailListvo();
			vo.setNo(no);
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);
			list.add(vo);
		}
		}catch (ClassNotFoundException ex){
			ex.printStackTrace();
		}catch (SQLException ex){
			ex.printStackTrace();
		}
		
		return list;
	}
}

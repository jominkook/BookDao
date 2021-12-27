package com.kosta.jdbcdao.jominkook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AuthorDaoImpl implements AuthorDao {
	
	private Connection getConnection() throws SQLException {
		   Connection conn = null;
		   try {
		     Class.forName("oracle.jdbc.driver.OracleDriver");
		     String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
		     conn = DriverManager.getConnection(dburl, "C##webdb", "1234");
		   } catch (ClassNotFoundException e) {
		     System.err.println("JDBC 드라이버 로드 실패!");
		   }
		 return conn;
	}
		  
	

	@Override
	public boolean insert(AuthorVo avo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try { 
			conn = getConnection();
			String sql = "INSERT INTO author " +
			          "VALUES(SEQ_AUTHOR_ID.NEXTVAL, " +
			          "?, ?)";
			pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, avo.getAuthor_name());
		    pstmt.setString(2, avo.getAuthor_desc());
		    cnt = pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
		}
		return cnt == 1;
	}


	@Override
	public boolean update(AuthorVo avo) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try { 		
			 conn = getConnection();
			 String sql =  "UPDATE author SET " +
			          "author_name=?, author_desc=? " +
			          "WHERE author_id=?";
			 pstmt = conn.prepareStatement(sql);			 		 
			 pstmt.setString(1, avo.getAuthor_name());
			 pstmt.setString(2, avo.getAuthor_desc());
			 pstmt.setInt(3, avo.getAuthor_id());
			 cnt = pstmt.executeUpdate();		 
					     
		     
		}catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
		}
		return 1 == cnt;
		
	}


	@Override
	public List<AuthorVo> select() {
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rs = null;
		List<AuthorVo> list = new ArrayList<>();
		try { 
		
			 conn = getConnection();
			 pstmt = conn.createStatement();
			 String sql = " SELECT author_id, author_name, author_desc " 
			                 + " FROM author "
			                 + " order by author_id ";
			      
			 rs = pstmt.executeQuery(sql);
		     
		      while(rs.next()) {
			        AuthorVo avo = new AuthorVo();
			        avo.setAuthor_id(rs.getInt("author_id"));
					avo.setAuthor_desc(rs.getString("author_desc"));
					avo.setAuthor_name(rs.getString("author_name"));
					list.add(avo);
					
			        
			}
		}catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
		}
		return list;
		
	}



	@Override
	public List<AuthorVo> AgetList() {
		// TODO Auto-generated method stub
		return null;
	}



	
}




package com.kosta.jdbcdao.jominkook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class BookDaoImpl implements BookDao {

	
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
	public List<BookVo> select()  {
		 Connection conn = null;
		 Statement pstmt = null;
		 ResultSet rs = null;
		 
		 List<BookVo> list = new ArrayList<BookVo>();
		 try {
			 conn = getConnection();
			 pstmt =conn.createStatement();
			 String sql = "SELECT book_id, title , pubs , to_char(pub_date,'yyyymmdd') pub_date , b.author_id ,a.author_name \r\n" + 
						"FROM book b ,author a  \r\n"+ 
						"WHERE b.author_id = a.author_id \r\n";
						
						
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery(sql);
			 while(rs.next()) {
				BookVo bvo = new BookVo();
				
				bvo.setAuthor_name(rs.getString("author_name"));
				bvo.setBook_pubs(rs.getString("pubs"));
				bvo.setBook_pub(rs.getString("pub_date"));
				list.add(bvo);
					
		       
		      }
			 
		 }catch (SQLException e) {
		  System.err.println("ERROR:" + e.getMessage());
		 }
		 return list;
	}
	
	@Override
	public List<BookVo> select(int author_id) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;	 
		 List<BookVo> list = new ArrayList<>();
		 try {
			 conn = getConnection();
			
			 String sql = "SELECT book_id, title , pubs , pub_date , b.author_id ,a.author_name \r\n" + 
						"FROM book b ,author a  \r\n"+ 
						"WHERE b.author_id = a.author_id "+
						"AND a.author_id = ?";
			pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, author_id);
		    rs = pstmt.executeQuery();
		    while(rs.next()) {
		    	
		    	BookVo bvo = new BookVo();
		    	bvo.setBook_id(rs.getInt("book_id"));
		    	bvo.setBook_title(rs.getString("title"));
		    	bvo.setBook_pubs(rs.getString("pubs"));
		    	bvo.setBook_pub(rs.getString("pub_date"));	    	
		    	list.add(bvo);
		    
		    }
		 }catch (SQLException e) {
			  System.err.println("ERROR:" + e.getMessage());
		 }
		 return list;
	}

	@Override
	public boolean insert(BookVo bvo) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 int cnt = 0;
		 
		 try {
			 conn = getConnection();
		     String sql = "INSERT INTO book(book_id, title, pubs, pub_date,author_id) " +
		          "VALUES(SEQ_BOOK_ID.NEXTVAL, " +
		          "?, ?, to_date(?,'yyyymmdd'),?)";
		     pstmt = conn.prepareStatement(sql);
		     pstmt.setString(1, bvo.getBook_title());
		     pstmt.setString(2, bvo.getBook_pubs());
		     pstmt.setString(3, bvo.getBook_pub());
		     pstmt.setInt(4, bvo.getAuthor_id());
		     cnt = pstmt.executeUpdate();
			 
		 } catch (Exception e) {
		      System.err.println("ERROR:" + e.getMessage());
		 }
		return cnt == 1;
	}

	@Override
	public boolean update(BookVo bvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	    int cnt = 0;
		
	    try {
	    	conn = getConnection();
	 	    String sql = "UPDATE BOOK SET " +
	 	    		"title=?, pubs=?, pub_date = to_date(?,'YYYYMMDD') " +
	 	    		"WHERE book_id=?";
	 	    pstmt = conn.prepareStatement(sql);
	 	    pstmt.setString(1, bvo.getBook_title());
	 	    pstmt.setString(2, bvo.getBook_pubs());
	 	    pstmt.setString(3, bvo.getBook_pub());
	 	    pstmt.setInt(4, bvo.getBook_id());
	 	    cnt = pstmt.executeUpdate();
		
	    }catch (Exception e) {
	    	System.err.println("ERROR:" + e.getMessage());
	    }
	    return 1 == cnt;
	}
	

}

		








package kr.ac.sungkyul.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.bookmall.vo.BookVo;

public class BookDao {
	
	public void updateStatus( Long no, Integer status ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.연결 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "skudb", "skudb");
			
			//3. statement 준비
			String sql =
            "update book set status = 1  where no = ?"; 
			pstmt = conn.prepareStatement(sql);
			
		
			//4. 바인딩
			pstmt.setLong(1, no);
			
			//5. SQL 실행
			pstmt.executeUpdate();

			
			String sql2 =
					"select title from book where no=?"; 
			pstmt = conn.prepareStatement(sql2);
			
			
			//4. 바인딩
			pstmt.setLong(1, no);
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			rs.next();

			String title = rs.getString(1);
			System.out.println(title+"(이)가 대여되었습니다.");
			
			
			
			
		}catch( ClassNotFoundException e ){
			System.out.println( "드라이버를 찾을 수 없습니다. : " + e);
		}catch( SQLException e ){
			System.out.println( "SQL 에러 : " + e);
		}finally{
			try{
			if( pstmt != null ){
				pstmt.close();
			}
			if( conn != null ){
				conn.close();
			}
			}catch( SQLException e ){
				System.out.println( "SQL conn 에러 : " + e);
			}
		}
		 //대여중 정보
	}
	
	
	
	
//	public int updateStatus( Long no, Integer status ){
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int count = 0;
//		
//		try{
//			//1. 드라이버 로딩
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			//2.연결 얻어오기
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(url, "skudb", "skudb");
//			
//			//3. statement 준비
//			String sql =
//            "update book set status = 1  where no = ?"; 
//			pstmt = conn.prepareStatement(sql);
//			
//		
//			//4. 바인딩
//			pstmt.setLong(1, no);
//			
//			//5. SQL 실행
//			pstmt.executeUpdate();
//			
//			
//			
//			
//			
//		}catch( ClassNotFoundException e ){
//			System.out.println( "드라이버를 찾을 수 없습니다. : " + e);
//		}catch( SQLException e ){
//			System.out.println( "SQL 에러 : " + e);
//		}finally{
//			try{
//			if( pstmt != null ){
//				pstmt.close();
//			}
//			if( conn != null ){
//				conn.close();
//			}
//			}catch( SQLException e ){
//				System.out.println( "SQL conn 에러 : " + e);
//			}
//		}
//		return 0; //대여중 정보
//	}
	
	
	//update() 
	public int update( BookVo vo ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try{
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";  
			conn = DriverManager.getConnection(url, "skudb", "skudb");
			
			
			//3.SQL준비
			String sql = 
				"update book set title=?, rate=?, author_no=? where no=?";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getRate());
			pstmt.setLong(3, vo.getAuthorNo());
			pstmt.setLong(4, vo.getNo());
			
			//5. SQL 실행
			count = pstmt.executeUpdate();
			
		}catch( ClassNotFoundException e ){
			System.out.println( "드라이버를 찾을 수 없습니다. : " + e);
		}catch( SQLException e ){
			System.out.println( "SQL 에러 : " + e);
		}finally{
			try{
			if( pstmt != null ){
				pstmt.close();
			}
			if( conn != null ){
				conn.close();
			}
			}catch( SQLException e ){
				System.out.println( "SQL conn 에러 : " + e);
			}
		}
		return count;
	
	}
	
	
	
	//delete() 전체 삭제
	public int delete(){
		Connection conn = null;
		Statement stmt = null;
		int count = 0;
		
		try{
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"skudb","skudb");
			
			//3. SQL 준비
			String sql = "delete from book";
				
			//5. SQL 실행
			stmt = conn.createStatement();
			count  = stmt.executeUpdate(sql); //sql문이 jdbc에 들어있기 때문에 sql을 안 넣어 줘도 됩니다. 
			
			
		}catch( ClassNotFoundException e ){
			System.out.println( "드라이버를 찾을 수 없습니다. : " + e);
		}catch( SQLException e ){
			System.out.println( "SQL 에러 : " + e);
		}finally{
			try{
			if( stmt != null ){
				stmt.close();
			}
			if( conn != null ){
				conn.close();
			}
			}catch( SQLException e ){
				System.out.println( "SQL conn 에러 : " + e);
			}
		}
		return count;
	}
	
	
	
	//delete()
	public int delete( long no ){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try{
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"skudb","skudb");
			
			//3. SQL 준비
			String sql = "delete from book where no=?";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩
			pstmt.setLong(1, no);
			
			//5. SQL 실행
			count  = pstmt.executeUpdate(); //sql문이 jdbc에 들어있기 때문에 sql을 안 넣어 줘도 됩니다. 
			
			
		}catch( ClassNotFoundException e ){
			System.out.println( "드라이버를 찾을 수 없습니다. : " + e);
		}catch( SQLException e ){
			System.out.println( "SQL 에러 : " + e);
		}finally{
			try{
			if( pstmt != null ){
				pstmt.close();
			}
			if( conn != null ){
				conn.close();
			}
			}catch( SQLException e ){
				System.out.println( "SQL conn 에러 : " + e);
			}
		}
		return count;
	}
	
	
	//insert()
	public int insert( BookVo vo ){
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
		//1.드라이버 로딩
		Class.forName( "oracle.jdbc.driver.OracleDriver"  );
		
		//2.연결얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url,"skudb","skudb");
		
		//3.statment 준비
		String sql = 
				"insert into book values(seq_book.nextval, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		
		//4.바인딩
		pstmt.setString( 1, vo.getTitle() );
		pstmt.setInt( 2, vo.getRate());
		pstmt.setLong(3, vo.getStatus());
		pstmt.setLong( 4, vo.getAuthorNo());
		
		//5.query 실행
		count = pstmt.executeUpdate();
		
		}catch(ClassNotFoundException e) {
			System.out.println( "드라이버 로딩 실패 :" + e  );
		} catch (SQLException e ){
			System.out.println( "error:" + e );
		} finally {
			try {
				//6. 자원정리
				if( pstmt != null ) {
					pstmt.close();
				}
				
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException e ) {
				System.out.println( "error:" + e );
			}
		}
		
		return count;
	}
	
	
	
	//getList()
	public List<BookVo> getList(){
		List<BookVo> list = new ArrayList<BookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
	
			//1. 드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
	
			//2. 연결얻어고기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "skudb", "skudb");
	
			stmt = conn.createStatement();
	
			//4. sql문 실행
			String sql = 
					"select b.no,"
					+ "b.title,"
					+ "b.rate,"
					+ "b.status,"
					+ "b.price,"
					+ "a.name, "
					+ "c.name"
					+ " from book b,author a, category c"
					+ " where a.no = b.author_no"
					+ " and c.no = b.category_no";
			
			rs = stmt.executeQuery(sql);
			
			//5. 결과처리
		while( rs.next() ){
			Long no = rs.getLong( 1 );
			String title = rs.getString( 2 );
			Integer rate = rs.getInt( 3 );
			Integer status = rs.getInt( 4 );
			Integer price = rs.getInt(5);
			String authorName = rs.getString(6);
			String categoryName = rs.getString(7);
			
			
			
			BookVo vo = new BookVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setRate(rate);
			vo.setStatus(status);
			vo.setPrice(price);
			vo.setAuthorName(authorName);
			vo.setCategoryName(categoryName);
			
			list.add(vo);
		}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println( "드라이버 로딩 실패:" + e);
		}catch (SQLException e ){
			System.out.println( "error:" + e );
			e.printStackTrace();
		} finally {
			try {
				//6. 자원정리
				if( rs != null ) {
					rs.close();
				}
				
				if( stmt != null ) {
					stmt.close();
				}
				
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException e ) {
				System.out.println( "error:" + e );
			}
		}
		return list;
	}
	
}

package kr.ac.sungkyul.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.bookmall.vo.AuthorVo;

public class AuthorDao {
	
	//update
	public int update( AuthorVo vo ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try{
		//1. 드라이버 로딩
				Class.forName( "oracle.jdbc.driver.OracleDriver" );
			
				//2.connection 얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";  
				conn = DriverManager.getConnection( url,"skudb","skudb"); //연결스트링을 써줘야 한다 : url
				
				//3. SQL 준비
				String sql = 
				"update author set name=?, description=? where no=?";
				pstmt = conn.prepareStatement(sql); //쿼리를 jdbc드라이버에 준비하라
				
				//4. 바인딩
				pstmt.setString( 1, vo.getName());  //이 자리 타입이 number다. --> long값이니까
				pstmt.setString(2, vo.getDescription());
				pstmt.setLong(3, vo.getNo());
				
				//5. SQL실행
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
	
	
	
	//delete 전체삭제
	public int delete( ){
		Connection conn = null;
		Statement stmt = null;
		int count = 0;
		
		try{
		//1. 드라이버 로딩
				Class.forName( "oracle.jdbc.driver.OracleDriver" );
			
				//2.connection 얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";  
				conn = DriverManager.getConnection( url,"skudb","skudb"); //연결스트링을 써줘야 한다 : url
				
				//3. SQL 준비
				String sql = "delete from author";
			
				//5. SQL실행
				stmt = conn.createStatement(); //쿼리를 jdbc드라이버에 준비하라
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
	
	//delete
	public int delete( long no ){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		
		try{
		//1. 드라이버 로딩
		Class.forName( "oracle.jdbc.driver.OracleDriver" );
	
		//2.connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";  
		conn = DriverManager.getConnection( url,"skudb","skudb"); //연결스트링을 써줘야 한다 : url
		
		//3. SQL 준비
		String sql = "delete from author where no = ?";
		pstmt = conn.prepareStatement(sql); //쿼리를 jdbc드라이버에 준비하라
		
		//4. 바인딩
		pstmt.setLong( 1, no);  //이 자리 타입이 number다. --> long값이니까
		
		//5. SQL실행
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
	
	
	
	
	//insert
	public int insert( AuthorVo vo ) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//1. 드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			
			//2. 연결 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "skudb", "skudb");
			
			//3. statement 준비
			String sql =
            "insert into author values(seq_author.nextval, ?, ?)"; //'?' 이렇게 안하고 그냥 ? 이유는,여기서는 값이 바인딩 된다. 
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩
			pstmt.setString( 1, vo.getName() );
			pstmt.setString( 2, vo.getDescription() );
			
			//5. query 실행
			count = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
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
	
	public List<AuthorVo> getList() {
		List<AuthorVo> list = new ArrayList<AuthorVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1. 드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			
			//2. 연결 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "skudb", "skudb");
			
			//3. statement 생성
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql =
				"select no," +
			    "       name," +
				"       description" +
				"  from author";
			rs = stmt.executeQuery(sql);
			
			//5. 결과 처리
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String name = rs.getString( 2 );
				String description = rs.getString( 3 );

				AuthorVo vo = new AuthorVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setDescription(description);
				
				list.add( vo );
			}
		} catch (ClassNotFoundException e) {
			System.out.println( "드라이버 로딩 실패 :" + e  );
		} catch (SQLException e ){
			System.out.println( "error:" + e );
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
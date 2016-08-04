package kr.ac.sungkyul.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.bookmall.vo.CategoryVo;

public class CategoryDao {

	//getList()
	public List<CategoryVo> getList(){
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 연결 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"skudb","skudb");
			
			//3. statement생성
			stmt = conn.createStatement();
			
			//4. Sql문 실행
			String sql = "select no,name from category";
			rs = stmt.executeQuery(sql);
			
			//5. 결과 처리
			while( rs.next() ){
				Long no = rs.getLong( 1 );
				String name = rs.getString( 2 );
				
				CategoryVo vo = new CategoryVo();
				vo.setNo(no);
				vo.setName(name);
				
				list.add(vo);
			}
			
		}catch (ClassNotFoundException e) {
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
	
	
	
	//insert
	public int insert( CategoryVo vo ){
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
				"insert into category values(seq_category.nextval, ?)";
		pstmt = conn.prepareStatement(sql);
		
		//4.바인딩
		pstmt.setString( 1, vo.getName() );
		
		
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
	
	//update
	public int update( CategoryVo vo ){
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"skudb","skudb");
			
			String sql = 
					"update category"
					+ " set name = ?"
					+ " where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setLong(2, vo.getNo());
						
			count = pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e ){
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
}

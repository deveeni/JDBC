package kr.ac.sungkyul.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.bookmall.vo.CartVo;

public class CartDao {

	// getList
	public List<CartVo> getList(int num) {
		List<CartVo> list = new ArrayList<CartVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.연결 얻기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "skudb", "skudb");

		
			// 3. sql문
			String sql = "select m.name,b.title,c.quantity "
					+ " from book b,cart c,member m"
					+ " where m.no = ?"
					+ " and c.book_no = b.no";
			

			// 4. statement준비
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);

			//5. 쿼리 실행
			rs = pstmt.executeQuery();
			
			//6. 결과처리
			while (rs.next()) {
				String memberName = rs.getString(1);
				String bookTitle = rs.getString(2);
				Integer quantity = rs.getInt(3);

				CartVo vo = new CartVo();
				vo.setMemberName(memberName);
				vo.setBookTitle(bookTitle);
				vo.setQuantity(quantity);

				list.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 :" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원정리
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return list;
	}

	
	// getList
	public List<CartVo> getList() {
		List<CartVo> list = new ArrayList<CartVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.연결 얻기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "skudb", "skudb");

			// 3. statement 생성
			stmt = conn.createStatement();

			// 4. sql문 실행
			String sql = "select m.name,b.title,c.quantity "
					+ " from book b,cart c,member m"
					+ " where c.book_no = b.no"
					+ " and c.member_no = m.no";
			rs = stmt.executeQuery(sql);

			// 5. 결과처리
			while (rs.next()) {
				String memberName = rs.getString(1);
				String bookTitle = rs.getString(2);
				Integer quantity = rs.getInt(3);

				CartVo vo = new CartVo();
				vo.setMemberName(memberName);
				vo.setBookTitle(bookTitle);
				vo.setQuantity(quantity);

				list.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 :" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원정리
				if (rs != null) {
					rs.close();
				}

				if (stmt != null) {
					stmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return list;
	}

	// insert
	public int insert( CartVo vo ){
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			//1.드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver"  );

			//2.url, conn연결얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"skudb","skudb");
			
			//3. pstmt,sql 준비
			String sql = 
					"insert into Cart values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. pstmt 바인딩
			pstmt.setLong(1, vo.getBookNo());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setInt(3, vo.getQuantity());
			
			//5. query 실행
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


}

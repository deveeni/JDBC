package kr.ac.sungkyul.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.bookmall.vo.OrderBooksVo;
import kr.ac.sungkyul.bookmall.vo.OrdersVo;

public class OrderBooksDao {

	
	//getList
	public List<OrderBooksVo> getList(){
		
		List<OrderBooksVo> list = new ArrayList<OrderBooksVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
		
			// 1. 드라이버 로딩
						Class.forName("oracle.jdbc.driver.OracleDriver");

						// 2. 연결 얻어오기
						String url = "jdbc:oracle:thin:@localhost:1521:xe";
						conn = DriverManager.getConnection(url, "skudb", "skudb");

						// 3. statement 생성
						stmt = conn.createStatement();

						// 4. sql문 실행
						//도서번호, 도서제목, 수량
						String sql = "select b.no, b.title, ob.quantity"
								+ " from Book b,order_books ob,orders o "
								+ " where b.no = ob.book_no "
								+ " and o.no = ob.order_no";

						rs = stmt.executeQuery(sql);

						// 5. 결과 처리
						while (rs.next()) {
							Long bookNo = rs.getLong(1);
							String bookTitle = rs.getString(2);
							int quantity = rs.getInt(3);

							OrderBooksVo vo = new OrderBooksVo();
							vo.setBookNo(bookNo);
							vo.setBookTitle(bookTitle);
							vo.setQuantity(quantity);
							
							list.add(vo);
						}
		
		
		
	} catch (ClassNotFoundException e) {
		System.out.println("드라이버 로딩 실패 " + e);
	} catch (SQLException e) {
		System.out.println("error : " + e);
	} finally {
		try {
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
			System.out.println("error : " + e);

		}

	}
		return list;
	}
	
	//insert
	public int insert(OrderBooksVo vo){
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			
			//1.드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			//2.url, conn 연결 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "skudb", "skudb" );
			
			//3. pstmt, sql 준비
			String sql = "insert into Order_books values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. pstmt 바인딩
			pstmt.setLong(1, vo.getOrderNo());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setInt(3, vo.getQuantity());
			
			//5. query실행
			count = pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 :" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원정리
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
		
		return count;
		
	}
}

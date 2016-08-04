package kr.ac.sungkyul.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.bookmall.vo.OrdersVo;

public class OrdersDao {

	// display
	public List<OrdersVo> getList() {
		List<OrdersVo> list = new ArrayList<OrdersVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 연결 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "skudb", "skudb");

			// 3. statement 생성
			stmt = conn.createStatement();

			// 4. sql문 실행
			String sql = "select o.no, m.name, m.email, o.amount, o.address" 
					+ " from member m, orders o"
					+ " where m.no = o.member_no";

			rs = stmt.executeQuery(sql);

			// 5. 결과 처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String memberName = rs.getString(2);
				String memberEmail = rs.getString(3);
				int amount = rs.getInt(4);
				String address = rs.getString(5);

				OrdersVo vo = new OrdersVo();
				vo.setNo(no);
				vo.setMemberName(memberName);
				vo.setMemberEmail(memberEmail);
				vo.setAmount(amount);
				vo.setAddress(address);

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

	// insert
	public int insert(OrdersVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.url, conn연결얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "skudb", "skudb");

			// 3. pstmt,sql 준비
			String sql = "insert into orders values(seq_order.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			// 4. pstmt 바인딩
			pstmt.setLong(1, vo.getMemeberNo());
			pstmt.setInt(2, vo.getAmount());
			pstmt.setString(3, vo.getAddress());

			// 5. query 실행
			count = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
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

package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTestCount {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	
		//2. 연결얻어오기
			String url  = "jdbc:oracle:thin:@localhost:1521:xe";
				conn =  DriverManager.getConnection(url, "hr", "hr");
			
			
		//3. statmente 생성
			stmt = conn.createStatement();
			
		//4. SQL문 실행
			String sql = "select count(*) from employees";
			rs = stmt.executeQuery(sql);
			
		//5.결과처리	
			 while( rs.next() ){ // == true 일동안
				 //rs에 있는 데이터 빼오기
				 int count = rs.getInt( 1 );
				 System.out.println("전체" + count + "개의 row가 있습니다.");
			 }
			 
			 
			 
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println( "드라이버 로딩 실패" + e );
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				//6. 자원정리
				if( rs != null ){
					rs.close();
				}
				if(stmt != null ){
					stmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch( SQLException e ){
				
			}
		}

	}

}

package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTestInsert {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	
		//2. 연결얻어오기
			String url  = "jdbc:oracle:thin:@localhost:1521:xe";
				conn =  DriverManager.getConnection(url, "skudb", "skudb");
			
			
		//3. statmente 생성
			stmt = conn.createStatement();
			
		//4. SQL문 실행
			String sql = "insert into author values(,'상자', null)";
			int count = stmt.executeUpdate(sql); //count = 0 > 실패, count = 1 >하나 들어감
			
			System.out.println( count + "개의 row가 입력되었습니다.");
			
			
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println( "드라이버 로딩 실패" + e );
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error : " + e);
		} finally{
			try{
				//6. 자원정리
			
				if(stmt != null ){
					stmt.close();
				}
				if(conn != null){
					conn.close(); //conn닫으면서 commit이 일어나기 때문에 따로 commit을 안해줘도 됩니다. 
					
				}
			}catch( SQLException e ){
				e.printStackTrace();
				System.out.println("error2 : "+e);
			}
		}

	}

}

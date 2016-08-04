package kr.ac.sungkyul.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.hr.vo.EmployeeVo;

public class EmployeeDao {
	
	public List<EmployeeVo> getList( int minSalary, int maxSalary ){
		List<EmployeeVo> list = new ArrayList<EmployeeVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver" );
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"hr","hr");
				
			String sql = "select first_name,"
							   + "last_name,"
							   + " salary"
							   + " from Employees"
							   + " where salary between ? and ?"
							   + " order by salary asc";
			
			
			pstmt = conn.prepareStatement(sql);
			
			//바인딩
			pstmt.setInt(1, minSalary ); //위에서 %?%이렇게 안하는 대신 보낼때 바인딩 할 때 %를 붙여간다.
			pstmt.setInt(2, maxSalary ); 
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ){
				String firstName = rs.getString(1);
				String lastName = rs.getString(2);
				Integer salary = rs.getInt(3);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setSalary(salary);
				
				list.add(vo);
				
			}
			
		}catch(ClassNotFoundException e){
			System.out.println("드라이브 로드 할 수 없다!" + e);
		}catch(SQLException e){
			System.out.println("SQL에러 "+e);
		}finally{
			try{
				
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
			}catch(SQLException e){
				System.out.println("SQL에러 "+e);
			}
		}
		return list;
	}
	
	public List<EmployeeVo> getList( String name ){
	
		List<EmployeeVo> list = new ArrayList<EmployeeVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver" );
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url,"hr","hr");
		
		
		String sql = "select first_name,"
							   + " last_name," 
						       +" email,"
						       +" phone_number,"
						       +" to_char( hire_date, 'yyyy-mm-dd')"
						       +" from employees"
						       +" where first_name like ?"  //%?%이렇게 ㅎ안하고, string데이터 자체가 바인딩 되는것이다. 
						       +" or last_name like ?";
		
		pstmt = conn.prepareStatement(sql);
		
		
		//바인딩
		pstmt.setString(1, "%" + name + "%"); //위에서 %?%이렇게 안하는 대신 보낼때 바인딩 할 때 %를 붙여간다.
		pstmt.setString(2, "%" + name + "%"); 
		
		rs = pstmt.executeQuery();
		
		while( rs.next() ){
			String firstName = rs.getString(1);
			String lastName = rs.getString(2);
			String email = rs.getString(3);
			String phoneNumber = rs.getString(4);
			String hireDate = rs.getString(5);
			
			EmployeeVo vo = new EmployeeVo();
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);
			vo.setPhoneNumber(phoneNumber);
			vo.setHireDate(hireDate);
			
			list.add(vo);
			
		}
		
		}catch(ClassNotFoundException e){
			System.out.println("드라이버 로드할 수 없어 "+e);
		}catch(SQLException e){
			System.out.println("SQL에러 "+e);
		}finally{
			try{
				
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
			}catch(SQLException e){
				System.out.println("SQL에러 "+e);
			}
		}
		
		
		
		return list;
		
	}

	
	
}

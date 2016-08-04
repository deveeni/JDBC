package kr.ac.sungkyul.hr.app;

import java.util.List;
import java.util.Scanner;

import kr.ac.sungkyul.hr.dao.EmployeeDao;
import kr.ac.sungkyul.hr.vo.EmployeeVo;

public class HRApp {

	public static void main(String[] args) {

		//searchByName();
		serchBySalary();
	}
	
	
	
	//문제2
	public static void serchBySalary(){
	Scanner scanner = new Scanner(System.in);
		System.out.print("임금 입력(최저 최고)>>");
		
		/* 코드 작성 */
		int minSalary = scanner.nextInt();
		int maxSalary = scanner.nextInt();
		
		System.out.println( minSalary + ":" + maxSalary );
		EmployeeDao dao = new EmployeeDao();
		List<EmployeeVo> list = dao.getList( minSalary, maxSalary );
		System.out.println("==================================");
		for( EmployeeVo vo : list ){
			System.out.println(vo.getFirstName()+" "+vo.getLastName()+"\t\t\t"+vo.getSalary());
		}
		scanner.close();

	}
	
	
	public static void searchByName(){
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("이름입력>>");
		String name = scanner.nextLine();
		
		EmployeeDao dao = new EmployeeDao();
		
		List<EmployeeVo> list = dao.getList(name);
	
		System.out.println("==================================");
		for( EmployeeVo vo : list ){
			System.out.println( vo );
		}
		scanner.close();
	}

}

package kr.ac.sungkyul.bookmall.app;

import java.util.List;
import java.util.Scanner;

import kr.ac.sungkyul.bookmall.dao.BookDao;
import kr.ac.sungkyul.bookmall.vo.BookVo;


public class BookMall_rent {

	private static long num;
	public static void main(String[] args) {

		Scanner key = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		num = (long)(key.nextInt());
		
		
		BookDao dao = new BookDao();
		dao.updateStatus(num, 1); //0:대여가능, 1:대여중
		
		displayBookInfo();//dao리스트 
		
	}

	public static void displayBookInfo(){
		
		//dao한테서 list꺼내서
	
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		
		
		System.out.println("*****도서 정보 출력하기******");
		for( BookVo vo  : list ){
			String status;
			if( vo.getStatus() == 1){
				status  = "대여중";
			}else{
				status = "대여가능";
			}
			System.out.println("책 제목:"+ vo.getTitle()+", 저자:"+ vo.getAuthorName() +",대여 유무:"+ status);
		}
		
		
		
	}
}

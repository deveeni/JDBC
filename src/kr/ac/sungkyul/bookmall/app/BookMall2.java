package kr.ac.sungkyul.bookmall.app;

import java.util.List;

import kr.ac.sungkyul.bookmall.dao.BookDao;
import kr.ac.sungkyul.bookmall.dao.CategoryDao;
import kr.ac.sungkyul.bookmall.dao.MemberDao;
import kr.ac.sungkyul.bookmall.dao.OrdersDao;
import kr.ac.sungkyul.bookmall.vo.BookVo;
import kr.ac.sungkyul.bookmall.vo.CategoryVo;
import kr.ac.sungkyul.bookmall.vo.MemberVo;
import kr.ac.sungkyul.bookmall.vo.OrdersVo;

public class BookMall2 {

	public static void main(String[] args) {

		
	//카테고리
		//카테고리 정보 출력
		displayCategoryInfo();
		
	//도서
		//도서 정보 출력
		displayBookInfo();
		
	//회원
		//회원 정보 출력
		displayMemberInfo();
		
	//Orders 
		//주문정보 출력
		displayOrders();
	}
	
	
/*---------------------카테고리-----------------*/
	public static void displayCategoryInfo(){
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> list = dao.getList();
		
		System.out.println("---카테고리 정보 출력하기---");
		for( CategoryVo vo : list ){
			System.out.print( vo.getNo() + ". 카테고리명 : " + vo.getName());
			System.out.println( ); 
		}
	}

/*---------------------도서-----------------*/	
	public static void displayBookInfo(){
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		
		System.out.println("---도서 정보 출력하기---");

		for( BookVo vo : list ){
			System.out.print( vo.getNo()
					+ ". 제목 : " + vo.getTitle()
					+ "\t 평가 : " + vo.getRate()
					+ "\t 가격 :" + vo.getPrice()
					+ "\t 저자 : " + vo.getAuthorName());
			System.out.println( ); 
		}
	}
	
	
/*---------------------회원-----------------*/	
	public static void displayMemberInfo(){
		MemberDao dao = new MemberDao();
		List<MemberVo> list = dao.getList();
		
		System.out.println("---회원 정보 출력하기---");
		for( MemberVo vo : list ){
			System.out.println( vo ); 
		}
	}
	
	
/*---------------------주문-----------------*/	

	public static void displayOrders(){
		OrdersDao dao = new OrdersDao();
		List<OrdersVo> list = dao.getList();
		
		for( OrdersVo vo : list ){
	
			System.out.println("주문 번호:" + vo.getNo());
			System.out.println("주문자: " + vo.getMemberName());
			System.out.println("주문자 이메일: " + vo.getMemberEmail());
			System.out.println("결제금액: " + vo.getAmount() + "원");
			System.out.println("배송지: " + vo.getAddress());
			System.out.println("------------------");
			}
	}

}


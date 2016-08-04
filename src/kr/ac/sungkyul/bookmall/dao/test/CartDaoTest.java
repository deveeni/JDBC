package kr.ac.sungkyul.bookmall.dao.test;

import java.util.List;
import java.util.Scanner;

import kr.ac.sungkyul.bookmall.dao.CartDao;
import kr.ac.sungkyul.bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {

		displayCart();
		// cartInsert();

	}

	public static void displayCart() {
		CartDao dao = new CartDao();
		List<CartVo> list = dao.getList();

//		Scanner memberNo = new Scanner( System.in );
//		System.out.print("장바구니조회를 하고 싶은 [회원번호]를 입력하세요 >> ");
//		int num = ( memberNo.nextInt() );
//		List<CartVo> list = dao.getList( num );
//
//		System.out.println( "---Cart 정보 출력하기---" );
//
//		//System.out.println(  + "님의 장바구니 입니다.");

		for ( CartVo vo : list ) {
			System.out.println( vo.getMemberName() + "님의 장바구니 입니다.");
			System.out.println("책 제목 : " + vo.getBookTitle() + "\t 수량 : " + vo.getQuantity());
		}
	}

	public static void cartInsert() {
		CartVo vo = new CartVo();
		CartDao dao = new CartDao();

		vo.setBookNo(2L);
		vo.setMemberNo(1L);
		vo.setQuantity(1);
		dao.insert(vo);

	}
}

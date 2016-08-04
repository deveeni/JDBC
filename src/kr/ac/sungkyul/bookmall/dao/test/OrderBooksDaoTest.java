package kr.ac.sungkyul.bookmall.dao.test;

import java.util.List;

import kr.ac.sungkyul.bookmall.dao.OrderBooksDao;

import kr.ac.sungkyul.bookmall.vo.OrderBooksVo;


public class OrderBooksDaoTest {

	public static void main(String[] args) {
		//orderBooksInsert();
		displayOrderBooks();
		
	}
	
	public static void orderBooksInsert(){	
		OrderBooksVo vo = new OrderBooksVo();
		OrderBooksDao dao = new OrderBooksDao();
		
		vo.setBookNo(1L);
		vo.setOrderNo(1L);
		vo.setQuantity(3);
		
		dao.insert(vo);

	}
	
	public static void displayOrderBooks(){
		OrderBooksDao dao = new OrderBooksDao();
		List<OrderBooksVo> list = dao.getList();
		
		for( OrderBooksVo vo : list ){
			System.out.println(vo);
			//        도서번호, 도서제목, 수량
//			System.out.print("도서번호. "+ vo.getBookNo());
//			System.out.print("\t 제목 : " + vo.getBookTitle());
//			System.out.print("\t 수량 : " + vo.getQuantity());
//			System.out.println("");
			}
	}
}

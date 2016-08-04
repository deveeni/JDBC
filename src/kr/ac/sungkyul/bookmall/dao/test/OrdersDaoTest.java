package kr.ac.sungkyul.bookmall.dao.test;

import java.util.List;

import kr.ac.sungkyul.bookmall.dao.OrdersDao;
import kr.ac.sungkyul.bookmall.vo.OrdersVo;

public class OrdersDaoTest {

	public static void main(String[] args) {
		//ordersInsert();
		//displayOrders();
		
	}
	
	public static void ordersInsert(){	
		OrdersVo vo = new OrdersVo();
		OrdersDao dao = new OrdersDao();
		
		vo.setMemeberNo(2L);
		vo.setAmount(56000);
		vo.setAddress("범계");
		
		dao.insert(vo);

	}
	
	public static void displayOrders(){
		OrdersDao dao = new OrdersDao();
		List<OrdersVo> list = dao.getList();
		
		for( OrdersVo vo : list ){
//		    --주문번호, 주문자 이름, 이메일, 결제금액, 배송지
			System.out.println("주문 번호:" + vo.getNo());
			System.out.println("주문자: " + vo.getMemberName());
			System.out.println("주문자 이메일: " + vo.getMemberEmail());
			System.out.println("결제금액: " + vo.getAmount() + "원");
			System.out.println("배송지: " + vo.getAddress());
			System.out.println("------------------");
			}
	}

}

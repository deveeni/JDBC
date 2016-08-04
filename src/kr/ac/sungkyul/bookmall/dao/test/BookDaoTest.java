package kr.ac.sungkyul.bookmall.dao.test;

import java.util.List;

import kr.ac.sungkyul.bookmall.dao.AuthorDao;
import kr.ac.sungkyul.bookmall.dao.BookDao;
import kr.ac.sungkyul.bookmall.vo.AuthorVo;
import kr.ac.sungkyul.bookmall.vo.BookVo;

public class BookDaoTest {
	public static void main(String[] args) {

		//testBookDaoGetList();

		//testBookDaoInsert();
		//testBookDaoDelete();
		//testBookDaoDeleteAll();
		//testBookDaoUpdate();
	}
	
	public static void testBookDaoUpdate(){
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();
		vo.setTitle("트와일라잇");
		vo.setRate(1);
		vo.setStatus(1);
		vo.setAuthorNo(1);
		
		dao.update(vo);
	}
	
	
	public static void testBookDaoDeleteAll(){
		BookDao dao = new BookDao();
		int count = dao.delete();
		System.out.println("삭제된 row 수 : "+count);
	}
	
	public static void testBookDaoDelete(){
		BookDao dao = new BookDao();
		int count = dao.delete(1L);
		System.out.println("삭제된 row 수 : "+count);
	}
	
	public static void testBookDaoInsert(){
		
		BookVo vo = new BookVo();
		BookDao dao = new BookDao();
		
		vo.setTitle("트와일라잇");
		vo.setRate(1);
		vo.setStatus(0);
		vo.setAuthorNo(1);
		dao.insert(vo);
		
		vo = new BookVo();
		vo.setTitle("브레이킹던");
		vo.setRate(2);
		vo.setStatus(0);
		vo.setAuthorNo(1);
		dao.insert(vo);
		
		vo = new BookVo();
		vo.setTitle("아리랑");
		vo.setRate(3);
		vo.setStatus(0);
		vo.setAuthorNo(2);
		dao.insert(vo);
		
		vo = new BookVo();
		vo.setTitle("젊은그들");
		vo.setRate(4);
		vo.setStatus(0);
		vo.setAuthorNo(3);
		dao.insert(vo);
		
		vo = new BookVo();
		vo.setTitle("아프니까 청춘이다");
		vo.setRate(2);
		vo.setStatus(0);
		vo.setAuthorNo(4);
		dao.insert(vo);
		
		vo = new BookVo();
		vo.setTitle("귀천");
		vo.setRate(2);
		vo.setStatus(0);
		vo.setAuthorNo(5);
		dao.insert(vo);
		
		vo = new BookVo();
		vo.setTitle("태백산맥");
		vo.setRate(2);
		vo.setStatus(0);
		vo.setAuthorNo(6);
		dao.insert(vo);
		
		vo = new BookVo();
		vo.setTitle("풀하우스");
		vo.setRate(3);
		vo.setStatus(0);
		vo.setAuthorNo(6);
		dao.insert(vo);
	}

	
	public static void testBookDaoGetList(){
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		
		for( BookVo vo : list ){
			System.out.println(vo);
		}
	}
}

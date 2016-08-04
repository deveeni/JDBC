package kr.ac.sungkyul.bookmall.dao.test;

import java.util.List;

import kr.ac.sungkyul.bookmall.dao.CategoryDao;
import kr.ac.sungkyul.bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	
	public static void main(String[] args) {
		displayCategoryInfo();
		//categoryInsert();
		//categoryUpdate();
		
	}
	
	
	
	public static void displayCategoryInfo(){
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> list = dao.getList();
		
		System.out.println("---카테고리 정보 출력하기---");
		for( CategoryVo vo : list ){
			System.out.print( vo.getNo() + ". 카테고리명 : " + vo.getName());
			System.out.println( ); 
		}
	}
	
	public static void categoryInsert(){
		CategoryVo vo = new CategoryVo();
		CategoryDao dao = new CategoryDao();
		
		vo.setName("IT/컴퓨터");
		dao.insert(vo);
		
	}
	
	public static void categoryUpdate(){
		CategoryVo vo = new CategoryVo();
		CategoryDao dao = new CategoryDao();
		
		vo.setNo(1L);
		vo.setName("철학이");
		dao.update(vo);
	}
}

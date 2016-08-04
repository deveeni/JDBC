package kr.ac.sungkyul.bookmall.dao.test;

import java.util.List;

import kr.ac.sungkyul.bookmall.dao.MemberDao;
import kr.ac.sungkyul.bookmall.vo.MemberVo;



public class MemberDaoTest {

	public static void main(String[] args) {

		displayMemberInfo();
		//memberInsert();
		//memberUpdate();
	}

	public static void displayMemberInfo(){
		MemberDao dao = new MemberDao();
		List<MemberVo> list = dao.getList();
		
		System.out.println("---회원 정보 출력하기---");
		for( MemberVo vo : list ){
			System.out.println( vo ); 
		}
	}
	
	
	public static void memberInsert(){
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();
		
		vo.setName("로비");
		vo.setPassword("234rr");
		dao.insert(vo);
	}
	
	public static void memberUpdate(){
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();
		
		vo.setNo(3L);
		vo.setName("또비");
		vo.setEmail("robby@gmail.com");
		vo.setPassword("23456s");
		dao.update(vo);
		
	}
}

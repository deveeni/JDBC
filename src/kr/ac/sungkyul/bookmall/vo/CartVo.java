package kr.ac.sungkyul.bookmall.vo;

public class CartVo {

	private Long bookNo;
	private Long memberNo;
	private Integer quantity;
	private String bookTitle;
	private String memberName;
	
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	
	
	
	
}

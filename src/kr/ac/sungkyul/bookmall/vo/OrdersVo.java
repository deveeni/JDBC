package kr.ac.sungkyul.bookmall.vo;

public class OrdersVo {

	private Long no;
	private Long memeberNo;
	private String memberName;
	private String memberEmail;
	private Integer amount;
	private String address;
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getMemeberNo() {
		return memeberNo;
	}
	public void setMemeberNo(Long memeberNo) {
		this.memeberNo = memeberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}

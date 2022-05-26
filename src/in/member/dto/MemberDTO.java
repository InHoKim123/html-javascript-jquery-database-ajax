package in.member.dto;

public class MemberDTO {
	private String id;
	private	String passwd;
	private String name;
	private String email;
	private String nicname;
	private int managernum;
	private String memberday;
	private String membertech;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNicname() {
		return nicname;
	}
	public void setNicname(String nicname) {
		this.nicname = nicname;
	}
	public int getManagernum() {
		return managernum;
	}
	public void setManagernum(int managernum) {
		this.managernum = managernum;
	}
	public String getMemberday() {
		return memberday;
	}
	public void setMemberday(String memberday) {
		this.memberday = memberday;
	}
	public String getMembertech() {
		return membertech;
	}
	public void setMembertech(String membertech) {
		this.membertech = membertech;
	}
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", passwd=" + passwd + ", name=" + name + ", email=" + email + ", nicname="
				+ nicname + ", managernum=" + managernum + ", memberday=" + memberday + ", membertech=" + membertech
				+ "]";
	}

	
}

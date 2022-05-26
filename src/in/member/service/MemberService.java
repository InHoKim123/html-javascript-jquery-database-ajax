package in.member.service;

import java.util.ArrayList;

import in.member.dto.MemberDTO;

public interface MemberService {
	public MemberDTO memberLogin(String id, String passwd);
	
	public ArrayList<MemberDTO> memberSelectAll();
	
	public MemberDTO memberSelect(String id);
	
	
	public MemberDTO memberUpdate(String id, String passwd, String name, String email, String nicname, int managernum,
			String membertech);
	
	public MemberDTO memberDelete(String id);

	public MemberDTO memberInsert(String id, String passwd, String name, String email, String nicname, int managernum,
			String memberday, String membertech);
	
	public int memberCheckedId(String id);
}

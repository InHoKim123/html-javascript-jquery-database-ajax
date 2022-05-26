package in.member.controller;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import in.handler.HandlerAdapter;
import in.member.control.Controller;
import in.member.dao.MemberDAO;
import in.member.dto.MemberDTO;


@WebServlet("/MemberUpdateView")
public class MemberUpdateView implements Controller {
	
	private static final Log log = LogFactory.getLog(MemberUpdateView.class);
	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		log.info(id);
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();
		arrayList = memberDAO.memberSelectAll();
		
		String id2 = "";
		String passwd = "";
		String name = "";
		String email = "";
		String nicname = "";
		int managernum = 0;
		String memberday = "";
		String membertech = "";
		
		for (MemberDTO memberDTO2 : arrayList) {
			id2 = memberDTO2.getId();
			passwd = memberDTO2.getPasswd();
			name = memberDTO2.getName();
			email = memberDTO2.getEmail();
			nicname = memberDTO2.getNicname();
			managernum = memberDTO2.getManagernum();
			memberday = memberDTO2.getMemberday();
			membertech = memberDTO2.getMembertech();
			//log.info(memberDTO2);
			if (id2.equals(id)) {
				memberDTO.setId(id);
				memberDTO.setPasswd(passwd);
				memberDTO.setName(name);
				memberDTO.setEmail(email);
				memberDTO.setNicname(nicname);
				memberDTO.setManagernum(managernum);
				memberDTO.setMemberday(memberday);
				memberDTO.setMembertech(membertech);
				
				request.setAttribute("id", id);
				request.setAttribute("passwd", passwd);
				request.setAttribute("name", name);
				request.setAttribute("email", email);
				request.setAttribute("nicname", nicname);
				request.setAttribute("managernum", managernum);
				request.setAttribute("memberday", memberday);
				request.setAttribute("membertech", membertech);
				break;
			}else {
				
			}								
		}
		
		log.info(memberDTO);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/member/MemberUpdate.jsp");
		return handlerAdapter;
	}

}

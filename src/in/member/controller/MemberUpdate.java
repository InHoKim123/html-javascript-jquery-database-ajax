package in.member.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import in.handler.HandlerAdapter;
import in.member.control.Controller;
import in.member.dao.MemberDAO;
import in.member.dto.MemberDTO;


@WebServlet("/MemberUpdate")
public class MemberUpdate implements Controller {
	
	private static final Log log = LogFactory.getLog(MemberUpdate.class);

	
	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		log.info(id);
		String passwd = request.getParameter("passwd");
		log.info(passwd);
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String nicname = request.getParameter("nicname");
		int managernum = Integer.parseInt(request.getParameter("managernum"));
		String memberday = request.getParameter("memberday");
		String membertech = request.getParameter("membertech");
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId(id);
		memberDTO.setPasswd(passwd);
		memberDTO.setName(name);
		memberDTO.setEmail(email);
		memberDTO.setNicname(nicname);
		memberDTO.setManagernum(managernum);
		memberDTO.setMemberday(memberday);
		memberDTO.setMembertech(membertech);
		
		log.info(memberDTO);
		
		memberDTO = memberDAO.memberUpdate(id, passwd, name, email, nicname, managernum, membertech);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/member/MemberUpdate_view.jsp");
		return handlerAdapter;
	}

}

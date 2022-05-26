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

@WebServlet("/MemberInsert")
public class MemberInsert implements Controller {

	private static final Log log = LogFactory.getLog(MemberInsert.class);

	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		log.info(id);
		String passwd = request.getParameter("passwd");
		log.info(passwd);
		String name = request.getParameter("name");
		log.info(name);
		String email = request.getParameter("email");
		String nicname = request.getParameter("nicname");
		log.info(nicname);
		int managernum = Integer.parseInt(request.getParameter("managernum"));
		log.info(managernum);
		String memberday = request.getParameter("memberday");
		log.info(memberday);
		String membertech = request.getParameter("membertech");
		log.info(membertech);

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();
		log.info(arrayList);
		arrayList = memberDAO.memberSelectAll();
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		//String id2 = "";
		//int count = 0;

		//if (count == arrayList.size()) {}
			memberDTO.setId(id);
			memberDTO.setPasswd(passwd);
			memberDTO.setName(name);
			memberDTO.setEmail(email);
			memberDTO.setNicname(nicname);
			memberDTO.setManagernum(managernum);
			memberDTO.setMemberday(memberday);
			memberDTO.setMembertech(membertech);
			
			memberDTO = memberDAO.memberInsert(id, passwd, name, email, nicname, managernum, memberday, membertech);
			
			handlerAdapter.setPath("/WEB-INF/view/member/MemberInsert_view.jsp");
		
		return handlerAdapter;
		
	}

}

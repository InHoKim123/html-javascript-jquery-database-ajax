package in.login.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import in.handler.HandlerAdapter;
import in.member.control.Controller;
import in.member.dao.MemberDAO;
import in.member.dto.MemberDTO;


@WebServlet("/Login")
public class Login implements Controller{

	
	private static final Log log = LogFactory.getLog(Login.class);
	
	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");



		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		
		memberDTO = memberDAO.memberLogin(id, passwd);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		log.info(memberDTO);
		if (memberDTO.getId() == null) {
			handlerAdapter.setPath("/WEB-INF/view/login/LoginFail.jsp");			
		}else {
			memberDTO = memberDAO.memberSelect(id);
			int managernum = memberDTO.getManagernum(); 
			log.info("사용자 권한 : " + managernum);
			String nicname = memberDTO.getNicname();
			log.info("닉네임 : " + nicname);
			
			
			HttpSession session = request.getSession();
			session.setAttribute("seid", id);
			session.setAttribute("seman", managernum);
			session.setAttribute("senic", nicname);
			
			handlerAdapter.setPath("/WEB-INF/view/login/LoginDone.jsp");
			log.info("로그인성공");
		}
		return handlerAdapter;
	}

}

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


@WebServlet("/MemberSelectDetail")
public class MemberSelectDetail implements Controller {
          
	private static final Log log = LogFactory.getLog(MemberSelectDetail.class);

	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		log.info(id);
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO = memberDAO.memberSelect(id);
		log.info(memberDTO);
		request.setAttribute("passwd", memberDTO.getPasswd());
		request.setAttribute("name", memberDTO.getName());
		request.setAttribute("email", memberDTO.getEmail());
		request.setAttribute("nicname", memberDTO.getNicname());
		request.setAttribute("managernum", memberDTO.getManagernum());
		request.setAttribute("memberday", memberDTO.getMemberday());
		request.setAttribute("membertech", memberDTO.getMembertech());
		request.setAttribute("memberDTO", memberDTO);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/member/MemberSelectDetail_view.jsp");
		return handlerAdapter;
	}


}

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



@WebServlet("/MemberSelectAll")
public class MemberSelectAll implements Controller {	
	private static final Log log = LogFactory.getLog(MemberSelectAll.class);


	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		log.info(memberDTO);
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();
		arrayList = memberDAO.memberSelectAll();
		request.setAttribute("arrayList", arrayList);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("WEB-INF/view/member/MemberSelect_view.jsp");
		return handlerAdapter;
	}


}

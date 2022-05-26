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


@WebServlet("/MemberDelete")
public class MemberDelete implements Controller {

	
	private static final Log log = LogFactory.getLog(MemberDelete.class);

	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		log.info(id);
		String passwd = request.getParameter("passwd");
		log.info(passwd);
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();
		arrayList = memberDAO.memberSelectAll();
		log.info(memberDTO);
		String id2 = "";
		String passwd2 = "";
		int count = 0;
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		for (MemberDTO memberDTO2 : arrayList) {
			id2 = memberDTO2.getId();
			passwd2 = memberDTO2.getPasswd();
			
			if (id2.equals(id) && passwd2.equals(passwd)) {
				memberDTO = memberDAO.memberDelete(id);
				handlerAdapter.setPath("/WEB-INF/view/member/MemberDelete_view.jsp?id=\" + id");
				break;
			}else {
				count = count + 1;
			}		
		}
		log.info("카운트 : " + count);
		if (count == arrayList.size()) { 
			handlerAdapter.setPath("/WEB-INF/view/member/MemberDeleteFail_view.jsp");
		}
		
		return handlerAdapter;
		
	}

}

package in.member.controller;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import in.handler.HandlerAdapter;
import in.member.control.Controller;
import in.member.dao.MemberDAO;

@WebServlet("/MemberIdCheck")
public class MemberIdCheck implements Controller {
	
	private static final Log log = LogFactory.getLog(MemberIdCheck.class);
	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("userid");
		log.info("아이디 중복 : " + userid);
		
		MemberDAO memberDAO = new MemberDAO();
		int idCheck = memberDAO.memberCheckedId(userid);
		log.info(idCheck);
		if (idCheck == 0) {
			log.info("이미 존재하는 아이디");
		}else {
			log.info("사용 가능 아이디");
		}
		try {
			PrintWriter out = response.getWriter();
			out.write(idCheck + "");
			out.close();
		} catch (Exception e) {
			e.printStackTrace( );
		}
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/member/MemberInsert.jsp");
		
		return null;
	}


}

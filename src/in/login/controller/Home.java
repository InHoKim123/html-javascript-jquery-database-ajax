package in.login.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import in.handler.HandlerAdapter;
import in.member.control.Controller;
import in.post.dao.PostDAO;
import in.post.dto.PostDTO;


@WebServlet("/Home")
public class Home implements Controller {
	
	private static final Log log = LogFactory.getLog(Home.class);

	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		PostDTO postDTO = new PostDTO();
		PostDAO postDAO = new PostDAO();
		postDTO = postDAO.postSelectMaxView();
		log.info("최고 조회수 : " + postDTO);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/login/Home.jsp");
		return handlerAdapter;
	}

}

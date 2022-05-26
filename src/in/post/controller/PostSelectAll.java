package in.post.controller;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import in.handler.HandlerAdapter;
import in.member.control.Controller;
import in.post.dao.PostDAO;
import in.post.dto.PostDTO;


@WebServlet("/PostSelectAll")
public class PostSelectAll implements Controller {
	private static final Log log = LogFactory.getLog(PostSelectAll.class);



	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		PostDAO postDAO = new PostDAO();
		PostDTO postDTO = new PostDTO();
		log.info(postDTO);
		ArrayList<PostDTO> arrayList = new ArrayList<PostDTO>();
		arrayList = postDAO.postSelectAll();
		request.setAttribute("arrayList", arrayList);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/post/PostSelect.jsp");
		return handlerAdapter;
	}

}

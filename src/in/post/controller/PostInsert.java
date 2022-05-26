package in.post.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import in.handler.HandlerAdapter;
import in.member.control.Controller;
import in.post.dao.PostDAO;
import in.post.dto.PostDTO;


@WebServlet("/PostInsert")
public class PostInsert implements Controller {
	
	private static final Log log = LogFactory.getLog(PostInsert.class);


	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String posttitle = request.getParameter("posttitle");
		String postpasswd = request.getParameter("postpasswd");
		String postcontent = request.getParameter("postcontent");
		String postwriter = request.getParameter("postwriter");
		String postkind = request.getParameter("postkind");
		
		log.info(posttitle);
		
		PostDAO postDAO = new PostDAO();
		PostDTO postDTO = new PostDTO();
		
		postDTO.setPosttitle(posttitle);
		postDTO.setPasswd(postpasswd);
		postDTO.setPostcontent(postcontent);
		postDTO.setPostwriter(postwriter);
		postDTO.setPostkind(postkind);
		
		log.info(postDTO);
		
		postDAO.postInsert(postDTO);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/post/PostInsert_view.jsp");
		return handlerAdapter;
	}

}

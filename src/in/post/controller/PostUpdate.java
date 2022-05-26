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


@WebServlet("/PostUpdate")
public class PostUpdate implements Controller {
	
	private static final Log log = LogFactory.getLog(PostUpdate.class);



	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		int postnum = Integer.parseInt(request.getParameter("postnum"));
		log.info(postnum);
		String posttitle = request.getParameter("posttitle");
		log.info(posttitle);
		String postwriter = request.getParameter("postwriter");
		String postpasswd = request.getParameter("postpasswd");
		String postkind = request.getParameter("postkind");
		String postcontent = request.getParameter("postcontent");
		String postwriteday = request.getParameter("postwriteday");
		
		PostDAO postDAO = new PostDAO();
		PostDTO postDTO = new PostDTO();
		
		postDTO.setPostnum(postnum);
		postDTO.setPosttitle(posttitle);
		postDTO.setPasswd(postpasswd);
		postDTO.setPostcontent(postcontent);
		postDTO.setPostwriter(postwriter);		
		postDTO.setPostkind(postkind);
		postDTO.setPostwriteday(postwriteday);
		
		log.info(postDTO);
		postDTO = postDAO.postUpdate(postDTO);
		
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/post/PostUpdate_view.jsp");
		return handlerAdapter;
	}

}

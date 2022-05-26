package in.post.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import in.handler.HandlerAdapter;
import in.member.control.Controller;
import in.post.dao.PostDAO;
import in.post.dto.PostDTO;


@WebServlet("/PostUpdateView")
public class PostUpdateView implements Controller {
	
	private static final Log log = LogFactory.getLog(PostUpdateView.class);
	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		int postnum = Integer.parseInt(request.getParameter("postnum"));
		//log.info(postnum);
		
		PostDTO postDTO2 = new PostDTO();
		PostDAO postDAO = new PostDAO();
		List<PostDTO> list = new ArrayList<PostDTO>();
		list = postDAO.postSelectAll();
		
		String posttitle = "";
		String postcontent = "";
		String postpasswd = "";
		String postwriter = "";
		String postwriteday = "";
		int views = 0;
		String postkind = "";
		
		for (PostDTO postDTO : list) {
			int postnum2 = postDTO.getPostnum();
			posttitle = postDTO.getPosttitle();
			postpasswd = postDTO.getPasswd();
			postcontent = postDTO.getPostcontent();
			postwriter = postDTO.getPostwriter();
			postwriteday = postDTO.getPostwriteday();
			views = postDTO.getViews();
			postkind = postDTO.getPostkind();
			
			if (postnum == postnum2) {
				postDTO2.setPostnum(postnum);
				postDTO2.setPosttitle(posttitle);
				postDTO2.setPasswd(postpasswd);
				postDTO2.setPostcontent(postcontent);
				postDTO2.setPostwriter(postwriter);
				postDTO2.setPostwriteday(postwriteday);
				postDTO2.setPostkind(postkind);
				postDTO2.setViews(views);
				
				request.setAttribute("postnum", postnum);
				request.setAttribute("posttitle", posttitle);
				request.setAttribute("postpasswd", postpasswd);
				request.setAttribute("postcontent", postcontent);
				request.setAttribute("postwriter", postwriter);
				request.setAttribute("postwriteday", postwriteday);
				request.setAttribute("postkind", postkind);
				request.setAttribute("views", views);
				break;
			}else {
				
			}
		}
		//log.info(postcontent);
		log.info(postDTO2);
		
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("./WEB-INF/view/post/PostUpdate.jsp");
		return handlerAdapter;
	}

}

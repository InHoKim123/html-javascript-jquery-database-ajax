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

@WebServlet("/PostDelete")
public class PostDelete implements Controller {
	
	private static final Log log = LogFactory.getLog(PostDelete.class);

	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		int postnum = Integer.parseInt(request.getParameter("postnum"));
		log.info("글 번호 : " + postnum);
		
		PostDAO postDAO = new PostDAO();
		PostDTO postDTO = new PostDTO();
		ArrayList<PostDTO> arrayList = new ArrayList<PostDTO>();
		arrayList = postDAO.postSelectAll();
		request.setAttribute("arrayList", arrayList);
		
		int postnum2 = 0;
		int count = 0;
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		for (PostDTO postDTO2 : arrayList) {
			postnum2 = postDTO2.getPostnum();
			if (postnum2 == postnum) {
				postDTO = postDAO.postDelete(postnum);
				log.info(postDTO);
				handlerAdapter.setPath("/WEB-INF/view/post/PostDelete_view.jsp");
				break;
			}else {
				count = count + 1;
			}
		}
		
		if (count == arrayList.size()) {
			handlerAdapter.setPath("/WEB-INF/view/post/PostDeleteFail.jsp");
		}
		
		return handlerAdapter;
	}

}

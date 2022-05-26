package in.post.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.handler.HandlerAdapter;
import in.member.control.Controller;
import in.post.dao.PostDAO;
import in.post.dto.PostDTO;

@WebServlet("/PostSelectDetail")
public class PostSelectDetail implements Controller {

	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		int postnum = Integer.parseInt(request.getParameter("postnum"));
		System.out.println(postnum);

		PostDTO postDTO = new PostDTO();
		PostDAO postDAO = new PostDAO();
		postDTO = postDAO.postSelect(postnum);
		postnum = postDTO.getPostnum();
		String posttitle = postDTO.getPosttitle();
		String postpasswd = postDTO.getPasswd();
		String postcontent = postDTO.getPostcontent();
		String postwriter = postDTO.getPostwriter();
		String postwriteday = postDTO.getPostwriteday();
		int views = postDTO.getViews();
		String postkind = postDTO.getPostkind();

		request.setAttribute("postnum", postnum);
		request.setAttribute("posttitle", posttitle);
		request.setAttribute("postpasswd", postpasswd);
		request.setAttribute("postcontent", postcontent);
		request.setAttribute("postwriter", postwriter);
		request.setAttribute("postwriteday", postwriteday);
		request.setAttribute("views", views);
		request.setAttribute("postkind", postkind);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/post/PostSelectDetail.jsp");
		return handlerAdapter;

	}
}

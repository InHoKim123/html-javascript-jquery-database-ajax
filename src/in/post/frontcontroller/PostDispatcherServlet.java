package in.post.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import in.handler.HandlerAdapter;
import in.member.control.Controller;
import in.post.controller.PostDelete;
import in.post.controller.PostInsert;
import in.post.controller.PostSelectAll;
import in.post.controller.PostSelectDetail;
import in.post.controller.PostUpdate;
import in.post.controller.PostUpdateView;


@WebServlet("/PostDispatcherServlet")
public class PostDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private static final Log log = LogFactory.getLog(PostDispatcherServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI( );
		log.info("requestURI : " + requestURI);
		// URL에서 /(슬래시)를 첨부한 컨텍스트 이름인 /컨텍스트명/맵핑명을 반환한다.
		String contextPath = request.getContextPath( );
		log.info("path : " + contextPath);
		// URL이 http://localhost/FrontDept/DeptSelect.do라면
		// /FrontDept/DeptSelect.do가 반환된다.
		String pathURL = requestURI.substring(contextPath.length( ));
		log.info("맵핑명 조회 - " + pathURL);
		HandlerAdapter handlerAdapter = null;
		Controller controller = null;
		
		if (pathURL.equals("/PostSelect.po")) {
			controller = new PostSelectAll();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("게시글 조회 실패");
			}
		}
		else if (pathURL.equals("/PostSelectDetail.po")) {
			controller = new PostSelectDetail();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("게시글 상세 조회 실패");
			}
		}
		else if (pathURL.equals("/PostInsert.po")) {
			handlerAdapter = new HandlerAdapter();
			handlerAdapter.setPath("/WEB-INF/view/post/PostInsert.jsp");
		}
		else if (pathURL.equals("/PostInsertView.po")) {
			controller = new PostInsert();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("게시글 작성 실패");
			}
		}
		else if (pathURL.equals("/PostDeleteView.po")) {
			handlerAdapter = new HandlerAdapter();
			handlerAdapter.setPath("/WEB-INF/view/post/PostDelete.jsp");
		}
		else if (pathURL.equals("/PostDelete.po")) {
			controller = new PostDelete();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("게시글 삭제 실패");
			}
		}
		else if (pathURL.equals("/PostUpdate.po")) {
			controller = new PostUpdateView();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("게시글 수정 조회 실패");
			}
		}
		else if (pathURL.equals("/PostUpdateView.po")) {
			controller = new PostUpdate();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("게시글 수정 실패");
			}
		}
		
		if(handlerAdapter != null) {
			if(handlerAdapter.isRedirect( )) {
				response.sendRedirect(handlerAdapter.getPath( ));
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(handlerAdapter.getPath( ));
				dispatcher.forward(request, response);
			}
		}
		
	}
}

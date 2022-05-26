package in.member.frontcontroller;

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
import in.member.controller.MemberDelete;
import in.member.controller.MemberIdCheck;
import in.member.controller.MemberInsert;
import in.member.controller.MemberSelectAll;
import in.member.controller.MemberSelectDetail;
import in.member.controller.MemberUpdate;
import in.member.controller.MemberUpdateView;

@WebServlet("/MemberDispatcherServlet")
public class MemberDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(MemberDispatcherServlet.class);
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
		
		if (pathURL.equals("/MemberSelect.me")) {
			controller = new MemberSelectAll();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("회원 조회 실패");
			}
		}
		else if (pathURL.equals("/MemberSelectDetail.me")) {
			controller = new MemberSelectDetail();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("회원 상세 조회 실패");
			}
		}
		else if (pathURL.equals("/MemberInsert.me")) {
			handlerAdapter = new HandlerAdapter();
			handlerAdapter.setPath("/WEB-INF/view/member/MemberInsert.jsp");
		}
		else if (pathURL.equals("/MemberInsertView.me")) {
			controller = new MemberInsert();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("회원 가입 실패");
			}
		}
		else if (pathURL.equals("/MemberDeleteView.me")) {
			handlerAdapter = new HandlerAdapter();
			handlerAdapter.setPath("/WEB-INF/view/member/MemberDelete.jsp");
		}
		else if (pathURL.equals("/MemberDelete.me")) {
			controller = new MemberDelete();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("회원 삭제 실패");
			}
		}
		else if (pathURL.equals("/MemberUpdateView.me")) {
			controller = new MemberUpdateView();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("회원 정보 수정 실패");
			}		
		}
		else if (pathURL.equals("/MemberUpdate.me")) {
			controller = new MemberUpdate();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("회원 정보 수정 실패");
			}		
		}
		else if (pathURL.equals("/CheckedId.me")) {
			controller = new MemberIdCheck();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("회원 정보 수정 실패");
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

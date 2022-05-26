package in.login.frontcontroller;

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
import in.login.controller.Home;
import in.login.controller.LogOut;
import in.login.controller.Login;
import in.member.control.Controller;


@WebServlet("/LoginDispatcherServlet")
public class LoginDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(LoginDispatcherServlet.class);
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
		
		if (pathURL.equals("/Home.lo")) {
			controller = new Home();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("시작페이지 실패");
			}
		}
		else if (pathURL.equals("/Login.lo")) {
			handlerAdapter = new HandlerAdapter();
			handlerAdapter.setPath("/WEB-INF/view/login/Login.jsp");
		}
		else if (pathURL.equals("/LoginView.lo")) {
			controller = new Login();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("로그인 실패");
			}
		}
		else if (pathURL.equals("/LogOut.lo")) {
			handlerAdapter = new HandlerAdapter();
			handlerAdapter.setPath("/WEB-INF/view/login/Logout.jsp");
		}
		else if (pathURL.equals("/LogOutView.lo")) {
			controller = new LogOut();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("로그아웃 실패");
			}
		}
		else if (pathURL.equals("/DoLoginFirst.lo")) {
			handlerAdapter = new HandlerAdapter();
			handlerAdapter.setPath("/WEB-INF/view/login/MoveToLogin.jsp");
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

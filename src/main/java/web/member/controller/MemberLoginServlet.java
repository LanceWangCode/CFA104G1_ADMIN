package web.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.membership.MemberImpl;

import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoImpl;
import web.member.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username =  request.getParameter("username");
		String password =  request.getParameter("password");
		
		MemberDao dao = new MemberDaoImpl();
		Member member = dao.selectByUsernameAndPassowrd(username, password);
		
		if (member == null) {
			// redirect to index.html
			response.sendRedirect(request.getContextPath()+"/index.jsp?error=LoginFail");
			System.out.println("Login Fail");			
		} else {
			// redirect to admin.html
			HttpSession session=request.getSession();  
	        session.setAttribute("username",member.getNickname());  
			response.sendRedirect(request.getContextPath()+"/dashboard.jsp");
			System.out.println(member.getNickname());
		}
	}

}

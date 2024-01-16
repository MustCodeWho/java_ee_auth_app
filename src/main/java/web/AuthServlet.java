package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SingletonConnection;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/authServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SingletonConnection DAO;
	
	@Override
		public void init() throws ServletException {
			DAO = new SingletonConnection();
		}
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean isLoggedIn = DAO.login(email, password);
		System.out.print(isLoggedIn);
		
		if(isLoggedIn) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			session.setAttribute("login", email);
		}else {
			request.setAttribute("status", "failed");
			response.sendRedirect("login.jsp");
		}
		
		
		
	}
	
	
}

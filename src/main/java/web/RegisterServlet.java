package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SingletonConnection;
import metier.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	SingletonConnection DAO;
	@Override
	public void init() throws ServletException {
		DAO = new SingletonConnection();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		
		User user = new User(username,email,password,phone);
		
		int status = DAO.register(user);
		
		request.getRequestDispatcher("registration.jsp").forward(request, response);
		
		System.out.print("-------------------"+status+"-----------------");		
		if(status > 0 ) {
			request.setAttribute("status", "success");
		}else {
			request.setAttribute("status", "failed");
		}
		
	}

}

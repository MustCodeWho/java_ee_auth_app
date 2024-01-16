package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import metier.User;

public class SingletonConnection {
	private static Connection connexion;
	private final static String REGISTER_USER = "insert into users(username,email,password,phone) values (?,?,?,?) ";	
	private final static String LOGIN_USER = "SELECT * FROM users WHERE email=? and password=?";
		static {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbusers?useSSL=false","root","");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		public static Connection getConnexion() {
			return connexion;
		}
		
		
		// Register (username,email,password,mobile);
		
		public int register(User user) {
			Connection con = getConnexion();
			int status = 0;
			try {
				PreparedStatement ps = con.prepareStatement(REGISTER_USER);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getPhone());
				status = ps.executeUpdate();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return status;
			
		}
		
		
		public boolean login(String email,String password) {
			Connection con = getConnexion();
			try {
				PreparedStatement ps = con.prepareStatement(LOGIN_USER);
				ps.setString(1,email);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					return true;
				}
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				return false;
		}
}

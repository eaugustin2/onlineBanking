

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import onlineBanking.User;


@WebServlet("/forgotLogin")
public class forgotLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public forgotLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String errorUrl = "/forgot.jsp";
		String url = "/loginResetOption.jsp";
		
		//HttpSession session = request.getSession(false); There is no session since there was no login, have to make a call to database
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String dob = request.getParameter("dob");
		
		
		if(isCorrect(fname,lname,dob)) {
			//redirect to a new page and proceed with questions and create a session, retrieve session after user picks which way they want to retrieve account
			String[] user = new String[8];
			
			user = getInfo(fname,lname,dob);
			
			User userLogin = new User(user[0],user[1],user[2],user[3],user[4],user[5],user[6],user[7]);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("userLogin", userLogin);
			
			
			getServletContext()
				.getRequestDispatcher(url)
				.forward(request, response);
			
		}
		
		//A session should be created here to send either email or phone number to resetLoginHandler
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("Error, One of these is incorrect");
		
		getServletContext()
			.getRequestDispatcher(errorUrl)
			.include(request, response);
	}
	
	
	
	protected boolean isCorrect(String fname, String lname, String dob) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String mysqlUrl = "jdbc:mysql://localhost/onlineBanking?serverTimezone=UTC";
			String dbuser = "sampleBanker";
			String dbpass = "password1";
			
			Connection con = DriverManager.getConnection(mysqlUrl,dbuser,dbpass);
			
			Statement stmt = con.createStatement();
			
			ResultSet myResult = stmt.executeQuery("select * from userInfo where first_name ='"+fname+"' and last_name='"+lname+"' and date_of_birth='"+dob+"'");
			
			
			if(myResult.next()) {
				return true;
			}
			
			
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	protected String[] getInfo(String fname, String lname, String dob) {
		String[] user = new String[8];
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String mysqlUrl = "jdbc:mysql://localhost/onlineBanking?serverTimezone=UTC";
			String dbuser = "sampleBanker";
			String dbpass = "password1";
			
			Connection con;
			
			con = DriverManager.getConnection(mysqlUrl,dbuser,dbpass);
			Statement stmt = con.createStatement();
			
			ResultSet myResult = stmt.executeQuery("select * from userInfo where first_name ='"+fname+"' and last_name='"+lname+"' and date_of_birth='"+dob+"'");

			
			int i =0;
			while(myResult.next()) {
				while(i<8) {
					user[i] = myResult.getString(i+1);
					
					
					i++;
				}
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	

}

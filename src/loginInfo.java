

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineBanking.User;

import java.sql.*;
import java.io.PrintWriter;


@WebServlet("/loginInfo")
public class loginInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public loginInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url ="/dashboard.jsp"; //forward page here if username and password are in the database
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
 		
		/*
		 * need to check if username or password is null
		 */
		
		if(isEmpty(username, password)) {
			out.println("<h2>Password or Username cannot be empty </h2>");
			getServletContext()
			.getRequestDispatcher("/index.jsp")
			.include(request, response);
		}
		
		else if(!validLogin(username,password)) {
			out.println("<h2>Incorrect Username or Password</h2>");
			getServletContext()
			.getRequestDispatcher("/index.jsp")
			.include(request, response);
		}
		
		else {
		
			//Creating a session	
			HttpSession session = request.getSession();
			
			String[] user = new String[8];
			
			user = getInfo(username,password);
			
			
			User userLogin = new User(user[0],user[1],user[2],user[3],user[4],user[5],user[6],user[7]); //passes all data to the bean which makes it easier to send data throught eh object bean instead of sending multi[ple different objects
			
			//request.setAttribute("userLogin", userLogin); //to send to the JSP file
			
			
			session.setAttribute("userLogin", userLogin); //saves data in session, can be retrieved when another session asks for it via getSession(false)
			
		
			getServletContext()
			.getRequestDispatcher(url)
			.forward(request,response);
		}
		
		
		
	}
	
	protected boolean isEmpty(String user, String pass) {
		if(user == "" || pass == "")
			return true;
		
		return false;
	}
	
	/*
	 * This function should be to check if the given username or password is in the database, return accordingly
	 */
	protected boolean validLogin(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String mysqlConnection = "jdbc:mysql://localhost/onlineBanking?serverTimezone=UTC";
			String dbuser = "sampleBanker";
			String dbpass = "password1";
			
			Connection con;
			
			con = DriverManager.getConnection(mysqlConnection,dbuser,dbpass);
			Statement stmt = con.createStatement();
			
			ResultSet myResult = stmt.executeQuery("select * from userInfo where username ='"+username+"' and password='"+password+"'");
			
			
			if(myResult.next()) {
				return true;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected String[] getInfo(String username, String password) {
		String[] user = new String[8];
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String mysqlUrl = "jdbc:mysql://localhost/onlineBanking?serverTimezone=UTC";
			String dbuser = "sampleBanker";
			String dbpass = "password1";
			
			Connection con;
			
			con = DriverManager.getConnection(mysqlUrl,dbuser,dbpass);
			Statement stmt = con.createStatement();
			
			ResultSet myResult = stmt.executeQuery("select * from userInfo where username ='"+username+"' and password='"+password+"'");

			
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

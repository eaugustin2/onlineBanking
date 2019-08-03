

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
//import java.io.PrintWriter;
import onlineBanking.User;


@WebServlet("/balanceUpdate")
public class balanceUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public balanceUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url ="/dashboard.jsp";
		
		//retrieveing an already started session
		HttpSession session = request.getSession(false);
		
		//retrieving data sent via session, using user object because the sent data was sent through as an object bean
		User userLogin = (User) session.getAttribute("userLogin");
		
		
		
		String withdraw =  request.getParameter("withdrawAmount");
		String deposit = request.getParameter("depositAmount");
		
		
		
		DecimalFormat df = new DecimalFormat("#############.##");
		double balanceAmount = Double.parseDouble(userLogin.getBalance());
		
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		
		
		if(withdraw != null) {
			
			
			double withdrawAmount = Double.parseDouble(withdraw);
			
			userLogin.setBalance(df.format(balanceAmount - withdrawAmount));
			
			updateBalance(userLogin.getUsername(),userLogin.getBalance());
		}
		
		else if(deposit !=null) {
			
			double depositAmount = Double.parseDouble(deposit);
			userLogin.setBalance(df.format(balanceAmount + depositAmount));
			
			updateBalance(userLogin.getUsername(),userLogin.getBalance());
			
			//make sure database updates with new value
		}
		
		
		getServletContext()
		.getRequestDispatcher(url)
		.forward(request,response);
		
		
	}
	
	protected void updateBalance(String username, String balance) {
		Connection con;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/onlineBanking?serverTimezone=UTC";
			String dbUsername = "sampleBanker";
			String dbpassword = "password1";
			
			con = DriverManager.getConnection(url,dbUsername,dbpassword);
			Statement s = con.createStatement();
			
			String query = "update userInfo set balance = '"+balance+"' where username='"+username+"'";
			
			
			s.executeUpdate(query);
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

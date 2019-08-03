

import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.regex.*;


@WebServlet("/signUp")
public class signUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public signUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/index.jsp";
		String errorUrl = "/signUp.jsp";
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String vpassword = request.getParameter("vpassword");
		String phone = request.getParameter("phone");
		String dob = request.getParameter("dob");
		String initialBalance = "500.00";
		
		String emailFormat = "[a-zA-Z0-9\\W]+@[a-zA-Z0-9]+\\.[a-zA-Z]+";
		String phoneFormat = "\\d{3}-\\d{3}-\\d{4}$";
		String dobFormat = "\\d{4}-\\d{2}-\\d{2}$";
		
		boolean isEmail = Pattern.matches(emailFormat, email);
		boolean isPhone = Pattern.matches(phoneFormat, phone);
		boolean isDob = Pattern.matches(dobFormat, dob);
		
		/*
		 * Check if any value is null
		 */
		if(fname == "" || lname == "" || email == "" || username == "" || password == "" || vpassword == ""||phone == "" || dob == "") {
			out.println("Please fill out all fields");
			getServletContext()
				.getRequestDispatcher(errorUrl)
				.include(request, response);
		}
		
		else if(!(fname instanceof String) || !(lname instanceof String)) {
			out.println("Your first or last name should be stricly letters");
		}
		
		else if(!isEmail) {
			out.println("Not a proper email format");
			getServletContext()
				.getRequestDispatcher(errorUrl)
				.include(request, response);
		}	
		
		//create an else if for email address
		
		else if(isDuplicate(username)) {
			//if true
			out.println("This username is already in use, please pick another");
			getServletContext()
			.getRequestDispatcher(errorUrl)
			.include(request, response);
			
		}
		
		else if(!password.equals(vpassword)) {
			out.println("Passwords do not match");
			
			getServletContext()
				.getRequestDispatcher(errorUrl)
				.include(request, response);
		}
		
		else if(!isPhone) {
			out.println("Please enter the proper phone format");
			getServletContext()
			.getRequestDispatcher(errorUrl)
			.include(request, response);
		}
		
		else if(!isDob) {
			out.println("Please enter the correct Date of Birth Format");
			getServletContext()
			.getRequestDispatcher(errorUrl)
			.include(request, response);
		}
		
		else {
			updateDB(fname,lname,email,username,password,phone,dob,initialBalance);
			
			try {
				out.println("<h1>Account created successfully!</h1>");
				
				//Potentially put thank you for signing up email
				
				thankYouEmail(fname,lname,email);
				
				getServletContext()
				.getRequestDispatcher(url)
				//.forward(request, response);
				.include(request, response);
			}
			finally {
				out.close();
			}
			
		}
		
	}
	
	protected boolean isDuplicate(String username) {
		Connection con;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/onlineBanking?serverTimezone=UTC";
			String dbUsername = "sampleBanker";
			String dbpassword = "password1";
			
			con = DriverManager.getConnection(url,dbUsername,dbpassword);
			Statement stmt = con.createStatement();
			
			ResultSet myResult = stmt.executeQuery("select * from userInfo where username='"+username+"'");
			
			if(myResult.next()) {
				return true;
			}
			
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	/*
	 * If this returns false then that means there is a user with this username;
	 */
	protected void updateDB(String fname, String lname, String email, String username, String password, String phone, String dob, String balance) {
		Connection con;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/onlineBanking?serverTimezone=UTC";
			String dbUsername = "sampleBanker";
			String dbpassword = "password1";
			
			con = DriverManager.getConnection(url,dbUsername,dbpassword);
			Statement s = con.createStatement();
			
			String query = "insert into userInfo" + "(first_name, last_name, email, phone, username, password, date_of_birth, balance)" + "values ('"+fname+"','"+lname+"','"+email+"','"+phone+"','"+username+"','"+password+"','"+dob+"','"+balance+"')";
			
			s.executeUpdate(query);
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void thankYouEmail(String fname, String lname, String userEmail) {
		//set properties
				Properties props = new Properties();
				String fromEmail = "onlineBankingProject2@gmail.com";
				String emailPassword = "onlineBanking11!";
				String subject = "Signing Up";
				
				
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth","true");
				props.put("mail.smtp.port", "465");
				
				//get mail session
				Session mailSession = Session.getDefaultInstance(props,
						new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmail,emailPassword);
					}
				});
				
				//compose message
				try {
					MimeMessage message = new MimeMessage(mailSession);
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
					message.setSubject(subject);
					message.setText("Thank you " + fname +" "+lname + " for signing up ");
					
					//send message
					Transport.send(message);
					
					System.out.println("message send");
				}
				
				catch(MessagingException e) {
					e.printStackTrace();
				}
				
	}

}

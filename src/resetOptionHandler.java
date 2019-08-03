

import java.io.IOException;

//import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineBanking.User;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.*;



@WebServlet("/resetOptionHandler")
public class resetOptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public resetOptionHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/index.jsp";
		String errorUrl = "/loginResetOption.jsp";
		HttpSession session = request.getSession(false);
		
		User userLogin = (User) session.getAttribute("userLogin"); //use this to send to customers email or phone number
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		
		String resetOption = request.getParameter("reset");
		
		if(resetOption == null) {
			out.println("Please make a choice");
			getServletContext()
				.getRequestDispatcher(errorUrl)
				.include(request,response);
		}
		
		else if(resetOption.contentEquals("email")) {
			//email api
			System.out.println("You picked email");
			sendEmail(userLogin.getEmail(), userLogin.getUsername(), userLogin.getPassword());
			getServletContext()
				.getRequestDispatcher(url)
				.forward(request, response);
			
		}
		
	
	}
	
	protected void sendEmail(String userEmail, String username, String password) {
		//set properties
		Properties props = new Properties();
		String fromEmail = "onlineBankingProject2@gmail.com";
		String emailPassword = "onlineBanking11!";
		String subject = "Account Information";
		
		
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
			message.setText("Your username is: " + username + " and your password is: " + password);
			
			//send message
			Transport.send(message);
			
			System.out.println("message send");
		}
		
		catch(MessagingException e) {
			e.printStackTrace();
		}
		
	}

}



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineBanking.User;


@WebServlet("/logOut")
public class logOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public logOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		String url ="/index.jsp";
		
		
		//User userLogin = (User) session.getAttribute("userLogin");
		
		session.removeAttribute("userLogin");
		session.invalidate();
		
		getServletContext()
		.getRequestDispatcher(url)
		.forward(request, response);
		
		
		
		//Make sure a user cannot back into the page
		
	}

}

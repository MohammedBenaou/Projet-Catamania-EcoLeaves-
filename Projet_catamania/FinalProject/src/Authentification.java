
import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Authentification extends HttpServlet {       

	private Connexion con;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
/*		String driver = config.getInitParameter("driver");
		String url = config.getInitParameter("url");
		String bdLogin = config.getInitParameter("bdLogin");
		String bdPassword = config.getInitParameter("bdPassword");*/
		con = new Connexion();
		//System.out.println("connected *********************************");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String pw=request.getParameter("password");
		String email=request.getParameter("email");
		
		Client c = con.getClient(login, pw);
		if(email !=null)
		{
			con.insertClient(login, pw,email);
		}
		
		PrintWriter out = response.getWriter();
		
		if (c==null) {
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			rs.forward(request, response);
			
		} else {
			//out.println("Authetification réussie");
			//out.println("Login : "+c.getLogin());	
			//request.setAttribute("login", login);
			HttpSession session =request.getSession();
            session.setAttribute("login", login);
			RequestDispatcher rs = request.getRequestDispatcher("index2.jsp");
			rs.forward(request, response);
		}
	}

}

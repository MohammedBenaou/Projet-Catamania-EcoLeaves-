
import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Events extends HttpServlet {       

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
		String nom = request.getParameter("nom");
		String description=request.getParameter("description");
		String date=request.getParameter("date");
		String adresse=request.getParameter("adresse");
		String ville=request.getParameter("ville");
		

		if(nom !=null)
		{
			con.insertEvent(nom, description, date, adresse, ville);
			RequestDispatcher rs = request.getRequestDispatcher("events_list.jsp");
			rs.forward(request, response);
		}
		
		PrintWriter out = response.getWriter();
		
/*		if (c==null) {
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			rs.forward(request, response);
			
		} else {
			//out.println("Authetification réussie");
			//out.println("Login : "+c.getLogin());	
			RequestDispatcher rs = request.getRequestDispatcher("index2.html");
			rs.forward(request, response);
		}*/
	}

}

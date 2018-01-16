import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {
	
	private Connection con;

	public Connexion() {
		
        try {
        	Class.forName("com.mysql.jdbc.Driver");  
        	con=DriverManager.getConnection(  
        	"jdbc:mysql://localhost:3306/tp2","root","");
        	Statement stmt=con.createStatement(); 
        	ResultSet rs1=stmt.executeQuery("select * from client2");  
			while(rs1.next())  
			System.out.println(rs1.getString(1)+"  "+rs1.getString(2)+"  "+rs1.getString(3)+"  "+rs1.getString(4)+"  "+rs1.getString(5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connexion(String driver, String url, String bdLogin, String bdPassword) {
			
	        try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,bdLogin,bdPassword);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public Client getClient(String login, String password) {
		PreparedStatement ps;
		System.out.println("i'm in getClient");
		try {
			System.out.println("i'm in Try getClient");
			System.out.println(con);
			ps = con.prepareStatement("select * from client where login=? and password=?");
			System.out.println("i passed prepareStatment");
			ps.setString(1, login);
	        ps.setString(2, password);
	        ResultSet rs =ps.executeQuery();
	        if (rs.next()){
	        	Client c = new Client(login, password);
	        	return c;
	        } else {
	        	return null;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
        
	}
	
	public void insertClient(String login, String password,String email) {
		Statement stmt = null;
		System.out.println("i'm in insertClient");
		try {
			System.out.println("i'm in Try insertClient");
			System.out.println(con);
			stmt = con.createStatement();
			//String sql = "INSERT INTO client (login,password,email) VALUES( ?, ?, ?)";
			String sql = "INSERT INTO client"
					+ "(login, password, email) VALUES ('"+login+"','"+password+"','"+email+"')";
			//ps = con.prepareStatement("select * from client where login=? and password=?");
			stmt.executeUpdate(sql);
			System.out.println("i passed prepareStatment");
/*			ps.setString(1, login);
	        ps.setString(2, password);
	        ResultSet rs =ps.executeQuery();
	        if (rs.next()){
	        	Client c = new Client(login, password);
	        	return c;
	        } else {
	        	return null;
	        }*/
		} catch (SQLException e) {
			e.printStackTrace();
			//return null;
		}
		
        
	}
	
	public void insertEvent(String nom, String description,String date,String adresse, String ville) {
		Statement stmt = null;
		System.out.println("i'm in insertClient");
		try {
			System.out.println("i'm in Try insertClient");
			System.out.println(con);
			stmt = con.createStatement();
			//String sql = "INSERT INTO client (login,password,email) VALUES( ?, ?, ?)";
			String sql = "INSERT INTO events"
					+ "(nom, description, date, adresse, ville) VALUES ('"+nom+"','"+description+"','"+date+"','"+adresse+"','"+ville+"')";
			//ps = con.prepareStatement("select * from client where login=? and password=?");
			stmt.executeUpdate(sql);
			System.out.println("i passed prepareStatment");
/*			ps.setString(1, login);
	        ps.setString(2, password);
	        ResultSet rs =ps.executeQuery();
	        if (rs.next()){
	        	Client c = new Client(login, password);
	        	return c;
	        } else {
	        	return null;
	        }*/
		} catch (SQLException e) {
			e.printStackTrace();
			//return null;
		}
		
        
	}
	
	public void insertTroc(String nom, String categorie,String description, String photo, String creator) {
		Statement stmt = null;
		System.out.println("i'm in insertClient");
		try {
			System.out.println("i'm in Try insertClient");
			System.out.println(con);
			stmt = con.createStatement();
			//String sql = "INSERT INTO client (login,password,email) VALUES( ?, ?, ?)";
			String sql = "INSERT INTO product"
					+ "(nom, categorie, description, photo, creator) VALUES ('"+nom+"','"+categorie+"','"+description+"','"+photo+"','"+creator+"')";
			//ps = con.prepareStatement("select * from client where login=? and password=?");
			stmt.executeUpdate(sql);
			System.out.println("i passed prepareStatment");
/*			ps.setString(1, login);
	        ps.setString(2, password);
	        ResultSet rs =ps.executeQuery();
	        if (rs.next()){
	        	Client c = new Client(login, password);
	        	return c;
	        } else {
	        	return null;
	        }*/
		} catch (SQLException e) {
			e.printStackTrace();
			//return null;
		}
		
        
	}
}

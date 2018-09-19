package it.objectmethod.cercacitta.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.objectmethod.cercacitta.bean.CercaCittaBean;

/**
 * Servlet implementation class AggiungiModificaServlet
 */
public class AggiungiModificaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/world";   
	static final String USER = "root";
	static final String PASS = "root";   
    
    
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("identd")==null) {
		String citta =request.getParameter("citta");
		String countrycode =request.getParameter("countrycode");
		String district =request.getParameter("district");
		int population =Integer.parseInt(request.getParameter("population"));
		Connection conn = null;
		Statement stmt = null;
		
		//  Database credentials
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			
			
				String sql;
			
				sql = "select max(ID) from city";
				ResultSet rs = stmt.executeQuery(sql);
				//STEP 5: Extract data from result set
				while(rs.next()){
					//Retrieve by column name
					int id  = rs.getInt("max(ID)");
					System.out.println(id);
					id++;
					sql="insert into city values("+id+",'"+citta+"','"+countrycode+"','"+district+"',"+population+")";
					stmt = conn.createStatement();
					stmt.executeUpdate(sql);
				}				
			
			
			
			
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		
		}//end try
		System.out.println("Goodbye!");
		
		//request.setAttribute("listacitta", v);
		request.getRequestDispatcher("CercaCitta.jsp").forward(request, response);
		} //request.getparameter
		if (request.getParameter("identd")!=null) {
			int ident= Integer.parseInt(request.getParameter("identd"));
			Connection conn = null;
			Statement stmt = null;
			ArrayList<CercaCittaBean> v = new ArrayList();
			//  Database credentials
			try{
				//STEP 2: Register JDBC driver
				Class.forName("com.mysql.jdbc.Driver");

				//STEP 3: Open a connection
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL,USER,PASS);

				//STEP 4: Execute a query
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				String sql;
				sql = "select * from city where ID ="+ident+"";
				ResultSet rs = stmt.executeQuery(sql);
				//STEP 5: Extract data from result set
				while(rs.next()){
					//Retrieve by column name
					CercaCittaBean ccb = new CercaCittaBean();
					int id = rs.getInt("ID");
					String citta  = rs.getString("name");
					ccb.setId(id);
					ccb.setCitta(citta);
					v.add(ccb);
					System.out.println(citta);
					
				}
				//STEP 6: Clean-up environment
				rs.close();
				stmt.close();
				conn.close();
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}finally{
				//finally block used to close resources
				try{
					if(stmt!=null)
						stmt.close();
				}catch(SQLException se2){
				}// nothing we can do
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					se.printStackTrace();
				}//end finally try
			}//end try
			System.out.println("Goodbye!");
			request.setAttribute("citta",v );
			request.getRequestDispatcher("CercaCittaModifica.jsp").forward(request, response);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

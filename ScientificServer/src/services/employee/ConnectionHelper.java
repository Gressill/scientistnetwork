package services.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.URLDecoder;

public class ConnectionHelper
{	
	private static String ClassString 		= "com.mysql.jdbc.Driver";//"oracle.jdbc.driver.OracleDriver";
	private static String ConnectionString 	= "jdbc:mysql://localhost:3306/employees" ;
	private String UserName 			= "root";
	private String PassWord 			= "uestc";
	
	private String url;
	private static Connection Conn;

	private static ConnectionHelper instance;

	public String getUrl()
	{
		return url;
	}

	private ConnectionHelper(String s)
	{
		try
		{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			String str = URLDecoder.decode(getClass().getClassLoader().getResource("services").toString(),"UTF-8");
			
			str= str.substring(0, str.indexOf("classes/services")); 
			if ( str.startsWith("file:/C:",0))
			{
				
				str=str.substring(6);
			
			}
			
			else
			{
				
				str=str.substring(5);
			
			}
			
			url = "jdbc:derby:" + str + "database/testdrive";
			
			System.out.println("Database url "+url);
			
			//url="jdbc:derby:testdrive";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private ConnectionHelper()
	{

//		try {
//			Class.forName(ClassString);
//			if ((UserName == null) && (PassWord == null)) {
//				Conn = DriverManager.getConnection(ConnectionString);
//			} else {
//				Conn = DriverManager.getConnection(ConnectionString, UserName,
//						PassWord);
//			}
//
//			Stmt = Conn.createStatement();
//			mResult = true;
//		} catch (Exception e) {
//			System.out.println(e.toString()+"\n"+"Error Message: Database Connection Open Error reported in line 56 DatabaseOperation.java");
//			mResult = false;
//		}
		url = ConnectionString;
	}

	public static ConnectionHelper getInstance()
	{
		if (instance == null)
			instance = new ConnectionHelper();
		return instance;
	}

	//打开连接
	public synchronized static Connection OpenConnection() {
		boolean mResult = true;
		try {
			Class.forName(ClassString);
			Conn = DriverManager.getConnection(ConnectionString,"root","uestc");
			mResult = true;
		} catch (Exception e) {
			System.out.println(e.toString()+"\n"+"Error Message: Database Connection Open Error reported in line 71 DatabaseOperation.java");
			mResult = false;
		}
		return (Conn);
	}
	
	public static Connection getConnection() throws java.sql.SQLException
	{
		return OpenConnection();
		//return DriverManager.getConnection(getInstance().getUrl());
	}

	public static void closeConnection(Connection c)
	{
		try
		{
			if (c != null)
			{
			    c.close();
            }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}

package libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * use to operation database
 * @author yufei
 *
 */

public class DatabaseOperation {

	private String ClassString		 	= null;
	private String ConnectionString 		= null;
	private String UserName 				= null;
	private String PassWord 				= null;

	private Connection Conn;
	private Statement Stmt;

	public DatabaseOperation() {
		//From System.xml
		ClassString 		= "com.mysql.jdbc.Driver";//"oracle.jdbc.driver.OracleDriver";
		//ConnectionString 	= "jdbc:mysql://"+Constant.DB_IP+":3306/" + Constant.DB_DATABASE;//"jdbc:oracle:thin:@192.168.103.171:1521:jstrd";
		ConnectionString 	= "jdbc:mysql://localhost:3306/employees";
		UserName 			= "root";
		PassWord 			= "uestc";

		//For MySQL Driver
		//ClassString="org.gjt.mm.mysql.Driver";
		//ConnectionString="jdbc:mysql://localhost/softforum?user=&password=&useUnicode=true&characterEncoding=8859_1";
		//ClassString="com.mysql.jdbc.Driver";
		//ConnectionString="jdbc:mysql://localhost/dbdemo?user=root&password=&useUnicode=true&characterEncoding=gb2312";
	}

	//打开连接
	public synchronized boolean OpenConnection() {
		boolean mResult = true;
		try {
			Class.forName(ClassString);
			if ((UserName == null) && (PassWord == null)) {
				Conn = DriverManager.getConnection(ConnectionString);
			} else {
				Conn = DriverManager.getConnection(ConnectionString, UserName,
						PassWord);
			}

			Stmt = Conn.createStatement();
			mResult = true;
		} catch (Exception e) {
			System.out.println(e.toString()+"\n"+"Error Message: Database Connection Open Error reported in line 71 DatabaseOperation.java");
			mResult = false;
		}
		return (mResult);
	}

	//关闭数据库连接
	public void CloseConnection() {
		try {
			Stmt.close();
			//SimpleConnetionPool.close();
			Conn.close();
			if (Conn.isClosed()) {
				System.out.println("we close!!!");
			}
			//Conn = null;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.toString()+"\n"+"Error Message: Database Connection Close Error reported in line 69 DatabaseOperation.java");
		}
	}

	//获取当前时间(JAVA)
	public String GetDateTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mDateTime = formatter.format(cal.getTime());
		return (mDateTime);
	}

	//获取当前时间(T-SQL)
	public java.sql.Date GetDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String mDateTime = formatter.format(cal.getTime());
		return (java.sql.Date.valueOf(mDateTime));
	}

	//生成新的ID
	public int GetMaxID(String vTableName, String vFieldName) {
		int mResult = 0;
		boolean mConn = true;
		String mSql = new String();
		mSql = "select max(" + vFieldName + ")+1 as MaxID from " + vTableName;
		try {
			if (Conn != null) {
				mConn = Conn.isClosed();
			}
			if (mConn) {
				OpenConnection();
			}

			ResultSet result = ExecuteQuery(mSql);
			if (result.next()) {
				mResult = result.getInt("MaxID");
			}
			result.close();

			if (mConn) {
				CloseConnection();
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return (mResult);
	}
	
	//取一条记录
	public String getOneRecord(String sqlString) {
		String mResult = null;
		boolean mConn = true;
		//String mSql = new String();
		//mSql = "select max(" + vFieldName + ")+1 as MaxID from " + vTableName;
		try {
			if (Conn != null) {
				mConn = Conn.isClosed();
			}
			if (mConn) {
				OpenConnection();
			}

			ResultSet result = ExecuteQuery(sqlString);
			if (result.next()) {
				mResult = result.getString(1);
			}
			result.close();

			if (mConn) {
				CloseConnection();
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return (mResult);
	}

	//数据检索
	public ResultSet ExecuteQuery(String sqlString) {
		ResultSet result = null;
		try {
			result = Stmt.executeQuery(sqlString);
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("ExecuteQuery failed "+sqlString);
			FileOperate.writeWithFileWrite(Constant.LOG_PATH, sqlString+"\r\n");
		}
		return (result);
	}

	//数据更新(增、删、改)
	public int ExecuteUpdate(String sqlString) {
		int result = 0;
		try {
			result = Stmt.executeUpdate(sqlString);
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("ExecuteUpdate failed "+sqlString);
			FileOperate.writeWithFileWrite(Constant.LOG_PATH, sqlString+"\r\n");
		}
		return (result);
	}
	
	public int emptyTable(String tableName) {
		int result = 0;
		try {
			result = Stmt.executeUpdate("TRUNCATE "+tableName);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return (result);
	}
	
	public static boolean testConnection(){
		DatabaseOperation databaseOperation = new DatabaseOperation();
		if(databaseOperation.OpenConnection()){
			System.out.println("System Msgs: Server has connected to the mysql database.");
			databaseOperation.CloseConnection();
			return true;
		}else{
			System.out.println("System Msgs: Server has failed to connect to the mysql database.");
			return false;
		}
		
	}
	public static void main(String[] args) {
		testConnection();
	}
}


package libs;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author zico this is the Constant file
 */
public class Constant {

	// database string
	public static String DB_DATABASE;
	public static String DB_USER_NAME;
	public static String DB_PASSWORD;
	public static String DB_TABLE;
	public static String DB_COLUMN;
	public static int port;
	public static String DB_IP;
	public static String LOG_PATH = "C:/errorsql.txt";
	public static int MAX_ID = 104202;
	public static int START_ID = 104202;
	public static int START_ID_TWO = 0;
	public static int MAX_COUNT = 10169130;
	
	public static String PATH = "D:/wikidata/Data/";

	/**
	 * this function use to read the config.xml file to initial the game
	 * parameter,all this parameter in Constant file
	 * 
	 */
	public static boolean initGameFromXml() {

		ArrayList<Double> parameterList = null;
		// long lasting = System.currentTimeMillis();
		try {
			//get path
			String userdirString = System.getProperty("user.dir");
			File configfile = new File(userdirString+"/config.xml");
			System.out.println(userdirString);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(configfile);
			Element root = doc.getRootElement();
			port = Integer.parseInt(root.element("port").getTextTrim());
			DB_DATABASE = root.element("database").getTextTrim();
			DB_TABLE = root.element("databasetable").getTextTrim();
			DB_COLUMN = root.element("tablecolumn").getTextTrim();
			DB_USER_NAME = root.element("databaseusername").getTextTrim();
			DB_PASSWORD = root.element("databasepassword").getTextTrim();			
			DB_IP = root.element("databaseip").getTextTrim();
			START_ID = Integer.parseInt(root.element("startid").getTextTrim());
			//LOG_PATH = root.element("logpath").getTextTrim();
			//System.out.println("port:" + port + "\ndatabase" + DB__DATABASE+ "\nusername:" + DB_USER_NAME + "\npassword:" + DB_PASSWORD);
			System.out.println("System Msgs: Load config file succeed. The config argument is: ");
			System.out.println("DATABASE: "+DB_DATABASE+"\ndb_table: "+DB_TABLE+"\nDB_USER_NAME: "+DB_USER_NAME+"\nport: "+port+"\ndb_ip: "+DB_IP);
			return true;
		} catch (Exception e) {
			System.out.println("System Msgs: Load config file error:"+e.getMessage());
			return false;
		}
	}

}

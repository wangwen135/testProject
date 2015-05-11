package com.oracleTest;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.SortedMap;
import java.util.Map.Entry;

import oracle.sql.CharacterSet;



public class Test1 {

	/** Oracle数据库连接URL */
	private final static String DB_URL = "jdbc:oracle:thin:@10.2.1.104:1521:accs";

	/** Oracle数据库连接驱动 */
	private final static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** 数据库用户名 */
	private final static String DB_USERNAME = "accstr";

	/** 数据库密码 */
	private final static String DB_PASSWORD = "accstr";

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		/** 声明Connection连接对象 */
		Connection conn = null;
		try {
			/** 使用Class.forName()方法自动创建这个驱动程序的实例且自动调用DriverManager来注册它 */
			Class.forName(DB_DRIVER);
			/** 通过DriverManager的getConnection()方法获取数据库连接 */
			conn = DriverManager
					.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param connect
	 */
	public void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				/** 判断当前连接连接对象如果没有被关闭就调用关闭方法 */
				if (!conn.isClosed()) {
					conn.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void select(){
		Connection conn = null;
		try {
			conn = getConnection();
			Statement statement = conn.createStatement();
			
			String sql = "select  utl_raw.cast_to_varchar2('b22901bf2daa9f57bb4ce30c04e77efe3ad52befae35bb48eee523f2ad55140d') from dual";
			//sql = "select sendText from t_test2 t";
			//sql = new String(sql.getBytes("GBK"), "ISO-8859-1");
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				InputStream inputStream = rs.getBinaryStream(1);
//				//ByteArrayInputStream bainStream = new ByteArrayInputStream();
				int i = inputStream.available();
//				
				byte[] b = new byte[i];
				inputStream.read(b);
				
				System.out.println(new String(b,"MS950"));
				
				
				String s = CharacterSet.AL32UTF8ToString(b, 0, i, true);
				System.out.println(s);
				
				System.out.println(new String( s.getBytes("ISO-8859-1"),"UTF-8"));
				
//				SortedMap<String, Charset> sortedMap = Charset.availableCharsets();
				
//				for(Entry<String, Charset> e: sortedMap.entrySet()){
//					System.out.println(new String(b,e.getValue()));
//				}
				
//				while ((inputStream.read(b)) != -1) {
//					System.out.println(new String(b).toString());
//				}
				
				System.out.println(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			closeConnection(conn);
		}
	}
	
	public static void main(String[] args) {
		Test1 test1 = new Test1();
		test1.select();
		
		
	}
}

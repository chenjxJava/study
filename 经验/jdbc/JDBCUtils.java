package com.freemark.utils;

/**
 * Created by admin on 2017-06-30.
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//jdbc链接数据库,获取表名，字段名和数据
public class JDBCUtils {

	private static Map<String, Map<String, ArrayList>> resultMap = new HashMap<String, Map<String, ArrayList>>();

	public static void main(String[] args) throws Exception {

		Connection conn = getConnection();
		// 获取所有表名
		Statement statement = conn.createStatement();

		Map<String, Map<String, ArrayList>> result = getTables(conn);
		System.out.println(result);

		ResultSet resultSet = statement
			.executeQuery("select * from users");
		// 获取列名
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 0; i < metaData.getColumnCount(); i++) {
			// resultSet数据下标从1开始
			String columnName = metaData.getColumnName(i + 1);
			int type = metaData.getColumnType(i + 1);
			if (Types.INTEGER == type) {
				// int
			} else if (Types.VARCHAR == type) {
				// String
			}
			System.out.print(columnName + "\t");
		}
		System.out.println();
		// 获取数据
		while (resultSet.next()) {
			for (int i = 0; i < metaData.getColumnCount(); i++) {
				// resultSet数据下标从1开始
				System.out.print(resultSet.getString(i + 1) + "\t");
			}
			System.out.println();

		}
		statement.close();
		conn.close();

	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/shiro?useUnicode=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true";
		String user = "root";
		String password = "root";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);
		if (!conn.isClosed())
			System.out.println("Succeeded connecting to the Database!");
		else
			System.err.println("connect filed");
		return conn;
	}

	public static String convertDatabaseCharsetType(String in, String type) {
		String dbUser;
		if (in != null) {
			if (type.equals("oracle")) {
				dbUser = in.toUpperCase();
			} else if (type.equals("postgresql")) {
				dbUser = "public";
			} else if (type.equals("mysql")) {
				dbUser = null;
			} else if (type.equals("mssqlserver")) {
				dbUser = null;
			} else if (type.equals("db2")) {
				dbUser = in.toUpperCase();
			} else {
				dbUser = in;
			}
		} else {
			dbUser = "public";
		}
		return dbUser;
	}

	/**
	 * 获取数据库中表的原始信息
	 *
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Map<String, ArrayList>> getTables(Connection conn) throws SQLException {
		DatabaseMetaData dbMetData = conn.getMetaData();
		// mysql convertDatabaseCharsetType null
		ResultSet rs = dbMetData.getTables(null,
			convertDatabaseCharsetType("root", "mysql"), null,
			new String[]{"TABLE", "VIEW"});
		while (rs.next()) {
			HashMap<String, ArrayList> tableMap = new HashMap<String, ArrayList>();
			if (rs.getString(4) != null
				&& (rs.getString(4).equalsIgnoreCase("TABLE") || rs
				.getString(4).equalsIgnoreCase("VIEW"))) {
				String tableName = rs.getString(3).toLowerCase();
				//to get the list of tableName
				//tableList.add(tableName);

				// 根据表名提前表里面信息：
				ResultSet colRet = dbMetData.getColumns(null, "%", tableName,
					"%");
				while (colRet.next()) {
					int i = 1;
					ArrayList<String> columnList = new ArrayList<String>();
					String columnName = colRet.getString("COLUMN_NAME");
					String columnType = colRet.getString("TYPE_NAME");
					int datasize = colRet.getInt("COLUMN_SIZE");
					int digits = colRet.getInt("DECIMAL_DIGITS");
					String isNullable = colRet.getString("IS_NULLABLE");
					String remarks = colRet.getString("REMARKS");

					//to get the diff table's details of diff db
					columnList.add(columnName);
					columnList.add(columnType + "(" + datasize + "," + digits + ")");
					columnList.add(isNullable);
					columnList.add(remarks);
					tableMap.put(String.valueOf(i++), columnList);
				}
				resultMap.put(tableName, tableMap);
			}
		}
		return resultMap;
	}
}

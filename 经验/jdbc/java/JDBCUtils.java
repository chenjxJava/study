/**
 * Created by admin on 2017-06-30.
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import domain.Coloums;
import domain.Table;


//jdbc链接数据库,获取表名，字段名和数据
public class JDBCUtils {

	private static Map<String, Map<String, ArrayList>> resultMap = new HashMap<String, Map<String, ArrayList>>();

	public static void main(String[] args) throws Exception {
		Map<String, Table> table = getTables(getConnection());
		System.out.println(table);
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/shiro?useUnicode=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true";
		String user = "root";
		String password = "root";
		Properties props =new Properties();
		Class.forName(driver);
		props.setProperty("user", user);
		props.setProperty("password", password);
		props.setProperty("remarks", "true"); //设置可以获取remarks信息
		props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息

		Connection conn = DriverManager.getConnection(url, props);
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
	public static Map<String, Table> getTables(Connection conn) throws SQLException {
		DatabaseMetaData dbMetData = conn.getMetaData();
		// mysql convertDatabaseCharsetType null
		ResultSet rs = dbMetData.getTables(null,
			convertDatabaseCharsetType("root", "mysql"), null,
			new String[]{"TABLE", "VIEW"});
		HashMap<String, Table> result = new HashMap<String, Table>();
		while (rs.next()) {
			Table table = new Table();
			if (rs.getString(4) != null
				&& (rs.getString(4).equalsIgnoreCase("TABLE") || rs
				.getString(4).equalsIgnoreCase("VIEW"))) {
				String tableName = rs.getString("TABLE_NAME").toLowerCase();
				String remark = rs.getString("REMARKS");

				//to get the list of tableName
				table.setTableName(tableName);
				table.setTableDescption(remark);
				// 根据表名提前表里面信息：
				ResultSet colRet = dbMetData.getColumns(null, "%", tableName,
					"%");
				ArrayList<Coloums> columnList = new ArrayList<>();
				while (colRet.next()) {
					int i = 1;
					Coloums coloums = new Coloums();
					String columnName = colRet.getString("COLUMN_NAME");
					String columnType = colRet.getString("TYPE_NAME");
					int datasize = colRet.getInt("COLUMN_SIZE");
					int digits = colRet.getInt("DECIMAL_DIGITS");
					String isNullable = colRet.getString("IS_NULLABLE"); //[ 0:'YES'; 1:'NO'; 2:''; ]
					String remarks = colRet.getString("REMARKS");
					String columnDef = colRet.getString("COLUMN_DEF");  //默认值
					int ordinalPosition = colRet.getInt("ORDINAL_POSITION");   //表中列的索引（从1开始）
					//String isAutoincrement = rs.getString("IS_AUTOINCREMENT");	//

					//to get the diff table's details of diff db
					coloums.setCloumnsName(columnName);
					coloums.setType(columnType + "(" + datasize + "," + digits + ")");
					coloums.setIsNull(isNullable);
					coloums.setComment(remarks);
					coloums.setDefaults(columnDef);
					//coloums.setIsAutoIncrement(isAutoincrement);
					columnList.add(coloums);
				}
				table.setColoums(columnList);
				result.put(tableName, table);
			}
		}
		return result;
	}

	/**
	 * @Description: 获取表主键信息
	 * @author: chenzw
	 * @CreateTime: 2014-1-27 下午5:12:53
	 * @throws
	 */
	public static void getPrimaryKeysInfo() throws SQLException, ClassNotFoundException {
		Connection conn =  getConnection();
		ResultSet rs = null;
		try{
			DatabaseMetaData dbmd = conn.getMetaData();
			/**
			 * 获取对给定表的主键列的描述
			 * 方法原型:ResultSet getPrimaryKeys(String catalog,String schema,String table);
			 * catalog - 表所在的类别名称;""表示获取没有类别的列,null表示获取所有类别的列。
			 * schema - 表所在的模式名称(oracle中对应于Tablespace);""表示获取没有模式的列,null标识获取所有模式的列; 可包含单字符通配符("_"),或多字符通配符("%");
			 * table - 表名称;可包含单字符通配符("_"),或多字符通配符("%");
			 */
			rs = dbmd.getPrimaryKeys(null, null, "roles_permissions");

			while (rs.next()){
				String tableCat = rs.getString("TABLE_CAT");  //表类别(可为null)
				String tableSchemaName = rs.getString("TABLE_SCHEM");//表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
				String tableName = rs.getString("TABLE_NAME");  //表名
				String columnName = rs.getString("COLUMN_NAME");//列名
				short keySeq = rs.getShort("KEY_SEQ");//序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
				String pkName = rs.getString("PK_NAME"); //主键名称

				System.out.println(tableCat + " - " + tableSchemaName + " - " + tableName + " - " + columnName + " - "
					+ keySeq + " - " + pkName);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtils.close(rs,conn);
		}
	}

	//关闭连接
	public static void close(Object o){
		if (o == null){
			return;
		}
		if (o instanceof ResultSet){
			try {
				((ResultSet)o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(o instanceof Statement){
			try {
				((Statement)o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Connection){
			Connection c = (Connection)o;
			try {
				if (!c.isClosed()){
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs, Statement stmt,
													 Connection conn){
		close(rs);
		close(stmt);
		close(conn);
	}

	public static void close(ResultSet rs,
													 Connection conn){
		close(rs);
		close(conn);
	}
}

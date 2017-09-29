package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-07-14.
 */
public class Table {
	private static  int num = 1;

	public Table() {
		num++;
		this.index = num;
	}
	private int index;				//索引
	private String tableName;		//表名
	private String tableDescption;		//表名
	private List<Coloums> coloums = new ArrayList<Coloums>();

	public String getTableDescption() {
		return tableDescption;
	}

	public void setTableDescption(String tableDescption) {
		this.tableDescption = tableDescption;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Coloums> getColoums() {
		return coloums;
	}

	public void setColoums(List<Coloums> coloums) {
		this.coloums = coloums;
	}

	@Override
	public String toString() {
		return "Table{" +
			"index=" + index +
			", tableName='" + tableName + '\'' +
			", coloums=" + coloums +
			'}';
	}
}

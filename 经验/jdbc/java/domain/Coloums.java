package domain;

/**
 * Created by admin on 2017-07-14.
 */
/**
 * 表字段
 */
public class Coloums {
	private String cloumnsName;	//字段名
	private String type;				//类型
	private String isPrimaryKey;//主键
	private String isNull;			//非空
	private String isAutoIncrement;			//主键增长
	private String comment;			//注释
	private String defaults;			//默认值

	public String getIsAutoIncrement() {
		return isAutoIncrement;
	}

	public void setIsAutoIncrement(String isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}

	public String getDefaults() {
		return defaults;
	}

	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

	public String getCloumnsName() {
		return cloumnsName;
	}

	public void setCloumnsName(String cloumnsName) {
		this.cloumnsName = cloumnsName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public String getIsPrimaryKey() {
		return isPrimaryKey;
	}

	public void setIsPrimaryKey(String isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	@Override
	public String toString() {
		return "Coloums{" +
			"cloumnsName='" + cloumnsName + '\'' +
			", type='" + type + '\'' +
			", isPrimaryKey='" + isPrimaryKey + '\'' +
			", isNull='" + isNull + '\'' +
			", isAutoIncrement='" + isAutoIncrement + '\'' +
			", comment='" + comment + '\'' +
			", defaults='" + defaults + '\'' +
			'}';
	}
}

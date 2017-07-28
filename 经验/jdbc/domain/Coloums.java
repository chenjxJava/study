package com.freemark.domain;

/**
 * Created by admin on 2017-07-14.
 */
/**
 * 表字段
 */
public class Coloums {
	private String cloumnsName;	//字段名
	private String type;				//类型值
	private String comment;			//注释
	private String isNull;			//
	private String isPrimaryKey;//主键

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
			", comment='" + comment + '\'' +
			", isNull='" + isNull + '\'' +
			", isPrimaryKey='" + isPrimaryKey + '\'' +
			'}';
	}
}

package com.yl.entity.vo.base;

import java.io.Serializable;
import java.util.List;

/**
 * 操作结果-带实体信息
 * @author Tolk
 */
public class ResultEntity<T>  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//是否成功
	private boolean asSuccess = false;
	//返回Code
	private String code;
	//消息
	private String message;
	//数据
	private T entity;
	
	private List<T> entityList;
	
	public ResultEntity(String message) {
		super();
		this.message = message;
	}
	public ResultEntity(boolean asSuccess, String message) {
		super();
		this.asSuccess = asSuccess;
		this.message = message;
	}
	public ResultEntity(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public ResultEntity(boolean asSuccess, String code, String message) {
		super();
		this.asSuccess = asSuccess;
		this.code = code;
		this.message = message;
	}
	
	public ResultEntity(boolean asSuccess, String code, String message, T entity) {
		super();
		this.asSuccess = asSuccess;
		this.code = code;
		this.message = message;
		this.entity = entity;
	}
	public ResultEntity(boolean asSuccess, String code, String message, T entity, List<T> entityList) {
		super();
		this.asSuccess = asSuccess;
		this.code = code;
		this.message = message;
		this.entity = entity;
		this.entityList = entityList;
	}
	public boolean asSuccess() {
		return asSuccess;
	}
	public void setSuccess(boolean asSuccess) {
		this.asSuccess = asSuccess;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	public List<T> getEntityList() {
		return entityList;
	}
	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}
	
	
	
}

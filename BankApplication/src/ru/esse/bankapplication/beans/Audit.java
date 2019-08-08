package ru.esse.bankapplication.beans;

public class Audit {
	
	public static final String OBJECT_TYPE_CLIENT ="client";
	public static final String OBJECT_TYPE_ACCOUNT = "account";
	public static final String AUDIT_ACTION_CREATE = "create";
	public static final String AUDIT_ACTION_UPDATE = "update";
	public static final String AUDIT_ACTION_DELETE = "delete";
	
	private String object_id;
	private String object_type;
	private String action_date;
	private String action_id;
	private String action;
	private String new_value;
	
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public String getObject_type() {
		return object_type;
	}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}
	public String getAction_date() {
		return action_date;
	}
	public void setAction_date(String action_date) {
		this.action_date = action_date;
	}
	public String getAction_id() {
		return action_id;
	}
	public void setAction_id(String action_id) {
		this.action_id = action_id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getNew_value() {
		return new_value;
	}
	public void setNew_value(String new_value) {
		this.new_value = new_value;
	}
	
}

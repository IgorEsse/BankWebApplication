package ru.esse.bankapplication.beans;

public class Client {
	    
	private String id;
	private String username;
	private String password;
	private String birth_date;
	private String name;
	private String surname;
	
	public Client() {
        
	}

	public Client(String id, String username, String password, String birth_date, String name, String surname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.birth_date = birth_date;
		this.name = name;
		this.surname = surname;
	}

	public Client(String username, String password, String birth_date, String name, String surname) {
		super();
		this.username = username;
		this.password = password;
		this.birth_date = birth_date;
		this.name = name;
		this.surname = surname;
	}

	public String getId() {
		return id;
	}
	 
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	 
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}

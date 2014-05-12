package database;

import java.io.Serializable;

public class UserBean implements Serializable{
	private static final long serialVersionUID = -1177810759603877895L;
	private int id;
	private String firstName;
	private String surname;
	
	public UserBean() {}

	public UserBean(String firstName, String surname) {
		this.firstName = firstName;
		this.surname = surname;
	}
	
	public UserBean(int id, String firstName, String surname) {
		this(firstName, surname);
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
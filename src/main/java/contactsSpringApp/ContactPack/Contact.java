package contactsSpringApp.ContactPack;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class Contact {
	@Id
	private String id;
	private String fname;
	private String lname;
	private String tel;
	private String description;

	public Contact() {
	}

	public Contact(String id, String fname, String lname, String tel, String description) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.tel = tel;
		this.description = description;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFName() {
		return fname;
	}

	public void setFName(String name) {
		this.fname = name;
	}

	public String getLName() {
		return lname;
	}

	public void setLName(String name) {
		this.lname = name;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", fname=" + fname + ", lname=" + lname + ", tel=" + tel + ", description="
				+ description + "]";
	}

}

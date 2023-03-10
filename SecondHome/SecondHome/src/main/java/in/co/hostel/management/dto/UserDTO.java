package in.co.hostel.management.dto;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class UserDTO extends BaseDTO {

	@Column(name = "FIRST_NAME", length = 225)
	private String firstName;

	@Column(name = "LAST_NAME", length = 225)
	private String lastName;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD", length = 225)
	private String password;

	private String confirmPassword;

	@Column(name = "ROLE_ID", length = 225)
	private long roleId;

	@Column(name = "EMAIL_ID", length = 225)
	private String emailId;
	
	@Column(name = "CONTACT_NO", length = 225)
	private String contactNo;
	
	@Column(name = "GENDER", length = 225)
	private String gender;
	
	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name = "ADDRESS", length = 225)
	private String address;
	
	@Column(name = "CITY", length = 225)
	private String city;
	
	
	 
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private Set<ApplicationDTO> applications;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private Set<AllotmentDTO> allotments;
	
	
	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return firstName + " " + lastName;
	}

}

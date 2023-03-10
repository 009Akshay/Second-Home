package in.co.hostel.management.form;
import javax.validation.constraints.NotEmpty;

import in.co.hostel.management.dto.BaseDTO;
import in.co.hostel.management.dto.UserDTO;
import in.co.hostel.management.util.DataUtility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyProfileForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	
	@NotEmpty(message = "LastName is required")
	private String lastName;
	
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "UserName  is required")
	private String userName;
	
	@NotEmpty(message = "Contact No is required")
	private String contactNo;
	
	@NotEmpty(message = "Gender is required")
	private String gender;
	
	@NotEmpty(message = "DOB is required")
	private String dob;
	
	@NotEmpty(message = "Address is required")
	private String address;
	
	@NotEmpty(message = "City is required")
	private String city;
	
	
	public BaseDTO getDTO() {
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setEmailId(email);
		bean.setContactNo(contactNo);
		bean.setGender(gender);
		bean.setUserName(userName);
		bean.setAddress(address);
		bean.setCity(city);
		bean.setDob(DataUtility.getDate(dob));
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	
	public void populate(BaseDTO bDto) {
		UserDTO bean = (UserDTO) bDto;
		id=bean.getId();
		firstName=bean.getFirstName();
		lastName=bean.getLastName();
		email=bean.getEmailId();
		contactNo=bean.getContactNo();
		gender=bean.getGender();
		userName=bean.getUserName();
		city=bean.getCity();
		address=bean.getAddress();
		dob=DataUtility.getDateString(bean.getDob());
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();

	}

	

}

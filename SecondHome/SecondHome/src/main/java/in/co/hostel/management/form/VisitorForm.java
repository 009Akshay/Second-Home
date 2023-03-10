package in.co.hostel.management.form;

import javax.validation.constraints.NotEmpty;

import in.co.hostel.management.dto.BaseDTO;
import in.co.hostel.management.dto.VisitorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitorForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String  name;
	@NotEmpty(message = "Contact No is required")
	private String contactNo;
	@NotEmpty(message = "Student Name is required")
	private String studentName;
	@NotEmpty(message = "Address is required")
	private String address;
	@NotEmpty(message = "Relation is required")
	private String relation;
	@NotEmpty(message = "Purpose is required")
	private String purpose;
	
	
	@Override
	public BaseDTO getDTO() {
		VisitorDTO bean=new VisitorDTO();
		bean.setId(id);
		bean.setName(name);
		bean.setContactNo(contactNo);
		bean.setStudentName(studentName);
		bean.setRelation(relation);
		bean.setAddress(address);
		bean.setPurpose(purpose);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		VisitorDTO bean=(VisitorDTO)bDto;
		id=bean.getId();
		name=bean.getName();
		contactNo=bean.getContactNo();
		studentName=bean.getStudentName();
		address=bean.getAddress();
		relation=bean.getRelation();
		purpose=bean.getPurpose();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}

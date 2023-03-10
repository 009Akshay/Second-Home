package in.co.hostel.management.form;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import in.co.hostel.management.dto.BaseDTO;
import in.co.hostel.management.dto.HostelDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HostelForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;
	@NotEmpty(message = "Type is required")
	private String type;
	@NotEmpty(message = "Contact No is required")
	private String contactNo;
	@NotEmpty(message = "Address is required")
	private String address;
	@NotEmpty(message = "Description is required")
	private String description;
	@NotEmpty(message = "Fee is required")
	private String fee;
	
	
	@Override
	public BaseDTO getDTO() {
		HostelDTO bean=new HostelDTO();
		bean.setId(id);
		bean.setName(name);
		bean.setType(type);
		bean.setContactNo(contactNo);
		bean.setAddress(address);
		bean.setDescription(description);
		bean.setFee(fee);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		HostelDTO bean=(HostelDTO)bDto;
		id=bean.getId();
		name=bean.getName();
		type=bean.getType();
		contactNo=bean.getContactNo();
		address=bean.getAddress();
		description=bean.getDescription();
		fee=bean.getFee();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}

package in.co.hostel.management.form;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import in.co.hostel.management.dto.ApplicationDTO;
import in.co.hostel.management.dto.BaseDTO;
import in.co.hostel.management.dto.HostelDTO;
import in.co.hostel.management.dto.RoomDTO;
import in.co.hostel.management.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationForm extends BaseForm {

	private long userId;
	@Min(value = 1,message ="Hostel is required")
	private long hostelId;
	@Column(name="QUALIFICATION",length = 225)
	private String qualification;
	@Column(name="ADDRESS",length = 225)
	private String address;
	@Column(name="DESCRIPTION",length = 225)
	private String description;
	
	
	@Override
	public BaseDTO getDTO() {
		ApplicationDTO bean=new ApplicationDTO();
		bean.setId(id);
		bean.setHostelId(hostelId);
		bean.setQualification(qualification);
		bean.setAddress(address);
		bean.setDescription(description);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		ApplicationDTO bean=(ApplicationDTO)bDto;
		id=bean.getId();
		hostelId=bean.getHostelId();
		qualification=bean.getQualification();
		address=bean.getAddress();
		description=bean.getDescription();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}

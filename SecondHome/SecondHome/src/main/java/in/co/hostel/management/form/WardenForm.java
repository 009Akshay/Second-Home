package in.co.hostel.management.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import in.co.hostel.management.dto.BaseDTO;
import in.co.hostel.management.dto.WardenDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WardenForm extends BaseForm {

	
	@Min(value = 1,message = "Hostel is required")
	private long hotelId;
	
	@Min(value = 1,message = "Warden is required")
	private long userId;
	
	
	@Override
	public BaseDTO getDTO() {
		WardenDTO bean=new WardenDTO();
		bean.setId(id);
		bean.setHostelId(hotelId);
		bean.setUserId(userId);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		WardenDTO bean=(WardenDTO)bDto;
		id=bean.getId();
		hotelId=bean.getHostelId();
		userId=bean.getUserId();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}

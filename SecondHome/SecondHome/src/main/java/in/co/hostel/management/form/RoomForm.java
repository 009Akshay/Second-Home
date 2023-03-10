package in.co.hostel.management.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import in.co.hostel.management.dto.BaseDTO;
import in.co.hostel.management.dto.RoomDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomForm extends BaseForm {

	@NotEmpty(message = "Room No is required")
	private String roomNo;
	@NotEmpty(message = "Facilities is required")
	private String facilities;
	@NotEmpty(message = "Description is required")
	private String description;
	@Min(value = 1,message = "Hostel is required")
	private long hotelId;
	
	
	@Override
	public BaseDTO getDTO() {
		RoomDTO bean=new RoomDTO();
		bean.setId(id);
		bean.setRoomNo(roomNo);
		bean.setFacilities(facilities);
		bean.setHostelId(hotelId);
		bean.setDescription(description);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		RoomDTO bean=(RoomDTO)bDto;
		id=bean.getId();
		roomNo=bean.getRoomNo();
		facilities=bean.getFacilities();
		hotelId=bean.getHostelId();
		description=bean.getDescription();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}

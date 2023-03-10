package in.co.hostel.management.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import in.co.hostel.management.dto.AllotmentDTO;
import in.co.hostel.management.dto.BaseDTO;
import in.co.hostel.management.dto.RoomDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllotmentForm extends BaseForm {

	@Min(value = 1,message = "Room is required")
	private long roomId;
	private long hotelId;
	private long userId;
	private long applicationId;
	
	
	@Override
	public BaseDTO getDTO() {
		AllotmentDTO bean=new AllotmentDTO();
		bean.setId(id);
		bean.setUserId(userId);
		bean.setRoomId(roomId);
		bean.setHostelId(hotelId);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		AllotmentDTO bean=(AllotmentDTO)bDto;
		id=bean.getId();
		hotelId=bean.getHostelId();
		userId=bean.getUserId();
		roomId=bean.getRoomId();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}

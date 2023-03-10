package in.co.hostel.management.dto;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ALLOTMENT")
@Getter
@Setter
public class AllotmentDTO extends BaseDTO {


	private long hostelId;
	private long userId;
	private long roomId;
	
	@ManyToOne
	@JoinColumn(name="USER",nullable = false)
	private UserDTO user;
	
	@ManyToOne
	@JoinColumn(name="HOSTEL",nullable = false)
	private HostelDTO hostel;
	
	@ManyToOne
	@JoinColumn(name="ROOM",nullable = false)
	private RoomDTO room;
	
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}

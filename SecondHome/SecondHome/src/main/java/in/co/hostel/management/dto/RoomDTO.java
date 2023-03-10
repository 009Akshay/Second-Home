package in.co.hostel.management.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ROOM")
@Setter
@Getter
public class RoomDTO extends BaseDTO {
	
	@Column(name="ROOM_NO",length = 225)
	private String roomNo;
	@Column(name="FACILITIES",length = 225)
	private String facilities;
	@Column(name="DESCRIPTION",length = 225)
	private String description;
	
	private long hostelId;
	
	@ManyToOne
	@JoinColumn(name="HOSTEL",nullable = false)
	private HostelDTO hostel;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "room"
			+ "")
	private Set<AllotmentDTO> allotments;
			
	@Override
	public String getKey() {
		return String.valueOf(id);
	}
	@Override
	public String getValue() {
		return roomNo;
	}

}

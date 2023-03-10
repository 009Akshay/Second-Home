package in.co.hostel.management.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="HOSTEL")
@Setter
@Getter
public class HostelDTO extends BaseDTO {
	
	
	@Column(name="NAME",length = 225)
	private String name;
	@Column(name="TYPE",length = 225)
	private String type;
	@Column(name="CONTACT_NO",length = 225)
	private String contactNo;
	@Column(name="ADDRESS",length = 225)
	private String address;
	@Column(name="DESCRIPTION",length = 225)
	private String description;
	@Column(name="FEE",length = 225)
	private String fee;
	
	
	

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "hostel")
	private Set<RoomDTO> rooms;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "hostel")
	private Set<WardenDTO> wardens;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "hostel")
	private Set<ApplicationDTO> applications;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "hostel")
	private Set<AllotmentDTO> allotments;


	@Override
	public String getKey() {
		return id+"";
	}

	@Override
	public String getValue() {
		return name;
	}

}

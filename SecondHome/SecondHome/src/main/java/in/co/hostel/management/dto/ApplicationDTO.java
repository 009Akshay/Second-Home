package in.co.hostel.management.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="APPLICATION")
@Getter
@Setter
public class ApplicationDTO extends BaseDTO {
	
	private long userId;
	private long hostelId;
	
	@ManyToOne
	@JoinColumn(name="USER",nullable = false)
	private UserDTO user;
	
	@ManyToOne
	@JoinColumn(name="HOSTEL",nullable = false)
	private HostelDTO hostel;
	
	@Column(name="QUALIFICATION",length = 225)
	private String qualification;
	@Column(name="ADDRESS",length = 225)
	private String address;
	@Column(name="DESCRIPTION",length = 225)
	private String description;
	
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

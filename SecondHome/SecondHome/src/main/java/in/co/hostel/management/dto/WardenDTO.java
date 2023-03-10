package in.co.hostel.management.dto;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="WARDEN")
@Getter
@Setter
public class WardenDTO extends BaseDTO {
	
	private long userId;
	private long hostelId;
	
	@ManyToOne
	@JoinColumn(name="WARDEN",nullable = false)
	private UserDTO user;
	
	@ManyToOne
	@JoinColumn(name="HOSTEL",nullable = false)
	private HostelDTO hostel;
	
	

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}

package in.co.hostel.management.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="VISITOR")
@Getter
@Setter
public class VisitorDTO  extends BaseDTO{

	@Column(name="NAME",length = 225)
	private String  name;
	@Column(name="CONTACT_NO",length = 225)
	private String contactNo;
	@Column(name="STUDENT_NAME",length = 225)
	private String studentName;
	@Column(name="ADDRESS",length = 225)
	private String address;
	@Column(name="RELATION",length = 225)
	private String relation;
	@Column(name="PURPOSE",length = 225)
	private String purpose;
	
	
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

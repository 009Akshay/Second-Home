package in.co.hostel.management.dao;

import java.util.List;

import in.co.hostel.management.dto.ApplicationDTO;


public interface ApplicationDAOInt {

	public long add(ApplicationDTO dto);
	
	public void delete(ApplicationDTO dto);
	
	public ApplicationDTO findBypk(long pk);
	
	public ApplicationDTO findByUserId(long userId);
	public ApplicationDTO findByUserIdAndHostel(long userId,long hostelId);
	
	public void update(ApplicationDTO dto);
	
	public List<ApplicationDTO> list();
	
	public List<ApplicationDTO>list(int pageNo,int pageSize);
	
	public List<ApplicationDTO> search(ApplicationDTO dto);
	
	public List<ApplicationDTO> search(ApplicationDTO dto,int pageNo,int pageSize);
	
	
}

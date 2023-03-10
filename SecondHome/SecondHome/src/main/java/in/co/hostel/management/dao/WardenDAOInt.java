package in.co.hostel.management.dao;

import java.util.List;

import in.co.hostel.management.dto.WardenDTO;


public interface WardenDAOInt {

	public long add(WardenDTO dto);
	
	public void delete(WardenDTO dto);
	
	public WardenDTO findBypk(long pk);
	
	public WardenDTO findByUserId(long userId);
	
	public void update(WardenDTO dto);
	
	public List<WardenDTO> list();
	
	public List<WardenDTO>list(int pageNo,int pageSize);
	
	public List<WardenDTO> search(WardenDTO dto);
	
	public List<WardenDTO> search(WardenDTO dto,int pageNo,int pageSize);
	
	
}

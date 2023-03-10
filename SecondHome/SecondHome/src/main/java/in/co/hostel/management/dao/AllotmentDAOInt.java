package in.co.hostel.management.dao;

import java.util.List;

import in.co.hostel.management.dto.AllotmentDTO;


public interface AllotmentDAOInt {

	public long add(AllotmentDTO dto);
	
	public void delete(AllotmentDTO dto);
	
	public AllotmentDTO findBypk(long pk);
	
	public AllotmentDTO findByAllotmentNo(String AllotmentNo);
	
	public void update(AllotmentDTO dto);
	
	public List<AllotmentDTO> list();
	
	public List<AllotmentDTO>list(int pageNo,int pageSize);
	
	public List<AllotmentDTO> search(AllotmentDTO dto);
	
	public List<AllotmentDTO> search(AllotmentDTO dto,int pageNo,int pageSize);
	
	
}

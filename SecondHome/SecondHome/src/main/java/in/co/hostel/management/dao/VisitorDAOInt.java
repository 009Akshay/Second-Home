package in.co.hostel.management.dao;

import java.util.List;

import in.co.hostel.management.dto.VisitorDTO;


public interface VisitorDAOInt {

	public long add(VisitorDTO dto);
	
	public void delete(VisitorDTO dto);
	
	public VisitorDTO findBypk(long pk);
	
	public VisitorDTO findByVisitorNo(String VisitorNo);
	
	public void update(VisitorDTO dto);
	
	public List<VisitorDTO> list();
	
	public List<VisitorDTO>list(int pageNo,int pageSize);
	
	public List<VisitorDTO> search(VisitorDTO dto);
	
	public List<VisitorDTO> search(VisitorDTO dto,int pageNo,int pageSize);
	
	
}

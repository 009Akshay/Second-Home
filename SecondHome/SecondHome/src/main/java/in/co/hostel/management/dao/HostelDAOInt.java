package in.co.hostel.management.dao;

import java.util.List;

import in.co.hostel.management.dto.HostelDTO;



public interface HostelDAOInt {

	public long add(HostelDTO dto);
	
	public void delete(HostelDTO dto);
	
	public HostelDTO findBypk(long pk);
	
	public HostelDTO findByHostelName(String HostelName);
	
	public void update(HostelDTO dto);
	
	public List<HostelDTO> list();
	
	public List<HostelDTO>list(int pageNo,int pageSize);
	
	public List<HostelDTO> search(HostelDTO dto);
	
	public List<HostelDTO> search(HostelDTO dto,int pageNo,int pageSize);
	
	
}

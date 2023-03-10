package in.co.hostel.management.dao;

import java.util.List;

import in.co.hostel.management.dto.RoomDTO;


public interface RoomDAOInt {

	public long add(RoomDTO dto);
	
	public void delete(RoomDTO dto);
	
	public RoomDTO findBypk(long pk);
	
	public RoomDTO findByRoomNo(String roomNo);
	
	public RoomDTO findByRoomNoAndHostelId(String roomNo,long HostelId);
	
	public void update(RoomDTO dto);
	
	public List<RoomDTO> list();
	
	public List<RoomDTO>list(int pageNo,int pageSize);
	
	public List<RoomDTO> search(RoomDTO dto);
	
	public List<RoomDTO> search(RoomDTO dto,int pageNo,int pageSize);
	
	
}

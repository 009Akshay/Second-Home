package in.co.hostel.management.service;

import java.util.List;

import in.co.hostel.management.dto.RoomDTO;
import in.co.hostel.management.exception.DuplicateRecordException;

public interface RoomServiceInt {

	public long add(RoomDTO dto) throws DuplicateRecordException;

	public void delete(RoomDTO dto);

	public RoomDTO findBypk(long pk);

	public RoomDTO findByRoomNo(String roomNo);

	public void update(RoomDTO dto) throws DuplicateRecordException;

	public List<RoomDTO> list();

	public List<RoomDTO> list(int pageNo, int pageSize);

	public List<RoomDTO> search(RoomDTO dto);

	public List<RoomDTO> search(RoomDTO dto, int pageNo, int pageSize);


}

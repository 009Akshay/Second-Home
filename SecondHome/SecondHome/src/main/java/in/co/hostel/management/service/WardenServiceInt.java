package in.co.hostel.management.service;

import java.util.List;

import in.co.hostel.management.dto.WardenDTO;
import in.co.hostel.management.exception.DuplicateRecordException;

public interface WardenServiceInt {

	public long add(WardenDTO dto) throws DuplicateRecordException;

	public void delete(WardenDTO dto);

	public WardenDTO findBypk(long pk);

	public WardenDTO findByUserId(long id);

	public void update(WardenDTO dto) throws DuplicateRecordException;

	public List<WardenDTO> list();

	public List<WardenDTO> list(int pageNo, int pageSize);

	public List<WardenDTO> search(WardenDTO dto);

	public List<WardenDTO> search(WardenDTO dto, int pageNo, int pageSize);


}

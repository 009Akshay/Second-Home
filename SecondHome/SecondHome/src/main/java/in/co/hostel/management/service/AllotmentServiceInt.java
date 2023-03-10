package in.co.hostel.management.service;

import java.util.List;

import in.co.hostel.management.dto.AllotmentDTO;
import in.co.hostel.management.exception.DuplicateRecordException;

public interface AllotmentServiceInt {

	public long add(AllotmentDTO dto) throws DuplicateRecordException;

	public void delete(AllotmentDTO dto);

	public AllotmentDTO findBypk(long pk);

	public AllotmentDTO findByAllotmentNo(String AllotmentNo);

	public void update(AllotmentDTO dto) throws DuplicateRecordException;

	public List<AllotmentDTO> list();

	public List<AllotmentDTO> list(int pageNo, int pageSize);

	public List<AllotmentDTO> search(AllotmentDTO dto);

	public List<AllotmentDTO> search(AllotmentDTO dto, int pageNo, int pageSize);


}

package in.co.hostel.management.service;

import java.util.List;

import in.co.hostel.management.dto.ApplicationDTO;
import in.co.hostel.management.exception.DuplicateRecordException;

public interface ApplicationServiceInt {

	public long add(ApplicationDTO dto) throws DuplicateRecordException;

	public void delete(ApplicationDTO dto);

	public ApplicationDTO findBypk(long pk);

	public ApplicationDTO findByUserId(long userId);

	public void update(ApplicationDTO dto) throws DuplicateRecordException;

	public List<ApplicationDTO> list();

	public List<ApplicationDTO> list(int pageNo, int pageSize);

	public List<ApplicationDTO> search(ApplicationDTO dto);

	public List<ApplicationDTO> search(ApplicationDTO dto, int pageNo, int pageSize);


}

package in.co.hostel.management.service;

import java.util.List;

import in.co.hostel.management.dto.VisitorDTO;
import in.co.hostel.management.exception.DuplicateRecordException;

public interface VisitorServiceInt {

	public long add(VisitorDTO dto) throws DuplicateRecordException;

	public void delete(VisitorDTO dto);

	public VisitorDTO findBypk(long pk);

	public VisitorDTO findByVisitorNo(String VisitorNo);

	public void update(VisitorDTO dto) throws DuplicateRecordException;

	public List<VisitorDTO> list();

	public List<VisitorDTO> list(int pageNo, int pageSize);

	public List<VisitorDTO> search(VisitorDTO dto);

	public List<VisitorDTO> search(VisitorDTO dto, int pageNo, int pageSize);


}

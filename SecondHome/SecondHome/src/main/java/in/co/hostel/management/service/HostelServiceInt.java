package in.co.hostel.management.service;

import java.util.List;

import in.co.hostel.management.dto.HostelDTO;
import in.co.hostel.management.exception.DuplicateRecordException;

public interface HostelServiceInt {

	public long add(HostelDTO dto) throws DuplicateRecordException;

	public void delete(HostelDTO dto);

	public HostelDTO findBypk(long pk);

	public HostelDTO findByHostelName(String HostelName);

	public void update(HostelDTO dto) throws DuplicateRecordException;

	public List<HostelDTO> list();

	public List<HostelDTO> list(int pageNo, int pageSize);

	public List<HostelDTO> search(HostelDTO dto);

	public List<HostelDTO> search(HostelDTO dto, int pageNo, int pageSize);


}

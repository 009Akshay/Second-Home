package in.co.hostel.management.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.hostel.management.dao.WardenDAOInt;
import in.co.hostel.management.dto.RoomDTO;
import in.co.hostel.management.dto.WardenDTO;
import in.co.hostel.management.exception.DuplicateRecordException;


@Service
public class WardenServiceImpl implements WardenServiceInt {

	private static Logger log = Logger.getLogger(WardenServiceImpl.class.getName());

	@Autowired
	private WardenDAOInt dao;

	@Override
	@Transactional
	public long add(WardenDTO dto) throws DuplicateRecordException {
		log.info("WardenServiceImpl Add method start");
		WardenDTO existDTO = dao.findByUserId(dto.getUserId());
		if (existDTO != null)
			throw new DuplicateRecordException("Warden Are Already Alloted");
		long pk = dao.add(dto);
		log.info("WardenServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(WardenDTO dto) {
		log.info("WardenServiceImpl Delete method start");
		dao.delete(dto);
		log.info("WardenServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public WardenDTO findBypk(long pk) {
		log.info("WardenServiceImpl findBypk method start");
		WardenDTO dto = dao.findBypk(pk);
		log.info("WardenServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public WardenDTO findByUserId(long userId) {
		log.info("WardenServiceImpl findByWardenName method start");
		WardenDTO dto = dao.findByUserId(userId);
		log.info("WardenServiceImpl findByWardenName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(WardenDTO dto) throws DuplicateRecordException {
		log.info("WardenServiceImpl update method start");
		dao.update(dto);
		log.info("WardenServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<WardenDTO> list() {
		log.info("WardenServiceImpl list method start");
		List<WardenDTO> list = dao.list();
		log.info("WardenServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<WardenDTO> list(int pageNo, int pageSize) {
		log.info("WardenServiceImpl list method start");
		List<WardenDTO> list = dao.list(pageNo, pageSize);
		log.info("WardenServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<WardenDTO> search(WardenDTO dto) {
		log.info("WardenServiceImpl search method start");
		List<WardenDTO> list = dao.search(dto);
		log.info("WardenServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<WardenDTO> search(WardenDTO dto, int pageNo, int pageSize) {
		log.info("WardenServiceImpl search method start");
		List<WardenDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("WardenServiceImpl search method end");
		return list;
	}

	
}

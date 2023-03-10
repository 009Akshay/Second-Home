package in.co.hostel.management.service;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.hostel.management.dao.HostelDAOInt;
import in.co.hostel.management.dto.HostelDTO;
import in.co.hostel.management.exception.DuplicateRecordException;
import in.co.hostel.management.util.EmailBuilder;


@Service
public class HostelServiceImpl implements HostelServiceInt {

	private static Logger log = Logger.getLogger(HostelServiceImpl.class.getName());

	@Autowired
	private HostelDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	@Transactional
	public long add(HostelDTO dto) throws DuplicateRecordException {
		log.info("HostelServiceImpl Add method start");
		HostelDTO existDTO = dao.findByHostelName(dto.getName());
		if (existDTO != null)
			throw new DuplicateRecordException("Email Id Already Exist");
		long pk = dao.add(dto);
		log.info("HostelServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(HostelDTO dto) {
		log.info("HostelServiceImpl Delete method start");
		dao.delete(dto);
		log.info("HostelServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public HostelDTO findBypk(long pk) {
		log.info("HostelServiceImpl findBypk method start");
		HostelDTO dto = dao.findBypk(pk);
		log.info("HostelServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public HostelDTO findByHostelName(String HostelName) {
		log.info("HostelServiceImpl findByHostelName method start");
		HostelDTO dto = dao.findByHostelName(HostelName);
		log.info("HostelServiceImpl findByHostelName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(HostelDTO dto) throws DuplicateRecordException {
		log.info("HostelServiceImpl update method start");
		HostelDTO existDTO = dao.findByHostelName(dto.getName());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Hostel Name Id Already Exist");
		dao.update(dto);
		log.info("HostelServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<HostelDTO> list() {
		log.info("HostelServiceImpl list method start");
		List<HostelDTO> list = dao.list();
		log.info("HostelServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<HostelDTO> list(int pageNo, int pageSize) {
		log.info("HostelServiceImpl list method start");
		List<HostelDTO> list = dao.list(pageNo, pageSize);
		log.info("HostelServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<HostelDTO> search(HostelDTO dto) {
		log.info("HostelServiceImpl search method start");
		List<HostelDTO> list = dao.search(dto);
		log.info("HostelServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<HostelDTO> search(HostelDTO dto, int pageNo, int pageSize) {
		log.info("HostelServiceImpl search method start");
		List<HostelDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("HostelServiceImpl search method end");
		return list;
	}

	
}

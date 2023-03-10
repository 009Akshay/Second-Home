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

import in.co.hostel.management.dao.AllotmentDAOInt;
import in.co.hostel.management.dto.AllotmentDTO;
import in.co.hostel.management.exception.DuplicateRecordException;
import in.co.hostel.management.util.EmailBuilder;


@Service
public class AllotmentServiceImpl implements AllotmentServiceInt {

	private static Logger log = Logger.getLogger(AllotmentServiceImpl.class.getName());

	@Autowired
	private AllotmentDAOInt dao;


	@Override
	@Transactional
	public long add(AllotmentDTO dto) throws DuplicateRecordException {
		log.info("AllotmentServiceImpl Add method start");
		long pk = dao.add(dto);
		log.info("AllotmentServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(AllotmentDTO dto) {
		log.info("AllotmentServiceImpl Delete method start");
		dao.delete(dto);
		log.info("AllotmentServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public AllotmentDTO findBypk(long pk) {
		log.info("AllotmentServiceImpl findBypk method start");
		AllotmentDTO dto = dao.findBypk(pk);
		log.info("AllotmentServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public AllotmentDTO findByAllotmentNo(String AllotmentNo) {
		log.info("AllotmentServiceImpl findByAllotmentName method start");
		AllotmentDTO dto = dao.findByAllotmentNo(AllotmentNo);
		log.info("AllotmentServiceImpl findByAllotmentName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(AllotmentDTO dto) throws DuplicateRecordException {
		log.info("AllotmentServiceImpl update method start");
		dao.update(dto);
		log.info("AllotmentServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<AllotmentDTO> list() {
		log.info("AllotmentServiceImpl list method start");
		List<AllotmentDTO> list = dao.list();
		log.info("AllotmentServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<AllotmentDTO> list(int pageNo, int pageSize) {
		log.info("AllotmentServiceImpl list method start");
		List<AllotmentDTO> list = dao.list(pageNo, pageSize);
		log.info("AllotmentServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<AllotmentDTO> search(AllotmentDTO dto) {
		log.info("AllotmentServiceImpl search method start");
		List<AllotmentDTO> list = dao.search(dto);
		log.info("AllotmentServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<AllotmentDTO> search(AllotmentDTO dto, int pageNo, int pageSize) {
		log.info("AllotmentServiceImpl search method start");
		List<AllotmentDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("AllotmentServiceImpl search method end");
		return list;
	}

	
}

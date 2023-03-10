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

import in.co.hostel.management.dao.VisitorDAOInt;
import in.co.hostel.management.dto.VisitorDTO;
import in.co.hostel.management.exception.DuplicateRecordException;
import in.co.hostel.management.util.EmailBuilder;


@Service
public class VisitorServiceImpl implements VisitorServiceInt {

	private static Logger log = Logger.getLogger(VisitorServiceImpl.class.getName());

	@Autowired
	private VisitorDAOInt dao;


	@Override
	@Transactional
	public long add(VisitorDTO dto) throws DuplicateRecordException {
		log.info("VisitorServiceImpl Add method start");
		long pk = dao.add(dto);
		log.info("VisitorServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(VisitorDTO dto) {
		log.info("VisitorServiceImpl Delete method start");
		dao.delete(dto);
		log.info("VisitorServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public VisitorDTO findBypk(long pk) {
		log.info("VisitorServiceImpl findBypk method start");
		VisitorDTO dto = dao.findBypk(pk);
		log.info("VisitorServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public VisitorDTO findByVisitorNo(String VisitorNo) {
		log.info("VisitorServiceImpl findByVisitorName method start");
		VisitorDTO dto = dao.findByVisitorNo(VisitorNo);
		log.info("VisitorServiceImpl findByVisitorName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(VisitorDTO dto) throws DuplicateRecordException {
		log.info("VisitorServiceImpl update method start");
		dao.update(dto);
		log.info("VisitorServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<VisitorDTO> list() {
		log.info("VisitorServiceImpl list method start");
		List<VisitorDTO> list = dao.list();
		log.info("VisitorServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<VisitorDTO> list(int pageNo, int pageSize) {
		log.info("VisitorServiceImpl list method start");
		List<VisitorDTO> list = dao.list(pageNo, pageSize);
		log.info("VisitorServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<VisitorDTO> search(VisitorDTO dto) {
		log.info("VisitorServiceImpl search method start");
		List<VisitorDTO> list = dao.search(dto);
		log.info("VisitorServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<VisitorDTO> search(VisitorDTO dto, int pageNo, int pageSize) {
		log.info("VisitorServiceImpl search method start");
		List<VisitorDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("VisitorServiceImpl search method end");
		return list;
	}

	
}

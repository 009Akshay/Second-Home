package in.co.hostel.management.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.hostel.management.dto.VisitorDTO;


@Repository
public class VisitorDAOImpl implements VisitorDAOInt {

	private static Logger log = Logger.getLogger(VisitorDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(VisitorDTO dto) {
		log.info("VisitorDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("VisitorDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(VisitorDTO dto) {
		log.info("VisitorDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("VisitorDAOImpl Delete method End");

	}

	@Override
	public VisitorDTO findBypk(long pk) {
		log.info("VisitorDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		VisitorDTO dto = (VisitorDTO) session.get(VisitorDTO.class, pk);
		log.info("VisitorDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public VisitorDTO findByVisitorNo(String VisitorNo) {
		log.info("VisitorDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(VisitorDTO.class);
		criteria.add(Restrictions.eq("VisitorNo", VisitorNo));
		VisitorDTO dto = (VisitorDTO) criteria.uniqueResult();
		log.info("VisitorDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(VisitorDTO dto) {
		log.info("VisitorDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("VisitorDAOImpl update method End");
	}

	@Override
	public List<VisitorDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<VisitorDTO> list(int pageNo, int pageSize) {
		log.info("VisitorDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<VisitorDTO> query = session.createQuery("from VisitorDTO", VisitorDTO.class);
		List<VisitorDTO> list = query.getResultList();
		log.info("VisitorDAOImpl List method End");
		return list;
	}

	@Override
	public List<VisitorDTO> search(VisitorDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<VisitorDTO> search(VisitorDTO dto, int pageNo, int pageSize) {
		log.info("VisitorDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from VisitorDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				hql.append("and u.name like '%" + dto.getName() + "%'");
			}
			if (dto.getStudentName() != null && dto.getStudentName().length() > 0) {
				hql.append("and u.studentName like '%" + dto.getStudentName() + "%'");
			}
		
		}
		Query<VisitorDTO> query = session.createQuery(hql.toString(), VisitorDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<VisitorDTO> list = query.getResultList();
		log.info("VisitorDAOImpl Search method End");
		return list;
	}

	
}

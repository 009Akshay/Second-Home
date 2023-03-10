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

import in.co.hostel.management.dto.AllotmentDTO;


@Repository
public class AllotmentDAOImpl implements AllotmentDAOInt {

	private static Logger log = Logger.getLogger(AllotmentDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(AllotmentDTO dto) {
		log.info("AllotmentDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("AllotmentDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(AllotmentDTO dto) {
		log.info("AllotmentDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("AllotmentDAOImpl Delete method End");

	}

	@Override
	public AllotmentDTO findBypk(long pk) {
		log.info("AllotmentDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		AllotmentDTO dto = (AllotmentDTO) session.get(AllotmentDTO.class, pk);
		log.info("AllotmentDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public AllotmentDTO findByAllotmentNo(String AllotmentNo) {
		log.info("AllotmentDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AllotmentDTO.class);
		criteria.add(Restrictions.eq("AllotmentNo", AllotmentNo));
		AllotmentDTO dto = (AllotmentDTO) criteria.uniqueResult();
		log.info("AllotmentDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(AllotmentDTO dto) {
		log.info("AllotmentDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("AllotmentDAOImpl update method End");
	}

	@Override
	public List<AllotmentDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<AllotmentDTO> list(int pageNo, int pageSize) {
		log.info("AllotmentDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<AllotmentDTO> query = session.createQuery("from AllotmentDTO", AllotmentDTO.class);
		List<AllotmentDTO> list = query.getResultList();
		log.info("AllotmentDAOImpl List method End");
		return list;
	}

	@Override
	public List<AllotmentDTO> search(AllotmentDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<AllotmentDTO> search(AllotmentDTO dto, int pageNo, int pageSize) {
		log.info("AllotmentDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from AllotmentDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getHostelId() > 0) {
				hql.append("and u.hostelId = " + dto.getHostelId());
			}
			if (dto.getUserId() > 0) {
				hql.append("and u.userId = " + dto.getUserId());
			}
		
		
		}
		Query<AllotmentDTO> query = session.createQuery(hql.toString(), AllotmentDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<AllotmentDTO> list = query.getResultList();
		log.info("AllotmentDAOImpl Search method End");
		return list;
	}

	
}

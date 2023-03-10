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

import in.co.hostel.management.dto.WardenDTO;


@Repository
public class WardenDAOImpl implements WardenDAOInt {

	private static Logger log = Logger.getLogger(WardenDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(WardenDTO dto) {
		log.info("WardenDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("WardenDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(WardenDTO dto) {
		log.info("WardenDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("WardenDAOImpl Delete method End");

	}

	@Override
	public WardenDTO findBypk(long pk) {
		log.info("WardenDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		WardenDTO dto = (WardenDTO) session.get(WardenDTO.class, pk);
		log.info("WardenDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public WardenDTO findByUserId(long userId) {
		log.info("WardenDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(WardenDTO.class);
		criteria.add(Restrictions.eq("userId",userId));
		WardenDTO dto = (WardenDTO) criteria.uniqueResult();
		log.info("WardenDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(WardenDTO dto) {
		log.info("WardenDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("WardenDAOImpl update method End");
	}

	@Override
	public List<WardenDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<WardenDTO> list(int pageNo, int pageSize) {
		log.info("WardenDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<WardenDTO> query = session.createQuery("from WardenDTO", WardenDTO.class);
		List<WardenDTO> list = query.getResultList();
		log.info("WardenDAOImpl List method End");
		return list;
	}

	@Override
	public List<WardenDTO> search(WardenDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<WardenDTO> search(WardenDTO dto, int pageNo, int pageSize) {
		log.info("WardenDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from WardenDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
		}
		Query<WardenDTO> query = session.createQuery(hql.toString(), WardenDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<WardenDTO> list = query.getResultList();
		log.info("WardenDAOImpl Search method End");
		return list;
	}

	
}

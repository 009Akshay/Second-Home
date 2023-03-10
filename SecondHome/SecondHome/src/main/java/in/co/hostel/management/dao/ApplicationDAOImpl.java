package in.co.hostel.management.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.hostel.management.dto.ApplicationDTO;


@Repository
public class ApplicationDAOImpl implements ApplicationDAOInt {

	private static Logger log = Logger.getLogger(ApplicationDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(ApplicationDTO dto) {
		log.info("ApplicationDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("ApplicationDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(ApplicationDTO dto) {
		log.info("ApplicationDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("ApplicationDAOImpl Delete method End");

	}

	@Override
	public ApplicationDTO findBypk(long pk) {
		log.info("ApplicationDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		ApplicationDTO dto = (ApplicationDTO) session.get(ApplicationDTO.class, pk);
		log.info("ApplicationDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public ApplicationDTO findByUserId(long UserId) {
		log.info("ApplicationDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ApplicationDTO.class);
		criteria.add(Restrictions.eq("userId",UserId));
		ApplicationDTO dto = (ApplicationDTO) criteria.uniqueResult();
		log.info("ApplicationDAOImpl FindByLogin method End");
		return dto;
	}
	
	@Override
	public ApplicationDTO findByUserIdAndHostel(long UserId,long hostelId) {
		log.info("ApplicationDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ApplicationDTO.class);
		criteria.add(Restrictions.eq("userId",UserId));
		criteria.add(Restrictions.eq("hostelId",hostelId));
		ApplicationDTO dto = (ApplicationDTO) criteria.uniqueResult();
		log.info("ApplicationDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(ApplicationDTO dto) {
		log.info("ApplicationDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("ApplicationDAOImpl update method End");
	}

	@Override
	public List<ApplicationDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<ApplicationDTO> list(int pageNo, int pageSize) {
		log.info("ApplicationDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<ApplicationDTO> query = session.createQuery("from ApplicationDTO", ApplicationDTO.class);
		List<ApplicationDTO> list = query.getResultList();
		log.info("ApplicationDAOImpl List method End");
		return list;
	}

	@Override
	public List<ApplicationDTO> search(ApplicationDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<ApplicationDTO> search(ApplicationDTO dto, int pageNo, int pageSize) {
		log.info("ApplicationDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from ApplicationDTO as u where 1=1 ");
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
		Query<ApplicationDTO> query = session.createQuery(hql.toString(), ApplicationDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<ApplicationDTO> list = query.getResultList();
		log.info("ApplicationDAOImpl Search method End");
		return list;
	}

	
}

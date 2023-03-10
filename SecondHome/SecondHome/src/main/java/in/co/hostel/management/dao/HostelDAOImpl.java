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

import in.co.hostel.management.dto.HostelDTO;


@Repository
public class HostelDAOImpl implements HostelDAOInt {

	private static Logger log = Logger.getLogger(HostelDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(HostelDTO dto) {
		log.info("HostelDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("HostelDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(HostelDTO dto) {
		log.info("HostelDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("HostelDAOImpl Delete method End");

	}

	@Override
	public HostelDTO findBypk(long pk) {
		log.info("HostelDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		HostelDTO dto = (HostelDTO) session.get(HostelDTO.class, pk);
		log.info("HostelDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public HostelDTO findByHostelName(String HostelName) {
		log.info("HostelDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(HostelDTO.class);
		criteria.add(Restrictions.eq("name", HostelName));
		HostelDTO dto = (HostelDTO) criteria.uniqueResult();
		log.info("HostelDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(HostelDTO dto) {
		log.info("HostelDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("HostelDAOImpl update method End");
	}

	@Override
	public List<HostelDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<HostelDTO> list(int pageNo, int pageSize) {
		log.info("HostelDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<HostelDTO> query = session.createQuery("from HostelDTO", HostelDTO.class);
		List<HostelDTO> list = query.getResultList();
		log.info("HostelDAOImpl List method End");
		return list;
	}

	@Override
	public List<HostelDTO> search(HostelDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<HostelDTO> search(HostelDTO dto, int pageNo, int pageSize) {
		log.info("HostelDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from HostelDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			
			if (dto.getName() != null && dto.getName().length() > 0) {
				hql.append("and u.name like '%" + dto.getName() + "%'");
			}
			if (dto.getType() != null && dto.getType().length() > 0) {
				hql.append("and u.type like '%" + dto.getType() + "%'");
			}
			
		
		}
		Query<HostelDTO> query = session.createQuery(hql.toString(), HostelDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<HostelDTO> list = query.getResultList();
		log.info("HostelDAOImpl Search method End");
		return list;
	}


}

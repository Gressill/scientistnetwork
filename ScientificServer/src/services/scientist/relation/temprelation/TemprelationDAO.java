package services.scientist.relation.temprelation;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Temprelation entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see services.scientist.relation.temprelation.Temprelation
 * @author MyEclipse Persistence Tools
 */

public class TemprelationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TemprelationDAO.class);
	// property constants
	public static final String AUTHOR1 = "author1";
	public static final String AUTHOR2 = "author2";
	public static final String PAPER = "paper";

	protected void initDao() {
		// do nothing
	}

	public void save(Temprelation transientInstance) {
		log.debug("saving Temprelation instance");
		try {
        	//Transaction tx = getSession().beginTransaction();
            //getSession().save(transientInstance);
			getHibernateTemplate().save(transientInstance);
            //tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Temprelation persistentInstance) {
		log.debug("deleting Temprelation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Temprelation findById(java.lang.String id) {
		log.debug("getting Temprelation instance with id: " + id);
		try {
			Temprelation instance = (Temprelation) getHibernateTemplate()
					.get(
							"services.scientist.relation.temprelation.Temprelation",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Temprelation instance) {
		log.debug("finding Temprelation instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Temprelation instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Temprelation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAuthor1(Object author1) {
		return findByProperty(AUTHOR1, author1);
	}

	public List findByAuthor2(Object author2) {
		return findByProperty(AUTHOR2, author2);
	}

	public List findByPaper(Object paper) {
		return findByProperty(PAPER, paper);
	}

	public List findAll() {
		log.debug("finding all Temprelation instances");
		try {
			String queryString = "from Temprelation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Temprelation merge(Temprelation detachedInstance) {
		log.debug("merging Temprelation instance");
		try {
			Temprelation result = (Temprelation) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Temprelation instance) {
		log.debug("attaching dirty Temprelation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Temprelation instance) {
		log.debug("attaching clean Temprelation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TemprelationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TemprelationDAO) ctx.getBean("TemprelationDAO");
	}
}
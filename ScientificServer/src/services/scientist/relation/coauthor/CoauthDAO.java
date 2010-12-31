package services.scientist.relation.coauthor;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Coauth entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see services.scientist.relation.coauthor.Coauth
 * @author MyEclipse Persistence Tools
 */

public class CoauthDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(CoauthDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String COAUTH = "coauth";
	public static final String TITLE = "title";
	public static final String URL = "url";

	protected void initDao() {
		// do nothing
	}

	public void save(Coauth transientInstance) {
		log.debug("saving Coauth instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Coauth persistentInstance) {
		log.debug("deleting Coauth instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Coauth findById(java.lang.Integer id) {
		log.debug("getting Coauth instance with id: " + id);
		try {
			Coauth instance = (Coauth) getHibernateTemplate().get(
					"services.scientist.relation.coauthor.Coauth", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Coauth instance) {
		log.debug("finding Coauth instance by example");
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
		log.debug("finding Coauth instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Coauth as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByCoauth(Object coauth) {
		return findByProperty(COAUTH, coauth);
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all Coauth instances");
		try {
			String queryString = "from Coauth";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Coauth merge(Coauth detachedInstance) {
		log.debug("merging Coauth instance");
		try {
			Coauth result = (Coauth) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Coauth instance) {
		log.debug("attaching dirty Coauth instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Coauth instance) {
		log.debug("attaching clean Coauth instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CoauthDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CoauthDAO) ctx.getBean("CoauthDAO");
	}
}
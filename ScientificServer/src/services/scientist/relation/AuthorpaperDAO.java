package services.scientist.relation;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Authorpaper entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see services.scientist.relation.Authorpaper
 * @author MyEclipse Persistence Tools
 */

public class AuthorpaperDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AuthorpaperDAO.class);
	// property constants
	public static final String AID = "aid";
	public static final String ANAME = "aname";
	public static final String PID = "pid";
	public static final String PNAME = "pname";
	public static final String UID = "uid";
	public static final String UNAME = "uname";

	protected void initDao() {
		// do nothing
	}

	public void save(Authorpaper transientInstance) {
		log.debug("saving Authorpaper instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Authorpaper persistentInstance) {
		log.debug("deleting Authorpaper instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Authorpaper findById(java.lang.String id) {
		log.debug("getting Authorpaper instance with id: " + id);
		try {
			Authorpaper instance = (Authorpaper) getHibernateTemplate().get(
					"services.scientist.relation.Authorpaper", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Authorpaper instance) {
		log.debug("finding Authorpaper instance by example");
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
		log.debug("finding Authorpaper instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Authorpaper as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAid(Object aid) {
		return findByProperty(AID, aid);
	}

	public List findByAname(Object aname) {
		return findByProperty(ANAME, aname);
	}

	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List findByPname(Object pname) {
		return findByProperty(PNAME, pname);
	}

	public List findByUid(Object uid) {
		return findByProperty(UID, uid);
	}

	public List findByUname(Object uname) {
		return findByProperty(UNAME, uname);
	}

	public List findAll() {
		log.debug("finding all Authorpaper instances");
		try {
			String queryString = "from Authorpaper";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Authorpaper merge(Authorpaper detachedInstance) {
		log.debug("merging Authorpaper instance");
		try {
			Authorpaper result = (Authorpaper) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Authorpaper instance) {
		log.debug("attaching dirty Authorpaper instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Authorpaper instance) {
		log.debug("attaching clean Authorpaper instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AuthorpaperDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AuthorpaperDAO) ctx.getBean("AuthorpaperDAO");
	}
}
package services.scientist.author;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import services.dao.ADao;

/**
 * A data access object (DAO) providing persistence and search support for
 * Author entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see services.scientist.author.Author
 * @author MyEclipse Persistence Tools
 */

public class AuthorDAO extends HibernateDaoSupport implements ADao{
	private static final Logger log = LoggerFactory.getLogger(AuthorDAO.class);
	// property constants
	public static final String PASSWOED = "password";
	public static final String REALNAME = "realname";
	public static final String DEPARTMENT = "department";
	public static final String EMAIL = "email";

	protected void initDao() {
		// do nothing
	}

	public void save(Author transientInstance) {
		log.debug("saving Author instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Author persistentInstance) {
		log.debug("deleting Author instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Author findById(java.lang.String id) {
		log.debug("getting Author instance with id: " + id);
		try {
			Author instance = (Author) getHibernateTemplate().get(
					"services.scientist.author.Author", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Author instance) {
		log.debug("finding Author instance by example");
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
		log.debug("finding Author instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Author as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWOED, password);
	}

	public List findByRealname(Object realname) {
		return findByProperty(REALNAME, realname);
	}

	public List findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findAll() {
		log.debug("finding all Author instances");
		try {
			String queryString = "from Author";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	
	
	public List findAll(int i) {
		log.debug("finding all Author instances");
		try {
			String queryString = "from Author";
			HibernateTemplate ht = this.getHibernateTemplate();  
			int PRE_MAX_RESULT = getHibernateTemplate().getMaxResults();  
			ht.setMaxResults(i);  
			List resultlist = ht.find(queryString);  
			ht.setMaxResults(PRE_MAX_RESULT);  
			return resultlist;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Author merge(Author detachedInstance) {
		log.debug("merging Author instance");
		try {
			Author result = (Author) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Author instance) {
		log.debug("attaching dirty Author instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Author instance) {
		log.debug("attaching clean Author instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AuthorDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AuthorDAO) ctx.getBean("AuthorDAO");
	}

	public void createAuthor(Author author) {
		// TODO Auto-generated method stub
		
	}

	public List<Author> getAll() {
		Session session = getSession();
		session.beginTransaction();
		List<Author> authors = (List<Author>)session.createQuery("from author").list();
		session.getTransaction().commit();
		releaseSession(session);
		return authors;
		//return (List<Author>)getHibernateTemplate().find("from author");
	}

}
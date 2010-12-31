package services.scientist.relation;

/**
 * Authorpaper entity. @author MyEclipse Persistence Tools
 */
public class Authorpaper extends AbstractAuthorpaper implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Authorpaper() {
	}

	/** minimal constructor */
	public Authorpaper(String id) {
		super(id);
	}

	/** full constructor */
	public Authorpaper(String id, String aid, String aname, String pid,
			String pname, Integer uid, String uname) {
		super(id, aid, aname, pid, pname, uid, uname);
	}

}

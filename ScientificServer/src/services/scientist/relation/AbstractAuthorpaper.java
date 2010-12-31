package services.scientist.relation;

/**
 * AbstractAuthorpaper entity provides the base persistence definition of the
 * Authorpaper entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAuthorpaper implements java.io.Serializable {

	// Fields

	private String id;
	private String aid;
	private String aname;
	private String pid;
	private String pname;
	private Integer uid;
	private String uname;

	// Constructors

	/** default constructor */
	public AbstractAuthorpaper() {
	}

	/** minimal constructor */
	public AbstractAuthorpaper(String id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractAuthorpaper(String id, String aid, String aname, String pid,
			String pname, Integer uid, String uname) {
		this.id = id;
		this.aid = aid;
		this.aname = aname;
		this.pid = pid;
		this.pname = pname;
		this.uid = uid;
		this.uname = uname;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAid() {
		return this.aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

}
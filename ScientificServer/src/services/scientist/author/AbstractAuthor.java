package services.scientist.author;

/**
 * AbstractAuthor entity provides the base persistence definition of the Author
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAuthor implements java.io.Serializable {

	// Fields

	private String uid;
	private String password;
	private String realname;
	private String department;
	private String email;

	// Constructors

	/** default constructor */
	public AbstractAuthor() {
	}

	/** minimal constructor */
	public AbstractAuthor(String uid) {
		this.uid = uid;
	}

	/** full constructor */
	public AbstractAuthor(String uid, String password, String realname,
			String department, String email) {
		this.uid = uid;
		this.password = password;
		this.realname = realname;
		this.department = department;
		this.email = email;
	}

	// Property accessors

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return this.password;
	}

	public void setNickname(String password) {
		this.password = password;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
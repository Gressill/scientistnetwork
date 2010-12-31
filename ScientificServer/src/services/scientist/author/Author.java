package services.scientist.author;

/**
 * Author entity. @author MyEclipse Persistence Tools
 */
public class Author extends AbstractAuthor implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Author() {
	}

	/** minimal constructor */
	public Author(String uid) {
		super(uid);
	}

	/** full constructor */
	public Author(String uid, String password, String realname,
			String department, String email) {
		super(uid, password, realname, department, email);
	}

}

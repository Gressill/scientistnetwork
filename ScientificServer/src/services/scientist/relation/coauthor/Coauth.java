package services.scientist.relation.coauthor;

/**
 * Coauth entity. @author MyEclipse Persistence Tools
 */
public class Coauth extends AbstractCoauth implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Coauth() {
	}

	/** full constructor */
	public Coauth(String name, String coauth, String title, String url) {
		super(name, coauth, title, url);
	}

}

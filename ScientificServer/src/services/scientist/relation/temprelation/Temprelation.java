package services.scientist.relation.temprelation;

/**
 * Temprelation entity. @author MyEclipse Persistence Tools
 */
public class Temprelation extends AbstractTemprelation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Temprelation() {
	}

	/** minimal constructor */
	public Temprelation(String id) {
		super(id);
	}

	/** full constructor */
	public Temprelation(String id, String author1, String author2, String paper) {
		super(id, author1, author2, paper);
	}

}

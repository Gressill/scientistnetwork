package services.scientist.relation.temprelation;

/**
 * AbstractTemprelation entity provides the base persistence definition of the
 * Temprelation entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTemprelation implements java.io.Serializable {

	// Fields

	private String id;
	private String author1;
	private String author2;
	private String paper;

	// Constructors

	/** default constructor */
	public AbstractTemprelation() {
	}

	/** minimal constructor */
	public AbstractTemprelation(String id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractTemprelation(String id, String author1, String author2,
			String paper) {
		this.id = id;
		this.author1 = author1;
		this.author2 = author2;
		this.paper = paper;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor1() {
		return this.author1;
	}

	public void setAuthor1(String author1) {
		this.author1 = author1;
	}

	public String getAuthor2() {
		return this.author2;
	}

	public void setAuthor2(String author2) {
		this.author2 = author2;
	}

	public String getPaper() {
		return this.paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

}
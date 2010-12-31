package services.scientist.relation.coauthor;

/**
 * AbstractCoauth entity provides the base persistence definition of the Coauth
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCoauth implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String coauth;
	private String title;
	private String url;

	// Constructors

	/** default constructor */
	public AbstractCoauth() {
	}

	/** full constructor */
	public AbstractCoauth(String name, String coauth, String title, String url) {
		this.name = name;
		this.coauth = coauth;
		this.title = title;
		this.url = url;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoauth() {
		return this.coauth;
	}

	public void setCoauth(String coauth) {
		this.coauth = coauth;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
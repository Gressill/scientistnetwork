package services.employee;

public class Department
{
	private int id;
	private String name;
	private String manager;
	private int costcenter;
	private String businessunit;
	private String hrrep;
	private String locationstreet;
	private String locationcity;
	private String locationstate;
	private String locationzipcode;
	private String budget;
	private String actualexpenses;
	private String estsalary;
	private String actualsalary;
	private String esttravel;
	private String actualtravel;
	private String estsupplies;
	private String actualsupplies;
	private String estcontractors;
	private String actualcontractors;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name= name;
	}

	public String getManager()
	{
		return manager;
	}

	public void setManager(String manager)
	{
		this.manager= manager;
	}
	
	public int getCostcenter()
	{
		return costcenter;
	}

	public void setCostcenter(int costcenter)
	{
		this.costcenter = costcenter;
	}
	
	public String getBusinessunit()
	{
		return businessunit;
	}

	public void setBusinessunit(String businessunit)
	{
		this.businessunit= businessunit;
	}
	
	public String getHrrep()
	{
		return hrrep;
	}

	public void setHrrep(String hrrep)
	{
		this.hrrep= hrrep;
	}
	
	public String getLocationstreet()
	{
		return locationstreet;
	}

	public void setLocationstreet(String locationstreet)
	{
		this.locationstreet= locationstreet;
	}
	
	public String getLocationcity()
	{
		return locationcity;
	}

	public void setLocationcity(String locationcity)
	{
		this.locationcity= locationcity;
	}
	
	public String getLocationstate()
	{
		return locationstate;
	}

	public void setLocationstate(String locationstate)
	{
		this.locationstate= locationstate;
	}
	
	public String getLocationzipcode()
	{
		return locationzipcode;
	}

	public void setLocationzipcode(String locationzipcode)
	{
		this.locationzipcode= locationzipcode;
	}
	
	public String getBudget()
	{
		return budget;
	}

	public void setBudget(String budget)
	{
		this.budget= budget;
	}
	
	public String getActualexpenses()
	{
		return actualexpenses;
	}

	public void setActualexpenses(String actualexpenses)
	{
		this.actualexpenses= actualexpenses;
	}
	
	public String getEstsalary()
	{
		return estsalary;
	}

	public void setEstsalary(String estsalary)
	{
		this.estsalary= estsalary;
	}
	
	public String getActualsalary()
	{
		return actualsalary;
	}

	public void setActualsalary(String actualsalary)
	{
		this.actualsalary= actualsalary;
	}
	
	public String getEsttravel()
	{
		return esttravel;
	}

	public void setEsttravel(String esttravel)
	{
		this.esttravel= esttravel;
	}
	
	public String getActualtravel()
	{
		return actualtravel;
	}

	public void setActualtravel(String actualtravel)
	{
		this.actualtravel= actualtravel;
	}
	
	public String getEstsupplies()
	{
		return estsupplies;
	}

	public void setEstsupplies(String estsupplies)
	{
		this.estsupplies= estsupplies;
	}
	
	public String getActualsupplies()
	{
		return actualsupplies;
	}

	public void setActualsupplies(String actualsupplies)
	{
		this.actualsupplies= actualsupplies;
	}
	
	public String getEstcontractors()
	{
		return estcontractors;
	}

	public void setEstcontractors(String estcontractors)
	{
		this.estcontractors= estcontractors;
	}
	
	public String getActualcontractors()
	{
		return actualcontractors;
	}

	public void setActualcontractors(String actualcontractors)
	{
		this.actualcontractors= actualcontractors;
	}
	
    public String toString()
    {
        return "[id=" 						+ id + 
			", name=" 						+ name + 
			", manager=" 					+ manager + 
			", costcenter=" 				+ costcenter +  
			", businessunit=" 				+ businessunit +
			", hrrep=" 						+ hrrep + 
			", locationstreet=" 			+ locationstreet + 
			", locationcity=" 				+ locationcity + 
			", locationstate=" 				+ locationstate + 
			", locationzipcode=" 			+ locationzipcode + 
			", budget=" 					+ budget + 
			", actualexpenses=" 			+ actualexpenses + 
			", estsalary=" 					+ estsalary + 
			", actualsalary=" 				+ actualsalary + 
			", esttravel=" 					+ esttravel + 
			", actualtravel=" 				+ actualtravel + 
			", estsupplies=" 				+ estsupplies + 
			", actualsupplies=" 			+ actualsupplies + 
			", estcontractors=" 			+ estcontractors + 
			", actualcontractors=" 			+ actualcontractors + 
			"]";
    }
}


package postbodies;

public class Address
{
	private String firstname;
	private String lastname;
	private String city;
	
	public Address(String firstname, String lastname, String city)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
	}
	
	public String getFirstname()
	{
		return firstname;
	}
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	public String getLastname()
	{
		return lastname;
	}
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
}

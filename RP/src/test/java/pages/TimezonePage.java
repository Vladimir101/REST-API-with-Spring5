package pages;

public class TimezonePage
{
	private String week_number;
	private String utc_offset;
	private String unixtime;
	private String timezone;
	private String dst_until = null;
	private String dst_from = null;
	private boolean dst;
	private float day_of_year;
	private float day_of_week;
	private String datetime;
	private String abbreviation;

	// Getter Methods
	public String getWeek_number()
	{
		return week_number;
	}

	public String getUtc_offset()
	{
		return utc_offset;
	}

	public String getUnixtime()
	{
		return unixtime;
	}

	public String getTimezone()
	{
		return timezone;
	}

	public String getDst_until()
	{
		return dst_until;
	}

	public String getDst_from()
	{
		return dst_from;
	}

	public boolean getDst()
	{
		return dst;
	}

	public float getDay_of_year()
	{
		return day_of_year;
	}

	public float getDay_of_week()
	{
		return day_of_week;
	}

	public String getDatetime()
	{
		return datetime;
	}

	public String getAbbreviation()
	{
		return abbreviation;
	}

	// Setter Methods
	public void setWeek_number(String week_number)
	{
		this.week_number = week_number;
	}

	public void setUtc_offset(String utc_offset)
	{
		this.utc_offset = utc_offset;
	}

	public void setUnixtime(String unixtime)
	{
		this.unixtime = unixtime;
	}

	public void setTimezone(String timezone)
	{
		this.timezone = timezone;
	}

	public void setDst_until(String dst_until)
	{
		this.dst_until = dst_until;
	}

	public void setDst_from(String dst_from)
	{
		this.dst_from = dst_from;
	}

	public void setDst(boolean dst)
	{
		this.dst = dst;
	}

	public void setDay_of_year(float day_of_year)
	{
		this.day_of_year = day_of_year;
	}

	public void setDay_of_week(float day_of_week)
	{
		this.day_of_week = day_of_week;
	}

	public void setDatetime(String datetime)
	{
		this.datetime = datetime;
	}

	public void setAbbreviation(String abbreviation)
	{
		this.abbreviation = abbreviation;
	}
}
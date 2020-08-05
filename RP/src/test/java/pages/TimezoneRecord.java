package pages;
import com.fasterxml.jackson.annotation.*;

/*"abbreviation": "PDT", *
"client_ip": "73.162.153.202", *
"datetime": "2020-08-04T16:37:45.632733-07:00", *
"day_of_week": 2, *
"day_of_year": 217, *
"dst": true, *
"dst_from": "2020-03-08T10:00:00+00:00",
"dst_offset": 3600,
"dst_until": "2020-11-01T09:00:00+00:00",
"raw_offset": -28800,
"timezone": "America\/Los_Angeles",
"unixtime": 1596584265,
"utc_datetime": "2020-08-04T23:37:45.632733+00:00",
"utc_offset": "-07:00",
"week_number": 32*/
public record TimezoneRecord(	 
	 @JsonProperty("week_number") int week_number,
	 @JsonProperty("utc_offset") String utc_offset,
	 @JsonProperty("unixtime") long unixtime,
	 @JsonProperty("utc_datetime") String utc_datetime,
	 @JsonProperty("timezone") String timezone,
	 @JsonProperty("dst_until") String dst_until,
	 @JsonProperty("dst_from") String dst_from,
	 @JsonProperty("dst_offset") String dst_offset,
	 @JsonProperty("raw_offset") long raw_offset,
	 @JsonProperty("dst") boolean dst,
	 @JsonProperty("day_of_year") int day_of_year,
	 @JsonProperty("day_of_week") int day_of_week,
	 @JsonProperty("datetime") String datetime,
	 @JsonProperty("abbreviation") String abbreviation,
	 @JsonProperty("client_ip") String client_ip) 
{
	public TimezoneRecord()
	{
		this.abbreviation = "";
		this.client_ip = "";
		this.datetime = "";
		this.day_of_week = 0;
		this.day_of_year = 0;
		this.dst = false;
		this.dst_from = "";
		this.dst_offset = "";
		this.dst_until = "";
		this.raw_offset = 0;
		this.timezone = "";
		this.unixtime = 0;
		this.utc_datetime = "";
		this.utc_offset = "";
		this.week_number = 0;}
}

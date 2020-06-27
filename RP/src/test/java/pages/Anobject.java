package pages;
/* Since anarray contains elements of different types (int and String)
 * https://codebeautify.org/json-to-java-converter does not know how to 
 * deal with that array and DOES NOT create getter and setter for the field.
 * We must add them manually
 
"anobject": {
    "whoa": "nuts",
    "anarray": [
        1,
        2,
        "thr<h1>ee"
    ],
    "more": "stuff"
},
*/
import java.util.ArrayList;

public class Anobject
{
	private String whoa;
	ArrayList<Object> anarray = new ArrayList<Object>();
	private String more;

	// Getter Methods
	public String getWhoa()
	{
		return whoa;
	}

	public String getMore()
	{
		return more;
	}

	public ArrayList<Object> getAnarray()
	{
		return anarray;
	}

	// Setter Methods
	public void setWhoa(String whoa)
	{
		this.whoa = whoa;
	}

	public void setAnarray(ArrayList<Object> anarray)
	{
		this.anarray = anarray;
	}

	public void setMore(String more)
	{
		this.more = more;
	}
}
package lab_1;

import java.io.FileWriter;
import java.io.IOException;

public class Book implements Comparable
{
	static String Cols [ ] = { "title", "author", "date", "rubric", "name_magazine" };
	
	protected Log Log;
	protected String title;
	protected String author;
	protected int date;
	protected String rubric;
	protected String name_magazine;
	
	void setTitle(String aTitle)
	{
		this.title = aTitle;
	}
	
    void setAuthor(String author)
	{
		this.author = author;
	}
	
	void setDate(int date)
	{
		this.date = date;
	}
	
	void setDate(String date) throws IOException 
	{
		try 
		{
		this.setDate(Integer.parseInt(date));	
		} 
		
		catch (NumberFormatException nfe) 
		{
			Log.out("Wrong date");
		}
	}
	
	void setRubric(String rubric)
	{
		this.rubric = rubric;
	}
	
	void setName_magazine(String name_magazine)
	{
		this.name_magazine = name_magazine;
	}
	
	String getTitle()
	{
		return title;
	}
	
	String getAuthor()
	{
		return author;
	}
	
	int getdate()
	{
		return date;
	}
	
	String getRubric()
	{
		return rubric;
	}
	
	String getName_magazine()
	{
		return name_magazine;
	}
	
	public Book (Log aLog)
	{
		Log = aLog;
		title = new String();
		author = new String();
		rubric = new String();
		name_magazine = new String();
	}

	@Override
	public int compareTo(Object arg0) 
	{
		return date - ((Book)arg0).date;
	}
}

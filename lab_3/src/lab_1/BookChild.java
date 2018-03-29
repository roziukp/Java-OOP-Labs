package lab_1;

import java.io.FileWriter;

public class BookChild extends Book
{
	static int ID = 0;
	protected int id;
	
	int getId()
	{
		return id;
	}
	
	public BookChild(Log aLog)
	{
		super(aLog);
		id = ID++;
	}

}

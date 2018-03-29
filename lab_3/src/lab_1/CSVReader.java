package lab_1;

import java.awt.Window.Type;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader 
{
	static int ColIdxs [] = new int [ Book.Cols.length ];
	Log Log;
	ArrayList <BookChild> Books;
	public CSVReader (Log aLog)
	{
		Log = aLog;
	}
	
	public boolean ReadFile ( String FileName ) throws IOException
	{
		FileReader fr = new FileReader(FileName);
		BufferedReader br  = new BufferedReader(fr);
		String str = br.readLine();
		int idx = 0;
		
		for (String retval: str.split(";"))
		{
			for ( int colNo = 0; colNo < Book.Cols.length; colNo++ )
			{
				  if ( retval.equals(Book.Cols[colNo]) )
				  {
					 ColIdxs[idx] = colNo;
					 break;
				  }
			}
			
			Log.out(String.format("\"%s\" (%d) | ", retval, ColIdxs[idx]));
			idx++;

		}
		
		Log.out("\r\n");
		Books = new ArrayList<BookChild>();
		String errs = "";
		
		for(int i=0; ;i++)
		{
			BookChild book = new BookChild(Log);
			str = br.readLine();
			if ( str == null )
				break;

			idx = 0; 
			int strNo = 0;
			
			try
			{
				for (String retval: str.split(";"))
				{
					strNo++;
					try
					{
						switch ( ColIdxs[idx] )
						{
							case 0: book.setTitle(retval);  break;
							case 1: book.setAuthor(retval); break;
							case 2: book.setDate(Integer.parseInt(retval)); break;
							case 3: book.setRubric(retval); break;
							case 4: book.setName_magazine(retval); break;
						}
						
						idx++;
					}
					
					catch ( NumberFormatException Exc )
					{
						errs += String.format("err '%s' in line %d, "
								+ "field '%s'\r\n",
								Exc, strNo, Book.Cols[ColIdxs[idx]]);
						throw Exc;
					}
				}
				
				Books.add(book);
			}
			
			catch ( NumberFormatException Exc )
			{

			}
		}
		
		if ( ! errs.isEmpty() )
		Log.out("\nError(s) found :\n" + errs + "\n");
		Books.sort(null);
		
		for ( BookChild book : Books )
			Log.out(String.format("id %d : %s ,%s ,%s ,%s ,%s\r\n", 
					book.id, book.getTitle(), book.author, book.date, 
					book.rubric, book.name_magazine));

		return true;

	}

}

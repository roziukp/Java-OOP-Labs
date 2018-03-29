package lab_1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonWriter 
{
		public boolean WriteJSon(String filename,ArrayList <BookChild> CSV) throws IOException
	{
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw); 
		
		for ( Book book : CSV)
		{
			bw.write(String.format("{\"%s\":\"%s\",\"%s\":\"%s\","
					+ "\"%s\":\"%d\",\"%s\":\"%s\",\"%s\":\"%s\"}\n",
					"title", book.title,"author", book.author,
					"date", book.date, "name_magarine", 
					book.name_magazine,"rubric",book.rubric));
		}
		
		bw.close();
		fw.close();
		return true;
	}
}

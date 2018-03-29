package lab_1;

import java.io.FileWriter;
import java.io.IOException;

public class Log 
{
	String FileName;
	FileWriter Fw;
	Log(String aFileName) throws IOException
	{
		FileName = aFileName;
		Fw = new FileWriter(FileName); 
	}
	
	void out(String S) throws IOException
	{
		Fw.write(S);
		System.out.format(S);
	}
}

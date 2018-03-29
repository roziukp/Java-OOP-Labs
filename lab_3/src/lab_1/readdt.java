package lab_1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class readdt 
{
	public static void main(String[] args) throws IOException 
	{
		
		Log log = new Log(args[1]);
		CSVReader csv = new CSVReader(log);
		csv.ReadFile(args[0]);
		String fileJSon = new String(args[2]);
		JsonWriter json = new JsonWriter();
		json.WriteJSon(fileJSon, csv.Books);
		log.Fw.close();
	}

}

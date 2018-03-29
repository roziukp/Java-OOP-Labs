package lab1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonWriter
{
    public boolean WriteJSon(String filename,ArrayList <PC_child> CSV) throws IOException
    {
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.format("[\n"));
        int i=1;
            for ( PC pc : CSV)
            {
                    bw.write(String.format("\t{\n\t\t\"%s\":\"%s\",\n\t\t\"%s\":\"%s\",\n\t\t"
                                    + "\"%s\":\"%s\",\n\t\t\"%s\":\"%s\",\n\t\t\"%s\":\"%s\",\n\t\t\"%s\":\"%s\"\n\t}",
                            "CPU", pc.getCPU(),"Motherboard", pc.getMotherboard(),
                            "HDD", pc.getHDD(), "RAM",
                            pc.getRAM(),"Videocard",pc.getVideocard(),"DVD",pc.getDVD()));
                if (i<CSV.size())
                {
                    bw.write(String.format(",\n"));
                    i++;
                }
            }
        bw.write(String.format("\n]"));
        bw.close();
        fw.close();
        return true;
    }
}


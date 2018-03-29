package lab1;
import java.io.IOException;
public class PC implements Comparable <PC>
{
    final static String Cols [] = { "cpu", "motherboard", "hdd", "ram", "videocard","dvd"};

    protected String cpu;
    protected String motherboard;
    protected int hdd;
    protected int ram;
    protected String videocard;
    protected String dvd;

    void setCPU(String cpu){this.cpu=cpu;}
    void setMotherboard(String motherboard){this.motherboard = motherboard;}
    void setHDD(int hdd){this.hdd = hdd;}
    void setHDD(String hdd) throws Exception
    {
        try
        {
            this.setHDD(Integer.parseInt(hdd));
        }

        catch (NumberFormatException nfe)
        {
            throw new Exception("Wrong size of HDD!");
        }
    }

    void setRAM(int ram){this.ram=ram;}
    void setRAM(String ram) throws Exception
    {
        try
        {
            this.setRAM(Integer.parseInt(ram));
        }

        catch (NumberFormatException nfe)
        {
            throw new Exception("Wrong size of RAM!");
        }
    }
    void setVideocard(String videocard){this.videocard = videocard;}
    void setDVD(String dvd){this.dvd = dvd;}


    String getCPU(){return cpu;}
    String getMotherboard(){return motherboard;}
    String getDVD() {return dvd;}
    int getHDD(){return hdd;}
    int getRAM(){return ram;}
    String getVideocard(){return videocard;}


    public PC ()
    {
        motherboard = new String();
        cpu = new String();
        videocard = new String();
        dvd = new String();
    }

    @Override
    public int compareTo(PC aPC)
    {
        return videocard.compareTo(aPC.videocard);
    }

}

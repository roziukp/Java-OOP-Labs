package lab1;

public class PC_child extends PC implements Comparable<PC>
{
    static int ID = 0;
    protected int id;
    int getId()
    {	return id;}

    public PC_child()
    {
        id = ID++;
    }

    @Override
    public int compareTo(PC aPC) {
        return id - ((PC_child)aPC).id;
    }
}

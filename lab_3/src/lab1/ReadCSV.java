package lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class ReadCSV {
    static int ColIdxs[] = new int[PC.Cols.length];
    Log Log;
    ArrayList<PC_child> PCs;

    public ReadCSV(Log aLog) {
        Log = aLog;
    }

    public boolean ReadFile(String FileName) throws IOException {
        FileReader fr = new FileReader(FileName);
        BufferedReader br = new BufferedReader(fr);
        String str = br.readLine();
        int idx = 0;

        for (String retval : str.split(";")) {
            for (int colNo = 0; colNo < PC.Cols.length; colNo++) {
                if (retval.equals(PC.Cols[colNo])) {
                    ColIdxs[idx] = colNo;
                    break;
                }
            }

            Log.out(String.format("\"%s\" (%d) | ", retval, ColIdxs[idx]));
            idx++;

        }

        Log.out("\r\n");
        PCs = new ArrayList<PC_child>();
        String errs = "";

        for (int strNo=1; ;strNo++)
        {
            PC_child pc = new PC_child(

            );
            str = br.readLine();
            if (str == null)
                break;

            idx = 0;

            try {
                for (String retval : str.split(";")) {
                        switch (ColIdxs[idx]) {
                            case 0:
                                pc.setCPU(retval);
                                break;
                            case 1:
                                pc.setMotherboard(retval);
                                break;
                            case 2:
                                pc.setHDD(retval);
                                break;
                            case 3:
                                pc.setRAM(retval);
                                break;
                            case 4:
                                pc.setVideocard(retval);
                                break;
                            case 5:
                                pc.setDVD(retval);
                                break;
                        }

                        idx++;
                }

                PCs.add(pc);
            } catch (Exception Exc) {
                errs += String.format("err '%s' in line %d, "
                                + "field '%s'\r\n",
                        Exc, strNo, PC.Cols[ColIdxs[idx]]);
            }
        }

        if (!errs.isEmpty())
            Log.out("\nError(s) found :\n" + errs + "\n");
        PCs.sort(null);
        for (PC_child pc : PCs)
            Log.out(String.format("id %d : %s ,%s ,%s ,%s ,%s\r\n",
                    pc.id, pc.getCPU(), pc.getMotherboard(), pc.getHDD(), pc.getRAM(),
                    pc.getVideocard(), pc.getDVD()));

        return true;

    }
}
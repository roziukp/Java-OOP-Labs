package lab1;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;


public class READDAT
{
    static String [] Filters = {"lab_1.log", "lab1.csv", "lab_1.json" };
    static String [] Title = {"Choose Log", "Choose Data", "Choose JSON"};

    public static void main(String[] args) throws IOException
    {
        Log log = new Log(args[1]);
        ReadCSV csv = new ReadCSV(log);
        csv.ReadFile(args[0]);
        String fileJSon = new String(args[2]);
        JsonWriter json = new JsonWriter();

        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(0, 0, 500, 500);
        jf.setLayout(null);
        JButton jb = new JButton();

        //jb.setText("Click");
        //jf.add(jb);
        //jb.setBounds(10, 20, 80, 20);
        jf.setVisible(true);

        String [ ]  Files = new String [ 3 ]; //CSV = new String(), FileLog = new String(), FileJSON = new String();
        if (args.length >= 3)
        {
            Files[0] = args[1];
            Files[1] = args[0];
            Files[2] = args[2];
        }
        //else
        {
            FileDialog fd = new FileDialog(jf);
            class FileNameFilterLab implements FilenameFilter
            {

                @Override
                public boolean accept(File arg0, String arg1)
                {
                    // TODO Auto-generated method stub
                    return false;
                }
            }



            FilenameFilter fnf = new FileNameFilterLab();
            for (int i =0; i<3; i++)
            {
                fd.setFilenameFilter(fnf);
                fd.setFile(Filters[i]);
                fd.setTitle(Title[i]);
                fd.setVisible(true);
                if ( fd.getFile() == null || fd.getFile().isEmpty() )
                {
                    jf.dispose();
                    return;
                }
                Files[i] = fd.getFile();
            }
        }




        DefaultListModel <String> lm = new DefaultListModel <String>();
        JList <String> jl = new JList <String>(lm);
        JScrollPane sp = new JScrollPane(jl);
        sp.setBounds(10, 50, 120, 200);
        sp.setVerticalScrollBarPolicy(sp.VERTICAL_SCROLLBAR_ALWAYS);
        jf.add(sp);
        //System.out.format("click\n");
        for(PC_child pc: csv.PCs)
                lm.addElement(String.format("%2d : %s", pc.getId(), pc.getMotherboard()));

        JTextField [] jtf = new JTextField [ PC.Cols.length ];





        class TextChange implements DocumentListener
        {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                update(arg0);
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                update(arg0);
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                update(arg0);
            }

            public void update(DocumentEvent arg0)
            {
//				System.out.format("%s\n", arg0.toString());
                int idx = jl.getSelectedIndex();
                PC_child pc = csv.PCs.get(idx);


                pc.setCPU(jtf[0].getText());
                pc.setMotherboard(jtf[1].getText());

                jtf[2].setForeground(Color.BLACK);
                try
                {
                    pc.setHDD(jtf[2].getText());
                }
                catch (Exception nfe)
                {
                    jtf[2].setForeground(Color.red);
                }

                jtf[3].setForeground(Color.BLACK);
                try
                {
                    pc.setRAM(jtf[3].getText());
                }
                catch (Exception nfe)
                {
                    jtf[3].setForeground(Color.red);
                }

                pc.setVideocard(jtf[4].getText());
                pc.setDVD(jtf[5].getText());
                lm.setElementAt(String.format("%2d : %s",pc.getId(), pc.getMotherboard()), idx);

            }
        }
        TextChange tc = new TextChange();

        JLabel lblN1 = new JLabel();
        jf.add(lblN1);
        lblN1.setBounds(300, 25, 120, 50);
        lblN1.setText("Information:");



        int dy = 0;
        int i =0;
        for(String col:PC.Cols)
        {
            JLabel lblN = new JLabel();
            jf.add(lblN);
            lblN.setBounds(200, 80+dy, 100, 25);
            lblN.setText(col);
            jtf[i] = new JTextField();
            jtf[i].setBounds(300, 80+dy, 170, 25);
            jf.add(jtf[i]);
            jtf[i].setText(" ");
            dy = dy+30;
            i++;

        }



        class ListClick implements ListSelectionListener
        {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                // TODO Auto-generated method stub
                //System.out.format("%s \n", arg0 );
                if ( arg0.getValueIsAdjusting() )
                    return;
//					System.out.format("%s \n", ((JList)arg0.getSource()).getSelectedValue());
                  //  System.out.format("%s \n", ((JList)arg0.getSource()).getSelectedIndex());
                PC pc = csv.PCs.get(((JList)arg0.getSource()).getSelectedIndex());
//                System.out.format("%s \n", pc.getMotherboard());
                for ( int i = 0; i < pc.Cols.length; i++ )
                jtf[i].getDocument().removeDocumentListener(tc);
                jtf[0].setText(pc.getCPU());
                jtf[1].setText(pc.getMotherboard());
                jtf[2].setText(String.format("%d",pc.getHDD()));
                jtf[3].setText(String.format("%d",pc.getRAM()));
                jtf[4].setText(pc.getVideocard());
                jtf[5].setText(pc.getDVD());

                for ( int i = 0; i < pc.Cols.length; i++ )
                    jtf[i].getDocument().addDocumentListener(tc);

            }
        }


        ListClick lc = new ListClick();
        jl.addListSelectionListener(lc);

        jl.setSelectedIndex(0);
        json.WriteJSon(fileJSon, csv.PCs);
        log.Fw.close();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Nóra
 * Date: 2013.12.07.
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
public class Window {
    public JFrame frame;
    public JLabel targynev, targykod, tkovkod, tsemester, tfelev, tkredit, tnnev;
    public JComboBox kovkod, semester, felev, kredit, nev;
    public JTextField tnev, tkod;
    public  JTextArea szoveg;
    public JScrollPane jsp;

    public Window() {

        frame=new JFrame();
        frame.setTitle("SuperMI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(900,650));
        frame.setResizable(false);

        szoveg=new JTextArea();
        szoveg.setEditable(true);
        szoveg.setLineWrap(true);
        szoveg.setRows(35);
        szoveg.setColumns(80);
        jsp= new JScrollPane(szoveg);
        jsp.setSize(400,400);

        targynev=new JLabel("Tárgynév: ");
        targykod=new JLabel("Tárgykód: ");
        tnev = new JTextField();
        tnev.setColumns(10);
        tkod = new JTextField();
        tkod.setColumns(10);

        tnev.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    ArrayList<Targy> eredmeny =searchTargynev(tnev.getText());
                    if(eredmeny.isEmpty()){
                        szoveg.setText("Nincs ilyen tárgy.");
                    }
                    else{
                        String lista = "";
                        for(Targy t : eredmeny){
                            lista += t.toString() + "\n";

                        }
                        szoveg.setText(lista);
                    }

                }
            }
        });

        tkod.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                   ArrayList<Targy> eredmeny = new ArrayList<Targy>();
                    eredmeny.addAll(searchTargykod(tkod.getText()));
                    if(eredmeny.isEmpty()){
                        szoveg.setText("Nincs ilyen tárgy.");
                    }
                    else{
                        String lista = "";
                        for(Targy t : eredmeny){
                            lista += t.toString() + "\n";

                        }
                        szoveg.setText(lista);
                    }
                    szoveg.setCaretPosition(0);
                }
            }
        });

        JPanel felso = new JPanel();
        felso.add(targynev);
        felso.add(tnev);
        felso.add(targykod);
        felso.add(tkod);


        tkovkod = new JLabel("Követelmény: ");
        tsemester=  new JLabel("Szemeszter: ");
        tfelev = new JLabel("Félév: ");
        tkredit = new JLabel("Kredit: ");
        tnnev = new JLabel("Név: ");
        kovkod = new JComboBox();
        semester  = new JComboBox();
        felev  = new JComboBox();
        kredit  = new JComboBox();
        nev  = new JComboBox();


        for(int i=0; i<16;i++){
            kovkod.addItem(Integer.valueOf(i));
            semester.addItem(Integer.valueOf(i));
            felev.addItem(Integer.valueOf(i));
            kredit.addItem(Integer.valueOf(i));
            nev.addItem(Integer.valueOf(i));
        }
        kovkod.setSelectedIndex(1);
        semester.setSelectedIndex(1);
        felev.setSelectedIndex(1);
        kredit.setSelectedIndex(1);
        nev.setSelectedIndex(1);


        JPanel also = new JPanel();
        also.add(tkovkod);
        also.add(kovkod);
        also.add(tsemester);
        also.add(semester);
        also.add(tfelev);
        also.add(felev);
        also.add(tkredit);
        also.add(kredit);
        also.add(tnnev);
        also.add(nev);





        frame.setLayout(new FlowLayout());
        frame.add(felso, BorderLayout.NORTH);
        frame.add(also, BorderLayout.CENTER);




        frame.add(new JPanel().add(jsp));

        frame.setVisible(true);
    }

    public ArrayList<Targy> searchTargynev(String param){
        if(param != null && param.length()>0)   {
            ArrayList<Targy> keresett = new ArrayList<Targy>();
            for(Targy t : ParseHTML.mTargyak){
                if(t.mNev!=null && (t.mNev.toLowerCase()).contains(param.toLowerCase())){
                    keresett.add(t);
                }

            }
            return keresett;
        }
        return new ArrayList<Targy>();

    }

    public List<Targy> searchTargykod(String param){
        if(param != null && param.length()>0)   {
            for(Targy t : ParseHTML.mTargyak){
                if(t.mKod != null && t.mKod.contains(param)){
                   // public static java.util.List<Targy> findSimilar(Targy targy, Suly suly)
                    //SimilarityFinder.findSimilar(parseHTML.parseTargy(parseHTML.mTargyak.get(1)), new Suly(1,1,1,1));
                    //public Suly(double semester, double kovKod, double kredit, double felev){
                    try{

                        return SimilarityFinder.findSimilar(ParseHTML.parseTargy(t), new Suly(semester.getSelectedIndex(),kovkod.getSelectedIndex(),kredit.getSelectedIndex(),felev.getSelectedIndex(), nev.getSelectedIndex()));
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }

        return new ArrayList<Targy>();

    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public JTextArea szoveg, targyak;
    public JScrollPane jsp, jsp2;
    public JButton add, start, remove, clear;
    public ArrayList<Targy> kivalasztottak;

    public Window() {

        frame=new JFrame();
        frame.setTitle("SuperMI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(900, 800));
        frame.setResizable(false);
        kivalasztottak = new ArrayList<Targy>();

        szoveg=new JTextArea();
        szoveg.setEditable(true);
        szoveg.setLineWrap(true);
        szoveg.setRows(25);
        szoveg.setColumns(80);
        jsp= new JScrollPane(szoveg);
        jsp.setSize(400,400);

        //felső sor
        targynev=new JLabel("Tárgynév: ");
        targykod=new JLabel("Tárgykód: ");
        tnev = new JTextField();
        tnev.setColumns(10);
        tkod = new JTextField();
        tkod.setColumns(10);

        //gombok
        add=new JButton("Hozzáadás");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTargy(tkod.getText());
                refreshText();
            }
        });

        start=new JButton("Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kivalasztottak.size()>0){
                    ArrayList<Targy> eredmeny = new ArrayList<Targy>();
                    eredmeny.addAll(searchSimilarTargy(tkod.getText()));
                    if(eredmeny.isEmpty()){
                        szoveg.setText("Nincsenek hasonló tárgyak.");
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

        clear=new JButton("Lista törlése");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kivalasztottak.clear();
                refreshText();
            }
        });

        remove = new JButton("Törlés");
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTargy(tkod.getText());
                refreshText();
            }
        });

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


        JPanel felso = new JPanel();
        felso.add(targynev);
        felso.add(tnev);
        felso.add(targykod);
        felso.add(tkod);
        felso.add(add);
        felso.add(start);
        felso.add(remove);
        felso.add(clear);


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

        targyak = new JTextArea("Jelenleg nincsenek hozzáadott tárgyak.");
        targyak.setEditable(false);
        targyak.setLineWrap(true);
        targyak.setBackground(new Color(0,0,0,0));
        targyak.setColumns(80);
        targyak.setRows(15);
        jsp2= new JScrollPane(targyak);
        jsp2.setSize(100,100);

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
        frame.add(jsp2, BorderLayout.SOUTH);
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

    public List<Targy> searchSimilarTargy(String param){
        try{
            return SimilarityFinder.findSimilar(kivalasztottak, new Suly(
                semester.getSelectedIndex(),
                kovkod.getSelectedIndex(),
                kredit.getSelectedIndex(),
                felev.getSelectedIndex(),
                nev.getSelectedIndex()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ArrayList<Targy>();
    }

    public void addTargy(String param){
        if(param != null && param.length()>0)   {
            for(Targy t : ParseHTML.mTargyak){
                if(t.mKod != null && t.mKod.contains(param)){
                   kivalasztottak.add(t);
                   return;

                }
            }
        }
    }

    public void removeTargy(String param){
        if(param != null && param.length()>0)   {
            for(Targy t : kivalasztottak){
                if(t.mKod != null && t.mKod.contains(param)){
                    kivalasztottak.remove(t);
                    return;

                }
            }
        }
    }

    public void refreshText(){
        targyak.setText("");
        if(kivalasztottak.size()>0){
           String s = "Hozzáadott tárgyak: ";
            for(Targy t : kivalasztottak){
                s+=t.toText();
            }
            s=s.substring(0, s.length()-2);
            targyak.setText(s);

        }   else{
            targyak.setText("Jelenleg nincsenek hozzáadott tárgyak.");
        }
    }
}

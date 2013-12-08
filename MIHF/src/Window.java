import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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
    public JLabel targynev, targykod;
    public JTextField tnev, tkod;
    public  JTextArea szoveg;
    public JScrollPane jsp;

    public Window() {

        frame=new JFrame();
        frame.setTitle("SuperMI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800,800));
        frame.setResizable(false);

        szoveg=new JTextArea();
        szoveg.setEditable(true);
        szoveg.setLineWrap(true);
        szoveg.setRows(15);
        szoveg.setColumns(30);
        jsp= new JScrollPane(szoveg);
        jsp.setSize(200,200);

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
                    szoveg.setText(searchTargykod(tkod.getText()));
                }
            }
        });

        JPanel felso = new JPanel();
        felso.add(targynev);
        felso.add(tnev);
        felso.add(targykod);
        felso.add(tkod);

        frame.setLayout(new BorderLayout());
        frame.add(felso, BorderLayout.NORTH);




        frame.add(new JPanel().add(jsp), BorderLayout.CENTER);

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

    public String searchTargykod(String param){
        if(param != null && param.length()>0)   {
            for(Targy t : ParseHTML.mTargyak){
                if(t.mKod != null && t.mKod.contains(param)){
                    return t.mNev + ": " + t.mKod;
                }

            }
        }

        return "Nincs ilyen tárgy.";

    }
}

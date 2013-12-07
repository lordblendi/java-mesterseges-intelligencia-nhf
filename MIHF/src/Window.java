import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Nóra
 * Date: 2013.12.07.
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
public class Window {
    private JFrame frame;
    private JLabel targynev, targykod;
    private JTextField tnev, tkod;
    private  JTextArea szoveg;
    private JScrollPane jsp;

    public Window() {

        frame=new JFrame();
        frame.setTitle("SuperMI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,500));
        frame.setResizable(false);

        targynev=new JLabel("Tárgynév: ");
        targykod=new JLabel("Tárgykód: ");
        tnev = new JTextField();
        tnev.setColumns(10);
        tkod = new JTextField();
        tkod.setColumns(10);

        JPanel felso = new JPanel();
        felso.add(targynev);
        felso.add(tnev);
        felso.add(targykod);
        felso.add(tkod);

        frame.setLayout(new BorderLayout());
        frame.add(felso, BorderLayout.NORTH);

        szoveg=new JTextArea();
        szoveg.setEditable(true);
        szoveg.setLineWrap(true);
        szoveg.setRows(15);
        szoveg.setColumns(30);
        jsp= new JScrollPane(szoveg);
        jsp.setSize(200,200);

        frame.add(new JPanel().add(jsp), BorderLayout.CENTER);

        frame.setVisible(true);

    }
}

package webwviewselenium.GUI;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import org.openqa.selenium.Dimension;

public class MainFrame extends JFrame implements ActionListener {

    JButton scan = new JButton("Scan");
    JButton compare = new JButton("Compare");
    JButton settings = new JButton("Settings");

    public MainFrame() {

        super("WebviewTester");
        JLayeredPane a = new JLayeredPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenSize.getWidth() / 2);
        int y = (int) (screenSize.getHeight() / 2);
        setLocation(x, y);

        scan.addActionListener(this);

        compare.addActionListener(this);
        settings.addActionListener(this);

        add(scan);

        add(compare);
        add(settings);

        setLayout(new GridLayout(3, 1));

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == settings) {
            dispose();
            SettingsFrame np = new SettingsFrame();  
            np.setVisible(true);

        }
        if (source == scan) {
            dispose();
            ScanFrame np = new ScanFrame();  
            np.setVisible(true);

        }
    }



}

package webwviewselenium.GUI;

import webwviewselenium.GUI.MainFrame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class SettingsFrame extends JFrame implements ActionListener {

    JButton ConfigPaths = new JButton("Configure Paths");
    JButton BrowserSelect = new JButton("Browser Select");
    JButton ClearDB = new JButton("Clear scan Database");
    JButton Back = new JButton("Back");
    JButton Uninstall = new JButton("Uninstall");

    public SettingsFrame() {

        super("WebviewTester");
        JLayeredPane a = new JLayeredPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenSize.getWidth() / 2);
        int y = (int) (screenSize.getHeight() / 2);
        setLocation(x, y);

        ConfigPaths.addActionListener(this);

        BrowserSelect.addActionListener(this);
        Back.addActionListener(this);

        add(ConfigPaths);
        add(BrowserSelect);
        add(ClearDB);
        add(Uninstall);
        add(Back);

        setLayout(new GridLayout(5, 1));

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == Back) {
            dispose();
            MainFrame np = new MainFrame();
            np.setVisible(true);

        }
        if (source == ConfigPaths) {
            dispose();
            ConfigPathsFrame np = new ConfigPathsFrame();
            np.setVisible(true);

        }
    }
}

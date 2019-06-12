/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webwviewselenium.GUI;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import org.apache.commons.io.FileUtils;
import static webwviewselenium.XMLHendler.GetBooksAvaliabeforScan;
import static webwviewselenium.XMLHendler.GetScans;

public class ClearDatabaseFrame extends JFrame implements ActionListener {

    String SelectedScan;
    String pathToDelete;
    List<String> ScansAvaliable = new ArrayList<String>();

    String[] ScanInfo = new String[GetScans().size()];
    String[] ScanPaths = new String[GetScans().size()];
    JLabel Book = new JLabel("Book:");
    JButton Back = new JButton("Back");
    JButton Delete = new JButton("Delete");
    JComboBox<String> bookList = new JComboBox<>();

    public ClearDatabaseFrame() {

        super("WebviewTester");

        JLayeredPane a = new JLayeredPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenSize.getWidth() / 2);
        int y = (int) (screenSize.getHeight() / 2);
        setLocation(x, y);
        setLayout(new GridLayout(4, 1));

        add(Book);

        ScansAvaliable = GetScans();

        for (int i = 0; i < GetScans().size(); i++) {
            final String[] parts = GetScans().get(i).split("<>");
            ScanInfo[i] = parts[0];
            ScanPaths[i] = parts[1];

        }
        bookList.removeAllItems();
        for (String s : ScanInfo) {
            bookList.addItem(s);
        }

        add(bookList);
        SelectedScan = (String) bookList.getSelectedItem();
        Delete.addActionListener(this);
        Back.addActionListener(this);
        add(Delete);
        add(Back);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == Back) {
            dispose();
            SettingsFrame np = new SettingsFrame();
            np.setVisible(true);

        }
        if (source == Delete) {

            //SelectedScan
            for (int i = 0; i < GetScans().size(); i++) {
                if (ScanInfo[i].startsWith(SelectedScan)) {

                    pathToDelete = ScanPaths[i];
                }

            }

            Path path = Paths.get(pathToDelete);
            try {

                FileUtils.deleteDirectory(path.toFile());
            } catch (IOException ex) {
                Logger.getLogger(ClearDatabaseFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            ScansAvaliable = GetScans();
            String[] ScanInfo = new String[GetScans().size()];
            String[] ScanPaths = new String[GetScans().size()];
            for (int i = 0; i < GetScans().size(); i++) {
                final String[] parts = GetScans().get(i).split("<>");
                ScanInfo[i] = parts[0];
                ScanPaths[i] = parts[1];

            }
            bookList.removeAllItems();
            for (String s : ScanInfo) {
                bookList.addItem(s);
            }
        }

    }
}

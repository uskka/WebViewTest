/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webwviewselenium.GUI;

import java.awt.AWTException;
import webwviewselenium.GUI.MainFrame;
import webwviewselenium.BookScan.FolderManager;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import static webwviewselenium.BookScan.FolderManager.NewScanFolderSetup;
import static webwviewselenium.GUI.ComboBoxSearch.createComboBox;
import static webwviewselenium.WebwviewSelenium.GetBookID;
import static webwviewselenium.WebwviewSelenium.MakeScreenShots;
import static webwviewselenium.XMLHendler.GetBooksAvaliabeforScan;

public class ScanFrame extends JFrame implements ActionListener {

    JButton Start = new JButton("Start");
    JButton Stop = new JButton("Stop");
    JButton Back = new JButton("Back");
    JLabel Book = new JLabel("Book:");
    JLabel LoadingGif = new JLabel();
    String SelectedBook;

    String[] BookNames = new String[GetBooksAvaliabeforScan().size()];
    private String selectedBook;

// add to the parent container (e.g. a JFrame):
// get the selected item:
    public ScanFrame() {

        super("WebviewTester");
        for (int i = 0; i < BookNames.length; i++) {
            BookNames[i] = GetBooksAvaliabeforScan().get(i);

        }
        JComboBox<String> bookList = new JComboBox<>(BookNames);
        JLayeredPane a = new JLayeredPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenSize.getWidth() / 2);
        int y = (int) (screenSize.getHeight() / 2);
        setLocation(x, y);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Start.addActionListener(this);

        Stop.addActionListener(this);
        Back.addActionListener(this);

        c.weightx = 0.5;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(Book, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.gridx = 1;
        c.gridy = 0;
        add(createComboBox(BookNames), c);

        c.ipady = 20;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;

        // add(pbar, c);
        c.insets = new Insets(10, 0, 0, 0);

        c.ipady = 20;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        add(Start, c);

        c.insets = new Insets(0, 0, 0, 0);
        c.ipady = 20;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        add(Stop, c);

        c.ipady = 20;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        add(Back, c);

        SelectedBook = (String) bookList.getSelectedItem();
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
        if (source == Start) {

            try {
    
                String date = LocalDateTime.now().getSecond() + "." +  LocalDateTime.now().getMinute() + "." +LocalDateTime.now().getHour()+"."+LocalDateTime.now().getDayOfMonth()+"."+ LocalDateTime.now().getMonthValue()+"."+LocalDateTime.now().getYear();
                
                String folder = NewScanFolderSetup(SelectedBook, GetBookID(SelectedBook), date);
                MakeScreenShots("Biology",folder);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ScanFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ScanFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(ScanFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AWTException ex) {
                Logger.getLogger(ScanFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ScanFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ScanFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}

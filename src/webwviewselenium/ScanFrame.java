/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webwviewselenium;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import static webwviewselenium.ComboBoxSearch.createComboBox;

public class ScanFrame extends JFrame implements ActionListener {

    JButton Start = new JButton("Start");
    JButton Stop = new JButton("Stop");
    JButton Back = new JButton("Back");
    JLabel Book = new JLabel("Book:");
    String[] bookTitles = new String[]{"Effective Java", "Head First Java",
        "Thinking in Java", "Java for Dummies"};

    JComboBox<String> bookList = new JComboBox<>(bookTitles);

// add to the parent container (e.g. a JFrame):
// get the selected item:
    public ScanFrame() {

        super("WebviewTester");
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
        add(createComboBox(bookTitles), c);

        
        c.insets = new Insets(10,0,0,0);
        
        c.ipady = 20;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        add(Start, c);
        
        c.insets = new Insets(0,0,0,0);
         c.ipady = 20;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        add(Stop, c);
        
         c.ipady = 20;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        add(Back, c);
        String selectedBook = (String) bookList.getSelectedItem();
        System.out.println("You seleted the book: " + selectedBook);

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
    }
}

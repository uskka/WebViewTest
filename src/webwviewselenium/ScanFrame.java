/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webwviewselenium;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;


public class ScanFrame extends JFrame implements ActionListener {

    

    JButton Start = new JButton("Start");
    JButton Stop = new JButton("Stop");
    JButton Back = new JButton("Back");
    
    

    public ScanFrame() {
        
        super("WebviewTester");
        JLayeredPane a = new JLayeredPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenSize.getWidth() / 2);
        int y = (int) (screenSize.getHeight() / 2);
        setLocation(x, y);
        
        
        Start.addActionListener(this);
       
        Stop.addActionListener(this);
        Back.addActionListener(this);

        add(Start);
        
        add(Stop);
        add(Back);
        
        setLayout(new GridLayout(3, 1));

      
        setVisible(true);

    }
    
       

    @Override
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();

        if (source == Back) {
           dispose();
            MainFrame np=new MainFrame();  //NewJframe is the name of my next Jframe that would be shown
            np.setVisible(true);

       }
    }
}

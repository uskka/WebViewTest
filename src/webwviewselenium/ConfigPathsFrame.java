/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webwviewselenium;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


public class ConfigPathsFrame extends JFrame implements ActionListener {

    

    JButton scan = new JButton("Scan");
    JButton compare = new JButton("Compare");
    JButton Back = new JButton("Back");
    JButton Driver = new JButton("Driver path");
    JTextField DriverPath = new JTextField(100);
    JButton Config = new JButton("Config json path");
    JTextField ConfigPath = new JTextField(100);
    
    public ConfigPathsFrame() {
        
        super("WebviewTester");
        JLayeredPane a = new JLayeredPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenSize.getWidth() / 2);
        int y = (int) (screenSize.getHeight() / 2);
        setLocation(x, y);
        
        Config.addActionListener(this);
        Driver.addActionListener(this);
        DriverPath.addActionListener(this);
        scan.addActionListener(this);
        compare.addActionListener(this);
        Back.addActionListener(this);
        
   
        
        add(Config);
        add(ConfigPath);
        add(Driver);
        add(DriverPath);
        
        add(Back);
        
        setLayout(new GridLayout(3, 2));

      
        setVisible(true);

    }
    
       

    @Override
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();

        if (source == Back) {
           dispose();
            SettingsFrame np=new SettingsFrame();  //NewJframe is the name of my next Jframe that would be shown
            np.setVisible(true);

       }
        if (source == Driver) {
        DriverChooserDriver();

       }
        if (source == Config) {
        DriverChooserConfig();

       }
    }

public void DriverChooserDriver() {

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select an image");
		jfc.setAcceptAllFileFilterUsed(false);
		//file name filter could be added
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
		}
                DriverPath.setText(jfc.getSelectedFile().getPath());

	}

public void DriverChooserConfig() {

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select config .json");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("json file", "json");
		jfc.addChoosableFileFilter(filter);
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
		}
                ConfigPath.setText(jfc.getSelectedFile().getPath());

	}
}
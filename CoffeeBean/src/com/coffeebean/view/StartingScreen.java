package com.coffeebean.view;

/**
* @(#)StartingScreen.java
*
* displays the logo
*
* @author Gokce Muge CIL
* @version 1.00 2014/05/05
*/

import java.awt.*;
import javax.swing.*;

public class StartingScreen extends JWindow {
    
	private static final long serialVersionUID = 6115388952193718782L;
	
	//Variables
	private int duration;
   
	//Constructor
	public StartingScreen(int d) {
        duration = d;
    }

    public void showSplash() {
    
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);

        // Set the window's bounds, centering the window
        int width = 314;
        int height =318;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);

        // Build the splash screen
        JLabel label = new JLabel(new ImageIcon("extra\\icon\\coffee.png"));
        Color borderColor = new Color(87, 161, 89);

        content.add(label, BorderLayout.CENTER);
        content.setBorder(BorderFactory.createLineBorder(borderColor, 10));

        setVisible(true);

        try { Thread.sleep(duration); } catch (Exception e) {}

        setVisible(false);
    }

}
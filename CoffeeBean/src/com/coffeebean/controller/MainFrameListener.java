package com.coffeebean.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import com.coffeebean.view.MainContainer;

/**
* @(#)MainFrameListener.java
*
* Take a MainContainer as a parameter and execute frame actions
*
* @author Mustafa Erdem
* @version 1.00 2014/05/01
*/

public class MainFrameListener implements WindowListener {

	//Variables
	private MainContainer container;
	
	//Constructor
	public MainFrameListener( MainContainer container) {
		this.container = container;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		attemptExit( container );
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//Show a confirm dialog and ask user if he wants to save or not
	public static void attemptExit(MainContainer container){
		
		int answer = JOptionPane.showConfirmDialog(null, "Do you want to save your work before exit ?");
		
		if( answer == JOptionPane.YES_OPTION ){
			
			//Save everything before exit
			container.getCodeEditorCore().saveAll();
			//Perform exit
			System.exit(0);
		}
		
		if( answer == JOptionPane.NO_OPTION ){
			System.exit(0);
		}
	
	
	}


}

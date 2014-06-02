package com.coffeebean.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.coffeebean.core.ExampleCore;
import com.coffeebean.utilities.FileManager;
import com.coffeebean.view.ColorsForm;
import com.coffeebean.view.ExampleViewer;
import com.coffeebean.view.FontsForm;
import com.coffeebean.view.MainContainer;

/**
* @(#)MainActionListener.java
*
* Take a MainContainer as a parameter and execute action commands of MenuBar components
*
* @author Elbruz Özen
* @version 1.00 2014/05/01
*/

public class MainActionListener implements ActionListener {

	//Variables
	private MainContainer container;
	
	//Constructor
	public MainActionListener( MainContainer masterContainer ){
		
		this.container = masterContainer;
	
	}

	public void actionPerformed(ActionEvent e) {
		
		//Get source
		Object sourceItem = e.getSource();
	
		//Shortcuts
		if( sourceItem == container.getSaveAll_buildAllButton() ){
			container.getCodeEditorCore().saveAll();
			container.getCodeEditorCore().buildAll();
		}	
		
		else if( sourceItem == container.getSaveAll_buildAll_runButton() ){
			container.getCodeEditorCore().saveAll();
			boolean success = container.getCodeEditorCore().buildAll();
			
			//If builde operation is successfull, perform run operation
			//Prevents running obselete .class file
			if(success){
				container.getCodeEditorCore().runFile();
			}
		}	
		
		//File
		else if( sourceItem == container.getNewFile() ){
			container.getCodeEditorCore().newFileDialog();
		}	
		else if( sourceItem == container.getOpen() ){
			container.getCodeEditorCore().openFileDialog();
		}
		else if( sourceItem == container.getOpenDir() ){
			container.getCodeEditorCore().openCurrentDirectory();
		}
		else if( sourceItem == container.getSave() ){
			container.getCodeEditorCore().saveFile();
		}	
		else if( sourceItem == container.getSaveAll() || sourceItem == container.getSaveAllButton() ){
			container.getCodeEditorCore().saveAll();
		}	
		else if( sourceItem == container.getExit()){
			MainFrameListener.attemptExit(container);
		}
			
		//Tools
		else if( sourceItem == container.getBuild()){
			container.getCodeEditorCore().buildFile();
		}
		else if( sourceItem == container.getBuildAll()){
			container.getCodeEditorCore().buildAll();
		}	
		else if( sourceItem == container.getRun()){
			container.getCodeEditorCore().runFile();
		}
		
		//Examples
		else if( sourceItem == container.getExamples()){
			
			SwingUtilities.invokeLater( new Runnable() {
				
				@Override
				public void run() {
					
					ExampleCore eCore = null;
					try {
						eCore = new ExampleCore( container.getCodeEditorCore());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					@SuppressWarnings("unused")
					ExampleViewer eFrame = new ExampleViewer(eCore);
					
				}
			});
			
		}
		
		//Personalization
		else if( sourceItem == container.getBackgroundColor()){
			
			new ColorsForm(container).setVisible( true );
		
		}
		
		else if( sourceItem == container.getCodeEditorFont()){
			
			new FontsForm(container).setVisible( true);
		
		}
		
		//About
		else if( sourceItem == container.getCredits()){
			
			SwingUtilities.invokeLater( new Runnable() {	
				
				@Override
				public void run() {
					
					JOptionPane.showMessageDialog(null, "CoffeeBean Java IDE"
							+ "\nMay 2014\n---------------------------"
							+ "\nBerk Yurttaþ\nElbruz Özen\n"
							+ "Gamze Gül\nGökçe Müge Çil\nMustafa Erdem", "Credits", JOptionPane.INFORMATION_MESSAGE , 
							new ImageIcon("extra\\icon\\coffee.png"));
					
				}
		
			});
		
		}
		
		//Help
		else if( sourceItem == container.getHelp()){
			
			SwingUtilities.invokeLater( new Runnable() {	
				
				@Override
				public void run() {
					
					FileManager reader = null;
					
					try {
						reader = new FileManager();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					JTextArea textArea = new JTextArea( reader.readFile( "extra\\conf\\help.txt" ) );
					JScrollPane scrollPane = new JScrollPane(textArea);  
					
					textArea.setLineWrap(true);  
					textArea.setWrapStyleWord(true); 
					textArea.setEditable( false);
					
					scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
					
					JOptionPane.showMessageDialog(null, scrollPane, "Help Page",  
					                                       JOptionPane.QUESTION_MESSAGE);
				
					
				}
			
			});
		
		}
	
	}

}

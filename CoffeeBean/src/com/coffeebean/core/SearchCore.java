package com.coffeebean.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import com.coffeebean.utilities.CommandManager;
import com.coffeebean.utilities.FileManager;
import com.coffeebean.view.TutorialPanel;

/*
 * Contain core functions of SearchPanel
 * Author: Gamze Gül
 * 05.04.2014
 * 
 */


public class SearchCore{
	
	//properties
	private String javaDocAdress;
	private CommandManager cManager;
	private HashMap<String, String> engineList;
	private TutorialPanel panel;
	private FileManager fManager;
	
	//constructors
	public SearchCore() throws IOException{
		
		cManager = new CommandManager();
		javaDocAdress = "http://docs.oracle.com/javase/7/docs/api/overview-summary.html";
		fManager = new FileManager();
		engineList = loadSearchEngines();
		
	} 
		
	//opens java documentation
	public void openJavaDoc(){

			try {
				cManager.openURL( javaDocAdress );
			} catch (IOException e1) {
				
				JOptionPane.showMessageDialog(null, "Ooops, Web page can not be opened!");
				e1.printStackTrace();
			}
		
	}
	
	//Opens given search address
	public void openSearchPage( String engine , String key ){

		try {
			cManager.openURL( engine+key );
		} catch (IOException e1) {
			
			JOptionPane.showMessageDialog(null, "Ooops, Web page can not be opened!");
			e1.printStackTrace();
		}
	
	}
	
	//Load searchEngines and their addresses
	public HashMap<String, String> loadSearchEngines(){
		
		HashMap<String , String> engines = new HashMap<String, String>();
		
		File source = new File( "extra\\conf\\engine.dat" );
		
		//If file does not exist, format the file
		if( !source.exists() ){
	
			fManager.writeToFile( source.getPath(), "Stack Overflow\nhttp://stackoverflow.com/search?q=" );
			
		}
		
		//Read text
		String text = fManager.readFile( source.getPath());
		String[] fragments = text.split("\n");
		
		//Create fragments
		for( int i = 0 ; i < fragments.length ; i = i + 2 ){
			
			engines.put( fragments[i].trim() , fragments[i+1].trim() );
			
		}
		
		return engines;
		
	}
	
	//Getters and Setters
	public CommandManager getcManager() {
		return cManager;
	}

	public void setcManager(CommandManager cManager) {
		this.cManager = cManager;
	}

	public HashMap<String, String> getEngineList() {
		return engineList;
	}

	public void setEngineList(HashMap<String, String> engineList) {
		this.engineList = engineList;
	}

	public TutorialPanel getPanel() {
		return panel;
	}

	public void setPanel(TutorialPanel panel) {
		this.panel = panel;
	}
	
}

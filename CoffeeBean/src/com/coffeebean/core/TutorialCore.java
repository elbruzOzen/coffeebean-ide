package com.coffeebean.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.coffeebean.utilities.CommandManager;
import com.coffeebean.utilities.FileManager;

/**
* @(#)CommandManager.java
*
* This class provides the logic behind TutorialPanel
*
* @author Berk Yurttaþ
* @version 1.00 2014/05/01
*/

public class TutorialCore {
	
	//Properties
	private FileManager fManager;
	private CommandManager cManager;

	private String[] tutorialFragments;
	private int tutorialIndex;
	
	private String tutorialTextExt = ".txt";
	private String tutorialVideoExt = ".mp4";
	private String tutorialHTMLExt = ".html";
	private String tutorialHTMLExt2 = ".htm";
	
	//Constructor
	public TutorialCore(){
		
		super();
		
		tutorialIndex = 0;
		
		tutorialFragments = new String[0];
		
		try {
			cManager = new CommandManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			fManager = new FileManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Load tutorial files
	public File[] loadTutorials(){
		
		File folder = new File("extra\\tutorials");
		File[] listOfFiles = folder.listFiles(); 
		return listOfFiles;
	
	}
	
	//Load tutorial names
	public ArrayList<String> loadTutorialNames(){
		
		File[] files = loadTutorials();
		ArrayList< String > tutorialNames = new ArrayList<>();
		
		for( int i = 0 ; i < files.length ; i++){
			
			if( files[i].getName().endsWith("htm") || files[i].getName().endsWith("html") 
					|| files[i].getName().endsWith("mp4") || files[i].getName().endsWith("mkv"))
			tutorialNames.add( files[i].getName() );
		}
		
		return tutorialNames;
		
	}
	
	//Getters and Setters
	public String getTutorial(int i){
		return tutorialFragments[i];
	}
	
	public String[] getTutorialFragments(){
		return tutorialFragments;
	}
	
	public void setTutorialFragments(String[] fragments){
		this.tutorialFragments = fragments;
	}
	
	public int getTutorialIndex(){
		return tutorialIndex;
	}
	
	public void setTutorialIndex(int index){
		this.tutorialIndex = index;
	}
	
	public String getTutorialTextExt() {
		return tutorialTextExt;
	}

	public void setTutorialTextExt(String tutorialTextExt) {
		this.tutorialTextExt = tutorialTextExt;
	}

	public String getTutorialVideoExt() {
		return tutorialVideoExt;
	}

	public void setTutorialVideoExt(String tutorialVideoExt) {
		this.tutorialVideoExt = tutorialVideoExt;
	}

	public String getTutorialHTMLExt() {
		return tutorialHTMLExt;
	}

	public void setTutorialHTMLExt(String tutorialHTMLExt) {
		this.tutorialHTMLExt = tutorialHTMLExt;
	}

	public String getTutorialHTMLExt2() {
		return tutorialHTMLExt2;
	}

	public void setTutorialHTMLExt2(String tutorialHTMLExt2) {
		this.tutorialHTMLExt2 = tutorialHTMLExt2;
	}
	
	public FileManager getfManager() {
		return fManager;
	}

	public void setfManager(FileManager fManager) {
		this.fManager = fManager;
	}

	public CommandManager getcManager() {
		return cManager;
	}

	public void setcManager(CommandManager cManager) {
		this.cManager = cManager;
	}
	
}

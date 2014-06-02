package com.coffeebean.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.coffeebean.utilities.CommandManager;
import com.coffeebean.utilities.FileManager;

/*
 * ExampleCore.java
 * 
 * Author: Berk Yurttaþ
 * 
 * Connect ExamplePanel and background functions of tutorials
 * 
 */

public class ExampleCore {
	
	//Variables
	private FileManager fManager;
	private CommandManager cManager;
	private CodeEditorCore codeEditorCore;
	private String currentFileName;
	
	//Constructor
	public ExampleCore( CodeEditorCore codeEditorCore ) throws IOException{
		super();
		
		this.codeEditorCore = codeEditorCore;
		fManager = new FileManager();
		cManager = new CommandManager();
	}
	
	public File[] loadExamples(){
		
		File folder = new File("extra\\examples");
		File[] listOfFiles = folder.listFiles(); 
		return listOfFiles;
	
	}
	
	public ArrayList<String> loadExampleNames(){
		
		File[] files = loadExamples();
		ArrayList< String > tutorialNames = new ArrayList<>();
		
		for( int i = 0 ; i < files.length ; i++){
			
			tutorialNames.add( files[i].getName() );
			
		}
		
		return tutorialNames;
		
	}
	
	
	//Getters and Setters
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

	
	public void setCurrentFileName( String fileName ){
		currentFileName = fileName;
	}
	
	
	public void addExamples(String path){
		
		codeEditorCore.openFile( path );
		
	}

	public CodeEditorCore getCodeEditorCore() {
		return codeEditorCore;
	}

	public void setCodeEditorCore(CodeEditorCore codeEditorCore) {
		this.codeEditorCore = codeEditorCore;
	}

	public String getCurrentFileName() {
		return currentFileName;
	}
	

}

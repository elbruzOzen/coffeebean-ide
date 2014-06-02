package com.coffeebean.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
* @(#FileManager.java
*
* Manage  files created by CoffeeBean on Hard Disk
*
* @author Elbruz Özen
* @version 1.00 2014/05/01
*/


public class FileManager {

	//Properties
	private File file;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	
	public FileManager() throws IOException{
		
		file = null;
	
	}
	
	public  boolean exists( String path ){
		
		file = new File( path );
		return file.exists();
	
	}
	
	public  String readFile( String path ){
		
		try{
			
			file = new File( path );
			
			fileReader = new FileReader( file );
			bufferedReader = new BufferedReader( fileReader );
			
			String text = "";
			String temp = "";
			
			// Read the text
			while (true) {
	
				temp = bufferedReader.readLine();
	
				if (temp == null)
					break;
	
				text = text + temp + "\n";
			}
	
			// Close utilities
			bufferedReader.close();
			fileReader.close();
	
			// Return context of text
			return text;
		
		}catch(Exception e){
			
			System.out.println( e );
			return null;
			
		}
	
	}
	
	//Write the given text to given adress( It does not append, completely replaces the file )
	public boolean writeToFile( String string , String text ){
		
		try {
			
			fileWriter = new FileWriter( string );
			bufferedWriter = new BufferedWriter( fileWriter );

			String[] lines = text.split("\n");

			for (int i = 0; i < lines.length; i++) {
				bufferedWriter.append(lines[i]);
				bufferedWriter.newLine();
			}

			bufferedWriter.close();
			fileWriter.close();

			return true;
		
		} catch (Exception e) {
			
			System.out.println( e );
			return false;
		
	    }
		
	}

}

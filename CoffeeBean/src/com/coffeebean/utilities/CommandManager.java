package com.coffeebean.utilities;

import java.io.IOException;
import java.util.Scanner;
import java.io.InputStream;

/**
* @(#)CommandManager.java
*
* Provides an communcation line between cmd.exe and CoffeeBean
*
* @author Elbruz Özen
* @version 1.00 2014/05/01
*/

public class CommandManager {

	private Runtime runtime;
	private Process process;
	
	private InputStream inputStream;
	private Scanner scanner;
	
	private String response;
	 
	public CommandManager() throws IOException{
		
		//Take the current runtime of our program
		runtime = Runtime.getRuntime();
	
	}
	
	//Execute a single string command and return response
	public String runCommand( String command ) throws IOException
	{
		process = runtime.exec(command);
		response = returnResponse(process);
		return response;
	}
	
	//Execute list of commands and return response
	public String runCommand( String[] commands ) throws IOException
	{
		process = runtime.exec( commands );
		response = returnResponse(process);
		return response;
	}
	
	//Open the URL given as a String 
	public void openURL ( String url ) throws IOException
	{
		String[] commands = { "cmd" , "/c" , "start " + url };
		process = runtime.exec( commands );
	}
	
	//Open a new cmd.exe and run file, response is not needed
	public void runMedia( String mediaFile ) throws IOException
	{
			String[] commands = { "cmd" , "/c" , mediaFile };
			process = runtime.exec( commands );
	}
	
	//Execute compilation and return response( includes compilation errors )
	public String compile( String compiler , String file ) throws IOException
	{
		process = runtime.exec( compiler + " " + file );
		response = returnResponse(process);
		return response;
	
	}
	
	//Open a new cmd.exe and run file, response is not needed
	public void runFile( String runner , String file ) throws IOException
	{
		String[] commands = { "cmd" , "/k" , "start" , runner , file };
		process = runtime.exec( commands );
		
	}
	
	//Take a process as parameter, return response as String

	@SuppressWarnings("resource")
	public String returnResponse( Process process ) throws IOException{
		
		inputStream = process.getErrorStream();
        scanner = new Scanner( inputStream ).useDelimiter("\\A");
        
        String response = "";
        
        //Read if there is input
        if ( scanner.hasNext() ) {
            response = scanner .next();
        }
        else {
            response = "";
        }
        
        //Close streams and readers after reading
        inputStream.close();
        scanner.close();
        
        return response;
		
	}
	
}
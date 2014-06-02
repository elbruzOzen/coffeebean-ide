package com.coffeebean.core;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.coffeebean.controller.CodeTypeListener;
import com.coffeebean.controller.NewLineFilter;
import com.coffeebean.utilities.CommandManager;
import com.coffeebean.utilities.FileManager;
import com.coffeebean.view.ClassPropertySelect;
import com.coffeebean.view.CodeEditorPanel;

/**
* @(#)CodeEditorCore.java
*
* This class holds the logic behind the CodeEditorPanel
*
* @author Elbruz Özen
* @version 1.00 2014/05/01
*/


public class CodeEditorCore {
	
	//Key which locks the auto-tab by NewLineFilter class
	private boolean permissionForTab;
	
	//Properties
	private CodeEditorPanel editorPanel;
	
	//Utilities
	private CommandManager cManager;
	private FileManager fManager;
	
	public CodeEditorCore( final CodeEditorPanel editorPanel ){
		
		super();
		
		permissionForTab = true;
		
		/*
		 * This is not a good way to do this, model has a view instance, 
		 * however there is no simple way to get text for the save method 
		 */
		
		this.setEditorPanel(editorPanel);

		try {
			cManager = new CommandManager();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			fManager = new FileManager();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveFile(){
		
		//Check for empty tab
		JScrollPane scrollPane = (JScrollPane)getEditorPanel().getCodeTabs().getSelectedComponent();
		
		
		//Check for empty tab
		if( scrollPane != null ){
			
			JViewport viewport = scrollPane.getViewport(); 
			JTextPane editorPane = (JTextPane)viewport.getView(); 
			
			//Get written code
			String text = editorPane.getText();
			//Get path to write on
			String path = getEditorPanel().getCodeTabs().getTitleAt(getEditorPanel().getCodeTabs().getSelectedIndex());
			
			//Write file on hard disk
			fManager.writeToFile( path  , text );
			
			editorPanel.getConsole().setText( "File successfully saved!" );
			
			
		}else{
			
			editorPanel.getConsole().setText( "There is no file to save!" );
			
		}
	}
	
	//Save all open tabs
	public void saveAll(){
		
		int tabCount = getEditorPanel().getCodeTabs().getTabCount();
		int oldIndex = getEditorPanel().getCodeTabs().getSelectedIndex();
		
		//Save all open tabs
		for( int i = 0 ; i  < tabCount ; i++){
			
			getEditorPanel().getCodeTabs().setSelectedIndex(i);
			
			JScrollPane scrollPane = (JScrollPane)getEditorPanel().getCodeTabs().getSelectedComponent();
			JViewport viewport = scrollPane.getViewport(); 
			JTextPane editorPane = (JTextPane)viewport.getView(); 
		
			String text = editorPane.getText();
			String path = getEditorPanel().getCodeTabs().getTitleAt(i);

			fManager.writeToFile( path  , text );
		}
		
		//Return to previous tab view
		getEditorPanel().getCodeTabs().setSelectedIndex( oldIndex );
		
		if( tabCount > 0)
			editorPanel.getConsole().setText( "Files successfully saved!" );
		else
			editorPanel.getConsole().setText( "There is no file to save!" );
	
	}
	
	public boolean buildFile(){
		
		//Check if there is an opened tab
		if(getEditorPanel().getCodeTabs().getSelectedComponent() != null)
		{
		
			String response = "";
			
			try {
				response = cManager.compile( "javac" , getEditorPanel().getCodeTabs().getTitleAt( getEditorPanel().getCodeTabs().getSelectedIndex()) );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//Update the console
			getEditorPanel().getConsole().setText( response );
			
			if( response.equals("")){
				getEditorPanel().getConsole().setText( "File sucessfully builded!" );
				return true;
			}
			
			//If there is an error, return false
			return false;

		}
		else{
			
			String response = "There is no file to build!";
			getEditorPanel().getConsole().setText( response );
			return false;
		}
	}
	
	public boolean buildAll(){
		
		boolean success = true;
		
		String response = "Files successfully builded!";
		
		int tabCount = getEditorPanel().getCodeTabs().getTabCount();
		int oldIndex = getEditorPanel().getCodeTabs().getSelectedIndex();
		
		getEditorPanel().getConsole().setText( response );
		
		//Save all open tabs
		for( int i = 0 ; i  < tabCount ; i++){
			
			getEditorPanel().getCodeTabs().setSelectedIndex(i);
	
			try {
				response = cManager.compile( "javac" , getEditorPanel().getCodeTabs().getTitleAt( getEditorPanel().getCodeTabs().getSelectedIndex()) );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			if( !response.equals("")){
				getEditorPanel().getConsole().setText( response );
				success = false;
			}
	
		}
		
		if( tabCount == 0 ){
			response = "There is no file to build!";
			getEditorPanel().getConsole().setText( response );
			success = false;
		}
		
		//Return to previous tab view
		getEditorPanel().getCodeTabs().setSelectedIndex( oldIndex );
		
		return success;
	
	}
	
	public void runFile(){
		
		if( getEditorPanel().getCodeTabs().getSelectedComponent() != null)
		{
			String fileName = getEditorPanel().getCodeTabs().getTitleAt( getEditorPanel().getCodeTabs().getSelectedIndex());
			
			try {
				cManager.runFile( "java", fileName.substring( 0, fileName.length() -5 ) );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else{

			getEditorPanel().getConsole().setText( "There is no file to run!" );
			
		}
		
	}
	
	public void openFileDialog(){
		
		final JFileChooser chooser = new JFileChooser();
		
		//Start with working directory
		File workingDirectory = new File(System.getProperty("user.dir"));
		chooser.setCurrentDirectory(workingDirectory);
		
		chooser.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				openFile(chooser.getSelectedFile().getName());
				
			}
		});
		
		chooser.showOpenDialog( null );
	
	}
	
	public void openFile( String path ){
		
		final JTextPane codePane = new JTextPane();
	
		JScrollPane scrollPane = new JScrollPane(codePane);
		
		codePane.setFont( getEditorPanel().getFont() );
		codePane.setText( fManager.readFile( path));
		
		//Get only name of the file
		String[] fragments = path.split( "\\\\" );
		String title = fragments[ fragments.length-1];
		
		getEditorPanel().getCodeTabs().add( title , scrollPane );
		getEditorPanel().getCodeTabs().setSelectedComponent(scrollPane);
		
		//Add CodeTypeListener
		codePane.addKeyListener( new CodeTypeListener ( codePane , this ));
	
		highlightKeyWords(codePane);
		
		//Add new line filter
		AbstractDocument doc = (AbstractDocument)codePane.getDocument();
		doc.setDocumentFilter( new NewLineFilter( codePane , this) );
	
	}
	
	public void openCurrentDirectory(){
		
		//List all files
		File folder = new File(System.getProperty("user.dir"));
		File[] listOfFiles = folder.listFiles();
		
		ArrayList<String> sourceFiles = new ArrayList<String>();
		
		for( File f: listOfFiles){
			
			if( f.getName().endsWith(".java")){
				
				sourceFiles.add( f.getName() );
				
			}
			
		}
		
		
		if( sourceFiles.size() == 0){
			editorPanel.getConsole().setText( "There is no source file in the your directory!" );
		}
		
		//Open all source files in directory
		for( String path : sourceFiles ){

			JTextPane codePane = new JTextPane();	
			JScrollPane scrollPane = new JScrollPane(codePane);	
				
			codePane.setFont( getEditorPanel().getFont() );
			codePane.setText( fManager.readFile( path));
			
			//Pick file name
			String[] fragments = path.split( "\\\\" );
			String title = fragments[ fragments.length-1];
			
			getEditorPanel().getCodeTabs().add( title , scrollPane );
			getEditorPanel().getCodeTabs().setSelectedComponent(scrollPane);
			
			//Add CodeTypeListener
			codePane.addKeyListener( new CodeTypeListener ( codePane , this ));
			
			highlightKeyWords(codePane);
			
			//Add new line filter
			AbstractDocument doc = (AbstractDocument)codePane.getDocument();
			doc.setDocumentFilter( new NewLineFilter( codePane , this) );
	
		}
		
	}
	
	public void newFile( String fileName , String fileType ,  boolean constructorExist , boolean mainExists ){
		
		
			//Create new codePane
			final JTextPane codePane = new JTextPane();
			JScrollPane scrollPane = new JScrollPane(codePane);
			
			
			codePane.setFont( getEditorPanel().getFont() );
			//Create simple code template
			
			//Paste the stating template to CodeEditor
			String initial = "public " + fileType +  " " + fileName + "{\n" ;
			
			if(constructorExist)
				initial = initial + "\n\tpublic " + fileName + "(){\n\n\t}\n";
			if(mainExists){
				
				initial = "import java.io.*;\n\n" + initial;
				initial = initial + "\n\tpublic static void main(String[] args) " + "{\n\n\n\n"
						+ "\t\t//Keep console open\n"
						+ "\t\ttry{\n"
						+ "\t\t\tSystem.in.read();\n"
						+ "\t\t}catch( IOException e){\n"
						+ "\t\t\te.printStackTrace();"
						+ "\n\t\t}"
						+ "\n\t}";
			}
				
				
			initial = initial + "\n}";
			
			codePane.setText( initial );
			
			//Add CodeTypeListener
			codePane.addKeyListener( new CodeTypeListener ( codePane , this ));
		
			//Add codePane to new tab
			getEditorPanel().getCodeTabs().add( fileName + ".java" , scrollPane );
			
			getEditorPanel().getCodeTabs().setSelectedIndex( getEditorPanel().getCodeTabs().getTabCount() - 1 );
		
			highlightKeyWords(codePane);
			
			//Add new line filter
			AbstractDocument doc = (AbstractDocument)codePane.getDocument();
	        doc.setDocumentFilter( new NewLineFilter( codePane , this) );
	}
	
	public void newFileDialog(){
		
		//Choose the new class properties
		@SuppressWarnings("unused")
		ClassPropertySelect selector = new ClassPropertySelect(this);
		
	
	}
	
	public void highlightKeyWords( JTextPane codePane ){
		
		StyledDocument doc = ((JTextPane) codePane).getStyledDocument();
        Style style = ((JTextPane) codePane ).addStyle("Code Style", null);
        String text = codePane.getText();
        
        text = text.replace("\n", "\n ").replace("\t", "\t ");
        
    	//Divide text into words
        String[] codeFragments = text.split(" ");
        codePane.setText("");
        
        for( String temp : codeFragments){
        	
        	//Choose String colors
        	if( temp.equals( "class" ) || temp.equals( "enum" ) || temp.equals( "interface" ) ||temp.equals( "package" ))
        		StyleConstants.setForeground(style, new Color(222, 13, 34));
        	else if( temp.equals( "int" ) || temp.equals( "void" ) || temp.equals( "boolean" ) || temp.equals( "byte" ) || temp.equals( "char" ) || temp.equals( "double" )
        			|| temp.equals( "float" ) || temp.equals( "long" ) || temp.equals( "short" ))
        		StyleConstants.setForeground(style, new Color(60, 68, 166));
        	else if( temp.equals( "extends" ) || temp.equals( "implements" ))
        		StyleConstants.setForeground(style, new Color(245, 113, 5));
        	else if( temp.equals( "import" ))
        		StyleConstants.setForeground(style, new Color(42, 113, 45));
        	else if( temp.equals( "public" ) || temp.equals( "private" ) || temp.equals( "protected" ))
        		StyleConstants.setForeground(style, new Color(198, 219, 57));
        	else
        		StyleConstants.setForeground(style, Color.black);
        	
        	//Add the word
        	try { doc.insertString( doc.getLength() , temp.replace("\n ", "\n").replace("\t ", "\t") , style); }
            catch (BadLocationException k){}
        	
         	//Add a space after each word pasted
        	if( temp != codeFragments[ codeFragments.length - 1 ] && !(temp.contains("\n") || temp.contains("\t")))
	        	try { doc.insertString( doc.getLength() , " " , style); }
	            catch (BadLocationException k){}
        }
    	
            
        try { doc.insertString( doc.getLength() , "\n" , style); }
        catch (BadLocationException k){}
	        	
	}
	
	
	//Getters and Setters
	public boolean isPermissionForTab() {
		return permissionForTab;
	}

	public void setPermissionForTab(boolean permissionForTab) {
		this.permissionForTab = permissionForTab;
	}
	
	public CodeEditorPanel getEditorPanel() {
		return editorPanel;
	}

	public void setEditorPanel(CodeEditorPanel editorPanel) {
		this.editorPanel = editorPanel;
	}

}

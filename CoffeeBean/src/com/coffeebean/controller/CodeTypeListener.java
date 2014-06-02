package com.coffeebean.controller;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextPane;
import com.coffeebean.core.CodeEditorCore;

/**
* @(#)CodeTypeListener.java
*
* Follows key strokes on CodeEditor for highlight event, not a bright solution but works 
* DocumentListener can not be used for modifying the CodeEditor text
*
* @author Elbruz Özen
* @version 1.00 2014/05/01
*/

public class CodeTypeListener implements KeyListener{

	//Text pane which will be edited
	private JTextPane codePane;
	
	//For using highlight method
	private CodeEditorCore core;
	
	//Constructor
	public CodeTypeListener( JTextPane codePane , CodeEditorCore core) {
		
		this.codePane = codePane;
		this.core = core;
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		//Get the current  position of caret
		int caret = getCodePane().getCaretPosition();
		
		//Highlight condition
		if( e.getKeyChar() == ' ' ||  e.getKeyChar() == '\b' ){
			
			//Auto tab OFF
			core.setPermissionForTab( false );
			core.highlightKeyWords( getCodePane() );
		}
		
		//Return the caret into old position
		if( caret < getCodePane().getStyledDocument().getLength() )
			getCodePane().setCaretPosition(caret);
		
		//Auto tab ON
		core.setPermissionForTab( true );
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//Getters and Setters
	public JTextPane getCodePane() {
		return codePane;
	}

	public void setCodePane(JTextPane codePane) {
		this.codePane = codePane;
	}

}

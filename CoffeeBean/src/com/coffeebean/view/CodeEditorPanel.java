package com.coffeebean.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.coffeebean.utilities.CloseButtonTabbedPane;


/**
* @(#)CodeEditorPanel.java
*
* CodeEditorPanel GUI for application
*
* @author Elbruz Özen
* @version 1.00 2014/05/01
*/

public class CodeEditorPanel extends JPanel{

	private static final long serialVersionUID = 8346615388462906787L;
	
	//Visual Components
	private CloseButtonTabbedPane codeTabs;
	private JTextArea console;
	private JScrollPane consoleScroll;
	
	//Variables
	private Font textFont;
	
	//Constructor
	public CodeEditorPanel() {
		
		super();
		
		codeTabs = new CloseButtonTabbedPane();
		console = new JTextArea(5,58);
		consoleScroll = new JScrollPane(console , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		
		textFont = new Font("Sans", Font.PLAIN, 20);
		
		setLayout( new BorderLayout());
		
		//Component properties
		codeTabs.setPreferredSize( new Dimension(660, 500));
		codeTabs.setFont(new Font("Sans", Font.PLAIN, 20));
		console.setEditable( false );
		
		//Add components to panel
		add(codeTabs , "Center");
		add(consoleScroll , "South");
		
		setPreferredSize( new Dimension(700, 660) );
		setBorder( BorderFactory.createEmptyBorder(10,10,10,10));
	
	}
	
	//Getters
	public CloseButtonTabbedPane getCodeTabs() {
		return codeTabs;
	}

	public JTextArea getConsole() {
		return console;
	}

	public Font getFont() {
		return textFont;
	}
	
	//Setters
	public void setFont(Font font) {
		this.textFont = font;
	}

	public void setCodeTabs(CloseButtonTabbedPane codeTabs) {
		this.codeTabs = codeTabs;
	}

	public void setConsole(JTextArea console) {
		this.console = console;
	}
	

}

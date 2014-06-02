package com.coffeebean.utilities;

import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
* @(#)Browser.java
*
* Modified version of JEditorPane, used to show HTML tutorial files
* Also has link click and navigating capabilities.
*
* @author Mustafa Erdem
* @version 1.00 2014/05/01
*/

public class TutorialBrowser extends JEditorPane implements HyperlinkListener {

	private static final long serialVersionUID = -2832555117986985949L;

	//Constructor
	public TutorialBrowser(){
	    
		super();
		
		//Do not user to edit content
		setEditable(false);
		addHyperlinkListener(this);
		
		this.setLayout(new FlowLayout());

	}

	//Link clicked method
	public void hyperlinkUpdate(HyperlinkEvent h) {
	
		//If link is clicked
		if(h.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
			//Load the page
			loadPage(h.getURL().toString());
		}
	
	}
	
	//Load page method takes URL as string
	private void loadPage(String userText){
		
		try {
			setPage(userText);
		} catch (IOException e) {
			//TODO
		}
		
	}

}
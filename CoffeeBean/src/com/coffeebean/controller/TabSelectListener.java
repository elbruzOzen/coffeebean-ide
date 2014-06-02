package com.coffeebean.controller;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * Listen and fire events when selected tab is changed
 * 
 */

public class TabSelectListener implements ChangeListener{

	public void stateChanged(ChangeEvent e) {
		
		//Simply take full focus when tab is selected
		JTabbedPane source = (JTabbedPane) e.getSource();
		source.setSelectedComponent( source.getSelectedComponent());
		
	}

}

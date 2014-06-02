package com.coffeebean.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.coffeebean.core.TutorialCore;
import com.coffeebean.utilities.TutorialBrowser;

/**
* @(#)MainContainer.java
*
* GUI side of tutorial panel, shows the tutorials
*
* @author Berk Yurttaþ
* @version 1.00 2014/05/01
*/

public class TutorialPanel extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = -4804069050249235256L;
	
	//Properties
	private TutorialBrowser tutorialPane;
	
	@SuppressWarnings("rawtypes")
	private JComboBox tutorialSelector;
	private TutorialCore core;
	private File[] tutorials;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TutorialPanel(TutorialCore core){
		
		super();
		this.core = core;
		setLayout( new BorderLayout());

		tutorials = core.loadTutorials();
		tutorialPane = new TutorialBrowser();
		tutorialSelector = new JComboBox( core.loadTutorialNames().toArray() );
	
		add(new JScrollPane( tutorialPane ), BorderLayout.CENTER);
		add(tutorialSelector, BorderLayout.NORTH);
		
		tutorialSelector.addActionListener( this );

		setBorder( BorderFactory.createEmptyBorder(10,10,10,10));
	
	}

	
	//Tutorial selection event
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == tutorialSelector ){
			
			
			if( tutorialSelector.getSelectedItem().toString().endsWith( core.getTutorialVideoExt() )){
				
				try {
					core.getcManager().runMedia( tutorials[ tutorialSelector.getSelectedIndex()].toString() );
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if( tutorialSelector.getSelectedItem().toString().endsWith( core.getTutorialHTMLExt() ) || tutorialSelector.getSelectedItem().toString().endsWith( core.getTutorialHTMLExt2() )){
				
				try {
					tutorialPane.setPage( ((File)tutorials[ tutorialSelector.getSelectedIndex()]).toURI().toURL() );
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
	
		}
	}

	
	//Getters and setters
	public TutorialBrowser getTutorialPane() {
		return tutorialPane;
	}


	public void setTutorialPane(TutorialBrowser tutorialPane) {
		this.tutorialPane = tutorialPane;
	}
	
	
	
}

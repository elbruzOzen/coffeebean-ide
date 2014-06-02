package com.coffeebean.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.coffeebean.core.SearchCore;
import java.awt.*;
import java.io.IOException;
import java.util.Set;

/*
 * 
 * Show GUI of search panel
 * 
 * Author: Gamze Gül
 * 
 */

public class SearchPanel extends JPanel implements ActionListener{
	
	//properties
	
	private static final long serialVersionUID = 1L;
	private JTextField searchfield;
	private JButton javaDoc;
	private JButton search;
	private SearchCore searchCore;
	
	@SuppressWarnings("rawtypes")
	private JComboBox engineBox;
	
	//constructor
	public SearchPanel(SearchCore searchCore) throws IOException{
		
		this.searchCore = searchCore;
		
		setLayout(new FlowLayout());
	
		javaDoc = new JButton( "Java Doc");
		search = new JButton("Search");
		
		//Load names
		Set<String> names = searchCore.getEngineList().keySet();
		engineBox = new JComboBox<>( names.toArray() );
		
		searchfield = new JTextField(30);
		searchfield.setPreferredSize(new Dimension(200,25));
		
		add( javaDoc );
		add( searchfield );
		add( search );
		add( engineBox );
		
		//Action Listeners
		searchfield.addActionListener( this);
		javaDoc.addActionListener(this);
		search.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == javaDoc)
		{
			searchCore.openJavaDoc();
		} 
		
		else if(e.getSource() == search || e.getSource() == searchfield)
		{
		
			//Determine search properties
			String engine = searchCore.getEngineList().get( engineBox.getSelectedItem().toString() ) ;
			String keyword = searchfield.getText().trim().replaceAll(" ", "+");
			
			//Open search page
			searchCore.openSearchPage(engine, keyword );
			
			//Reset old text
			searchfield.setText("");

		}	
	}

}
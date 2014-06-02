package com.coffeebean.view;

import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
import com.coffeebean.core.ExampleCore;

/*
 * Show example and add to the panel as a new file
 * 
 * Author: Berk Yurttaþ
 * 
 */

public class ExampleViewer extends JFrame{
	
	private static final long serialVersionUID = 1494084666404253733L;
	
	private JPanel buttonPanel;
	private JButton add, cancel;
	private JList<String> exampleListShow;
	
	@SuppressWarnings("unused")
	private ExampleCore core;
	private JTextArea previewPane;
	private File[] examples;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ExampleViewer( final ExampleCore core ){
		
		super( "Examples" );
		
		this.core = core;
		
		add = new JButton("Add");
		cancel = new JButton("Cancel");
		buttonPanel = new JPanel();
		examples = core.loadExamples();
		exampleListShow = new JList( core.loadExampleNames().toArray() );
		
		//Preview properties
		previewPane = new JTextArea(22, 60);
		previewPane.setEditable(false);
		
		//Add ActionListener to Buttons
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(exampleListShow.getSelectedValue() != null){
					
					String path = examples[exampleListShow.getSelectedIndex()].getPath();
					core.addExamples(path);
					
					//Close the frame
					setVisible(false);
					dispose();
					
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		exampleListShow.addListSelectionListener( new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String example = core.getfManager().readFile( examples[exampleListShow.getSelectedIndex()].getPath());
				previewPane.setText(example);	
			}
		});
		
		//Add Components by following order
		buttonPanel.add(add);
		buttonPanel.add(cancel);
	
		JScrollPane sp1 = new JScrollPane( exampleListShow );
		JScrollPane sp2 = new JScrollPane( previewPane );
		
		sp1.setBorder( BorderFactory.createTitledBorder("List"));
		sp2.setBorder( BorderFactory.createTitledBorder("Preview"));
		
		add( sp1 , "North" );
		add( sp2 , "Center");
		add(buttonPanel, "South");
		
		//Frame Properties
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setVisible(true);
		
	}
	
	//Getters and Setters
	@SuppressWarnings("rawtypes")
	public JList getExamples(){
		return exampleListShow;
	}
	
	public JTextArea getExamplePane(){
		return previewPane;
	}


}
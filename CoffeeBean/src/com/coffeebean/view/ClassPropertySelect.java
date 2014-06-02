package com.coffeebean.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import com.coffeebean.core.CodeEditorCore;

/**
* @(#)ClassPropertySelector.java
*
* When user clicks new file option, a window appears
* User can select new file properties
*
* @author Elbruz Özen
* @version 1.00 2014/05/01
*/

public class ClassPropertySelect extends JDialog {

	private static final long serialVersionUID = 2223696022831222818L;
	
	private final JPanel contentPanel;
	private JTextField textField;
	private ButtonGroup fileTypeGroup;
	private JButton okButton;
	private JButton cancelButton;
	
	@SuppressWarnings("unused")
	private CodeEditorCore editCore;
	private final JCheckBox addEmptyConstructor;
	private final JCheckBox addMainMethod;
	private final JRadioButton classSelection;
	private JLabel javaLabel;
	private JLabel nameLabel;
	private final JRadioButton interfaceSelection;
	private JLabel fileTypeLabel;
	private final JRadioButton enumSelection;

	/**
	 * Create the dialog.
	 */
	public ClassPropertySelect( final CodeEditorCore editCore ) {
		
		this.editCore = editCore;
		
		contentPanel = new JPanel();
		fileTypeGroup = new ButtonGroup();
		textField = new JTextField();
		addEmptyConstructor = new JCheckBox("Add Empty Constructor");
		addMainMethod = new JCheckBox("Add Main Method");
		classSelection = new JRadioButton("class");
		javaLabel = new JLabel(".java");
		nameLabel = new JLabel("Name:");
		interfaceSelection = new JRadioButton("interface");
		fileTypeLabel = new JLabel("File Type:");
		enumSelection = new JRadioButton("enum");
		
		setBounds(500, 250, 450, 300);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//Set Layout by WindowBuilder Add-On of Eclipse
		SpringLayout springLayout = new SpringLayout();
		contentPanel.setLayout(springLayout);
	
		springLayout.putConstraint(SpringLayout.NORTH, textField, 32, SpringLayout.NORTH, contentPanel);
		springLayout.putConstraint(SpringLayout.EAST, textField, -98, SpringLayout.EAST, contentPanel);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		springLayout.putConstraint(SpringLayout.WEST, addEmptyConstructor, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(addEmptyConstructor);
		
		springLayout.putConstraint(SpringLayout.NORTH, addMainMethod, 6, SpringLayout.SOUTH, addEmptyConstructor);
		springLayout.putConstraint(SpringLayout.WEST, addMainMethod, 0, SpringLayout.WEST, addEmptyConstructor);
		contentPanel.add(addMainMethod);
		
		springLayout.putConstraint(SpringLayout.NORTH, javaLabel, 3, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, javaLabel, 27, SpringLayout.EAST, textField);
		contentPanel.add(javaLabel);
		
		springLayout.putConstraint(SpringLayout.WEST, textField, 67, SpringLayout.EAST, nameLabel);
		springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 3, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, addEmptyConstructor);
		contentPanel.add(nameLabel);
		
		springLayout.putConstraint(SpringLayout.NORTH, classSelection, 85, SpringLayout.NORTH, contentPanel);
		springLayout.putConstraint(SpringLayout.NORTH, addEmptyConstructor, 14, SpringLayout.SOUTH, classSelection);
		springLayout.putConstraint(SpringLayout.EAST, classSelection, -255, SpringLayout.EAST, contentPanel);
		contentPanel.add(classSelection);
		
		springLayout.putConstraint(SpringLayout.NORTH, enumSelection, 0, SpringLayout.NORTH, classSelection);
		springLayout.putConstraint(SpringLayout.WEST, enumSelection, 6, SpringLayout.EAST, classSelection);
		contentPanel.add(enumSelection);
		
		springLayout.putConstraint(SpringLayout.NORTH, interfaceSelection, 0, SpringLayout.NORTH, classSelection);
		springLayout.putConstraint(SpringLayout.WEST, interfaceSelection, 12, SpringLayout.EAST, enumSelection);
		contentPanel.add(interfaceSelection);
		
		springLayout.putConstraint(SpringLayout.NORTH, fileTypeLabel, 4, SpringLayout.NORTH, classSelection);
		springLayout.putConstraint(SpringLayout.WEST, fileTypeLabel, 0, SpringLayout.WEST, addEmptyConstructor);
		
		
		//Add other components
		fileTypeGroup.add(interfaceSelection);
		fileTypeGroup.add(enumSelection);
		fileTypeGroup.add(classSelection);
		
		classSelection.setSelected( true );
		
		
		contentPanel.add(fileTypeLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//Action Commands
		
		okButton.addActionListener( new  ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				
				String selectedFileType = null;
				
				if( classSelection.isSelected())
					selectedFileType = classSelection.getText();
				else if( enumSelection.isSelected())
					selectedFileType = enumSelection.getText();
				else if( interfaceSelection.isSelected())
					selectedFileType = interfaceSelection.getText();
			
				String fileName = textField.getText().trim();
				
				if( !fileName.equals("") ){
					editCore.newFile( fileName , selectedFileType , addEmptyConstructor.isSelected(), addMainMethod.isSelected());
				}
				
				setVisible(false);
				dispose();
			
				
			}
		
		});
		
		cancelButton.addActionListener( new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				dispose();
				
			}
		});
		
		classSelection.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				addEmptyConstructor.setEnabled( true );
				addMainMethod.setEnabled( true );
				
			}
		});
		
		enumSelection.addActionListener( new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				
				addEmptyConstructor.setSelected( false );
				addMainMethod.setSelected( false );
				addEmptyConstructor.setEnabled( false );
				addMainMethod.setEnabled( false );
				
			}
		});


		interfaceSelection.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				addEmptyConstructor.setSelected( false );
				addMainMethod.setSelected( false );
				addEmptyConstructor.setEnabled( false );
				addMainMethod.setEnabled( false );
				
			}
		});
		
		//Frame properties
		setTitle("Create New File");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}

}

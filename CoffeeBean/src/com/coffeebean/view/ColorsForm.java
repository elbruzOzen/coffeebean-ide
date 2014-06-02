package com.coffeebean.view;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * changes the color of main container's panels
 * @author Gokce Muge CIL
 * @version 1.00 2014/05/11
 */

public class ColorsForm extends javax.swing.JFrame {

	private static final long serialVersionUID = 3928310332961210394L;
	
	//Variables
	MainContainer container;

	//Constructor
    public ColorsForm(MainContainer container) {
    	super("Choose Background Color");
    	this.container = container;
        initComponents();
        this.setLocationRelativeTo( null);
    
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        OK = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        OK.setText("OK");
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });

        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(OK)
                .addGap(18, 18, 18)
                .addComponent(Cancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(OK)
                    .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        this.dispose();
    }                                      

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {                                   
    	
    	Color selectedColor = jColorChooser1.getSelectionModel().getSelectedColor();
    	
    	applyColor( selectedColor );
    	saveColor( selectedColor );
        
        this.container.setVisible(true);
        this.dispose();

    }                                  

    private void formWindowClosed(java.awt.event.WindowEvent evt) {   
        
        this.dispose();       
    }
    
    //Change color of components
    public void applyColor( Color color){
    	
    	 this.container.getDividePanel().setBackground( color);
         this.container.getDividePanel().setForeground(color);
         this.container.getTutorialPanel().setForeground(color);
         this.container.getTutorialPanel().setBackground(color);
         this.container.getShortcutPanel().setBackground(color);
         this.container.getCodeEditorPanel().setBackground(color);
         this.container.getSearchPanel().setBackground(color);
         this.container.getDividePanel().setBackground(color);
         this.container.getContentPane().setBackground(color);
      
    }
    
    //Save color on HDD for other runtime
    public void saveColor( Color color ){
    	
    	ObjectOutputStream os = null;
    	
    	try {
			os = new ObjectOutputStream( new FileOutputStream( "extra\\conf\\color.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	try {
			os.writeObject( color );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
                   
    private javax.swing.JButton Cancel;
    private javax.swing.JButton OK;
    private javax.swing.JColorChooser jColorChooser1;
    // End of variables declaration                   
}

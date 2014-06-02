package com.coffeebean.view;

import com.coffeebean.controller.CodeTypeListener;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;

/**
 * changes the font settings of Code Editor Panel
 *
 * @author Gokce Muge CIL
 * @version 1.00 2014/05/10
 */

public class FontsForm extends javax.swing.JFrame {
    
	private static final long serialVersionUID = 2719400531028032867L;

	private int check;
    
	//Properties
    private MainContainer container;
    
    //Constants
    private static String[] fontList=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    private static Integer [] fontSizelist={8,10,12,14,16,18,20,22,24,26,28,30,32};

    //Constructor
    public FontsForm(MainContainer container) {
	   	
    	super("Choose Editor Font");
	   	this.container = container; 
        initComponents();
        this.setLocationRelativeTo( null);
    }
    
    //Init GUI
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {

        OK = new javax.swing.JButton();
        fontsList = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        Cancel = new javax.swing.JButton();
        cbFontSize = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 255));
        setForeground(new java.awt.Color(204, 204, 255));

        OK.setText("OK");
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });

        fontsList.setModel(new javax.swing.DefaultComboBoxModel(FontsForm.getFontList()));
        fontsList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontsListActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Aharoni", 1, 14)); // NOI18N
        jLabel1.setText("Font");

        jLabel2.setFont(new java.awt.Font("Aharoni", 1, 14)); // NOI18N
        jLabel2.setText("Font Size");

        jCheckBox1.setText("Bold");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Aharoni", 1, 14)); // NOI18N
        jLabel3.setText("Font Style");

        jCheckBox2.setText("Italic");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        cbFontSize.setModel(new javax.swing.DefaultComboBoxModel(this.getFontSizelist()));
        cbFontSize.setName(""); // NOI18N
        cbFontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFontSizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(OK)
                .addGap(27, 27, 27)
                .addComponent(Cancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(105, 105, 105))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fontsList, javax.swing.GroupLayout.DEFAULT_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jLabel3)
                            .addComponent(jCheckBox2))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fontsList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OK)
                    .addComponent(Cancel))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        
     Font font1;
       
    private void OKActionPerformed(java.awt.event.ActionEvent evt) {                                   
        
    	try
        {
          
            font1 = new Font((String)fontsList.getSelectedItem(), checkMethod(), ((Integer)this.cbFontSize.getSelectedItem()));

            applyFont(font1);
            saveFont(font1);

            this.dispose();
            
        
        } 
        catch ( Exception e)
        {
        	
        	font1 = new Font((String)fontsList.getSelectedItem(), checkMethod(), ((Integer)this.cbFontSize.getSelectedItem()));
        	
        	applyFont(font1);
        	saveFont(font1);

            this.dispose();
        
        }
     
    }                                  

    private void fontsListActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
       
    }                                         

    private void cbFontSizeActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
   
    }                                          

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {                                       
    	this.dispose();
    }                                      


    // Variables declaration - do not modify                     
    private javax.swing.JButton Cancel;
    private javax.swing.JButton OK;
    
    @SuppressWarnings("rawtypes")
	private javax.swing.JComboBox cbFontSize;
    @SuppressWarnings("rawtypes")
	
    private javax.swing.JComboBox fontsList;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration                   

    //check boxes status
    public int  checkMethod(){
        if(this.jCheckBox1.isSelected()==true && this.jCheckBox2.isSelected()==false){
            check = 1;
        }
        else if(this.jCheckBox1.isSelected()==true && this.jCheckBox2.isSelected()==true){
            check = 3;
        }
        else if(this.jCheckBox1.isSelected()==false && this.jCheckBox2.isSelected()==true){
            check = 2;
        }
        else if(this.jCheckBox1.isSelected()==false && this.jCheckBox2.isSelected()==false){
            check = 0;
        }
        
        return check;
    }
    
    public void applyFont( Font font1){
    	
        this.container.getCodeEditorCore().getEditorPanel().setFont(font1);  
        
        //************************************
        
        int tabCount = container.getCodeEditorPanel().getCodeTabs().getTabCount();
		int oldIndex = container.getCodeEditorPanel().getCodeTabs().getSelectedIndex();
		
		for( int i = 0 ; i < tabCount ; i++){
			
			container.getCodeEditorPanel().getCodeTabs().setSelectedIndex(i);
			
			JScrollPane scrollPane = (JScrollPane)container.getCodeEditorPanel().getCodeTabs().getSelectedComponent();
			JViewport viewport = scrollPane.getViewport(); 
			JTextPane editorPane = (JTextPane)viewport.getView(); 
			
			editorPane.setFont(font1);
			
		}
		
		//Return to previous tab view
		container.getCodeEditorPanel().getCodeTabs().setSelectedIndex( oldIndex );
        
        //************************************
        
        this.container.getTutorialPanel().setFont(font1);
    
    }
    
    public void saveFont( Font font ){
    	
    	ObjectOutputStream os = null;
    	
    	try {
			os = new ObjectOutputStream( new FileOutputStream( "extra\\conf\\font.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	try {
			os.writeObject( font );
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
  
    //Getters and Setters
    public static String[] getFontList() {
        return fontList;
    }

    @SuppressWarnings("static-access")
	public void setFontList(String[] fontList) {
        this.fontList = fontList;
    }

    public Integer[] getFontSizelist() {
        
        return fontSizelist;
    }

    public static void setFontSizelist(Integer[] aFontSizelist) {
        fontSizelist = aFontSizelist;
    }
    
 }

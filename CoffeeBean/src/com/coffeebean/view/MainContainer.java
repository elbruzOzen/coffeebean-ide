package com.coffeebean.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.coffeebean.controller.MainActionListener;
import com.coffeebean.controller.MainFrameListener;
import com.coffeebean.core.CodeEditorCore;
import com.coffeebean.core.SearchCore;
import com.coffeebean.core.TutorialCore;

/**
* @(#)MainContainer.java
*
* This is the main frame of our application, all panels and their logic is linked here
* This class is on the top of design model
*
* @author Gamze Gül
* 
* Edited by Elbruz Özen
* 
* @version 1.00 2014/05/01
*/

public class MainContainer extends JFrame {


	private static final long serialVersionUID = -1253548513213171377L;

	//Properties
	private JMenuBar mainMenuBar;
	
	private JMenu file;
	private JMenu tools;
	private JMenu about;
	private JMenu personalization;
	
	private JMenuItem newFile;
	private JMenuItem open;
	private JMenuItem openDir;
	private JMenuItem save;
	private JMenuItem saveAll;
	private JMenuItem exit;

	private JMenuItem build;
	private JMenuItem buildAll;
	private JMenuItem run;
	private JMenuItem help;
	private JMenuItem credits;
	private JButton examples;
	
	private JMenuItem backgroundColor; 
	private JMenuItem codeEditorFont;
	
	private JButton saveAll_buildAll_runButton;
	private JButton saveAllButton;
	private JButton saveAll_buildAllButton;
	
	private JSplitPane dividePanel;
	private JPanel shortcutPanel;
	
	private CodeEditorCore codeEditorCore;
	private CodeEditorPanel codeEditorPanel;
	private SearchPanel searchPanel;
	private SearchCore searchCore;
	private TutorialCore tutorialCore;
	private TutorialPanel tutorialPanel;
	private JPanel rightPanel;
	
	
	//Constructor
	public MainContainer( String title ) throws IOException{
		
		super( title );
		
		//Theme
		setUpNimbus();
		
		//Setup Menu
		initializeMenu();
		
		//Icon
		setIconImage( new ImageIcon("extra\\icon\\icon.png").getImage());
		
		//Shrink buttons
		examples.setPreferredSize( new Dimension(examples.getWidth() , examples.getHeight()+25));
		
		examples.setFocusPainted( false);
		
		shortcutPanel = new JPanel( new FlowLayout(FlowLayout.LEFT));
		shortcutPanel.setPreferredSize( new Dimension(40,35));
		
		//Shortcut buttons
		saveAllButton = new JButton( new ImageIcon("extra\\icon\\save.png"));
		saveAll_buildAllButton = new JButton( new ImageIcon("extra\\icon\\compile.png"));
		saveAll_buildAll_runButton = new JButton( new ImageIcon("extra\\icon\\play.png"));
		
		saveAllButton.setToolTipText( "Save All Document");
		saveAll_buildAllButton.setToolTipText( "Save & Build All Document");
		saveAll_buildAll_runButton.setToolTipText( "Save & Build & Run All Document");
		
		rightPanel = new JPanel( new BorderLayout() );
		
		codeEditorPanel = new CodeEditorPanel();
		codeEditorCore = new CodeEditorCore(codeEditorPanel);
		
		tutorialCore = new TutorialCore();
		tutorialPanel = new TutorialPanel(tutorialCore);
		
		searchCore = new SearchCore();
		searchPanel = new SearchPanel( searchCore );
		
		rightPanel.add( searchPanel , "North");
		rightPanel.add( tutorialPanel , "Center");
		
		dividePanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, codeEditorPanel, rightPanel);
		dividePanel.setPreferredSize( new Dimension(1300, 700));
		
		//Add actionListeners
		saveAllButton.addActionListener( new MainActionListener( this ));
		saveAll_buildAllButton.addActionListener( new MainActionListener( this ));
		saveAll_buildAll_runButton.addActionListener( new MainActionListener( this ));
		
		shortcutPanel.add( saveAllButton );
		shortcutPanel.add( saveAll_buildAllButton );
		shortcutPanel.add( saveAll_buildAll_runButton );
		
		//Add components to frame
		add( dividePanel , "Center");
		add(shortcutPanel,"North");
	
		//Add Window Listener
		addWindowListener( new MainFrameListener( this ));
		
		//Set color
		getContentPane().setBackground( new Color(214, 217, 223));
		
		//ActionListeners
		setUpActionListeners();
		
		//Apply saved color and font
		loadColor();
		loadFont();
		
		//Set frame properties
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}

	
	public void initializeMenu(){
		
		mainMenuBar = new JMenuBar();
		
		file = new JMenu("File");
		tools = new JMenu("Tools");
		about = new JMenu("About");
		
		newFile = new JMenuItem("New");
		open = new JMenuItem("Open");
		openDir = new JMenuItem( "Open Workstation");
		save = new JMenuItem("Save");
		saveAll = new JMenuItem("Save All");
		exit = new JMenuItem("Exit");
		personalization = new JMenu("Personalization");
		build = new JMenuItem("Build");
		buildAll = new JMenuItem("Build All");
		run = new JMenuItem("Run");
		help = new JMenuItem("Help");
		credits = new JMenuItem("Credits");
		examples = new JButton("Examples");
		
		backgroundColor = new JMenuItem("Background Color");
		codeEditorFont = new JMenuItem("Editor Font");
		
		examples.setPreferredSize(new Dimension(40, 40));
		
		//Set up menu order
		
		setJMenuBar(mainMenuBar);
		
		mainMenuBar.add( file );
		mainMenuBar.add( tools );
		mainMenuBar.add( examples);
		mainMenuBar.add( personalization );
		mainMenuBar.add(about);
	
		file.add( newFile );
		file.add( open );
		file.add( openDir );
		file.add( save );
		file.add( saveAll );
		file.add( exit );

		tools.add( build );
		tools.add( buildAll );
		tools.add( run );
		
		personalization.add( backgroundColor);
		personalization.add( codeEditorFont );

		about.add(help);
		about.add(credits);
		
	}
	
	public void setUpActionListeners() {
		
	
		newFile.addActionListener( new MainActionListener( this ));
		open.addActionListener( new MainActionListener( this ));
		openDir.addActionListener( new MainActionListener( this ));
		save.addActionListener( new MainActionListener( this ));
		saveAll.addActionListener( new MainActionListener( this ));
		exit.addActionListener( new MainActionListener( this ));
		personalization.addActionListener( new MainActionListener( this ));
		build.addActionListener( new MainActionListener( this ));
		buildAll.addActionListener( new MainActionListener( this ));
		run.addActionListener( new MainActionListener( this ));
		backgroundColor.addActionListener( new MainActionListener( this ));
		codeEditorFont.addActionListener( new MainActionListener( this ));
		help.addActionListener( new MainActionListener( this ));
		credits.addActionListener( new MainActionListener( this ));
		examples.addActionListener( new MainActionListener( this ));
		
		
		
	}
	
	public void setUpNimbus(){
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
	}
	
	public void loadColor(){
		
		ObjectInputStream is = null;
		Color receivedColor = getContentPane().getBackground();
		
		try {
			
			is = new ObjectInputStream( new FileInputStream("extra\\conf\\color.dat"));
			receivedColor = (Color)is.readObject();
			applyColor( receivedColor );
		
		} catch (Exception e) {
			
			//If there is a problem, format setting file
			saveColor( receivedColor );
			//Call method again
			loadColor();
		}	
		
		
	}
	
	public void loadFont(){
		
		ObjectInputStream is = null;
		Font receivedFont = getContentPane().getFont();
		
		try {
			
			is = new ObjectInputStream( new FileInputStream("extra\\conf\\font.dat"));
			receivedFont = (Font)is.readObject();
			applyFont( receivedFont );
		
		} catch (Exception e) {
			
			//If there is a problem, format setting file
			saveFont( receivedFont );
			//Call method again
			loadFont();
		}	
		
		
	}
	
	public void applyFont( Font font1){
	    	
	        this.getCodeEditorCore().getEditorPanel().setFont(font1);  
	        
	        //************************************
	        
	        int tabCount = getCodeEditorPanel().getCodeTabs().getTabCount();
			int oldIndex = getCodeEditorPanel().getCodeTabs().getSelectedIndex();
			
			for( int i = 0 ; i < tabCount ; i++){
				
				getCodeEditorPanel().getCodeTabs().setSelectedIndex(i);
				
				JScrollPane scrollPane = (JScrollPane)getCodeEditorPanel().getCodeTabs().getSelectedComponent();
				JViewport viewport = scrollPane.getViewport(); 
				JTextPane editorPane = (JTextPane)viewport.getView(); 
				
				editorPane.setFont(font1);
				
			}
			
			//Return to previous tab view
			getCodeEditorPanel().getCodeTabs().setSelectedIndex( oldIndex );
	        
	        //************************************
	        
	        getTutorialPanel().setFont(font1);
	    
	}
	
	public void applyColor( Color color){
	    	
    	 getDividePanel().setBackground( color);
         getDividePanel().setForeground(color);
         getTutorialPanel().setForeground(color);
         getTutorialPanel().setBackground(color);
         getShortcutPanel().setBackground(color);
         getCodeEditorPanel().setBackground(color);
         getSearchPanel().setBackground(color);
         getDividePanel().setBackground(color);
         getContentPane().setBackground(color);
      
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

	//Getters and Setters
	public JMenuItem getNewFile() {
		return newFile;
	}

	public void setNewFile(JMenuItem newFile) {
		this.newFile = newFile;
	}

	public JMenuItem getOpen() {
		return open;
	}

	public void setOpen(JMenuItem open) {
		this.open = open;
	}

	public JMenuItem getOpenDir() {
		return openDir;
	}


	public void setOpenDir(JMenuItem openDir) {
		this.openDir = openDir;
	}


	public JMenuItem getSave() {
		return save;
	}

	public void setSave(JMenuItem save) {
		this.save = save;
	}

	public JMenuItem getExit() {
		return exit;
	}

	public void setExit(JMenuItem exit) {
		this.exit = exit;
	}


	public JMenu getPersonalization() {
		return personalization;
	}

	public void setPersonalization(JMenu preferences) {
		this.personalization = preferences;
	}

	public JMenuItem getBuild() {
		return build;
	}

	public void setBuild(JMenuItem build) {
		this.build = build;
	}

	public JMenuItem getRun() {
		return run;
	}

	public void setRun(JMenuItem run) {
		this.run = run;
	}

	public JMenuItem getHelp() {
		return help;
	}

	public void setHelp(JMenuItem help) {
		this.help = help;
	}

	public JMenuItem getCredits() {
		return credits;
	}

	public void setCredits(JMenuItem credits) {
		this.credits = credits;
	}

	public JButton getExamples() {
		return examples;
	}

	public void setExamples(JButton examples) {
		this.examples = examples;
	}


	public CodeEditorCore getCodeEditorCore() {
		return codeEditorCore;
	}

	public void setCodeEditorCore(CodeEditorCore codeEditorCore) {
		this.codeEditorCore = codeEditorCore;
	}

	public CodeEditorPanel getCodeEditorPanel() {
		return codeEditorPanel;
	}

	public void setCodeEditorPanel(CodeEditorPanel codeEditorPanel) {
		this.codeEditorPanel = codeEditorPanel;
	}

	public JMenuItem getSaveAll() {
		return saveAll;
	}

	public void setSaveAll(JMenuItem saveAll) {
		this.saveAll = saveAll;
	}

	public JMenuItem getBuildAll() {
		return buildAll;
	}

	public void setBuildAll(JMenuItem buildAll) {
		this.buildAll = buildAll;
	}

	public JButton getSaveAll_buildAll_runButton() {
		return saveAll_buildAll_runButton;
	}


	public void setSaveAll_buildAll_runButton(JButton saveAll_buildAll_runButton) {
		this.saveAll_buildAll_runButton = saveAll_buildAll_runButton;
	}


	public JButton getSaveAllButton() {
		return saveAllButton;
	}


	public void setSaveAllButton(JButton saveAllButton) {
		this.saveAllButton = saveAllButton;
	}


	public JButton getSaveAll_buildAllButton() {
		return saveAll_buildAllButton;
	}


	public void setSaveAll_buildAllButton(JButton saveAll_buildAllButton) {
		this.saveAll_buildAllButton = saveAll_buildAllButton;
	}


	public TutorialPanel getTutorialPanel() {
		return tutorialPanel;
	}


	public void setTutorialPanel(TutorialPanel tutorialPanel) {
		this.tutorialPanel = tutorialPanel;
	}


	public JSplitPane getDividePanel() {
		// TODO Auto-generated method stub
		return dividePanel;
	}


	public JPanel getShortcutPanel() {
		// TODO Auto-generated method stub
		return shortcutPanel;
	}


	public JPanel getSearchPanel() {
		
		return searchPanel;
		
	}


	public JMenuItem getBackgroundColor() {
		return backgroundColor;
	}


	public void setBackgroundColor(JMenuItem backgroundColor) {
		this.backgroundColor = backgroundColor;
	}


	public JMenuItem getCodeEditorFont() {
		return codeEditorFont;
	}


	public void setCodeEditorFont(JMenuItem codeEditorFont) {
		this.codeEditorFont = codeEditorFont;
	}
	
}

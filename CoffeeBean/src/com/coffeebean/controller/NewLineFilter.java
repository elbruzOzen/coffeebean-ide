package com.coffeebean.controller;

import javax.swing.*;
import javax.swing.text.*;

import com.coffeebean.core.CodeEditorCore;

/*
 * NewLineFilter.java
 * 
 * Provides aligned tab function for code editor
 * Closed during highlight period
 * 
 * http://stackoverflow.com/questions/15867900/java-auto-indentation-in-jtextpane
 * Answer 1
 * 
 * Edited by Elbruz Özen 11 May 2014
 * 
 */

public class NewLineFilter extends DocumentFilter
{
	
	//Properties
	private CodeEditorCore core;
	
	@SuppressWarnings("unused")
	private JTextPane pane;
	
	public NewLineFilter( JTextPane pane , CodeEditorCore core ){
		this.pane = pane;
		this.core = core;
	}
	
    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
        throws BadLocationException
    {
        if ("\n".equals(str) && core.isPermissionForTab())
            str = addWhiteSpace(fb.getDocument(), offs);

        super.insertString(fb, offs, str, a);
        
    }
 
    
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
        throws BadLocationException
    {
        if ("\n".equals(str) && core.isPermissionForTab())
            str = addWhiteSpace(fb.getDocument(), offs);

        super.replace(fb, offs, length, str, a);
    }

    
    private String addWhiteSpace(Document doc, int offset)
        throws BadLocationException
    {
        
    	StringBuilder whiteSpace = new StringBuilder("\n");
        Element rootElement = doc.getDefaultRootElement();
        int line = rootElement.getElementIndex( offset );
        int i = rootElement.getElement(line).getStartOffset();

        while (true)
        {
            String temp = doc.getText(i, 1);

            if (temp.equals(" ") || temp.equals("\t"))
            {
                whiteSpace.append(temp);
                i++;
            }
            else
                break;
        }

        return whiteSpace.toString();
    }
   
}
/* OpenURLDialog.java */

package elvira.gui;

import java.awt.*;
import javax.swing.*;

/**
 * A class that produce a Dialog for open a network from a URL
 *
 * @author fjdiez, ratienza
 * @version 0.1
 * @since 24/10/99
 */

public class OpenURLDialog extends javax.swing.JDialog
{

   /**
    * Creates a OpenURLDialog with all its elements and
    * fill the textarea with the information get from the current
    * network
    *
    * @param frame The owner of the dialog
    */

	public OpenURLDialog(Frame parent)
	{
		super(parent);
		
		// This code is automatically generated by Visual Cafe when you add
		// components to the visual environment. It instantiates and initializes
		// the components. To modify the code, only use code syntax that matches
		// what Visual Cafe can generate, or Visual Cafe may be unable to back
		// parse your Java file into its visual environment.
		//{{INIT_CONTROLS
		setTitle("Open a file from a URL");
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(null);
		setSize(344,155);
		setVisible(false);
		informationLabel.setText("Please, type the URL");
		getContentPane().add(informationLabel);
		informationLabel.setForeground(java.awt.Color.black);
		informationLabel.setBounds(36,24,120,24);
		getContentPane().add(textURL);
		textURL.setBounds(36,48,276,24);
		okButton.setText("OK");
		getContentPane().add(okButton);
		okButton.setBounds(72,96,84,36);
		cancelButton.setText("Cancel");
		getContentPane().add(cancelButton);
		cancelButton.setBounds(180,96,96,36);
		//}}
	
		//{{REGISTER_LISTENERS
		SymAction lSymAction = new SymAction();
		cancelButton.addActionListener(lSymAction);
		//}}
	}


	/**
	 * Creates a new OpenURLDialog with no parent frame
	 */

	public OpenURLDialog()
	{
		this((Frame)null);
	}


	/**
	 * Creates a new OpenURLDialog and set its title
	 */
	
	public OpenURLDialog(String sTitle)
	{
		this();
		setTitle(sTitle);
	}


	/**
	 * Shows or hide the OpenURLDialog and set its location
	 */
	
	public void setVisible(boolean b)
	{
		if (b)
			setLocation(50, 50);
		super.setVisible(b);
	}

	static public void main(String args[])
	{
		(new OpenURLDialog()).setVisible(true);
	}

	public void addNotify()
	{
		// Record the size of the window prior to calling parents addNotify.
		Dimension size = getSize();

		super.addNotify();

		if (frameSizeAdjusted)
			return;
		frameSizeAdjusted = true;

		// Adjust size of frame according to the insets
		Insets insets = getInsets();
		setSize(insets.left + insets.right + size.width, insets.top + insets.bottom + size.height);
	}

	// Used by addNotify
	boolean frameSizeAdjusted = false;

	//{{DECLARE_CONTROLS
	javax.swing.JLabel informationLabel = new javax.swing.JLabel();
	javax.swing.JTextField textURL = new javax.swing.JTextField();
	javax.swing.JButton okButton = new javax.swing.JButton();
	javax.swing.JButton cancelButton = new javax.swing.JButton();
	//}}


   /**
    * Manage the actions produce in the OpenURLDialog
    */

	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == cancelButton)
				cancelButton_actionPerformed(event);
		}
	}

	void cancelButton_actionPerformed(java.awt.event.ActionEvent event)
	{
		dispose();			 
	}
}
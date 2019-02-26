
/*
 * Fatima Ali
 * Period 2
 * GPInstructL1.java
 * 4/7/18
 * This class displays the instructions for Level 1 and contains a JButton. When clicked, the JButton uses card layout to allow the user to start playing Level 1. Images
 * were imported into this class to display the same background and formatting.

 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JButton;


	public class GPInstructL1 extends JPanel // displays text instructions and JButton to call game panel, using cardPanel in a the master navigation class
	{
		JButton playL1;
		Color bck;
    private Image billImage;
  	private String billName;

  	public GPInstructL1()
  	{
			setLayout(null); // set layout to null to allow the button to have set bounds to fit in next to the text instructions
  		playL1 = new JButton("Level 1");  // this class.addJbutton.addactionListener
  		playL1.setBounds(950,500, 250, 100);
  		add(playL1);

			billImage = null;
			billName = "BilliardsTable.png";
			getMyImage();
  	}

	 	public void getMyImage() // imports image of billiards table for background
	  {
			try
		 	{
		 		 billImage = ImageIO.read(new File(billName));
		  }
		  catch (IOException e)
		  {
				System.err.println("\n\nYour picture(s) can't be found.\n\n");
		 		e.printStackTrace();
		 	}
		}

		public void paintComponent(Graphics g)
	 	{
			super.paintComponent(g);
			g.drawImage(billImage, 0, 0, 1300, 700, this);

			Color text = new Color (74, 7, 114);
			g.setFont(new Font("Cambria", Font.BOLD,24));

			g.setColor(Color.WHITE);
			g.drawString("Instructions for Level 1", 90, 40); // display instructions for Level 2

			g.setFont(new Font("Cambria", Font.PLAIN,20));

			g.drawString("The distance formula is provided by clicking the 'DF' button on the top right corner.", 85, 120 );
			g.drawString("Use the formula to calculate the distance from the white ball to each of the colored balls.", 85, 170);
			g.drawString("While playing the game, click the button labeled '?' to return to these instructions.", 85, 270);
			g.drawString("--You get 5 points for answering all three distances correctly.", 95, 320);
			g.drawString("--You lose 7 points for answering one or more incorrect distances.", 95, 370);
		}


}

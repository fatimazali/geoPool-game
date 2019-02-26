/*
 * Fatima Ali
 * Period 2
 * GPInstructL2.java
 * 4/23/18
 * This class displays the instructions for Level 2 and contains a JButton. When clicked, the JButton uses card layout to allow the user to start playing Level 2. Images
 * were imported into this class to display the same background and formatting.

 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
/*public class GPInstructL2
{
	private Image billImage;
	private String billName;
	private CardLayout cards;
	GamePanelL2 g2;
	InstructPanel ip;

	 public GPInstructL2()
	 {
		 billImage = null;
		 billName = "BilliardsTable.png";

	 }
	 public static void main(String[] args)
	 {
		 GPInstructL2 l2 = new GPInstructL2();
		 l2.runIt();
	 }

	 public void runIt()
	 {
		 JFrame frame = new JFrame("Instructions: Level 2"); // construct JFrame to hold the JPanel
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 ip = new InstructPanel();  // construct InstructPanel to display instructions

		 frame.getContentPane().add(ip);
		 frame.setSize(1300, 700);  // construct the 1300 by 700 Jpanel
		 frame.setResizable(false);
		 frame.setLocation(0, 0);
		 frame.setVisible(true);
	 }*/

	public class GPInstructL2 extends JPanel
	{
		JButton playL2;
		Color bck;
    private Image billImage;
  	private String billName;

		 public GPInstructL2()
		 {

			setLayout(null); // set layout to null to allow the button to have set bounds to fit in next to the text instructions
			playL2 = new JButton("Play");  // this class.addJbutton.addactionListener
			playL2.setBounds(950,500, 250, 100);
			add(playL2);

      billImage = null;
 		  billName = "BilliardsTable.png";
		 }

		 public void paintComponent(Graphics g)
		 {
			super.paintComponent(g);
			getMyImage();
			drawPicture(g);

			Color text = new Color (74, 7, 114);
			g.setFont(new Font("Cambria", Font.BOLD,24));

			g.setColor(Color.WHITE);
			g.drawString("Instructions for Level 1", 92, 40); // display instructions for Level 1

      g.drawString("The distance formula is provided by clicking the button labelled 'd' on the top", 85, 120);
			g.drawString("right corner.", 85, 170);

			g.setFont(new Font("Cambria", Font.PLAIN,20));

			g.drawString("Use the formula to calculate the distance from the red ball to the four pockets on each corner. Using the speed", 85, 270);
			g.drawString("and the distance that you calculated, round to the nearest second the time it takes to each pocket.", 85, 320);

			g.drawString("While playing the game, click the button labeled '?' to return to these instructions.", 85, 420);
			g.drawString("-- 20 points are rewarded for each correctly calculated time.", 95, 470);
			g.drawString("-- 10 points are lost for the wrong answers.", 95, 520);

		 }

	public void getMyImage()
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
	 public void drawPicture(Graphics g)
		{
			 g.drawImage(billImage, 0, 0, 1300, 700, this);
		}

} // close instructPanel

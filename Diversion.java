import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

/*public class Diversion
{
	 public static void main(String[] args)
	 {
		 Diversion div = new Diversion();
		 div.runIt();
	 }

	 public void runIt()
	 {
		 JFrame frame = new JFrame("nm"); // construct JFrame to hold the JPanel
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 DivPan dp = new DivPan();  // construct InstructPanel to display instructions

		 frame.getContentPane().add(dp);
		 frame.setSize(1300, 700);  // construct the 1300 by 700 Jpanel
		 frame.setResizable(false);
		 frame.setLocation(0, 0);
		 frame.setVisible(true);
   }
 }*/

 class Diversion extends JPanel
 {
   protected JButton lev1;
   protected JButton lev2;
   protected JButton mm;

   public Diversion()
   {
     setLayout(null);
     lev1 = new JButton("Play Level 1");
     lev2 = new JButton("Play Level 2");
     mm = new JButton("Main Menu");

     lev1.setBounds(39, 529, 200, 100);
     add(lev1);

     lev2.setBounds(1061, 529, 200, 100);
     add(lev2);

     mm.setBounds(550, 39, 200, 100);
     add(mm);
   }

   public void paintComponent(Graphics g)
   {
     super.paintComponent(g);
     setBackground(new Color(36, 117, 37));
   }
 }

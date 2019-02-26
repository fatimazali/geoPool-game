import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
public class GoAhead extends JPanel
{
  private Image statImage;
  private String statStr;

  public GoAhead()
  {
    statImage = null;
    statStr = "GoAhead.png";
    getMyImage();
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.drawImage(statImage, 0, 0, 1300, 700, this);
  }

  public void getMyImage()
  {
	 try
	 {
		 statImage = ImageIO.read(new File(statStr));
   }
	 catch (IOException e)
	 {
     System.err.println("\n\nYour picture(s) can't be found.\n\n");
		 e.printStackTrace();
	 }
  }
}

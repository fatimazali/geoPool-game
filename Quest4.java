import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

class Quest4 extends JPanel
{
  private Image q4Image;
  private String q4Name;
  private TutorialPanel tuto;
  protected JButton mm;

  public Quest4(TutorialPanel tuto)
  {
    setLayout(null);
    this.tuto = tuto;
    q4Image = null;
    q4Name = "Quest4.png";
    mm = new JButton("Main Menu");
    mm.setBounds(0, 550, 200, 100);
    add(mm);
    getMyImage();
  }
  public void getMyImage()
  {
    try
    {
      q4Image = ImageIO.read(new File(q4Name));
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
    g.drawImage(q4Image, 0, 0, 1300, 700, this);
  }
}

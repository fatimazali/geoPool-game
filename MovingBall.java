import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class MovingBall extends JPanel implements ActionListener
{
  private Image billImage;
  private String billName;
  private int x1, y1, x2, y2, x3, y3, x4, y4;
  private int p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y;
  private Timer timer;

  public MovingBall()
  {
    setLayout(null);
    billImage = null;
    x1 = 650;
    y1 = 300;
    x2 = x1;
    x3 = x1;
    x4 = x1;
    y2 = y1;
    y3 = y1;
    y4 = y1;
    p1x = 52;
    p1y = 43;
  p2x = 1244;
    p2y = 43;
    p3x = 52;
    p3y = 550;
    p4x = 1244;
    p4y = 550;
    billName = "BilliardsTable.png";
    getMyImage();
    timer = new Timer(100, this);
    timer.start();
  }

  private void getMyImage()
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
    g.drawImage(billImage, 0, 0, 1300, 600, this);
    setBackground(new Color(41, 128, 89));

    if (x1 > p1x)
    {
    g.setColor(Color.RED);
    g.fillOval(x1, y1, 50, 50);
    double slope = (p1y-y1)*1.0/(p1x-x1);
    x1 -= 10;
    y1 -= 10 * slope;
    }
    if (x2 < p2x)
    {
    g.setColor(Color.RED);
    g.fillOval(x2, y2, 50, 50);
    double slope = (p2y-y2)*1.0/(p2x-x2);
    x2 += 10;
    y2 += 10 * slope;
    }
    if (x3 > p3x)
    {
    g.setColor(Color.RED);
    g.fillOval(x3, y3, 50, 50);
    double slope = (p3y-y3)*1.0/(p3x-x3);
    x3 -= 10;
    y3 -= 10 * slope;
    }
    if (x4 < p4x)
    {
    g.setColor(Color.RED);
    g.fillOval(x4, y4, 50, 50);
    double slope = (p4y-y4)*1.0/(p4x-x4);
    x4 += 10;
    y4 += 10 * slope;
    }
  }

  public void actionPerformed(ActionEvent e)
  {
    repaint();
  }

  public static void main(String[] args)
  {
    MovingBall bounce = new MovingBall();
    bounce.runIt();
  }

  public void runIt()
  {
    JFrame frame = new JFrame("geoPool");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.getContentPane().add(this);

    frame.setSize(1300, 700);
    frame.setResizable(false);
    frame.setLocation(0, 0);
    frame.setVisible(true);
  }
}

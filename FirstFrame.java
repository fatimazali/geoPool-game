import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

public class FirstFrame
{
  protected boolean callTuto = false;

  public FirstFrame()
  {
  }

  public static void main(String[] args)
  {
    FirstFrame ff = new FirstFrame();
    ff.runIt();
  }

  public void runIt()
  {
    JFrame frame = new JFrame("geoPool");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    FirstPanel firPan = new FirstPanel();
    frame.getContentPane().add(firPan);

    frame.setSize(1300, 700);
    frame.setResizable(false);
    frame.setLocation(0, 0);
    frame.setVisible(true);
  }

}

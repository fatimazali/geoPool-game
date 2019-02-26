import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;


public class PanelHolder extends JPanel
{
  JButton tutoButton;
  JButton startGame;
  Image billImage;
  String billName;
  Image stickImage;
  String stickName;


  public PanelHolder()
  {
    setLayout(null);

    billImage = null;
    stickImage = null;
    billName = "BilliardsTable.png";
    stickName = "doneRotate.png";
    getMyImage();

    tutoButton = new JButton("Tutorial");
    startGame = new JButton("Start Game");

    tutoButton.setBounds(490, 230, 300, 100);
    startGame.setBounds(490, 430, 300, 100);


    add(tutoButton);
    add(startGame);
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    drawPicture(g);

    Font myFnt = new Font("Serif", Font.BOLD, 50);
    g.setFont(myFnt);
    g.setColor(Color.RED);
    g.drawString("geoPool", 530, 140);

    g.setColor(Color.WHITE);
    g.fillOval(950, 355, 50, 50);

    g.setColor(new Color(201, 38, 30));
    g.fillOval(200, 280, 50, 50);

    g.setColor(new Color(0, 229, 255));
    g.fillOval(200, 340, 50, 50);

    g.setColor(new Color(0, 0, 0));
    g.fillOval(200, 400, 50, 50);

    g.setColor(new Color(252, 189, 15));
    g.fillOval(260, 310, 50, 50);

    g.setColor(new Color(169, 120, 204));
    g.fillOval(260, 370, 50, 50);

    g.setColor(new Color(255, 123, 0));
    g.fillOval(320, 340, 50, 50);
  }

  public void getMyImage()
  {
      try
      {
          billImage = ImageIO.read(new File(billName));
          stickImage = ImageIO.read(new File(stickName));
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
      g.drawImage(stickImage, 830, 160, 210, 440, this);
  }

}

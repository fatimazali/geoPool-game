import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

class Quest2 extends JPanel implements ActionListener
{
  JButton ontoQ3;
  private Image q2Image;
  private String q2Name;
  private JTextField lAC;
  private JTextField lBC;
  private JButton submit;
  JButton mm;
  private TutorialPanel tuto;

  public Quest2(TutorialPanel tuto)
  {
    this.tuto = tuto;
    setLayout(null);
    q2Image = null;
    q2Name = "Quest2.png";
    getMyImage();

    ontoQ3 = new JButton("Onto Question 3");
    ontoQ3.setBounds(1020, 550, 200, 100);
    add(ontoQ3);

    mm = new JButton("Main Menu");
    mm.setBounds(0, 550, 200, 100);
    add(mm);

    lAC = new JTextField("", 10);
    lAC.setBounds(400, 180, 100, 30);
    add(lAC);

    lBC = new JTextField("", 10);
    lBC.setBounds(400, 320, 100, 30);
    add(lBC);

    submit = new JButton("Submit");
    submit.setBounds(500, 370, 200, 50);
    submit.addActionListener(this);
    add(submit);
  }
  public void getMyImage()
  {
    try
    {
      q2Image = ImageIO.read(new File(q2Name));
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
    g.drawImage(q2Image, 0, 0, 1300, 700, this);

    g.setColor(Color.BLACK);
    g.setFont(new Font("Arial", Font.BOLD, 25));
    g.drawString("What is the length of segment AC in terms of x and y?", 400, 100);
    g.drawString("(You don't need subscripts; just type the number)", 400, 150);
    g.drawString("What is the length of segment BC in terms of x and y?", 400, 240);
    g.drawString("(You don't need subscripts; just type the number)", 400, 290);

    if(tuto.callRepaint)
    {
      if(tuto.gotCorrect)
      {
        g.drawString("That's the correct answer! Click the button to move on -->", 200, 600);
      }
      else
      {
        tuto.callRepaint = false;
        g.drawString("That's not correct. Try again!", 210, 600);
      }
    }
  }

  public void actionPerformed(ActionEvent e)
  {
    String ansAC = lAC.getText();
    String ansBC = lBC.getText();
    if(ansAC.equalsIgnoreCase("x2 - x1")||ansAC.equalsIgnoreCase("x2-x1"))
    {
      if(ansBC.equalsIgnoreCase("y2 - y1")||ansBC.equalsIgnoreCase("y2-y1"))
        tuto.gotCorrect = true;
    }
    tuto.callRepaint = true;
    repaint();
  }
}

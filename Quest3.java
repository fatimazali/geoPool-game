import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

class Quest3 extends JPanel implements ActionListener
{
  JButton ontoQ4;
  private JTextField bC;
  private JTextField bCsup;
  private JTextField aC;
  private JTextField aCsup;
  private Image q3Image;
  private String q3Name;
  private TutorialPanel tuto;
  JButton mm;
  private JButton submit;

  public Quest3(TutorialPanel tuto)
  {
    this.tuto = tuto;
    setLayout(null);
    ontoQ4 = new JButton("Last Slide");
    ontoQ4.setBounds(1050, 550, 200, 100);
    add(ontoQ4);

    q3Image = null;
    q3Name = "Quest2.png";
    getMyImage();

    mm = new JButton("Main Menu");
    mm.setBounds(0, 550, 200, 100);
    add(mm);

    bC = new JTextField("", 5); bCsup = new JTextField("", 1);
    aC = new JTextField("", 5); aCsup = new JTextField("", 1);

    bC.setBounds(532, 200, 30, 30); bCsup.setBounds(567, 185, 20, 20);
    aC.setBounds(664, 200, 30, 30); aCsup.setBounds(699, 185, 20, 20);

    add(bC); add(bCsup);
    add(aC); add(aCsup);

    submit = new JButton("Submit");
    submit.setBounds(500, 470, 200, 50);
    submit.addActionListener(this);
    add(submit);
  }

  public void getMyImage()
  {
    try
    {
      q3Image = ImageIO.read(new File(q3Name));
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
    g.drawImage(q3Image, 0, 0, 1300, 700, this);

    g.setColor(Color.BLACK);
    g.setFont(new Font("Serif", Font.BOLD, 25));
    g.drawString("AB    =    ", 400, 230);
    g.drawString("  +  ", 594, 230);
    g.setFont(new Font("Serif", Font.BOLD, 15));
    g.drawString("2", 440, 215);


    g.setFont(new Font("Arial", Font.BOLD, 25));
    g.setColor(Color.BLACK);
    g.drawString("Now that you have found the lengths of segment AC and BC,", 400, 70);
    g.drawString("find the square of the length of segment AB.", 400, 120);

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
    String subFirst = bC.getText();
    String supFirst = bCsup.getText();
    String subSec = aC.getText();
    String supSec = aCsup.getText();

    if(supFirst.equals("2") && supSec.equals("2"))
    {
      if((subFirst.equalsIgnoreCase("AC") && subSec.equalsIgnoreCase("BC")) ||
         (subSec.equalsIgnoreCase("AC") && subFirst.equalsIgnoreCase("BC")))
      {
        tuto.gotCorrect = true;
      }
    }
    else
    {
      tuto.gotCorrect = false;
    }

    tuto.callRepaint = true;
    repaint();
  }
}

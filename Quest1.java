import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

class Quest1 extends JPanel implements ActionListener
{
  JButton ontoQ2;
  private Image q1Image;
  private String q1Name;
  private JRadioButton euler;
  private JRadioButton pythagorus;
  private JRadioButton newton;
  private JRadioButton euclid;
  private ButtonGroup bg;
  JButton mm;
  private TutorialPanel tuto;
  private JButton submit;


  public Quest1(TutorialPanel tuto)
  {
    this.tuto = tuto;
    setLayout(null);
    ontoQ2 = new JButton("Onto Question 2");
    ontoQ2.setBounds(1050, 550, 200, 100);
    add(ontoQ2);

    mm = new JButton("Main Menu");
    mm.setBounds(0, 550, 200, 100);
    add(mm);

    bg = new ButtonGroup();

    euler = new JRadioButton("Euler's Theorem");
    euler.setBounds(400, 170, 300, 50);
    bg.add(euler);

    pythagorus = new JRadioButton("Pythagorean Theorem");
    pythagorus.setBounds(400, 240, 300, 50);
    bg.add(pythagorus);

    newton = new JRadioButton("Newton's Theorem");
    newton.setBounds(400, 310, 300, 50);
    bg.add(newton);

    euclid = new JRadioButton("Euclid's Theorem");
    euclid.setBounds(400, 380, 300, 50);
    bg.add(euclid);

    submit = new JButton("Submit");
    submit.setBounds(500, 470, 200, 50);
    submit.addActionListener(this);
    add(submit);

    q1Image = null;
    q1Name = "Quest1.png";

    add(euler);
    add(pythagorus);
    add(newton);
    add(euclid);

    getMyImage();
  }

  public void getMyImage()
  {
    try
    {
      q1Image = ImageIO.read(new File(q1Name));
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
    g.drawImage(q1Image, 0, 0, 1300, 700, this);
    g.setFont(new Font("Arial", Font.BOLD, 25));
    g.setColor(Color.BLACK);
    g.drawString("Let's say you have a line AC with endpoints at (x\u2081, y\u2081)", 400, 50);
    g.drawString("and (x\u2082, y\u2082). Which theorem are you most likely to use", 400, 100);
    g.drawString("to find the length of this line?", 400, 150);

    if(tuto.callRepaint)
    {
      if(tuto.gotCorrect)
      {
        g.drawString("That's the correct answer! Click the button to move on -->", 200, 600);
      }
      else
      {
        tuto.callRepaint = false;
        g.drawString("That's not correct. Try again!", 200, 600);
      }
    }
  }

  public void actionPerformed(ActionEvent e)
  {
    boolean pythSel = pythagorus.isSelected();
    if(pythSel)
      tuto.gotCorrect = true;
    tuto.callRepaint = true;
    repaint();
  }
} //End of Quest1

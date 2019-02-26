import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

class TutorialPanel extends JPanel implements ActionListener
  {
    CardLayout cards;
    Quest1 q1;
    Quest2 q2;
    Quest3 q3;
    Quest4 q4;
    boolean gotCorrect;
    private boolean tryMove;
    boolean callRepaint;
    private FirstPanel firstPanel;

    public TutorialPanel(FirstPanel firstPanel)
    {
      this.firstPanel = firstPanel;
      cards = new CardLayout();
      setLayout(cards);
      q1 = new Quest1(this);
      q2 = new Quest2(this);
      q3 = new Quest3(this);
      q4 = new Quest4(this);

      gotCorrect = false;
      tryMove = false;
      callRepaint = false;

      q1.ontoQ2.addActionListener(this);
      q1.mm.addActionListener(this);
      q2.ontoQ3.addActionListener(this);
      q2.mm.addActionListener(this);
      q3.ontoQ4.addActionListener(this);
      q3.mm.addActionListener(this);
      q4.mm.addActionListener(this);

      add(q1, "first question");
      add(q2, "second question");
      add(q3, "third question");
      add(q4, "fourth question");
    }

    public void actionPerformed(ActionEvent e)
    {
      String buttonPress = e.getActionCommand();
      if (buttonPress.equals("Onto Question 2") && gotCorrect)
      {
        gotCorrect = false;
        callRepaint = false;
        cards.show(this, "second question");
      }
      else if(buttonPress.equals("Onto Question 3") && gotCorrect)
      {
        gotCorrect = false;
        callRepaint = false;
        cards.show(this, "third question");
      }
      else if(buttonPress.equals("Last Slide"))
      {
        gotCorrect = false;
        callRepaint = false;
        cards.show(this, "fourth question");
      }
      else if (buttonPress.equals("Main Menu"))
      {
        gotCorrect = false;
        callRepaint = false;
        firstPanel.firstCard.show(firstPanel, "Start Panel");
      }
    }
  }

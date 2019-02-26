import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

public class FirstPanel extends JPanel implements ActionListener
{
  CardLayout firstCard;
  private TutorialPanel tutoPane;
  private GamePanelL2 bg;
  private GPInstructL2 instruct2;
  private Quest4 disty;
  private Diversion divert;
  private GamePanelL1 gp1;
  private GPInstructL1 instruct1;

  private Replay replay;
  private ReLearn relearn;
  private GoAhead gohead;

  public FirstPanel()
  {
    reset();
  }

  public void reset()
  {
    firstCard = new CardLayout();
    setLayout(firstCard);
    PanelHolder ph = new PanelHolder();
    bg = new GamePanelL2(this);

    divert = new Diversion();
    divert.lev1.addActionListener(this);
    divert.lev2.addActionListener(this);
    divert.mm.addActionListener(this);

    gp1 = new GamePanelL1(this);


    replay = new Replay();
    relearn = new ReLearn();
    gohead = new GoAhead();

    disty = new Quest4(tutoPane);

    tutoPane = new TutorialPanel(this);
    ph.tutoButton.addActionListener(this);
    ph.startGame.addActionListener(this);

    instruct2 = new GPInstructL2();
    instruct1 = new GPInstructL1();

    instruct2.playL2.addActionListener(this);
    instruct1.playL1.addActionListener(this);


    bg.backToInst.addActionListener(this);
    bg.backToMenu.addActionListener(this);
    bg.distPan.addActionListener(this);

    add(ph, "Start Panel");
    add(tutoPane, "Tutorial");
    add(instruct2, "Instructions2");
    add(instruct1, "Instructions1");
    add(bg, "Bounce Game");
    add(disty, "Quest4");
    add(divert, "Diversion");
    add(gp1, "Level 1");
    add(replay, "Replay");
    add(relearn, "ReLearn");
    add(gohead, "GoAhead");
  }
  public void actionPerformed(ActionEvent e)
  {
    String text = e.getActionCommand();
    if(text.equals("Tutorial"))
    {
      firstCard.show(this, "Tutorial");
    }
    else if(text.equals("Start Game"))
    {
      firstCard.show(this, "Diversion");
    }
    else if(text.equals("?") || text.equals("Play Level 2"))
    {
      firstCard.show(this, "Instructions2");
    }
    else if(text.equals("Play Level 1"))
    {
      firstCard.show(this, "Instructions1");
    }
    else if(text.equals("Level 1"))
    {
      firstCard.show(this, "Level 1");
    }
    else if(text.equals("Play Level 2") || text.equals("Play"))
    {
      firstCard.show(this, "Bounce Game");
    }
    else if(text.equals("DF"))
    {
      firstCard.show(this, "Quest4");
    }
    else if(text.equals("Main Menu") || text.equals("Menu"))
    {
      firstCard.show(this, "Start Panel");
    }
  }
}

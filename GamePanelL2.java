import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

/*
  Siri Konanoor
  GamePanelL2.java

  This program is the second level of the actual game that users play.
  The ball is placed in random places on the green area of the billiards BilliardsTable
  using Math.random() for the x & y coordinates. The coordinates of the four pockets
  on each corner are to be displayed on the screen. Although not done yet, what
  I will be working on next week is: displaying the speed to each pocket, coding the
  calculation of the distance formula, the animation of the ball going into the pocket.
*/

class GamePanelL2 extends JPanel implements ActionListener
{
  private Image billImage;
  private String billName;
  protected static int x, y;
  protected JButton backToInst;
  protected JButton distPan;
  protected JButton backToMenu;


  private static int botLeftTime;
  private static int topLeftTime;
  private static int topRightTime;
  private static int botRightTime;

  private JTextField blTxt;           //JTextField for bottom left pocket
  private JTextField tlTxt;           //JTextField for top left pocket
  private JTextField trTxt;           //JTextField for top right pocket
  private JTextField brTxt;           //JTextField for bottom right pocket

  private JButton submit;
  private FirstPanel fp;
  private int probNum;
  private int score;

  private static int speed;           //speed till each pocket
  private static int forY;            //math x-coordinate of the ball
  private static int forX;            //math y-coordinate of the ball
  private static int ballX;           //x coordinate of the ball
  private static int ballY;           //y coordinate of the ball

  protected int gotRight;             //keeps count of right answers

  private GamePanelL1 gp1;
  protected Timer timer;
  private ForTimer timmy;





   public GamePanelL2(FirstPanel fp)
  {
    setLayout(null);

    score = 0;
    speed = 20;

    gotRight = 0;

    billImage = null;
    billName = "BilliardsTable.png";
    getMyImage();

    blTxt = new JTextField("");
    blTxt.setBounds(250, 600, 40, 30);
    add(blTxt);

    tlTxt = new JTextField("");
    tlTxt.setBounds(250, 640, 40, 30);
    add(tlTxt);

    trTxt = new JTextField("");
    trTxt.setBounds(770, 600, 40, 30);
    add(trTxt);

    brTxt = new JTextField("");
    brTxt.setBounds(770, 640, 40, 30);
    add(brTxt);

    timmy = new ForTimer(x, y);
    timer = new Timer(100, timmy);




    backToInst = new JButton("?"); // JButton to access instructions panel
    backToInst.setBounds(250, 0, 100, 48);
    add(backToInst);

    backToMenu = new JButton("Menu"); // JButton to go back to main menu
    backToMenu.setBounds(370, 0, 100, 48);
    add(backToMenu);

    distPan = new JButton("DF"); // JButton to access distance formula
    distPan.setBounds(490, 0, 100, 48);
    add(distPan);



    submit = new JButton("Submit");
    submit.setBounds(1150, 625, 120, 50);
    submit.addActionListener(this);
    add(submit);

    gp1 = new GamePanelL1(fp);

    probNum = 1;
    this.fp = fp;
    reset();
  }

  public void reset()
  {
    forY = ((int) (Math.random()* 10 + 5))*10; //the random y coordinate of pockets
    forX = forY*2;                             //the random x-coordiante of the pockets

    ballX = (int)(Math.random()* forX + 0);    //the math x-coordinate of the ball
    ballY = (int)(Math.random()* forY + 0);    //the math y-coordinate of the ball

    x = (ballX*1300)/forX;                     //the CS x-coordinate of the ball
    y = ((forY-ballY)*600)/forY;               //the CS y-coordinate of the ball


    if(x < 105)
    {
      x += 105;
      ballX = (x*forX)/1300;
    }
    else if(x > 1175)
    {
      x -= 105;
      ballX = (x*forX)/1300;
    }

    if(y < 105)
    {
      y += 105;
      ballY = (y*forY)/1300;
    }
    else if (y > 495)
    {
      y -= 105;
      ballY = (y*forY)/1300;
    }


    System.out.println("\n\n\nx = " + x + " and forX = " + forX);
    System.out.println("\n\n\ny = " + y + " and forY = " + forY);








    blTxt.setText("");
    tlTxt.setText("");
    trTxt.setText("");
    brTxt.setText("");



    calcBotLeft();
    calcTopLeft();
    calcTopRight();
    calcBotRight();

    System.out.println("\n\n\n" + botLeftTime);
    System.out.println(topLeftTime);
    System.out.println(topRightTime);
    System.out.println(botRightTime);

    repaint();
  }









  public void getMyImage()
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

      g.setColor(Color.RED);
      g.fillOval(x, y, 50, 50);

      g.setColor(Color.ORANGE);
      g.setFont(new Font("Serif", Font.PLAIN, 35));



      String scoStr = "score = " + score;
      g.drawString(scoStr, 940, 30);

      String speedStr = "speed = " + speed;
      g.drawString(speedStr, 700, 30);



      g.setFont(new Font("Serif", Font.BOLD, 30));
      g.setColor(new Color(252, 210, 0));



      g.drawString("(0, 0)", 95, 560);      //coordinates for bottom left pocket
      g.drawString("(0, " + forY + ")", 95, 60);        //coordinates for top left pocket
      g.drawString("(" + forX + ", " + forY + ")", 1040, 60);    //coordinates for top right pocket
      g.drawString("(" + forX + ", 0)", 1040, 560);  //coordinates for bottom right






      g.setFont(new Font("Serif", Font.BOLD, 20));
      String ballCord = "" + ballX + ", " + ballY;      //for printing the cordinates of the ball
      g.drawString (ballCord, x + 50, y + 50);


      g.setColor(Color.WHITE);

      g.setFont(new Font("Serif", Font.BOLD, 25));
      g.drawString("Times:", 600, 590);

      g.setFont(new Font("Serif", Font.BOLD, 20));
      g.drawString("Bottom left pocket:", 20, 620);
      g.drawString("Top left pocket:", 20, 660);

      g.drawString("Top right pocket:", 520, 620);
      g.drawString("Bottom right pocket:", 520, 660);

      g.drawString("seconds", 300, 630);
      g.drawString("seconds", 300, 670);
      g.drawString("seconds", 820, 630);
      g.drawString("seconds", 820, 670);
  }

  public static void calcBotLeft()
  {
    double squareX;    //square of x2-x1
    double squareY;    //square of y2-y1
    double squareSum;  //sum of the two squares
    double botLeftRoot;   //leftBotRoot = square root of of the sum

    squareX = Math.pow(ballX-0, 2);
    squareY = Math.pow(ballY-0, 2);
    squareSum = squareX + squareY;
    botLeftRoot =  Math.sqrt(squareSum);

    botLeftTime = (int) (Math.round(botLeftRoot/speed));
  }

  public static void calcTopLeft()
  {
    double squareX;    //square of x2-x1
    double squareY;    //square of y2-y1
    double squareSum;  //sum of the two squares
    double topLeftRoot;   //topLeftRoot = square root of of the sum

    squareX = Math.pow(ballX-0, 2);
    squareY = Math.pow(ballY-forY, 2);
    squareSum = squareX + squareY;
    topLeftRoot =  Math.sqrt(squareSum);

    topLeftTime = (int) (Math.round(topLeftRoot/speed));
  }

  public static void calcTopRight()
  {
    double squareX;    //square of x2-x1
    double squareY;    //square of y2-y1
    double squareSum;  //sum of the two squares
    double topRightRoot;   //topRightRoot = square root of of the sum

    squareX = Math.pow(ballX-forX, 2);
    squareY = Math.pow(ballY-forY, 2);
    squareSum = squareX + squareY;
    topRightRoot =  Math.sqrt(squareSum);

    topRightTime = (int) (Math.round(topRightRoot/speed));
  }

  public static void calcBotRight()
  {
    double squareX;    //square of x2-x1
    double squareY;    //square of y2-y1
    double squareSum;  //sum of the two squares
    double botRightRoot;   //botRightRoot = square root of of the sum

    squareX = Math.pow(ballX-forX, 2);
    squareY = Math.pow(ballY-0, 2);
    squareSum = squareX + squareY;
    botRightRoot = Math.sqrt(squareSum);

    botRightTime = (int) (Math.round(botRightRoot/speed));
  }


  public void actionPerformed(ActionEvent e)
  {
      int answer = -999;

      boolean botLeft = blTxt.getText().equals("" + botLeftTime);
      boolean topLeft = tlTxt.getText().equals("" + topLeftTime);
      boolean topRight = trTxt.getText().equals("" + topRightTime);
      boolean botRight = brTxt.getText().equals("" + botRightTime);

      boolean rightAnswer = botLeft && topLeft && topRight && botRight;

      String[] options = {"Main Menu", "Next Problem"};//array that contains the name of button between one question and the other
      int optNum = -888;

      String[] lastOption = {"Skip to complete"}; //array that contains the name of the button to be displayed on the pop-up screen
      int lastNum = -888;

      if(probNum == 5)
      {
        lastNum = JOptionPane.showOptionDialog(null, "You are done with Level 2 of geoPool",
                                              "Result", JOptionPane.YES_NO_OPTION,
                                              JOptionPane.QUESTION_MESSAGE, null,
                                              lastOption, lastOption[0]);
      }

      else if(rightAnswer)
      {
        score += 20;
        gotRight++;
        timer.start();

        optNum = JOptionPane.showOptionDialog(null, "Yay, That's the right answer!",
                                              "Result",JOptionPane.YES_NO_OPTION,
                                              JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      }
      else if(!rightAnswer)
      {
        score -= 10;
        optNum = JOptionPane.showOptionDialog(null, "Sorry, that's the wrong answer.",
                                              "Result",JOptionPane.YES_NO_OPTION,
                                              JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      }
      if(optNum == 0)            //if user chose main menu
      {
        fp.firstCard.show(fp, "Start Panel");
      }
      else if(optNum == 1 || optNum == -1)      //if user chose next problem
      {
        probNum++;
        reset();
        repaint();
      }
      else if(lastNum == 0)
      {
        if(rightAnswer)
        {
            score += 20;
            gotRight++;
        }
        else
            score -= 10;

        determineGratify();
      }

  }

  public void determineGratify() //
  {
    gotRight += gp1.gotRight; //add the number of right answers from both levels

    if(gotRight < 4)
    {
      fp.firstCard.show(fp, "ReLearn"); //show reLearn
    }
    else if(gotRight >= 5 || gotRight <= 7)
    {
      fp.firstCard.show(fp, "Replay");  //show replay
    }
    else if(gotRight >= 8)
    {
      fp.firstCard.show(fp, "GoAhead"); //show goAhead
    }
  }
  class ForTimer extends JPanel implements ActionListener
  {
    private GamePanelL2 game2;
    private int pock;
    private int p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y;
    private double slope;
    private int xTime, yTime;

    public ForTimer(int x, int y)
    {
      pock = (int) Math.random()*4 + 1;

      p1x = 52;
      p1y = 43;
      p2x = 1244;
      p2y = 43;
      p3x = 52;
      p3y = 550;
      p4x = 1244;
      p4y = 550;
    }


    public void paintComponent(Graphics g)
    {
      if(/*pock == 1 &&*/ x > p1x)
      {
        g.setColor(Color.RED);
        g.fillOval(x, y, 50, 50);
        slope = (p1y-y)*1.0/(p1x-x);
        x -= 10;
        y -= 10 * slope;
      }
      else if(/*pock == 2 &&*/ x < p2x)
      {
        g.setColor(Color.RED);
        g.fillOval(x, y, 50, 50);
        slope = (p2y-y)*1.0/(p2x-x);
        x += 10;
        y += 10 * slope;
      }
      else if(/*pock == 3 &&*/ x > p3x)
      {
        g.setColor(Color.RED);
        g.fillOval(x, y, 50, 50);
        slope = (p3y-y)*1.0/(p3x-x);
        x -= 10;
        y -= 10 * slope;
      }
      else if (/*pock == 4 &&*/ x < p4x)
      {
        g.setColor(Color.RED);
        g.fillOval(x, y, 50, 50);
        slope = (p4y-y)*1.0/(p4x-x);
        x += 10;
        y += 10 * slope;
      }
    }

    public void actionPerformed(ActionEvent e)
    {
      repaint();
    }
  }
}

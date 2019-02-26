
/*
 * Fatima Ali
 * Period 2
 * GamePanelL1.java
 * 3/27/18
 * This class has the panel displayed while playing Level 1 of the game.
 * There are three JButtons displayed,  to access the Distance Formula panel or the Main Menu panel.
 The user has to calculate the distance from the white ball to the three colored balls, which use randomly
  generated coordinates. There are 5 rounds of Level 1, and in each round, the distance between the white
  and colored balls increases. If the user’s answers are correct, they move on the next level and get to
  watch a billiards stick hit the red ball into the top left corner pocket.
 *
 */

 /*
 import java.awt.*; // add all import statements
 import javax.swing.*;
 import java.awt.event.*;
 import java.io.*;
 import javax.imageio.ImageIO;
 import javax.swing.Timer;

/*public class GamePanelL1
{
	public static void main(String[] args)
	{
		GamePanelL1 bounce = new GamePanelL1();
		bounce.runIt();
	}

	 public void runIt()
	 {
		JFrame frame = new JFrame("geoPool");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		GamePanelL1Temp bPan = new GamePanelL1Temp();
		frame.getContentPane().add(bPan);

		frame.setSize(1300, 700);
		frame.setResizable(false);
		frame.setLocation(0, 0);
		frame.setVisible(true);
  }

	 public class GamePanelL1 extends JPanel implements ActionListener
	 {
		  private Image billImage; // image of billiards table
		  private String billName; // variable to hold billiard table file name
		  private int x, y, xYell, yYell, xRed, yRed, xGreen, yGreen; // variables to hold coordinates of white ball and colored balls
		  private int probNum, distRed, distYell, distGreen, runningScore; // variable to hold current problem number (counter variable), dist to each colored ball, and current score
		  private double tempDist;   // variable to take the sqaure root of to arrive at final distance, using distance formula calculations
		  protected JButton backToInst; // JButton to return to instructions
		  protected JButton distPan;  // JButton to return to distance formula
		  protected JButton backToMenu;  // JButton to return to main menu
		  protected JTextField redAns, yellAns, greenAns;  // JTextFields for user to type in answers of each distance length
		  protected boolean success, finished, unfinished; // booleans to perform if-else selection to determine which pop up display to show
		  protected boolean corrRed, corrYellow, corrGreen, corrTotal; // booleans to identify if user answers are correctl
      private int forY; // variables to create scale of CS coordinates to Cartesian coordinates
      private int forX;
      private int xYellMath, xMath, xRedMath, xGreenMath; //Cartesian coordinates of each pool abll
      private int yYellMath, yMath, yRedMath, yGreenMath;
      private int ballX;
      private int ballY;
      private int x1, y1, p1x, p1y; // x and y coordinates of moving red ball during animaition and upper left pocket
      private Timer timer; // timer to be used in ball animation

      private JButton submit;
      private FirstPanel fp;
  //    private int probNum;
      private int score;

		  public GamePanelL1(FirstPanel fp)
		  {
			setLayout(null);
			billImage = null;
			billName = "BilliardsTable.png";
			getMyImage();
			x = 950;
			y = 400;
			probNum = 0;

      forX = 140;
      forY = 70;

			backToInst = new JButton("?"); // JButton to access instructions panel
			backToInst.setBounds(110, 0, 100, 48);
			add(backToInst);

			backToMenu = new JButton("Menu"); // JButton to go back to main menu
			backToMenu.setBounds(230, 0, 100, 48);
			add(backToMenu);

			distPan = new JButton("DF"); // JButton to access distance formula
			distPan.setBounds(350, 0, 100, 48);
			add(distPan);

			redAns = new JTextField ("Red: ", 10);				// create 3 JTextFields for input
			redAns.setBounds(100, 610, 80, 30);      // add JTextField to null layout
			add(redAns);

			greenAns = new JTextField ("Green: ", 10);				// create 3 JTextFields for input
			greenAns.setBounds(300, 610, 80, 30);
			add(greenAns);

			yellAns = new JTextField ("Yellow: ", 10);				// create 3 JTextFields for input
			yellAns.setBounds(500, 610, 80, 30);
			add(yellAns);

			submit = new JButton("Submit");
			submit.setBounds(1150, 625, 120, 50);
			submit.addActionListener(this);
			add(submit);

      this.fp = fp;
      reset();

  }

  public void reset()
  {
    determineCoordinates(); // calculate new coordinates of the 3 colored balls
    corrTotal = false; // reset boolean to false in order to accurately assess whether answer is correct or not

    //forY = ((int) (Math.random()* 10 + 5))*10;    //the random y coordinate of pockets, use it to create the scale of lengths
    //forX = forY*2;

    //xYellMath = (int)(Math.random()* forX + 0);       //the math x-coordinate of the ball
    //yYellMath = (int)(Math.random()* forY + 0);       //the math y-coordinate of the ball

    redAns.setText(""); // reset the JTextFields that hold user answer input for the next of the 5 rounds
    yellAns.setText("");
    greenAns.setText("");

    xYellMath = (int)((forX * xYell) / 1300); // math coordinates of the yellow ball
    yYellMath = (int)((forY * yYell) / 1300);

    xMath = (int)((forX * x) / 1300); // math coordinates of the white ball
    yMath = (int)((forY * y) / 1300);

    xRedMath = (int)((forX * xRed) / 1300); // math coordinates of the red ball
    yRedMath = (int)((forY * yRed) / 1300);

    xGreenMath = (int)((forX * xGreen) / 1300); // math coordinates of the green ball
    yGreenMath = (int)((forY * yGreen) / 1300);

    p1x = 52; // x coordinate of the upper left pocket of the billiards table
    p1y = 43; // y coordinate of the upper left pocket of the billiards table

    determineDistance();
//    xYell = (xYellMath*1300)/forX;                        //the CS x-coordinate of the ball
//    yYell = ((forY-yYellMath)*600)/forY;                  //the CS y-coordinate of the ball
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

		public void determineCoordinates() // draw and fill ovals for each of the 4 balls, using Math.random to generate values inside the defined quadrant for the colored balls.
	 // make balls further away if "challenge" mode is selected.
	  {

			xRed = (int) (Math.random()*720 + 80); // x coord between 80 and 800
			yRed = (int) (Math.random()*410 + 90);	  // y coord between 90 and 500
			xYell = (int) (Math.random()*720 + 80);
			yYell= (int) (Math.random()*410 + 90);
			xGreen = (int) (Math.random()*720 + 80);
			yGreen= (int) (Math.random()*410 + 90);	    // if want to make more customizable, use an array for the 3 sets of x and y values?

	  }

		public void paintComponent(Graphics g) // draw and fill ovals for each of the 4 balls, using Math.random to generate values inside the defined quadrant for the colored balls.
		{
		  super.paintComponent(g);
			setBackground(new Color(41, 128, 89));

        g.drawImage(billImage, 0, 0, 1300, 600, this); // display background image

  			g.setColor(Color.WHITE); // draw white ball that has constant location at (950, 400)
  			g.fillOval(950, 400, 50, 50); //
  			determineCoordinates();
  									// draw red ball to be in the area between the rectangle: (80,90) and 80, 500) and and (800, 500) and (800, 90)
        g.setFont(new Font("Serif", Font.BOLD, 20));

      	g.setColor(Color.RED);
  			g.fillOval(xRed, yRed, 50, 50); //
        g.drawString("" + xRedMath + ", " + yRedMath, xRed + 50, yRed + 50);
        g.drawString("Red: ", 50, 630);

  			g.setColor(Color.YELLOW);
        g.fillOval(xYell, yYell, 50, 50); //
        g.drawString("" + xYellMath + ", " + yYellMath, xYell + 50, yYell + 50);
        g.drawString("Yellow: ", 200, 630);

  			g.setColor(Color.GREEN);
  			g.fillOval(xGreen, yGreen, 50, 50); //
        g.drawString("" + xGreenMath + ", " + yGreenMath, xGreen + 50, yGreen + 50);
        g.drawString("Green: ", 400, 630);

  			g.setColor(Color.WHITE);
        g.drawString("" + xMath + ", " + yMath, x + 50, y + 50);

        g.setFont(new Font("Serif", Font.BOLD, 50));
        g.drawString("Score: " + runningScore, 830,40);	// display current score  c
  			g.setFont(new Font("Serif", Font.BOLD, 20));
  			g.drawString("Enter in each distance, rounded to the nearest whole number:", 50, 600);
      }
	   public void determineDistance() // draw and fill ovals for each of the 4 balls, using Math.random to generate values inside the defined quadrant for the colored balls.
	  {
		  // calculate each of the three distances using the calculated coordinates
		  // square root of: dist1^2 + dist2^2

		  tempDist = (double)((xRedMath - xMath)*(xRedMath - xMath) + (yRedMath - yMath)*(yRedMath - yMath));
		  distRed = (int) (Math.round((Math.sqrt(tempDist))));

		  tempDist = (double)((xYellMath - xMath)*(xYellMath - xMath) + (yYellMath - yMath)*(yYellMath - yMath));
		  distYell = (int) (Math.round((Math.sqrt(tempDist))));

		  tempDist = (double)((xGreenMath - xMath)*(xGreenMath - xMath) + (yGreenMath - yMath)* (yGreenMath - yMath));
		  distGreen = (int) (Math.round((Math.sqrt(tempDist))));

      System.out.println("\n\n\n Red is " + distRed);
      System.out.println("\n\n\n Green is " + distGreen);
      System.out.println("\n\n\n Yell is " + distYell);

	  }

	public void actionPerformed(ActionEvent e) // when "submit" JButton is clicked, adjust the score and implement the counter. then call repaint, for updated score and new, further away balls to show up.
	{

    int answer = -999;

    corrRed = redAns.getText().equals("" + distRed);
    corrYellow = yellAns.getText().equals("" + distYell);
    corrGreen =  greenAns.getText().equals("" + distGreen);
    corrTotal = (corrRed && corrYellow && corrGreen);

    String[] options = { "Next Problem", "Main Menu"};
    int optNum = -55;
    String[] lastOption = {"~Skip~ to Level 2"};
    int lastNum = -55;

    if(probNum == 5)
    {
      lastNum = JOptionPane.showOptionDialog(null, "You are done with Level 1 of geoPool",
                                            "Result", JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null,
                                            lastOption, lastOption[0]);
    }

    else if(corrTotal)
    {
	  runningScore += 5; // adjust runningScore depending on win or loss
//	  hitColoredBall(); // does the program come back to this method afterwards
      optNum = JOptionPane.showOptionDialog(null, "Yay, That's the right answer!",
                                            "Result",JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
    else if(!corrTotal)
    {
      runningScore -= 7;
      optNum = JOptionPane.showOptionDialog(null, "Sorry, that's the wrong answer.",
                                            "Result",JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
    if(lastNum == 0)            //if user chooses to continue to level 2
    {
      fp.firstCard.show(fp, "Instructions");
    }

    if(optNum == 1) // if user clicks "main menu" button
    {
      fp.firstCard.show(fp, "Start Panel");
    }

    else if(optNum == 0 || optNum == -1)      //if user cicks x button or clicks next problem, continue to next problem
    {
      //pock = (int) (Math.random()* 4 + 1);
      //repaint();

      if(probNum != 5)
      {
        probNum++;
        reset();
      }
      else if(probNum == 5)
      {
        if(corrTotal)
          runningScore += 5;
        else
          runningScore -= 7;
      }
      repaint();
          // call the end panel?

    }

}
}

*/

/*
 * Fatima Ali
 * Period 2
 * GamePanelL1.java
 * 3/27/18
 * This class has the panel displayed while playing Level 1 of the game.
 * There are three JButtons displayed,  to access the Distance Formula panel or the Main Menu panel.
 The user has to calculate the distance from the white ball to the three colored balls, which use randomly
  generated coordinates. There are 5 rounds of Level 1, and in each round, the distance between the white
  and colored balls increases. If the user’s answers are correct, they move on the next level and get to
  watch a billiards stick hit the red ball into the top left corner pocket.

 * in action listener for submit button, increase counter by 1 and call repaint(); use for loop to make it cycle through 5 rounds total
 * -- use Math.round() to round distance to nearest
 *
 */
 import java.awt.*; // add all import statements
 import javax.swing.*;
 import java.awt.event.*;
 import java.io.*;
 import javax.imageio.ImageIO;
 import javax.swing.Timer;

	 public class GamePanelL1 extends JPanel implements ActionListener
	 {
		  private Image billImage; // image of billiards table
		  private String billName; // variable to hold billiard table file name
		  private int x, y, xYell, yYell, xRed, yRed, xGreen, yGreen; // variables to hold coordinates of white ball and colored balls
		  private int probNum, distRed, distYell, distGreen, runningScore; // variable to hold current problem number (counter variable), dist to each colored ball, and current score
		  private double tempDist;   // variable to take the sqaure root of to arrive at final distance, using distance formula calculations
		  protected JButton backToInst; // JButton to return to instructions
		  protected JButton distPan;  // JButton to return to distance formula
		  protected JButton backToMenu;  // JButton to return to main menu
		  protected JTextField redAns, yellAns, greenAns;  // JTextFields for user to type in answers of each distance length
		  protected boolean success, finished, unfinished; // booleans to perform if-else selection to determine which pop up display to show
		  protected boolean corrRed, corrYellow, corrGreen, corrTotal;
      private int forY; // variables to create scale of CS coordinates to Cartesian coordinates
      private int forX;
      private int xYellMath, xMath, xRedMath, xGreenMath; //Cartesian x-coordinate of the yellow ball
      private int yYellMath, yMath, yRedMath, yGreenMath;
      private int ballX; // X coordinate of
      private int ballY;

      private JButton submit;
      private FirstPanel fp;
  //    private int probNum;
      private int score;

      protected int gotRight;

		  public GamePanelL1(FirstPanel fp)
		  {
			setLayout(null);
			billImage = null;
			billName = "BilliardsTable.png";
			getMyImage();
			x = 950;
			y = 400;
			probNum = 0;

      forX = 140;
      forY = 70;

			backToInst = new JButton("?"); // JButton to access instructions panel
			backToInst.setBounds(110, 0, 100, 48);
      backToInst.addActionListener(this);
			add(backToInst);

			backToMenu = new JButton("Menu"); // JButton to go back to main menu
			backToMenu.setBounds(230, 0, 100, 48);
      backToMenu.addActionListener(this);
			add(backToMenu);

			distPan = new JButton("DF"); // JButton to access distance formula
			distPan.setBounds(350, 0, 100, 48);
      distPan.addActionListener(this);
			add(distPan);

			redAns = new JTextField ("Red: ", 10);				// create 3 JTextFields for input
			redAns.setBounds(100, 610, 80, 30);
			add(redAns);

			greenAns = new JTextField ("Green: ", 10);				// create 3 JTextFields for input
			greenAns.setBounds(300, 610, 80, 30);
			add(greenAns);

			yellAns = new JTextField ("Yellow: ", 10);				// create 3 JTextFields for input
			yellAns.setBounds(500, 610, 80, 30);
			add(yellAns);

			submit = new JButton("Submit");
			submit.setBounds(1150, 625, 120, 50);
			submit.addActionListener(this);
			add(submit);

      this.fp = fp;
      reset();
  }

  public void reset()
  {
    determineCoordinates();
    corrTotal = false;

    //forY = ((int) (Math.random()* 10 + 5))*10;    //the random y coordinate of pockets, use it to create the scale of lengths
    //forX = forY*2;

    //xYellMath = (int)(Math.random()* forX + 0);       //the math x-coordinate of the ball
    //yYellMath = (int)(Math.random()* forY + 0);       //the math y-coordinate of the ball

    redAns.setText("");
    yellAns.setText("");
    greenAns.setText("");

    xYellMath = (int)((forX * xYell) / 1300); // convert CS coordinates to Cartesian/math coordinates by scaling the values
    yYellMath = (int)((forY * yYell) / 1300);

    xMath = (int)((forX * x) / 1300);
    yMath = (int)((forY * y) / 1300);

    xRedMath = (int)((forX * xRed) / 1300);
    yRedMath = (int)((forY * yRed) / 1300);

    xGreenMath = (int)((forX * xGreen) / 1300);
    yGreenMath = (int)((forY * yGreen) / 1300);
    determineDistance();
//    xYell = (xYellMath*1300)/forX;                        //the CS x-coordinate of the ball
//    yYell = ((forY-yYellMath)*600)/forY;                  //the CS y-coordinate of the ball
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

		public void determineCoordinates() // draw and fill ovals for each of the 4 balls, using Math.random to generate values inside the defined quadrant for the colored balls.
	 // make balls further away if "challenge" mode is selected.
	  {
		//	for

			xRed = (int) (Math.random()*720 + 80); // create random x coord between 80 and 800 for red ball
			yRed = (int) (Math.random()*410 + 90);	// create random y coord between 90 and 500 for red ball
			xYell = (int) (Math.random()*720 + 80);
			yYell= (int) (Math.random()*410 + 90);
			xGreen = (int) (Math.random()*720 + 80);
			yGreen= (int) (Math.random()*410 + 90);

	  }

		public void paintComponent(Graphics g) // draw and fill ovals for each of the 4 balls, using Math.random to generate values inside the defined quadrant for the colored balls.
		{

			super.paintComponent(g);
			setBackground(new Color(41, 128, 89));

			g.drawImage(billImage, 0, 0, 1300, 600, this); // display background image


			g.setColor(Color.WHITE); // draw white ball that has constant location at (950, 400)
			g.fillOval(950, 400, 50, 50); //
			determineCoordinates();
									// draw red ball to be in the area between the rectangle: (80,90) and 80, 500) and and (800, 500) and (800, 90)
      g.setFont(new Font("Serif", Font.BOLD, 20));

    	g.setColor(Color.RED);
			g.fillOval(xRed, yRed, 50, 50); //
      g.drawString("" + xRedMath + ", " + yRedMath, xRed + 50, yRed + 50);
      g.drawString("Red: ", 50, 630);

			g.setColor(Color.YELLOW);
      g.fillOval(xYell, yYell, 50, 50); //
      g.drawString("" + xYellMath + ", " + yYellMath, xYell + 50, yYell + 50);
      g.drawString("Yellow: ", 200, 630);

			g.setColor(Color.GREEN);
			g.fillOval(xGreen, yGreen, 50, 50); //
      g.drawString("" + xGreenMath + ", " + yGreenMath, xGreen + 50, yGreen + 50);
      g.drawString("Green: ", 400, 630);

			g.setColor(Color.WHITE);
      g.drawString("" + xMath + ", " + yMath, x + 50, y + 50);

      g.setFont(new Font("Serif", Font.BOLD, 50));
      g.drawString("Score: " + runningScore, 830,40);	// display current score  c
			g.setFont(new Font("Serif", Font.BOLD, 20));
			g.drawString("Enter in each distance, rounded to the nearest whole number:", 50, 600);
		}

	   public void determineDistance() // draw and fill ovals for each of the 4 balls, using Math.random to generate values inside the defined quadrant for the colored balls.
	  {
		  // calculate each of the three distances using the calculated coordinates
		  // square root of: dist1^2 + dist2^2

		  tempDist = (double)((xRedMath - xMath)*(xRedMath - xMath) + (yRedMath - yMath)*(yRedMath - yMath));
		  distRed = (int) (Math.round((Math.sqrt(tempDist))));

		  tempDist = (double)((xYellMath - xMath)*(xYellMath - xMath) + (yYellMath - yMath)*(yYellMath - yMath));
		  distYell = (int) (Math.round((Math.sqrt(tempDist))));

		  tempDist = (double)((xGreenMath - xMath)*(xGreenMath - xMath) + (yGreenMath - yMath)* (yGreenMath - yMath));
		  distGreen = (int) (Math.round((Math.sqrt(tempDist))));

      System.out.println("\n\n\n Red is " + distRed);
      System.out.println("\n\n\n Green is " + distGreen);
      System.out.println("\n\n\n Yell is " + distYell);

	  }

	public void actionPerformed(ActionEvent e) // when "submit" JButton is clicked, adjust the score and implement the counter. then call repaint, for updated score and new, further away balls to show up.
	{
    String txt = e.getActionCommand();

    if(txt.equalsIgnoreCase("Menu"))
    {
      fp.firstCard.show(fp, "Start Panel");
    }
    else if(txt.equalsIgnoreCase("df"))
    {
      fp.firstCard.show(fp, "Quest4");
    }
    else if(txt.equalsIgnoreCase("?"))
    {
      fp.firstCard.show(fp, "Instructions1");
    }

    else if(txt.equalsIgnoreCase("submit"))
    {
      int answer = -999;

      corrRed = redAns.getText().equals("" + distRed); // set corrRed to true if red distance is correct
      corrYellow = yellAns.getText().equals("" + distYell); // set corrYellow to true if  distance is correct
      corrGreen =  greenAns.getText().equals("" + distGreen); // set corrGreen to true if  distance is correct
      corrTotal = (corrRed && corrYellow && corrGreen);

      String[] options = { "Next Problem", "Main Menu"};
      int optNum = -55;
      String[] lastOption = {"Skip to Level 2"};
      int lastNum = -55;

      if(probNum == 5)
      {
        lastNum = JOptionPane.showOptionDialog(null, "You are done with Level 1 of geoPool",
                                              "Result", JOptionPane.YES_NO_OPTION,
                                              JOptionPane.QUESTION_MESSAGE, null,
                                              lastOption, lastOption[0]);
      }

      else if(corrTotal)
      {
  	  runningScore += 5; // adjust runningScore depending on win or loss

        optNum = JOptionPane.showOptionDialog(null, "Yay, That's the right answer!",
                                              "Result",JOptionPane.YES_NO_OPTION,
                                              JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        gotRight++;
      }
      else if(!corrTotal)
      {
        runningScore -= 7;
        optNum = JOptionPane.showOptionDialog(null, "Sorry, that's the wrong answer.",
                                              "Result",JOptionPane.YES_NO_OPTION,
                                              JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      }
      if(lastNum == 0)            //if user chooses to continue to level 2
      {
        fp.firstCard.show(fp, "Start Panel");
      }

      if(optNum == 1) // if user clicks "main menu" button
      {
        fp.firstCard.show(fp, "Start Panel");
      }

      else if(optNum == 0 || optNum == -1)      //if user cicks x button or clicks next problem, continue to next problem
      {
        if(probNum != 5) // if user is not on the last round
        {
          probNum++; // increment round counter
          reset(); // reset the billairds table with new balls
        }
        else if(probNum == 5) // if user is on the last round
        {
          if(corrTotal) // if all answers were correct in last round
          {
            runningScore += 5; // adjust score
            gotRight++; // increment number of correct rounds
          }
          else
            runningScore -= 7; //adjust score
        }
        repaint();
      }
    }
  }
}





												// draw 3 colored balls that have randomly generated coordinates but are in the
												// choosing harder difficulty makes the randomly generated coordinate values be harder to calculate with;
											// import instructions on  how to use distance formula; be sure to cite it in the bibliography and verbally


											// method that determines distance to balls, using booleans to determine if user has selected "challenge" mode or "regular" mode

											// if challenge mode was selected by user, the distance to colored balls will be greater

											// insert image of an example problem on how to use it

											 // in round 1, you're given 3 colored balls and 1 white ball
											// , you use dist formula to calculate distance between the balls and the user rounds down / turns the final answer into an integer (after not rounding anything during the calculations)
											//
											// which balls are within a certain specified distance, and then you click each of the balls that are in that specified distance



											 // 2nd level:
											 // you're given coordinates of the ball and the pocket that you want to hit the ball in. you have to hit the ball off a specified wall and then it will bounce off into the pocket if your calulcations are done correctly


											// use nullLayout to hold all of the components
											// import picture of Billiards table
											// create JTextField to type coordinates of x and y in, as (x,y); specify the format, in math/Cartesian coordinates, not the type used in programming
											// convert the x coordinate and confirm that the y coordinate is equal to the converted version of the top or bottom

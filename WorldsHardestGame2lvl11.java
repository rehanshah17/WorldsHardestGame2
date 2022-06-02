import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class WorldsHardestGame2lvl11 extends JPanel implements KeyListener,Runnable
{
	private int x;
	private int y;
	private int a;
	private int b;
	private int R1;
	private int U1;
	private int D1;
    private int speed;
	private int enemySpeed,eSpeed;
    private int hor,vert;
  	private int c1X,c1Y,c2X,c2Y,c3X,c3Y,c4X,c4Y;
	private int fails;
	private Rectangle c1,c2,c3,c4;
	private Polygon border;
	private Polygon board;
	private JFrame frame;
	private Thread t;
	private boolean gameOn;
	private boolean kPressed;
	private boolean checkPoint, firstLife;
	private Font f;
	private Color backGround;
	private Color playerBorder;
	private Color green;
	private Color tile;
	private Color innerCoinYellow;
	private Color outerCoinYellow;
	public WorldsHardestGame2lvl11()
	{
		frame=new JFrame();
		x=341;
		y=141;
		a = 255;
		b = 230;
		R1 = 431;
		U1 = 181;
		D1 = 355;
		c1X = 267;
		c1Y = 242;
		c2X = 267;
		c2Y = 292;
		c3X = 417;
		c3Y = 242;
		c4X = 417;
	 	c4Y = 292;
        speed = 2;
		enemySpeed = 3;
		eSpeed = -3;
        hor = 0;
        vert = 0;
		fails = 0;
		gameOn = true;
		checkPoint = false;
		firstLife = true;
		kPressed = false;
		int[] boarderXPoints = {295, 405, 405, 455, 455, 405, 405, 295, 295, 245, 245, 295};
		int[] boarderYPoints = {120, 120, 220, 220, 330, 330, 430, 430, 330, 330, 220, 220};
		border = new Polygon(boarderXPoints, boarderYPoints, boarderXPoints.length);
		int[] xPoints = {300, 400, 400, 450, 450, 400, 400, 300, 300, 250, 250, 300};
		int[] yPoints = {125, 125, 225, 225, 325, 325, 425, 425, 325, 325, 225, 225};
		board = new Polygon(xPoints, yPoints, xPoints.length);
		f=new Font("ARIAL",Font.PLAIN,35);
		backGround = new Color(171,162,252);
		playerBorder = new Color(127,0,0);
		green =  new Color(158, 242, 155);
		tile = new Color(224,218,254);
		innerCoinYellow = new Color(255,200,0);
		outerCoinYellow = new Color(145,115,1);
		frame.addKeyListener(this);
		frame.add(this);
		frame.setSize(700,550);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t=new Thread(this);
		t.start();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		//all painting (AND ONLY PAINTING) happens here!
		//Don't use this method to deal with mathematics
		//The painting imps aren't fond of math.
		//fill background
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0,0,800,600);
		g2d.setPaint(backGround);
		g2d.fillRect(0,50,700,450);

		//draw scoreboard
		g2d.setColor(Color.WHITE);
		g2d.setFont(f);
		g2d.drawString("LEVEL: 11",0,40);
		g2d.drawString("COINS: ",300,40);
		g2d.drawString("FAILS: "  + fails,550,40);


		//polygon
		g2d.setColor(Color.BLACK);
		g2d.fill(border);
		g2d.setColor(Color.WHITE);
		g2d.fill(board);
		g2d.setPaint(green);
		g2d.fillRect(300,125,100,50);
		g2d.fillRect(300,375,100,50);

		//left side checkerboard
		int tileDim = 25;
		int tileX = 250;
		int tileY = 225;
		boolean place = true;
		for(int numRows = 1; numRows <= 2; numRows++)
		{
			tileX = 250;
			for(int j = 0 ; j <= 1; j++)
			{

				if(place)
				{
					g2d.setColor(tile);
					g2d.fillRect(tileX,tileY,25,25);
					tileX += tileDim;
					tileY += tileDim;
				}
				else{
					g2d.setColor(tile);
					g2d.fillRect(tileX,tileY,25,25);
					tileX += tileDim;
					tileY += tileDim;
				}
				place = !(place);


			}

		}
		//middle checkerboard

		tileDim = 25;
		tileX = 300;
		tileY = 175;
		boolean alt = true;
		for(int numRows = 1; numRows <= 8; numRows++)
		{
			for(int j = 0 ; j <= 3; j++)
			{
				if(alt)
				{
					tileX = 300;
					g2d.setColor(tile);
					g2d.fillRect(tileX,tileY,25,25);
					tileX +=50;
					g2d.fillRect(tileX,tileY,25,25);
				}else
				{
					tileX = 325;
					g2d.setColor(tile);
					g2d.fillRect(tileX,tileY,25,25);
					tileX +=50;
					g2d.fillRect(tileX,tileY,25,25);
				}
			}
			alt = !(alt);
			tileY += tileDim;

		}

		//right side checkerboard
		tileX = 400;
		tileY = 225;
		place = true;
		for(int numRows = 1; numRows <= 2; numRows++)
		{
			tileX = 400;
			for(int j = 0 ; j <= 1; j++)
			{

				if(place)
				{
					g2d.setColor(tile);
					g2d.fillRect(tileX,tileY,25,25);
					tileX += tileDim;
					tileY += tileDim;
				}
				else{
					g2d.setColor(tile);
					g2d.fillRect(tileX,tileY,25,25);
					tileX += tileDim;
					tileY += tileDim;
				}
				place = !(place);


			}

		}

		//g2d.setPaint(backGround);
		//g2d.fillRect(0,50,700,450);

		//Your character
		g2d.setColor(playerBorder);
		g2d.fillRect(x,y,19,19);
		g2d.setColor(Color.RED);
		g2d.fillRect(x+4,y+4,11,11);


		//coins
		//if(!w1)
		{
		g2d.setColor(outerCoinYellow);//Top Left Coin
		g2d.fillOval(c1X, c1Y, 16, 16);
		g2d.setColor(innerCoinYellow);
		g2d.fillOval(c1X+4, c1Y+4, 8, 8);
		}

		//if(!w2)
		{
		g2d.setColor(outerCoinYellow);//Bottom Left Coin
		g2d.fillOval(c2X, c2Y, 16, 16);
		g2d.setColor(innerCoinYellow);
		g2d.fillOval(c2X+4, c2Y+4, 8, 8);
		}

		//if(!w3)
		{
		g2d.setColor(outerCoinYellow);//Top Right Coin
		g2d.fillOval(c3X, c3Y, 16, 16);
		g2d.setColor(innerCoinYellow);
		g2d.fillOval(c3X+4, c3Y+4, 8, 8);
		}

		//if(!w4)
		{
		g2d.setColor(outerCoinYellow);//Right Bottom Coin
		g2d.fillOval(c4X, c4Y, 16, 16);
		g2d.setColor(innerCoinYellow);
		g2d.fillOval(c4X+4, c4Y+4, 8, 8);
		}

		//enemy
		//L1
		g2d.setColor(Color.BLACK);
		g2d.fillOval(a,b,14,14);
		g2d.setColor(Color.BLUE);
		g2d.fillOval(a+3,b+3,8,8);

		//L2
		g2d.setColor(Color.BLACK);
		g2d.fillOval(a,b+50,14,14);
		g2d.setColor(Color.BLUE);
		g2d.fillOval(a+3,b+53,8,8);

		//R1
		g2d.setColor(Color.BLACK);
		g2d.fillOval(R1,b+25,14,14);
		g2d.setColor(Color.BLUE);
		g2d.fillOval(R1+3,b+28,8,8);

		//R2
		g2d.setColor(Color.BLACK);
		g2d.fillOval(R1,b+75,14,14);
		g2d.setColor(Color.BLUE);
		g2d.fillOval(R1+3,b+78,8,8);

		//U1
		g2d.setColor(Color.BLACK);
		g2d.fillOval(306,U1,14,14);
		g2d.setColor(Color.BLUE);
		g2d.fillOval(309,U1+3,8,8);

		//U2
		g2d.setColor(Color.BLACK);
		g2d.fillOval(356,U1,14,14);
		g2d.setColor(Color.BLUE);
		g2d.fillOval(359,U1+3,8,8);

		//D1
		g2d.setColor(Color.BLACK);
		g2d.fillOval(331,D1,14,14);
		g2d.setColor(Color.BLUE);
		g2d.fillOval(334,D1+3,8,8);

		//D2
		g2d.setColor(Color.BLACK);
		g2d.fillOval(381,D1,14,14);
		g2d.setColor(Color.BLUE);
		g2d.fillOval(384,D1+3,8,8);


		if(x == 700)
		{
			gameOn = false;
			g2d.fillRect(0,0,1000,1000);
			g2d.setColor(Color.WHITE);
			g2d.setFont(f);
			g2d.drawString("YOU WIN",0,40);

		}

	}
	public void run()
	{
		while(true)
		{
			if(gameOn)
			{
				c1 = new Rectangle(c1X, c1Y, 16, 16);
				c2 = new Rectangle(c2X, c2Y, 16, 16);
				c3 = new Rectangle(c3X, c3Y, 16, 16);
				c4 = new Rectangle(c4X, c4Y, 16, 16);

				if(new Rectangle(x,y,19,19).intersects(c1))
				{
					c1X = 1000;
					c1Y = 1000;

				}
				if(new Rectangle(x,y,19,19).intersects(c2))
				{
					c2X = 1000;
					c2Y = 1000;

				}
				if(new Rectangle(x,y,19,19).intersects(c3))
				{
					c3X = 1000;
					c3Y = 1000;

				}
				if(new Rectangle(x,y,19,19).intersects(c4))
				{
					c4X = 1000;
					c4Y = 1000;
				}

				if(new Rectangle(x,y,19,19).intersects(new Rectangle(300,375,100,50)))
				{
					checkPoint = true;
				}
				checkCollision();
				checkBorderCollisions();

				//player collisions
				if(board.contains(new Rectangle(x + hor,y,19,19)))
                	x += hor;

				if(board.contains(new Rectangle(x,y  + vert,19,19)))
					y += vert;

				try
				{
					t.sleep(15);
				}catch(InterruptedException e)
				{

				}
				repaint();
			}
		}
	}

	public void respawn()
	{

	}
	public void checkCollision()
	{
		ArrayList<Rectangle> enemies = new ArrayList<>();
		enemies.add((new Rectangle(a,b,14,14)));//L1
		enemies.add((new Rectangle(a,b+50,14,14)));//L2
		enemies.add((new Rectangle(R1,b+25,14,14)));//R1
		enemies.add((new Rectangle(R1,b+75,14,14)));//R2
		enemies.add((new Rectangle(306,U1,14,14)));//U1
		enemies.add((new Rectangle(356,U1,14,14)));//U2
		enemies.add((new Rectangle(331,D1,14,14)));//D1
		enemies.add((new Rectangle(381,D1,14,14)));//D2

		for(int i = 0; i < enemies.size();i++)
		{
			if(new Rectangle(x,y,20,20).intersects(enemies.get(i)))
			{
				fails++;
				respawn();
			}
		}		
	}
	

	public void checkBorderCollisions()
	{
		//L1
		if(board.contains(new Rectangle(a + enemySpeed,b,14,14)))
		{
			a+=enemySpeed;
		}
		else{
			enemySpeed *=-1;
		}
		//U1
		if(board.contains(new Rectangle(306,U1 + enemySpeed,14,14)))
		{
			U1+=enemySpeed;
		}
		else{
			enemySpeed *=-1;
		}
		//R1
		if(board.contains(new Rectangle(R1 + eSpeed,b + 25,14,14)))
		{
			R1+=eSpeed;
		}
		else{
			eSpeed *=-1;
		}
		//D1
		if(board.contains(new Rectangle(306,D1 + eSpeed,14,14)))
		{
			D1+=eSpeed;
		}
		else{
			eSpeed *=-1;
		}
	}
	public void keyPressed(KeyEvent ke)
	{

		if(ke.getKeyCode()==39)//right arrow
			hor = speed;
		if(ke.getKeyCode()==37)//left arrow
			hor = -speed;
		if(ke.getKeyCode()==38)//down arrow
			vert = -speed;
		if(ke.getKeyCode()==40)//up arrow
			vert = speed;

	}
	public void keyReleased(KeyEvent ke)
	{
		System.out.println(ke.getKeyCode());
		if(ke.getKeyCode()==39)//right arrow
			hor = 0;
		if(ke.getKeyCode()==37)//left arrow
			hor = 0;
		if(ke.getKeyCode()==38)//down arrow
			vert = 0;
		if(ke.getKeyCode()==40)//up arrow
			vert = 0;
		if(ke.getKeyCode()==75)//k button
			kPressed = !kPressed;
			if(kPressed)
			{
				enemySpeed = 0;
				eSpeed = 0;
			}
			if(!kPressed)
			{
				enemySpeed = 3;
				eSpeed = -3;
			}

	}
	public void keyTyped(KeyEvent ke)
	{
	}
	public static void main(String args[])
	{
		WorldsHardestGame2lvl11 app = new WorldsHardestGame2lvl11();
	}
}

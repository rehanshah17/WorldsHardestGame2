import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;

public class WorldsHardestGame2lvl11 extends JPanel implements KeyListener,Runnable
{
	private int x;
	private int y;
	private int a;
	private int b;
	private int R1;
    private int speed;
    private int hor;
    private int vert;
	private int fails;
	private Rectangle e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16;
	private Polygon border;
	private Polygon board;
	private JFrame frame;
	private Thread t;
	private boolean gameOn;
	private Font f;
	private Color backGround;
	private Color playerBorder;
	private Color green;
	private Color tile;
	public WorldsHardestGame2lvl11()
	{
		frame=new JFrame();
		x=1;
		y=200;
		a = 255;
		b = 230;
		R1 = 431;
        speed = 2;
        hor = 0;
        vert = 0;
		fails = 0;
		gameOn=true;
		int[] boarderXPoints = {295, 405, 405, 455, 455, 405, 405, 295, 295, 245, 245, 295};
		int[] boarderYPoints = {120, 120, 220, 220, 330, 330, 430, 430, 330, 330, 220, 220};
		border = new Polygon(boarderXPoints, boarderYPoints, boarderXPoints.length);
		int[] xPoints = {300, 400, 400, 450, 450, 400, 400, 300, 300, 250, 250, 300};
		int[] yPoints = {125, 125, 225, 225, 325, 325, 425, 425, 325, 325, 225, 225};
		board = new Polygon(xPoints, yPoints, xPoints.length);
		f=new Font("ARIAL",Font.PLAIN,23);
		backGround = new Color(171,162,252);
		playerBorder = new Color(127,0,0);
		green =  new Color(158, 242, 155);
		tile = new Color(224,218,254);
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
		g2d.drawString("LEVEL: 11",0,50);
		g2d.drawString("COINS",325,50);
		g2d.drawString("FAILS: "  + fails,600,50);

		//enemy
		

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

	}
	public void run()
	{
		while(true)
		{
			if(gameOn)
			{
				//MATH HAPPENS HERE!!!!
				//keep count of steps
				//modify points

				//intersection is true if even one point is shared

				//must be entirely inside for contains to be true

				
                x += hor;
				y += vert;
				try
				{
					t.sleep(10);
				}catch(InterruptedException e)
				{

				}
				repaint();
			}
		}
	}

	public void keyPressed(KeyEvent ke)
	{

		System.out.println(ke.getKeyCode()); //this will show you the code of different keys
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
		if(ke.getKeyCode()==39)//right arrow
			hor = 0;
		if(ke.getKeyCode()==37)//left arrow
			hor = 0;
		if(ke.getKeyCode()==38)//down arrow
			vert = 0;
		if(ke.getKeyCode()==40)//up arrow
			vert = 0;

	}
	public void keyTyped(KeyEvent ke)
	{
	}
	public static void main(String args[])
	{
		WorldsHardestGame2lvl11 app = new WorldsHardestGame2lvl11();
	}
}

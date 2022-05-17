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
    private int speed;
    private int hor;
    private int vert;
	private Rectangle r1, r2;
	private Polygon poly;
	private JFrame frame;
	private Thread t;
	private boolean gameOn;
	private Font f;
	private Color color;
	public WorldsHardestGame2lvl11()
	{
		frame=new JFrame();
		x=1;
		y=200;
		a = 400;
		b = 200;
        speed = 3;
        hor = 0;
        vert = 0;
		gameOn=true;
		r1 = new Rectangle(x,y,25,25);
		r2 = new Rectangle(a,b,50,50);
		int[] xPoints = {100, 150, 150, 200, 200, 100};
		int[] yPoints = {100, 100, 200, 200, 250, 250};
		poly = new Polygon(xPoints, yPoints, xPoints.length);
		f=new Font("TIMES NEW ROMAN",Font.PLAIN,50);
		frame.addKeyListener(this);
		frame.add(this);
		frame.setSize(800,500);
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
		g2d.fillRect(0,0,800,500);

		//draw scoreboard
		g2d.setColor(Color.RED);
		g2d.setFont(f);
		g2d.drawString("A",50,50);

		//enemy
		g2d.setColor(Color.GREEN);
		g2d.fillRect(a,b,50,50);

		//polygon
		g2d.setColor(Color.MAGENTA);
		g2d.fill(poly);

		//Your character
		g2d.setColor(Color.BLUE);
		g2d.fillOval(x,y,25,25);

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
				r1 = new Rectangle(x,y,25,25);
				r2 = new Rectangle(a,b,50,50);
				if (r1.intersects(r2))
					System.out.println("OUCH");

				//must be entirely inside for contains to be true
				if (poly.contains(r1))
					System.out.println("INSIDE");

				
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
		EndOfYearProjectStarterCode app=new EndOfYearProjectStarterCode();
	}
}

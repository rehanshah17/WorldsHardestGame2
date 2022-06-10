public class Coin {
    private int x;
    private int y;
    private int a;
    private int b;
    boolean collected;

    public Coin(int x, int y)
    {
        this.x = x;
        this.y = y;
        a = x;
        b = y;
        collected = false;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int newX){
        x = newX;
    }

    public void setY(int newY){
        y = newY;
    }

    public void setCollected(boolean state){
        collected = state;
        if(collected)
        {
            x = 1000;
            y = 1000;
        }
        if(!collected)
        {
            x = a;
            y = b;
        }
    }
    
}

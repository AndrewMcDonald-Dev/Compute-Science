import java.awt.*;



class CircleShape implements Moveable{

    private int x;
    private int y;
    private int size;

    public CircleShape(int xx, int yy, int size){
        this.x = xx + 200;
        this.y = yy + 200;
        this.size=size;
    }


    public void draw(Graphics2D g2){
		g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(1.2f));
        drawCircle(g2, this.x, this.y, this.size);
    }

    public void translate(int x, int y){}

    public void drawCircle(Graphics2D gg, double x, double y, double diam) {

        gg.drawOval((int) (x-(diam/2)), (int) (y-(diam/2)), (int) diam, (int) diam);
        if(diam <= 8){
            return;
        }
        drawCircle(gg, x + (diam / 2), y, diam / 2);
        drawCircle(gg, x - (diam / 2), y, diam / 2);
        drawCircle(gg, x, y + (diam / 2), diam / 2);
        drawCircle(gg, x, y - (diam / 2), diam / 2);
    }
}

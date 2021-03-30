import java.awt.*;
import java.awt.geom.*;
import java.awt.Color;

class ClockShape2 implements Moveable {
    private int x;
    private int y;
    private int width;

    private int seconds;
    private int minutes;
    private int hours;
    private int hAngle;
    private int mAngle;
    private int sAngle;

    private int part;
    private static int NOON = -90;

    public ClockShape2(int x, int y, int width) {

        mAngle = -90;
        hAngle = -90;
        sAngle = -90;
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public void setSecond(int second) {
        this.seconds = second;
        sAngle = (6 * this.seconds) + NOON;
    }

    public int getSecond() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getPart(){
        return part;
    }

    public void setMinute(int minute) {
        this.minutes = minute;
        mAngle = (6 * this.minutes) + NOON;
    }

    public void setHour(int hour) {
        this.hours = hour;
        hAngle = (30 * this.hours) + NOON;

    }

    public void setHourSmall(int part){
        hAngle = (6 * (this.hours * 5 + part)) + NOON;
    }

    public void setPart(int part){
        this.part = part;
    }

    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g2) {
        Ellipse2D.Double clock = new Ellipse2D.Double(x, y, width, width);
        Point2D.Double endMinutes = new Point2D.Double(400 + (width * 0.4) * Math.cos(Math.toRadians(mAngle)),
                300 + (width * 0.4) * Math.sin(Math.toRadians(mAngle)));
        Point2D.Double endSeconds = new Point2D.Double(400 + (width * 0.35) * Math.cos(Math.toRadians(sAngle)),
                300 + (width * 0.35) * Math.sin(Math.toRadians(sAngle)));
        Point2D.Double endHours = new Point2D.Double(400 + (width * 0.3) * Math.cos(Math.toRadians(hAngle)),
                300 + (width * 0.3) * Math.sin(Math.toRadians(hAngle)));

                
            g2.setColor(Color.decode("#33ccff"));
            g2.fill(clock);
            g2.draw(clock);
                
                
            Shape seconds = new Line2D.Double(new Point((width / 2) + 100, width / 2), endSeconds);
            g2.setStroke(new BasicStroke(5));
            g2.setColor(Color.decode("#8F94A9"));
            g2.draw(seconds);
                
            Shape hours = new Line2D.Double(new Point((width / 2) + 100, width / 2), endHours);
            g2.setStroke(new BasicStroke(11));
            g2.setColor(Color.decode("#0000FF"));
            g2.draw(hours);
                
            Shape minutes = new Line2D.Double(new Point((width / 2) + 100, width / 2), endMinutes);
            g2.setStroke(new BasicStroke(10));
            g2.setColor(Color.decode("#0000FF"));
            g2.draw(minutes);
                
            Ellipse2D.Double center = new Ellipse2D.Double(x + 296, (y + width/6)+200, width/64,width/64);
            g2.setColor(Color.black);
            g2.fill(center);
            g2.draw(center);

            for(int j = 0; j <= 59; j++){
                if(!((j - 1) % 5 == 0)){
                    Ellipse2D.Double min = new Ellipse2D.Double(395 + 275 * (Math.cos((j - 1) * Math.PI/30)), 300 + 275 * (Math.sin((j - 1) * Math.PI/30)), 7, 7);
                    g2.setColor(Color.black);
                    g2.fill(min);
                }else {
                    Ellipse2D.Double hour = new Ellipse2D.Double(391 + 275 * (Math.cos((j - 1) * Math.PI/30)), 295 + 275 * (Math.sin((j - 1) * Math.PI/30)), 18, 18);
                    g2.setColor(Color.red);
                    g2.fill(hour);
                }
            }
    }
} 
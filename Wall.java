package tankgame05;

import java.awt.*;

public class Wall {
    int x;
    int y;
    boolean isLive=true;
    int wide=4;
    int direct;

    public Wall(int x, int y,int direct,int color) {
        if(direct==1){
            this.x=x;
            this.y=y;
            this.color=color;
            wide=4;
            length=20;
        }
        else {
            this.x=x;
            this.y=y;
            this.color=color;
            wide=20;
            length=4;
        }
    }
    int color;
    int length=20;
    public void drawWall(Graphics g){
        if(color%2==1) {
            g.setColor(Color.YELLOW);
        }
        else {
            g.setColor(Color.GRAY);
        }
        if(this.isLive) {
            g.fillRect(this.x, this.y, this.wide, this.length);
        }

    }
}


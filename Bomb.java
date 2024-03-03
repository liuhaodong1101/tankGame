package tankgame05;

public class Bomb {
    int x,y;
    int lifeTime =18;
    boolean isLive =true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //减少生命时间
    public void lifeTimeDown(){
        if(lifeTime>0) lifeTime--;
        else {
            isLive=false;
        }
    }
}

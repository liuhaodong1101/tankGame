package tankgame05;

public class Tank {
    private int x; //坦克横坐标
    private int y; //坦克纵坐标
    private int direct=0;//坦克方向 0 上 1 右边 2 下 3 左

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //坦克移动
    private int speed=1;
    boolean isLive = true;

    public void moveUp(){
        y=y-speed;
    }
    public void moveRight(){
        x=x+speed;
    }
    public void moveDown(){
        y=y+speed;
    }
    public void moveLeft(){
        x=x-speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

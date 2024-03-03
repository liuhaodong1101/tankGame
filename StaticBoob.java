package tankgame05;


public class StaticBoob implements Runnable{
    int x;//bullet x
    int y;//bullet y
    int array=3;
    boolean isLive = true;
    public StaticBoob(int x, int y, int array) {
        this.x = x;
        this.y = y;
        this.array=array;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!(x>=0&&x<=1000&&y>=0&&y<=750&&isLive)) {
                isLive=false;
                break;
            }
        }
    }
}

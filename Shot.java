package tankgame05;

public class Shot implements Runnable{
    int x;//bullet x
    int y;//bullet y

    Hero hero=null;

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    int direct=0;
    int speed=3;
    public boolean isTrace=false;
    boolean isLive = true;
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        if (isTrace == false) {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                switch (direct) {
                    case 0:
                        y = y - speed;
                        break;//shang
                    case 1:
                        x = x + speed;
                        break;//you
                    case 2:
                        y = y + speed;
                        break;//xia
                    case 3:
                        x = x - speed;
                        break;//zhuo
                }
                if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                    isLive = false;
                    break;
                }
            }
        }
    else {
        int time1=0;
        while (true) {
            time1=time1+1;
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(hero.getX()<x) x=x-speed/2;
            else if(hero.getX()>=x) x=x+speed/2;
            if(hero.getY()<y) y=y-speed/2;
            else y=y+speed/2;

            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive&&time1<280)) {
                isLive = false;
                break;
            }
        }
    }

    }

}

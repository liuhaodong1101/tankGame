package tankgame05;

public class Buffer implements Runnable{
    int x;
    int y;
    int power;
    boolean isLive = true;
    public Buffer(int x, int y, int power) {
        this.x = x;
        this.y = y;
        this.power = power;
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
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            isLive=false;
            break;
        }


    }
    public void getBuffer(Hero hero,Buffer buffer){
        if(hero!=null&&hero.isLive&&buffer!=null&&buffer.isLive) {
            if (hero.getX() +20> buffer.x - 20 && hero.getX() +20< buffer.x + 20 && hero.getY()+30 > buffer.y - 20 && hero.getY() +30< buffer.y + 20) {
                switch (buffer.power) {
                    case 0:
                        hero.addSpeed();
                        break;
                    case 1:
                        hero.addMaxBoobNum();
                        break;
                    case 2:
                        hero.addMaxShotNum();
                        break;
                    case 3:
                        hero.addArmorNum();
                        break;
                }
                buffer.isLive = false;
            }
        }
    }
}

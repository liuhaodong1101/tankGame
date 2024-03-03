package tankgame05;

import java.awt.*;
import java.util.Vector;
@SuppressWarnings({"all"})

public class Boss extends Tank implements Runnable{
    Vector<Shot> shots=new Vector<>();
    //增加成员，敌人坦克可以看到所有敌人坦克位置
    Vector<EnemyTank>enemyTanks = new Vector<>();
    Hero hero=null;

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    int ArmorNum=10;
    boolean isLive=true;
    public Boss(int x, int y){
        super(x,y);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTankss) {
        enemyTanks = enemyTankss;
    }
    //判断当前敌人坦克是否和其他敌人坦克重叠
    public boolean isTouchEnemyTank(){
        switch (this.getDirect()){
            case 0:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(isLive&&enemyTank.isLive){
                        //
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            if(       this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+60) return  true;
                            if(       this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+40
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+60) return  true;
                        }
                        else if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            if(       this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+60
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+40) return  true;
                            if(       this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+60
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+40) return  true;
                        }
                    }
                }
                break;
            case 1:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(isLive&&enemyTank.isLive){
                        //
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            if(       this.getX()+60>=enemyTank.getX()
                                    &&this.getX()+60<=enemyTank.getX()+40
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+60) return  true;
                            if(       this.getX()+60>=enemyTank.getX()
                                    &&this.getX()+60<=enemyTank.getX()+40
                                    &&this.getY()+40>=enemyTank.getY()
                                    &&this.getY()+40<=enemyTank.getY()+60) return  true;
                        }
                        else if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            if(       this.getX()+60>=enemyTank.getX()
                                    &&this.getX()+60<=enemyTank.getX()+60
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+40) return  true;
                            if(       this.getX()+60>=enemyTank.getX()
                                    &&this.getX()+60<=enemyTank.getX()+60
                                    &&this.getY()+40>=enemyTank.getY()
                                    &&this.getY()+40<=enemyTank.getY()+40) return  true;
                        }
                    }
                }
                break;
            case 2:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(isLive&&enemyTank.isLive){
                        //
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            if(       this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    &&this.getY()+60>=enemyTank.getY()
                                    &&this.getY()+60<=enemyTank.getY()+60) return  true;
                            if(       this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+40
                                    &&this.getY()+60>=enemyTank.getY()
                                    &&this.getY()+60<=enemyTank.getY()+60) return  true;
                        }
                        else if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            if(       this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+60
                                    &&this.getY()+60>=enemyTank.getY()
                                    &&this.getY()+60<=enemyTank.getY()+40) return  true;
                            if(       this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+60
                                    &&this.getY()+60>=enemyTank.getY()
                                    &&this.getY()+60<=enemyTank.getY()+40) return  true;
                        }
                    }
                }
                break;
            case 3:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(isLive&&enemyTank.isLive){
                        //
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            if(       this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+60) return  true;
                            if(       this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    &&this.getY()+40>=enemyTank.getY()
                                    &&this.getY()+40<=enemyTank.getY()+60) return  true;
                        }
                        else if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            if(       this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+60
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+40) return  true;
                            if(       this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+60
                                    &&this.getY()+40>=enemyTank.getY()
                                    &&this.getY()+40<=enemyTank.getY()+40) return  true;
                        }
                    }
                }
                break;
        }
        return false;
    }
    @Override
    public void run() {
        while (true){
            if(shots.size()<5&&isLive){
                Shot s =null;
                switch (getDirect()){
                    case 0:
                        s=new Shot(getX()+40,getY(),0);
                        s.setHero(hero);
                        s.isTrace=true;
                        break;
                    case 1:
                        s=new Shot(getX()+120,getY()+40,1);
                        s.setHero(hero);
                        s.isTrace=true;
                        break;
                    case 2:
                        s=new Shot(getX()+40,getY()+120,2);
                        s.setHero(hero);
                        s.isTrace=true;
                        break;
                    case 3:
                        s=new Shot(getX(),getY()+40,3);
                        s.setHero(hero);
                        s.isTrace=true;
                        break;
                }
                s.speed=7;
                shots.add(s);
                new Thread(s).start();
            }
            switch (getDirect()){
                case 0:
                    for(int i=0;i<30;i++){
                        if(getY()>0&&!isTouchEnemyTank()) moveUp();
                        try {
                            Thread.sleep(70);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for(int i=0;i<30;i++){
                        if(getX()+60<1000&&!isTouchEnemyTank()) moveRight();
                        try {
                            Thread.sleep(70);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for(int i=0;i<30;i++){
                        if(getY()+60<750&&!isTouchEnemyTank()) moveDown();
                        try {
                            Thread.sleep(70);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for(int i=0;i<30;i++){
                        if(getX()>0&&!isTouchEnemyTank()) moveLeft();
                        try {
                            Thread.sleep(70);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            setDirect((int)(Math.random()*4));
            if(isLive==false){
                break;
            }
        }
    }
    public void drawBoss(int x, int y, Graphics g){
        g.setColor(Color.red);
        switch(getDirect()){
            case 0://向上
                g.fill3DRect(x,y,20,120,false);//左轮子
                g.fill3DRect(x+60,y,20,120,false);//右轮子
                g.fill3DRect(x+20,y+20,40,80,false);//坦克体
                g.fillOval(x+20,y+40,40,40);//画出圆盖子
                g.drawLine(x+40,y,x+40,y+60);//炮管
                break;
            case 1://向右
                g.fill3DRect(x,y,120,20,false);//左轮子
                g.fill3DRect(x,y+60,120,20,false);//右轮子
                g.fill3DRect(x+20,y+20,80,40,false);//坦克体
                g.fillOval(x+40,y+20,40,40);//画出圆盖子
                g.drawLine(x+70,y+40,x+120,y+40);//炮管
                break;
            case 2://向下
                g.fill3DRect(x,y,20,120,false);//左轮子
                g.fill3DRect(x+60,y,20,120,false);//右轮子
                g.fill3DRect(x+20,y+20,40,80,false);//坦克体
                g.fillOval(x+20,y+40,40,40);//画出圆盖子
                g.drawLine(x+40,y+120,x+40,y+60);//炮管
                break;
            case 3://向左
                g.fill3DRect(x,y,120,20,false);//左轮子
                g.fill3DRect(x,y+60,120,20,false);//右轮子
                g.fill3DRect(x+20,y+20,80,40,false);//坦克体
                g.fillOval(x+40,y+20,40,40);//画出圆盖子
                g.drawLine(x+60,y+40,x,y+40);//炮管
                break;
            default:
                break;
        }
    }
}

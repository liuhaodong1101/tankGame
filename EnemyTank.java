package tankgame05;

import java.util.Vector;
@SuppressWarnings({"all"})

public class EnemyTank extends Tank implements Runnable{
    Vector<Shot> shots=new Vector<>();
    //增加成员，敌人坦克可以看到所有敌人坦克位置
    Vector<EnemyTank>enemyTanks = new Vector<>();

    boolean isLive=true;
    public EnemyTank(int x, int y){
        super(x,y);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }
    //判断当前敌人坦克是否和其他敌人坦克重叠
    public boolean isTouchEnemyTank(){
        switch (this.getDirect()){
            case 0:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(enemyTank!=this){
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
                    if(enemyTank!=this){
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
                    if(enemyTank!=this){
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
                    if(enemyTank!=this){
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
            if(shots.size()<1&&isLive){
                Shot s =null;
                switch (getDirect()){
                    case 0:
                        s=new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s=new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s=new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s=new Shot(getX(),getY()+20,3);
                        break;
                }
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
}

package tankgame05;

import java.util.Vector;

public class Hero extends Tank {
    public Hero(int x,int y){
        super(x,y);
    }
    Shot shot = null;
    int MaxShotNum=3;
    int MaxBoobNum=0;
    int ArmorNum=1;
    public void addArmorNum(){
        ArmorNum=ArmorNum+1;
    }
    public void addMaxBoobNum(){
        MaxBoobNum=MaxBoobNum+1;
    }
    public void addMaxShotNum(){
        MaxShotNum=MaxShotNum+1;
    }
    public void addSpeed(){
        setSpeed(getSpeed()+1);
    }
    public int getMaxShotNum(){
        return MaxShotNum;
    }
    public int getMaxBoobNum(){
        return MaxBoobNum;
    }
    StaticBoob boob=null;
    //
    Vector<Shot> shots = new Vector<>();
    Vector<StaticBoob> boobs=new Vector<>();
    public void setShotEnemyTank(){
        if(shots.size()==MaxShotNum){
            return;
        }
        switch (getDirect()){//hero direct
            case 0://shang
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1://you
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2://xia
                shot = new Shot(getX()+20,getY()+60,2);
                break;
            case 3://zuo
                shot = new Shot(getX(),getY()+20,3);
                break;
        }
        shots.add(shot);
        new Thread(shot).start();
    }
    public void setBoobs(){
        if(boobs.size()==MaxBoobNum){
            return;
        }
        switch (getDirect()){//hero direct
            case 0://shang
                boob = new StaticBoob(getX()+20,getY(),3);
                break;
            case 1://you
                boob= new StaticBoob(getX()+60,getY()+20,3);
                break;
            case 2://xia
                boob = new StaticBoob(getX()+20,getY()+60,3);
                break;
            case 3://zuo
                boob = new StaticBoob(getX(),getY()+20,3);
                break;
        }
        boobs.add(boob);
        new Thread(boob).start();
    }
}

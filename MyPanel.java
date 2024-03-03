package tankgame05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//绘图区域
//为了让Panel不停的重绘子弹，需要将MyPanel 实现Runnable 当作一个线程
public class MyPanel extends JPanel implements KeyListener ,Runnable{
    Hero hero;
    //定义敌人坦克，放入到Vector中
    //Base
    Base base;
    Vector<Boss> bosses =new Vector<>();
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Wall> Walls =new Vector<>();
    int wallNum=40;
    Vector<Node> nodes = new Vector<>();
    Vector<Buffer> buffers=new Vector<>();
    //set a vector to save bombs
    //当子弹击中坦克时加一颗炸弹
    Vector<Bomb> bombs=new Vector<>();
    //三张炸弹图片显示爆炸
    Image image1=null;
    Image image2=null;
    Image image3=null;

    int enemyTankSize = 6;
    int maxSameSize=6;

    public MyPanel(String key){

        Recoder.setEnemyTanks(enemyTanks);
        switch (key){
            case "1":
                //初始化敌人的坦克
                for(int i=0;i<maxSameSize/2;i++){
                    EnemyTank enemyTank = new EnemyTank(100*(i+1),0);
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(2);
                    Thread thread=new Thread(enemyTank);
                    thread.start();
                    Shot shot=new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                for(int i=0;i<maxSameSize/2;i++){
                    EnemyTank enemyTank = new EnemyTank(100*(i+1),600);
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(0);
                    Thread thread=new Thread(enemyTank);
                    thread.start();
                    Shot shot=new Shot(enemyTank.getX()+20,enemyTank.getY(),enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                //初始化敌人的坦克
                nodes= Recoder.getNodesAndAllEnemyTank();
                for(int i=0;i<nodes.size();i++){
                    Node node= nodes.get(i);

                    EnemyTank enemyTank = new EnemyTank(node.getX(),node.getY());
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(node.getDirect());
                    Thread thread=new Thread(enemyTank);
                    thread.start();
                    Shot shot=new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("输入有误");

        }
        hero=new Hero(100,100);//初始化自己的坦克
        hero.setSpeed(10);
        base = new Base(400, 300, 5, 100, 100, 50, 50);
        for(int i=0;i<wallNum/4-4;i++){
            Wall wall=new Wall(390+20*i,290,0,i);
            Walls.add(wall);
            wall= new Wall(390,290+20*i,1,i);
            Walls.add(wall);
            wall= new Wall(390+20*i,410,0,i);
            Walls.add(wall);
            wall= new Wall(510,290+20*i,1,i);
            Walls.add(wall);
        }

        //
        image1=Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/1.gif"));
        image2=Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/2.gif"));
        image3=Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/3.gif"));
    }
    public void showInfo(Graphics g){
        //
        g.setColor(Color.BLACK);
        Font font= new Font("宋体",Font.BOLD,25);
        g.setFont(font);
        g.drawString("累计击毁地方坦克数量： ",1020, 30);
        drawTank(1020,60, g,0,0 );
        g.setColor(Color.BLACK);
        g.drawString(Recoder.getAllEnemyTankNum()+"",1080,100);
        g.drawString("目前可同时射出的子弹数量： ",1020, 200);
        g.drawString(hero.getMaxShotNum()+" ",1040,240);
        g.drawString("目前可埋雷的数量: ",1020,280);
        g.drawString(hero.getMaxBoobNum()+" ",1040,320);
        g.drawString("当前护甲数： ",1020,360);
        g.drawString(hero.ArmorNum+" ",1040,400);
        g.drawString("基地血量： ", 1020, 440);
        g.drawString(base.life+" ",1040, 480);
        g.setColor(Color.red);
        Font font1= new Font("宋体",Font.BOLD,75);
        g.setColor(Color.red);
        g.setFont(font1);
        if(hero.isLive==false||base.life==0){
            g.drawString("YOU LOSE!",400,200);
        }
        g.setColor(Color.BLUE);
        if(enemyTanks.size()==0&&hero.isLive){
            g.drawString("YOU WIN!",400,200);
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//默认黑色
        showInfo(g);
        //画基地

        if(base.life>0) {
            g.setColor(Color.GREEN);
            g.fill3DRect(base.x, base.y, base.wide1, base.length1, true);
            g.setColor(Color.red);
            g.fillRect(base.x + 25, base.y + 25, 50, 50);
            g.setColor(Color.BLUE);
            g.fillOval(base.x + 35, base.y + 35, 30, 30);
        }
        //wall
        for (int i=0;i<Walls.size();i++){
            Wall wall=Walls.get(i);
            if(wall.isLive) {
                wall.drawWall(g);
            }
        }
        //画坦克
       if(hero!=null&&hero.isLive) drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),1);
        //画出hero的所有子弹
        for(int i=0; i<hero.shots.size();i++) {
            Shot shot=hero.shots.get(i);
            if (shot != null && shot.isLive) {
                g.draw3DRect(shot.x, shot.y, 3, 3, false);
            }
            else {
                hero.shots.remove(shot);
            }
        }
        //画出hero所有的地雷
        for(int i=0; i<hero.boobs.size();i++) {
            StaticBoob boob=hero.boobs.get(i);
            if (boob != null && boob.isLive) {
                g.setColor(Color.red);
                g.fill3DRect(boob.x, boob.y, 5, 5, true);
            }
            else {
                hero.boobs.remove(boob);
            }
        }
        //
        for(int i=0;i<bombs.size();i++){
            Bomb bomb=bombs.get(i);
            if(bomb.lifeTime>12){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            }
            else  if(bomb.lifeTime>6){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }
            else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            bomb.lifeTimeDown();
            if(bomb.lifeTime==0) bombs.remove(bomb);
        }
        //画出敌人的坦克 遍历Vector
        for(int i=0; i<enemyTanks.size();i++){
            //取出坦克
            EnemyTank enemyTank= enemyTanks.get(i);
            if(enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                //enemy shot
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        for(int i1=0;i1<bosses.size();i1++){
            Boss boss=bosses.get(i1);
            if(boss.isLive){
                boss.drawBoss(boss.getX(),boss.getY(),g);
                for(int j1=0;j1<boss.shots.size();j1++){
                    Shot shot = boss.shots.get(j1);
                    if (shot.isLive) {
                        g.fillOval(shot.x-5, shot.y-5, 10, 10);
                    } else {
                        boss.shots.remove(shot);
                    }
                }
            }
        }
         //画出随机产生的Buffer
            for(int j=0;j<buffers.size();j++){
                Buffer buffer=buffers.get(j);
                if(buffer.isLive&&buffer!=null){
                    switch (buffer.power){
                        case 0:
                            g.setColor(Color.BLACK);
                            break;
                        case 1:
                            g.setColor(Color.BLUE);
                            break;
                        case 2:
                            g.setColor(Color.GREEN);
                            break;
                        case 3:
                            g.setColor(Color.ORANGE);
                            break;
                    }
                    g.fillOval(buffer.x,buffer.y,10,10);
                }
                else {
                    buffers.remove(buffer);
                }
            }

        }

    }
    public void HitWall() {
      for(int i=0;i<enemyTanks.size();i++){
          for(int j=0;j<Walls.size();j++){
              EnemyTank enemyTank=enemyTanks.get(i);
              Wall wall=Walls.get(j);
              for(int k=0;k<enemyTank.shots.size();k++){
                  Shot shot=enemyTank.shots.get(k);
                  if(wall.isLive&&shot.isLive&&shot.x>=wall.x&&shot.x<=wall.x+wall.wide&&
                          shot.y>=wall.y&&shot.y<=wall.y+wall.length){
                      wall.isLive=false;
                      shot.isLive=false;
                      enemyTank.shots.remove(shot);
                  }
              }
          }
      }
      for(int i1=0;i1<bosses.size();i1++){
          for(int j1=0;j1<Walls.size();j1++){
              Boss s =bosses.get(i1);
              Wall wall01=Walls.get(j1);
              for(int k1=0;k1<s.shots.size();k1++){
                  Shot shot01=s.shots.get(k1);
                  if(wall01.isLive&&shot01.isLive&&shot01.x>=wall01.x-1&&shot01.x<=wall01.x+wall01.wide+1&&
                          shot01.y>=wall01.y-1&&shot01.y<=wall01.y+wall01.length+1){
                      wall01.isLive=false;
                      shot01.isLive=false;
                      s.shots.remove(shot01);
                  }
              }
          }
      }
    }
    // make a func to draw tank
    //x y 坦克左上角横纵坐标
    //g 画笔
    //dir 方向
    //type 坦克类型
    public void drawTank(int x,int y,Graphics g,int direction,int type){
        switch(type){
            case 0: //our tank
                g.setColor(Color.cyan);
                break;
            case 1: //enemy tank
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克方向绘制不同坦克
        //direction表示方向 0 向上 1 向右 2 向下 3 向左
        switch(direction){
            case 0://向上
                g.fill3DRect(x,y,10,60,false);//左轮子
                g.fill3DRect(x+30,y,10,60,false);//右轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克体
                g.fillOval(x+10,y+20,20,20);//画出圆盖子
                g.drawLine(x+20,y,x+20,y+30);//炮管
                break;
            case 1://向右
                g.fill3DRect(x,y,60,10,false);//左轮子
                g.fill3DRect(x,y+30,60,10,false);//右轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克体
                g.fillOval(x+20,y+10,20,20);//画出圆盖子
                g.drawLine(x+30,y+20,x+60,y+20);//炮管
                break;
            case 2://向下
                g.fill3DRect(x,y,10,60,false);//左轮子
                g.fill3DRect(x+30,y,10,60,false);//右轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克体
                g.fillOval(x+10,y+20,20,20);//画出圆盖子
                g.drawLine(x+20,y+60,x+20,y+30);//炮管
                break;
            case 3://向左
                g.fill3DRect(x,y,60,10,false);//左轮子
                g.fill3DRect(x,y+30,60,10,false);//右轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克体
                g.fillOval(x+20,y+10,20,20);//画出圆盖子
                g.drawLine(x+30,y+20,x,y+20);//炮管
                break;
            default:
                break;
        }
    }
    public  void  hitEnemyTankOrBoss(){

        if(hero.shots.size()!=0){
            boolean check=false;
            for(int i=0;i<enemyTanks.size();i++){
                EnemyTank enemyTank= enemyTanks.get(i);
                hitEnemyTankByShot(hero.shots,enemyTank);
            }
        }
        if(hero.boobs.size()!=0){
            for(int i=0;i<enemyTanks.size();i++){
                EnemyTank enemyTank=enemyTanks.get(i);
                hitEnemyTankByBoob(hero.boobs,enemyTank);
            }
            for(int i=0;i<bosses.size();i++){
                Boss boss=bosses.get(i);
                hitBossByBoob(hero.boobs,boss);
            }
        }
        if(hero.shots.size()!=0){
            for(int i1=0;i1<bosses.size();i1++){
                Boss boss= bosses.get(i1);
                hitBossByShot(hero.shots,boss);
            }
        }

    }
    //
    public void hitHero(){
        for(int i=0;i<enemyTanks.size();i++){
            EnemyTank enemyTank=enemyTanks.get(i);
            for (int j=0;j<enemyTank.shots.size();j++){
                Shot shot = enemyTank.shots.get(j);
                if(hero.isLive&&shot.isLive){
                    hitHeroByEnemyTank(shot,hero);
                    if(shot.isLive==false) enemyTank.shots.remove(shot);
                }
            }
        }
        for(int j=0;j<bosses.size();j++){
            Boss boss=bosses.get(j);
            for(int k=0;k<boss.shots.size();k++){
                Shot shot = boss.shots.get(k);
                if(hero.isLive&&shot.isLive){
                    hitHeroByEnemyTank(shot,hero);
                    if(shot.isLive==false) boss.shots.remove(shot);
                }
            }
        }
    }
    public void hitBaseFromEnemyTank(){
        for(int i=0;i<enemyTanks.size();i++){
            EnemyTank enemyTank=enemyTanks.get(i);
            for (int j=0;j<enemyTank.shots.size();j++){
                Shot shot = enemyTank.shots.get(j);
                if(base.life>0&&shot.isLive){
                    hitBase(shot,base);
                    if(shot.isLive==false) enemyTank.shots.remove(shot);
                }
            }
        }
    }
    public  void hitEnemyTankByBoob(Vector<StaticBoob> t, Tank enemyTank){
        for(int i=0;i<t.size();i++) {
            StaticBoob s=t.get(i);
            switch (enemyTank.getDirect()) {
                case 0:
                case 2:
                    if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60) {
                        s.isLive = false;
                        enemyTank.isLive = false;
                        enemyTanks.remove(enemyTank);
                        Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                        bombs.add(bomb);
                        Recoder.addallEnemyTankNum();
                    }
                    break;
                case 1:
                case 3:
                    if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40) {
                        s.isLive = false;
                        enemyTank.isLive = false;
                        enemyTanks.remove(enemyTank);
                        Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                        bombs.add(bomb);
                        Recoder.addallEnemyTankNum();
                    }
                    break;
            }
        }
    }
    public  void  hitBossByShot(Vector<Shot> shots,Boss boss){
        for(int j=0;j<shots.size();j++) {
            Shot s=shots.get(j);
            switch (boss.getDirect()) {
                case 0:
                case 2:
                    if (s.isLive&&boss.isLive&&s.x > boss.getX() && s.x < boss.getX() + 80 && s.y > boss.getY() && s.y < boss.getY() + 120) {
                        if (boss.ArmorNum > 0) {
                            s.isLive = false;
                            shots.remove(s);
                            boss.ArmorNum--;
                            Bomb bomb = new Bomb(boss.getX(), boss.getY());
                            bombs.add(bomb);
                        } else {
                            s.isLive = false;
                            shots.remove(s);
                            boss.isLive = false;
                            bosses.remove(boss);
                            Bomb bomb = new Bomb(boss.getX(), boss.getY());
                            bombs.add(bomb);
                        }
                    }
                    break;
                case 1:
                case 3:
                    if (s.x > boss.getX() && s.x < boss.getX() + 120 && s.y > boss.getY() && s.y < boss.getY() + 80) {
                        if (boss.ArmorNum > 0) {
                            s.isLive = false;
                            shots.remove(s);
                            boss.ArmorNum--;
                            Bomb bomb = new Bomb(boss.getX(), boss.getY());
                            bombs.add(bomb);
                        } else {
                            s.isLive = false;
                            shots.remove(s);
                            boss.isLive = false;
                            bosses.remove(boss);
                            Bomb bomb = new Bomb(boss.getX(), boss.getY());
                            bombs.add(bomb);
                        }
                    }
                    break;
            }
        }
    }
    public  void  hitBossByBoob(Vector<StaticBoob> boobs,Boss boss){
        for(int j=0;j<boobs.size();j++) {
            StaticBoob s=boobs.get(j);
            switch (boss.getDirect()) {
                case 0:
                case 2:
                    if (s.isLive&&boss.isLive&&s.x > boss.getX() && s.x < boss.getX() + 80 && s.y > boss.getY() && s.y < boss.getY() + 120) {
                        if (boss.ArmorNum > 0) {
                            s.isLive = false;
                            boobs.remove(s);
                            boss.ArmorNum--;
                            Bomb bomb = new Bomb(boss.getX(), boss.getY());
                            bombs.add(bomb);
                        } else {
                            s.isLive = false;
                            boobs.remove(s);
                            boss.isLive = false;
                            bosses.remove(boss);
                            Bomb bomb = new Bomb(boss.getX(), boss.getY());
                            bombs.add(bomb);
                        }
                    }
                    break;
                case 1:
                case 3:
                    if (s.isLive&&boss.isLive&&s.x > boss.getX() && s.x < boss.getX() + 120 && s.y > boss.getY() && s.y < boss.getY() + 80) {
                        if (boss.ArmorNum > 0) {
                            s.isLive = false;
                            boobs.remove(s);
                            boss.ArmorNum--;
                            Bomb bomb = new Bomb(boss.getX(), boss.getY());
                            bombs.add(bomb);
                        } else {
                            s.isLive = false;
                            boobs.remove(s);
                            boss.isLive = false;
                            bosses.remove(boss);
                            Bomb bomb = new Bomb(boss.getX(), boss.getY());
                            bombs.add(bomb);
                        }
                    }
                    break;
            }
        }
    }
    public  void hitHeroByEnemyTank(Shot s, Hero enemyTank){

            switch (enemyTank.getDirect()) {
                case 0:
                case 2:
                    if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60) {
                        if(enemyTank.ArmorNum>0) {
                            s.isLive=false;
                            enemyTank.ArmorNum--;
                        }
                        else {
                            s.isLive = false;
                            enemyTank.isLive = false;
                            enemyTanks.remove(enemyTank);
                            Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                            bombs.add(bomb);
                        }
                    }
                    break;
                case 1:
                case 3:
                    if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40) {
                        if(enemyTank.ArmorNum>0) {
                            s.isLive=false;
                            enemyTank.ArmorNum--;
                        }
                        else {
                            s.isLive = false;
                            enemyTank.isLive = false;
                            enemyTanks.remove(enemyTank);
                            Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                            bombs.add(bomb);
                        }
                    }
                    break;
            }

    }

    public  void hitBase(Shot s, Base base){
            if(s.isLive&&base.life>0){
                if(s.x>base.x&&s.x<base.x+base.wide1&&s.y>base.y&&s.y<base.y+base.length1){
                    s.isLive=false;
                    base.life--;
                    Bomb bomb = new Bomb(s.x,s.y);
                    bombs.add(bomb);
                }
            }
    }
    public  void hitBaseFromBoss(Boss boss, Base base){
        for(int i=0;i<boss.shots.size();i++) {
           Shot s=boss.shots.get(i);
            if (s.isLive && base.life > 0) {
                if (s.x > base.x && s.x < base.x + base.wide1 && s.y > base.y && s.y < base.y + base.length1) {
                    s.isLive = false;
                    boss.shots.remove(s);
                    base.life--;
                    Bomb bomb = new Bomb(s.x, s.y);
                    bombs.add(bomb);
                }
            }
        }
    }
    public void hitBaseFromBosses(){
        for(int i=0;i<bosses.size();i++){
            Boss s =bosses.get(i);
            if(s.isLive){
                hitBaseFromBoss(s,base);
            }
        }
    }
    public  void hitEnemyTankByShot(Vector<Shot> t, EnemyTank enemyTank){
           for(int i=0;i<t.size();i++) {
               Shot s=t.get(i);
               switch (enemyTank.getDirect()) {
                   case 0:
                   case 2:
                       if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60) {
                           s.isLive = false;
                           enemyTank.isLive = false;
                           int j=0;
                           j=(int)(Math.random()*6);
                           if(j<=3){
                              Buffer buffer= new Buffer(enemyTank.getX()+20,enemyTank.getY()+30,(int)(Math.random()*4));
                              new Thread(buffer).start();
                              buffers.add(buffer);
                           }
                           enemyTanks.remove(enemyTank);
                           Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                           bombs.add(bomb);
                           Recoder.addallEnemyTankNum();
                           if(Recoder.getAllEnemyTankNum()<enemyTankSize){
                               if(Recoder.getAllEnemyTankNum()==enemyTankSize/2)
                               {
                                   Boss boss1;
                                   Thread thread1;
                                   Shot shot1;
                                   boss1=new Boss(400,600);
                                   boss1.setHero(hero);
                                   boss1.setEnemyTanks(enemyTanks);
                                   boss1.setDirect(3);
                                   boss1.setSpeed(6);
                                   thread1=new Thread(boss1);
                                   thread1.start();
                                   shot1=new Shot(boss1.getX(),boss1.getY()+40,boss1.getDirect());
                                   shot1.speed=7;
                                   new Thread(shot1).start();
                                   boss1.shots.add(shot1);
                                   bosses.add(boss1);
                               }
                               EnemyTank enemyTank1;
                               Thread thread;
                               Shot shot;
                               int k=0;
                               k=(int)(Math.random()*4);
                               switch (k){
                                   case 0:
                                       enemyTank1 = new EnemyTank(0,0);
                                       enemyTank1.setEnemyTanks(enemyTanks);
                                       enemyTank1.setDirect(2);
                                       thread=new Thread(enemyTank1);
                                       thread.start();
                                       shot=new Shot(enemyTank1.getX()+20,enemyTank1.getY()+60,enemyTank1.getDirect());
                                       enemyTank1.shots.add(shot);
                                       new Thread(shot).start();
                                       enemyTanks.add(enemyTank1);
                                       break;
                                   case 1:
                                       enemyTank1 = new EnemyTank(800,0);
                                       enemyTank1.setEnemyTanks(enemyTanks);
                                       enemyTank1.setDirect(2);
                                       thread=new Thread(enemyTank1);
                                       thread.start();
                                       shot=new Shot(enemyTank1.getX()+20,enemyTank1.getY()+60,enemyTank1.getDirect());
                                       enemyTank1.shots.add(shot);
                                       new Thread(shot).start();
                                       enemyTanks.add(enemyTank1);
                                       break;
                                   case 2:
                                       enemyTank1 = new EnemyTank(800,600);
                                       enemyTank1.setEnemyTanks(enemyTanks);
                                       enemyTank1.setDirect(0);
                                       thread=new Thread(enemyTank1);
                                       thread.start();
                                       shot=new Shot(enemyTank1.getX()+20,enemyTank1.getY(),enemyTank1.getDirect());
                                       enemyTank1.shots.add(shot);
                                       new Thread(shot).start();
                                       enemyTanks.add(enemyTank1);
                                       break;
                                   case 3:
                                       enemyTank1 = new EnemyTank(0,600);
                                       enemyTank1.setEnemyTanks(enemyTanks);
                                       enemyTank1.setDirect(0);
                                       thread=new Thread(enemyTank1);
                                       thread.start();
                                       shot=new Shot(enemyTank1.getX()+20,enemyTank1.getY(),enemyTank1.getDirect());
                                       enemyTank1.shots.add(shot);
                                       new Thread(shot).start();
                                       enemyTanks.add(enemyTank1);
                                       break;
                               }
                           }
                       }

                       break;
                   case 1:
                   case 3:
                       if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40) {
                           s.isLive = false;
                           enemyTank.isLive = false;
                           int j=0;
                           j=(int)(Math.random()*6);
                           if(j<=3){
                               Buffer buffer= new Buffer(enemyTank.getX()+30,enemyTank.getY()+20,(int)(Math.random()*4));
                               new Thread(buffer).start();
                               buffers.add(buffer);
                           }
                           enemyTanks.remove(enemyTank);
                           Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                           bombs.add(bomb);
                           Recoder.addallEnemyTankNum();
                           if(Recoder.getAllEnemyTankNum()<enemyTankSize){
                               if(Recoder.getAllEnemyTankNum()==enemyTankSize/2)
                               {
                                   Boss boss1;
                                   Thread thread1;
                                   Shot shot1;
                                   boss1=new Boss(400,600);
                                   boss1.setEnemyTanks(enemyTanks);
                                   boss1.setHero(hero);
                                   boss1.setDirect(3);
                                   boss1.setSpeed(6);
                                   thread1=new Thread(boss1);
                                   thread1.start();
                                   shot1=new Shot(boss1.getX(),boss1.getY()+40,boss1.getDirect());
                                   shot1.speed=7;
                                   new Thread(shot1).start();
                                   boss1.shots.add(shot1);
                                   bosses.add(boss1);
                               }
                               EnemyTank enemyTank1;
                               Thread thread;
                               Shot shot;
                               int k=0;
                               k=(int)(Math.random()*4);
                               switch (k){
                                   case 0:
                                       enemyTank1 = new EnemyTank(0,0);
                                       enemyTank1.setEnemyTanks(enemyTanks);
                                       enemyTank1.setDirect(1);
                                       thread=new Thread(enemyTank1);
                                       thread.start();
                                       shot=new Shot(enemyTank1.getX()+60,enemyTank1.getY()+20,enemyTank1.getDirect());
                                       enemyTank1.shots.add(shot);
                                       new Thread(shot).start();
                                       enemyTanks.add(enemyTank1);
                                       break;
                                   case 1:
                                       enemyTank1 = new EnemyTank(800,0);
                                       enemyTank1.setEnemyTanks(enemyTanks);
                                       enemyTank1.setDirect(3);
                                       thread=new Thread(enemyTank1);
                                       thread.start();
                                       shot=new Shot(enemyTank1.getX(),enemyTank1.getY()+20,enemyTank1.getDirect());
                                       enemyTank1.shots.add(shot);
                                       new Thread(shot).start();
                                       enemyTanks.add(enemyTank1);
                                       break;
                                   case 2:
                                       enemyTank1 = new EnemyTank(800,600);
                                       enemyTank1.setEnemyTanks(enemyTanks);
                                       enemyTank1.setDirect(3);
                                       thread=new Thread(enemyTank1);
                                       thread.start();
                                       shot=new Shot(enemyTank1.getX(),enemyTank1.getY()+20,enemyTank1.getDirect());
                                       enemyTank1.shots.add(shot);
                                       new Thread(shot).start();
                                       enemyTanks.add(enemyTank1);
                                       break;
                                   case 3:
                                       enemyTank1 = new EnemyTank(0,600);
                                       enemyTank1.setEnemyTanks(enemyTanks);
                                       enemyTank1.setDirect(1);
                                       thread=new Thread(enemyTank1);
                                       thread.start();
                                       shot=new Shot(enemyTank1.getX()+60,enemyTank1.getY()+20,enemyTank1.getDirect());
                                       enemyTank1.shots.add(shot);
                                       new Thread(shot).start();
                                       enemyTanks.add(enemyTank1);
                                       break;
                               }
                           }
                       }
                       break;

               }
           }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
   //wdsa 按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            //改变坦克的方向
            hero.setDirect(0);
            //修改坦克的坐标
           if(hero.getY()>0) hero.moveUp();
        }
        else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirect(1);
           if(hero.getX()+60<1000) hero.moveRight();
        }
        else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirect(2);
            if(hero.getY()+60<750)hero.moveDown();
        }
        else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirect(3);
            if(hero.getX()>0) hero.moveLeft();
        }
        //如果用户按下J 就发射
        if(e.getKeyCode()==KeyEvent.VK_J){
           // if(hero.shot==null||hero.shot.isLive==false) {
                hero.setShotEnemyTank();
            //}
        }
        //set boob
        else if (e.getKeyCode()==KeyEvent.VK_K){
            hero.setBoobs();
        }
        //重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100ms自动重绘
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hitEnemyTankOrBoss();
            hitBaseFromEnemyTank();
            hitBaseFromBosses();
            hitHero();
            HitWall();
            for(int i=0;i<buffers.size();i++){
                Buffer buffer=buffers.get(i);
                buffer.getBuffer(hero,buffer);
            }
            this.repaint();
        }
    }
}

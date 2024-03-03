package tankgame05;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankGame extends JFrame {
    //定义一个MyPanel
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        TankGame tankGame = new TankGame();
    }
    public TankGame(){
         System.out.println("请输入选择，1表示新游戏， 2表示继续");
         String key = scanner.next();
         mp = new MyPanel(key);
         Thread thread=new Thread(mp);
         thread.start();
         this.add(mp);
         this.setSize(1400,950);
         this.addKeyListener(mp);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setVisible(true);
         this.addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent e) {
                 Recoder.keepRecord();
                 System.exit(0);
             }
         });
    }

}

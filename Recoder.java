package tankgame05;

import java.io.*;
import java.util.Vector;

public class Recoder {
    private static int allEnemyTankNum = 0;
    private static BufferedWriter bufferedWriter = null;
    private static BufferedReader bufferedReader =null;
    private static String recordFile = "c:\\TankGameRecord.txt";
    private static Vector<EnemyTank> enemyTanks=null;
    private static  Vector<Node> nodes = new Vector<>();

    public static Vector<Node> getNodesAndAllEnemyTank(){
        try {
            bufferedReader =new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum =Integer.parseInt(bufferedReader.readLine());
            String line = "";
            while ((line=bufferedReader.readLine())!=null){
               String[] xyd= line.split(" ");
               int x,y,d;
               x=Integer.parseInt(xyd[0]);
               y=Integer.parseInt(xyd[1]);
               d=Integer.parseInt(xyd[2]);
              Node node= new Node(x,y,d);
               nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return nodes;
    }
    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recoder.enemyTanks = enemyTanks;
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recoder.allEnemyTankNum = allEnemyTankNum;
    }

    public static void addallEnemyTankNum(){
        Recoder.allEnemyTankNum++;
    }
    public static void keepRecord(){
        try {
            bufferedWriter =new BufferedWriter(new FileWriter(recordFile));
            bufferedWriter.write(allEnemyTankNum+ "\r\n");
            for(int i=0;i<enemyTanks.size();i++){
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive){
                    String record =enemyTank.getX()+" "+ enemyTank.getY()+ " "+ enemyTank.getDirect();
                    bufferedWriter.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

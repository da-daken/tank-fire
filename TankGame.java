package tankgame;

import javax.swing.*;

//游戏框架
public class TankGame extends JFrame implements Runnable {
    private static Mypanel mp=null;


    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
        Thread thread = new Thread(tankGame);
        thread.start();
    }

    public TankGame() {
        mp=new Mypanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp); //把面板（就是游戏绘图区域）加进框架里面
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void run() {
        while(true){
            if(mp.enemyTanks.size()==0){
                System.out.println("游戏结束");
                System.out.println("呀呼,你嬴了！");
                break;
            }
            if(!mp.mytank.islive){
                System.out.println("游戏结束");
                System.out.println("啊哦,你输了！");
                break;
            }
        }
    }
}

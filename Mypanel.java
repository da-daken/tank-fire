package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//tank 大战绘图
public class Mypanel extends JPanel implements KeyListener,Runnable {
    //我的tank
    Mytank mytank=null;
    //敌方tank
    Vector<EnemyTank> enemyTanks=new Vector<>();

    //5辆敌方坦克
    int enemytanksize=5;

    //初始化坦克
    public Mypanel(){
        //我的坦克
        mytank=new Mytank(100,100);
        //敌方坦克和子弹
        for(int i=0;i<enemytanksize;i++) {
            EnemyTank enemyTank = new EnemyTank((int) (Math.random() * 960), (int) (Math.random() * 690));
            enemyTank.setDir((int) (Math.random() * 3));
            enemyTanks.add(enemyTank);
            //这里启动enemyTank自由移动线程
            Thread thread = new Thread(enemyTank);
            thread.start();
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //画面板
        g.fillRect(0,0,1000,750);
        if(mytank.islive) {
            //画自己的坦克
            drawtank(mytank.getX(), mytank.getY(), g, mytank.getDir(), 0);
        }
        //画敌方的随机坦克
        for(int i=0;i<enemyTanks.size();i++){
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //问题：如果把enemyTank自由移动线程启动在这的话，再调用repaint的时候则会一直启动线程
            //造成bug（一直上下左右，而且移动的很快，我也不知道为什么会这样）
            //（或许是一直启动线程让他的间隔变短了）

            //判断当前坦克是否被击中
            if(enemyTank.islive) {
                drawtank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDir(), 1);
            }
                for (int j = 0; j < enemyTank.bullets.size(); j++) {
                    //取出子弹
                    Bullet bullet = enemyTank.bullets.get(j);
                    if (bullet.islive) {
                        g.fillOval(bullet.x, bullet.y, 5, 5);
                    } else {
                        enemyTank.bullets.remove(bullet);
                    }
                }
        }
        //画自己坦克的子弹
        drawMybullet(g);
    }

    //判断子弹是否击中敌方坦克
    public void hittank(Bullet b,Tank t){
        switch (t.getDir()){
            case 0:
            case 3:
                if(b.x>t.getX()+10 && b.x<t.getX()+30 && b.y>t.getY()+10 && b.y<t.getY()+50){
                    b.islive=false;
                    t.islive=false;
                }
                break;
            case 1:
            case 2:
                if(b.x>t.getX()+10 && b.x<t.getX()+50 && b.y>t.getY()+10 && b.y<t.getY()+30){
                    b.islive=false;
                    t.islive=false;
                }
                break;
        }
    }
    //画坦克
    // x y 坦克坐标
    // g 画笔
    // dir 坦克运动方向
    // type 区别敌方和自己 自己为绿色 敌方为红色
    public void drawtank(int x,int y,Graphics g,int dir,int type){
        switch (type){
            case 0://Mytank
                g.setColor(Color.GREEN);
                break;
            case 1://Enemytank
                g.setColor(Color.red);
                break;
        }
        //根据方向绘制坦克（炮塔）
        //0 向上 1向右 2向左 3向下
        switch (dir){
            case 0:
                //向上
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y-5);
                break;
            case 1:
                //向右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+65,y+20);
                break;
            case 2:
                //向左
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x-5,y+20);
                break;
                case 3:
                //向下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+65);
                break;
        }
    }
    //画自己的坦克子弹
    public void drawMybullet(Graphics g){
        for(int i=0;i<mytank.bullets.size();i++) {
            Bullet bullet = mytank.bullets.get(i);
            if (bullet != null && bullet.islive == true) {
                g.fillOval(bullet.x, bullet.y, 5, 5);
            }
            else{
                mytank.bullets.remove(bullet);
            }
        }
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    //wasd 控制上下左右
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            mytank.setDir(0);
            mytank.moveup();
        }
        if(e.getKeyCode()==KeyEvent.VK_A){
            mytank.setDir(2);
            mytank.moveleft();
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            mytank.setDir(1);
            mytank.moveright();
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            mytank.setDir(3);
            mytank.movedown();
        }
        //如果按下‘j'就发射子弹
        if(e.getKeyCode()==KeyEvent.VK_J){
            mytank.shot();
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //在run重绘前判断子弹是否击中坦克
            //判断自己的子弹是否存活，能这样判断是因为在执行hittank前子弹都还是true
            if(mytank.bullet!=null && mytank.bullet.islive){
                //遍历是哪个坦克被击中
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank enemyTank = enemyTanks.get(i);
                    for(int j=0;j<mytank.bullets.size();j++) {
                        Bullet bullet = mytank.bullets.get(j);
                        hittank(bullet, enemyTank);
                    }
                    if(!enemyTank.islive){
                        enemyTanks.remove(enemyTank);
                    }
                }
            }

            //敌方子弹是否击中自己
            for(int i=0;i<enemyTanks.size();i++){
                EnemyTank enemyTank = enemyTanks.get(i);
                for(int j=0;j<enemyTank.bullets.size();j++){
                    Bullet bullet = enemyTank.bullets.get(j);
                    if(mytank.islive && bullet.islive) {
                        hittank(bullet, mytank);
                    }
                }

            }

            this.repaint();
        }
    }
}

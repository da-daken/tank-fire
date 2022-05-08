package tankgame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
    Vector<Bullet> bullets=new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }


    @Override
    public void run() {

        while(true){
            //敌方发射子弹数
            if(islive && bullets.size()<10){
                Bullet bullet=null;
                switch (this.getDir()){
                    case 0:
                        bullet = new Bullet(this.getX() + 20, this.getY() - 5, this.getDir());
                        break;
                    case 1:
                        bullet = new Bullet(this.getX() + 65, this.getY() + 20, this.getDir());
                        break;
                    case 2:
                        bullet = new Bullet(this.getX() - 5, this.getY() + 20, this.getDir());
                        break;
                    case 3:
                        bullet = new Bullet(this.getX() + 20, this.getY() + 65, this.getDir());
                        break;
                }
                bullets.add(bullet);
                new Thread(bullet).start();
            }
            //getDir()将由0~3的随机函数进行选择
            switch (getDir()){
                case 0:
                    for(int i=0;i<30;i++) {
                        moveup();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for(int i=0;i<30;i++) {
                        moveright();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for(int i=0;i<30;i++) {
                        moveleft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for(int i=0;i<30;i++) {
                        movedown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            //随机改变坦克方向
            setDir((int)(Math.random()*4));

            if(!islive){
                break;
            }
        }
    }


}

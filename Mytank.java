package tankgame;

import java.util.Vector;

// 我的坦克
// 定义初始坐标
public class Mytank extends Tank {
    public Mytank(int x,int y) {
        super(x,y);
    }

    Bullet bullet=null;
    //子弹集合
    Vector<Bullet> bullets=new Vector<>();
    //射击
    public void shot(){
        //将面板子弹设置在20颗内
        if(bullets.size()<=19) {
            switch (this.getDir()) {
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
            Thread thread = new Thread(this.bullet);
            thread.start();
        }
    }

}

package tankgame;

public class Bullet implements Runnable{
    int x,y;//子弹的坐标
    int dir;//子弹的方向
    int speed=10;//子弹的速度
    boolean islive=true;//子弹是否存活

    public Bullet(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (dir){
                case 0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    x-=speed;
                    break;
                case 3:
                    y+=speed;
            }
            if(!(x>=0 && x<=1000 && y<=750 && y>=0) || !islive){
                islive=false;
                break;
            }
        }
    }
}

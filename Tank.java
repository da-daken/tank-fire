package tankgame;

//坦克定义
public class Tank {
    private int x;//tank横坐标
    private int y;//tank纵坐标
    private int dir;//tank方向
    private int type;//tank类型
    public boolean islive=true;

    public void moveup() {
        if( y>0 ) {
            y -= 3;
        }
    }
    public void moveright() {
        if( x+60<1000 ) {
            x += 3;
        }
    }
    public void moveleft() {
        if( x>0 ) {
            x -= 3;
        }
    }
    public void movedown() {
        if( y+60<750 ) {
            y += 3;
        }
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}

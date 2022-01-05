package collection;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private int x;
    private double y;

    public Coordinates(int var1, double var2) {
        this.x = var1;
        this.y = var2;
    }

    public Coordinates() {
    }

    public String toString() {
        return "Здание:" + this.x + ", Аудитория: " + this.y;
    }

    public boolean setY(double var1) {
        if (var1 <= 705 &var1!=0 ) {
            this.y = var1;
            return true;
        } else {
            System.out.println("Значение координаты по оси x должно быть меньше или равно 705");
            return false;
        }
    }

    public boolean setX(int var1) {
        this.x = var1;
        return true;
    }

    public int getX() {
        return x;
    }

    public double getY() {
        return y;
    }



}

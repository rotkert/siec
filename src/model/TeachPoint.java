package model;

/**
 * Created by Adrian on 2015-01-11.
 */
public class TeachPoint {
    private double x, y, resultW, resultB;

    public TeachPoint(double x, double y, double resultW, double resultB) {
        this.x = x;
        this.y = y;
        this.resultW = resultW;
        this.resultB = resultB;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getResultW() {
        return resultW;
    }

    public void setResultW(double resultW) {
        this.resultW = resultW;
    }

    public double getResultB() {
        return resultB;
    }

    public void setResultB(double resultB) {
        this.resultB = resultB;
    }
}

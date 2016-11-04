/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.Random;

/**
 *
 * @author Ersa
 */
public class Kromosom {

    private int[] x = new int[10];
    private double fitness;

    public Kromosom(int[] x) {
        for (int i = 0; i < 10; i++) {
            this.x[i] = x[i];
        }
        setFitness();
    }

    public Kromosom() {
        for (int i = 0; i < x.length; i++) {
            this.x[i] = randomizeWeight();
        }
        setFitness();
    }

    private int randomizeWeight() {
        Random r = new Random();
        return r.nextInt(9);
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness() {
        double[] y = new double[2];
        double temp1 = 0;
        double temp2 = 0;
        for (int i = 0; i < 5; i++) {
            temp1 += x[i] * Math.pow(10, (-i - 1));
            temp2 += 9 * Math.pow(10, -(i + 1));
        }
        y[0] = -5.12 + (10.24 / temp2) * temp1;
        temp1 = 0;
        temp2 = 0;
        for (int i = 5; i < 10; i++) {
            temp1 += x[i] * Math.pow(10, (-i - 1));
            temp2 += 9 * Math.pow(10, -(i + 1));
        }
        y[1] = -5.12 + (10.24 / temp2) * temp1;
        this.fitness = 1 / ((100 * Math.pow(y[0] * y[0] - y[1], 2) + Math.pow(1 - y[0], 2)) + 0.0001);
    }

    public int getX(int index) {
        return x[index];
    }

    public void setX(int x, int index) {
        this.x[index] = x;
    }

    public int getGenLenght() {
        return x.length;
    }
    

    public double[] getValue() {
        double[] y = new double[2];
        double temp1 = 0;
        double temp2 = 0;
        for (int i = 0; i < 5; i++) {
            temp1 += x[i] * Math.pow(10, (-i - 1));
            temp2 += 9 * Math.pow(10, -(i + 1));
        }
        y[0] = -5.12 + (10.24 / temp2) * temp1;
        temp1 = 0;
        temp2 = 0;
        for (int i = 5; i < 10; i++) {
            temp1 += x[i] * Math.pow(10, (-i - 1));
            temp2 += 9 * Math.pow(10, -(i + 1));
        }
        y[1] = -5.12 + (10.24 / temp2) * temp1;
        return y;
    }
}

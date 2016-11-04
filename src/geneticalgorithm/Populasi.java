/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Ersa
 */
public class Populasi {

    public Kromosom[] populasi;

    public Populasi(int populationSize, boolean create) {
        populasi = new Kromosom[populationSize];
        if (create) {
            for (int i = 0; i < populationSize; i++) {
                this.populasi[i] = new Kromosom();
            }
        }
    }

    public int getNumbOfPop() {
        return populasi.length;
    }

    public Kromosom getKromosom(int index) {
        return populasi[index];
    }

    public Kromosom getFittest() {
        Kromosom fittest = populasi[0];
        for (int i = 1; i < this.populasi.length; i++) {
            if (populasi[i].getFitness() > fittest.getFitness()) {
                fittest = this.populasi[i];
            }
        }
        return fittest;
    }

    public void setKromosom(int index, Kromosom k) {
        this.populasi[index] = k;
    }

    public double totalFitness() {
        double fitness = 0;
        for (int i = 1; i < this.populasi.length; i++) {
            fitness += getKromosom(i).getFitness();
        }
        return fitness;
    }

    public void sortByFitness() {
        Arrays.sort(populasi, new Comparator<Kromosom>() {
            @Override
            public int compare(Kromosom o1, Kromosom o2) {
                if (o1.getFitness() == o2.getFitness()) {
                    return 0;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return -1;
                } else {
                    return 1;
                }
            }

        });
    }

}

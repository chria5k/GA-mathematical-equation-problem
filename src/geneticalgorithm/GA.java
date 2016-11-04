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
public class GA {

    static double crossOverRate = 0.9;
    static double mutationRate = 0.6;
    static String parentSelection = "ts";
    static int TournamentSize = 5;
    static String survivorSelection = "gr";
    static int elitismOffset = 2;

    public static Kromosom TournamentSelection(Populasi pop) {
        Random r = new Random();
        Populasi PopulasiTurnamen = new Populasi(TournamentSize, false);
        for (int i = 0; i < TournamentSize; i++) {
            Kromosom k = pop.getKromosom(r.nextInt(pop.getNumbOfPop()));
            PopulasiTurnamen.setKromosom(i, k);
        }
        return PopulasiTurnamen.getFittest();
    }

    public static Kromosom rouletteWheelSelection(Populasi pop) {
        Random r = new Random();
        double randNum = r.nextDouble() * pop.totalFitness();
        int i;
        for (i = 0; i < pop.getNumbOfPop() && randNum > 0; ++i) {
            randNum -= pop.getKromosom(i).getFitness();
        }
        return pop.getKromosom(i - 1);
    }

    public static Kromosom[] Crossover(Populasi pop) {
        Random r = new Random();
        Kromosom parent1 = null;
             Kromosom parent2 = null;
        if (parentSelection == "rw") {
            parent1 = rouletteWheelSelection(pop);
            parent2 = rouletteWheelSelection(pop);
        } else if (parentSelection == "ts") {
            parent1 = TournamentSelection(pop);
            parent2 = TournamentSelection(pop);
        }

        Kromosom[] child = new Kromosom[2];
        if (r.nextDouble() < crossOverRate) {
            child[0] = new Kromosom();
            child[1] = new Kromosom();
            int titik = r.nextInt(parent1.getGenLenght());
            for (int i = 0; i < parent1.getGenLenght(); i++) {
                int x, y;
                x = parent1.getX(i);
                y = parent2.getX(i);
                if (i < titik) {
                    child[0].setX(x, i);
                    child[1].setX(y, i);
                } else {
                    child[0].setX(y, i);
                    child[1].setX(x, i);
                }
            }
            child[0].setFitness();
            child[1].setFitness();
        } else {
            child[0] = parent1;
            child[1] = parent2;
        }

        return child;
    }

    public static Kromosom Mutation(Kromosom kromosom) {
        Random r = new Random();
        for (int i = 0; i < kromosom.getGenLenght(); i++) {
            if (r.nextDouble() < mutationRate) {
                int genBaru = r.nextInt(9);
                kromosom.setX(genBaru, i);
            }
        }
        kromosom.setFitness();
        return kromosom;
    }

    public static Populasi Evolution(Populasi pop) {
        Populasi newPopulation = new Populasi(pop.getNumbOfPop(), false);
        if (survivorSelection == "ss") {
            Populasi childPopulation = new Populasi(pop.getNumbOfPop(), false);
            for (int i = 0; i < pop.getNumbOfPop(); i++) {
                Kromosom[] child = Crossover(pop);
                childPopulation.setKromosom(i, child[0]);
                i++;
                if (i < childPopulation.getNumbOfPop()) {
                    childPopulation.setKromosom(i, child[1]);
                }
            }
            for (int i = 0; i < childPopulation.getNumbOfPop(); i++) {
                Kromosom y = Mutation(childPopulation.getKromosom(i));
                childPopulation.setKromosom(i, y);;
            }
            Populasi totalPopulation = new Populasi(pop.getNumbOfPop() + childPopulation.getNumbOfPop(), false);
            for (int i = 0; i < pop.getNumbOfPop() + childPopulation.getNumbOfPop(); i++) {
                int j = 0;
                if (i >= childPopulation.getNumbOfPop()) {
                    totalPopulation.setKromosom(i, childPopulation.getKromosom(j));
                    j++;
                } else {
                    totalPopulation.setKromosom(i, pop.getKromosom(i));
                }

            }
            totalPopulation.sortByFitness();
            for (int i = 0; i < pop.getNumbOfPop(); i++) {
                newPopulation.setKromosom(i, totalPopulation.getKromosom(i));
            }

        } else if (survivorSelection == "gr") {
            Kromosom k = pop.getFittest();
            for (int i = 0; i < elitismOffset; i++) {
                newPopulation.setKromosom(i, k);
            }
            for (int i = elitismOffset; i < pop.getNumbOfPop(); i++) {
                Kromosom[] child = Crossover(pop);
                newPopulation.setKromosom(i, child[0]);
                i++;
                if (i < newPopulation.getNumbOfPop()) {
                    newPopulation.setKromosom(i, child[1]);
                }
            }
        }

        return newPopulation;

    }
}

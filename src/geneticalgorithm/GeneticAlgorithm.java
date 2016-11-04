/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

/**
 *
 * @author Ersa
 */
public class GeneticAlgorithm {

    final static int numOfPop = 400;
    final static int numOfGen = 50;

    public static void main(String[] args) {
        for (int j = 0; j < 5; j++) {
            System.out.println("percobaan ke " + (j+1));
            Populasi pop = new Populasi(numOfPop, true);
            System.out.println("Nilai awal");
            Kromosom k = pop.getFittest();
            double[] r = k.getValue();
            System.out.println("X1 = " + r[0]);
            System.out.println("X2 = " +r[1]);
            System.out.println("Fitness = " + k.getFitness());
            for (int i = 0; i < numOfGen; i++) {
                pop = GA.Evolution(pop);
            }
            k = pop.getFittest();
            r = k.getValue();
            System.out.println("X1 = " + r[0]);
            System.out.println("X2 = " + r[1]);
            System.out.println("Fitness = " + k.getFitness());
            System.out.println("");
            System.out.println("");
        }
    }

}

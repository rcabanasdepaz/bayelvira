/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author rcabanas
 */
public class SortVectors {

    public static ArrayList quicksort(double v[]) {

        ArrayList out = new ArrayList();

        int index[] = new int[v.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }

        double v2[] = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            v2[i] = v[i];
        }
        quicksort(v2, index, 0, v.length - 1);

        out.add(v2);
        out.add(index);

        return out;


    }

    private static void quicksort(double v[], int index[], int izda, int dcha) {




        int pivote; // Posición del pivote pivote; // Posición del pivote
        if (izda < dcha) {
            pivote = partir(v, index, izda, dcha);
            quicksort(v, index, izda, pivote - 1);
            quicksort(v, index, pivote + 1, dcha);
        }
    }

    private static int partir(double v[], int index[], int primero, int ultimo) {

        double pivote = v[primero];
        double tmp;
        int tmp2;

        int izda = primero + 1;
        int dcha = ultimo;

        do {
            while ((izda <= dcha) && (v[izda] <= pivote)) {
                izda++;
            }
            while ((izda <= dcha) && (v[dcha] > pivote)) {
                dcha--;
            }

            if (izda < dcha) {
                tmp = v[izda];
                v[izda] = v[dcha];
                v[dcha] = tmp;

                tmp2 = index[izda];
                index[izda] = index[dcha];
                index[dcha] = tmp2;
            }
        } while (izda <= dcha);

        tmp = v[primero];
        v[primero] = v[dcha];
        v[dcha] = tmp;

        tmp2 = index[primero];
        index[primero] = index[dcha];
        index[dcha] = tmp2;

        return dcha;


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int N = 10000;
        Random rand = new Random(1234);
        ArrayList sort = null;

        for (int k = 0; k < 10000; k++) {
            double v[] = new double[N];
            for (int i = 0; i < N; i++) {
                v[i] = rand.nextDouble() * 50;
            }


            sort = quicksort(v);
        }
        double vsorted[] = (double[]) sort.get(0);
        int indexsorted[] = (int[]) sort.get(1);

        for (int i = 0; i < vsorted.length; i++) {
            System.out.print(vsorted[i] + " ");
        }

        System.out.println();
        for (int i = 0; i < indexsorted.length; i++) {
            System.out.print(indexsorted[i] + " ");
        }
    


    }
}

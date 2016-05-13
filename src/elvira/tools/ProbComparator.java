/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools;

import elvira.Configuration;
import elvira.potential.Potential;
import elvira.potential.PotentialTable;

/**
 *
 * @author rafa
 */
public class ProbComparator {
    
    
        public static double getMeanKB(Potential p1, Potential p2) {

        PotentialTable t1 = new PotentialTable(p1);
        PotentialTable t2 = new PotentialTable(p2);
        

        
        if(t1.getSize() != t2.getSize())
            return -1;
        
        double diffProb = 0;
        
        Configuration c = new Configuration(t1.getVariables());
        double v1, v2;
        for(int i=0; i<t1.getSize(); i++) {
            v1 = t1.getValue(c);
            v2 = t2.getValue(c);
            if(v1 !=0 && v2!=0) {
            diffProb += Math.log(v1/v2)*v1;
            }
            c.nextConfiguration();
        }
        return diffProb/t1.getSize();
    }    
    
        public static double getMeaneEuclidean(Potential p1, Potential p2) {

        PotentialTable t1 = new PotentialTable(p1);
        PotentialTable t2 = new PotentialTable(p2);
        

        
        if(t1.getSize() != t2.getSize())
            return -1;
        
        double diffProb = 0;
        
        Configuration c = new Configuration(t1.getVariables());
        double v1, v2;
        for(int i=0; i<t1.getSize(); i++) {
            v1 = t1.getValue(c);
            v2 = t2.getValue(c);
            if(v1 != v2)
            diffProb += Math.pow(v1-v2,2);
            c.nextConfiguration();
        }
        return Math.sqrt(diffProb/t1.getSize());
    }    
        
    
}

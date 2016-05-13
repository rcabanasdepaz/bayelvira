/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.idiagram;

import elvira.Configuration;
import elvira.FiniteStates;
import elvira.potential.Potential;
import elvira.potential.PotentialIntervalTable;
import elvira.potential.PotentialTable;

import java.util.Vector;

/**
 * Utility class with functions devoted to computing 
 * expected utilities differencies between two solutions
 * The main part of the methods will be static
 * @author mgomez
 */
public class EUComparator {
 
      /**
     * Method to compare the policies obtained as a consequence
     * of two evalautions on the same IDiagram using the relative error
     * @param reference solution
     * @param <code>resultsToCompare</code> evaluation to compare with
     * @result <code>double</code> mean relative error
     */
    public Vector<Double> compareEU_relativeError(Vector reference, Vector resultsToCompare) {
        Potential result;
        Potential resultToCompare;
        Vector vars;
        Vector varsForConf = new Vector();
        FiniteStates decision;
        Configuration partial, total, totalToCompare;
        double utility, utilityToCompare, max, diff = 0, diffLocal;
        long k;
        long cases;
        int i, j, indMax;
        Vector<Double> difEU = new Vector<Double>();
        
        // To compare both policies we must see the expected
        // utility of the proposed policy for the evaluation
        // respect to the evaluation passed as an argument
        // We will consider all tables

        for (i = 0; i < reference.size(); i++) {

            // Select the potential related with this own object

            result = new PotentialTable((Potential)reference.elementAt(i));
            
            // Select the potential related with the results to be compared
            // with this

            resultToCompare = new PotentialTable((Potential) resultsToCompare.elementAt(i));

            // Make a configuration over the whole set of
            // variables except the last one. The last one is related
            // with the decision variable and it must be kept appart
            // to loop over it

            vars = result.getVariables();
            for (j = 0; j < vars.size() - 1; j++) {
                varsForConf.addElement(vars.elementAt(j));
            }

            // Build the configuration

            partial = new Configuration(varsForConf);

            // Build a new configuration with the whole set of variables
            // This configuration will be used to access to the table with
            // the optimal policy, given a certain configuration

            total = new Configuration(vars);

            // Retrieve the decision to consider

            decision = (FiniteStates) vars.elementAt(j);

            // The decision is used to determine the number od states
            // of the related variable. So is computed the final size
            // of the potential with the optimal policy, without taking
            // into account the number of states of the decision

            cases = (long) FiniteStates.getSize(vars) / decision.getNumStates();

            // Built a configuration with the variables of the potential
            // passed as argument and that will be compared

            totalToCompare = new Configuration(resultToCompare.getVariables());

            // Once the configuration is done, we must go over
            // all of its values, to retrieve the optimal policy
            // for it

            diff = 0;
            for (k = 0; k < cases; k++) {

                // Copy the values from partial configuration

                total.resetConfiguration(partial);

                // Get the optimal policy for each case, in the table
                // related to this object

                max = 0;
                utility = 0;
                for (j = 0, indMax = 0; j < decision.getNumStates(); j++) {
                    total.putValue(decision, j);
                    utility = result.getValue(total);

                    if (j == 0) {
                        max = utility;
                    } else {
                        if (max < utility) {
                            max = utility;
                            indMax = j;
                        }
                    }
                }

                // Set the value for the maximum value in the total
                // configuration

                total.putValue(decision, indMax);

                // Once obtained the maximum for the base table,
                // get this value for the second one

                for (j = 0; j < totalToCompare.size(); j++) {
                    totalToCompare.putValue(total.getVariable(j).getName(), total.getValue(j));
                }

                // Get the expected utility related to this policy, but in the potential
                // passed as argument

                utilityToCompare = resultToCompare.getValue(totalToCompare);

                // Compute the difference


                if((max+utilityToCompare)==0)
                    diff+=0;
                else
                    diff += Math.abs((max - utilityToCompare)/(max+utilityToCompare));

                // Go to the next configuration

                partial.nextConfiguration();
            }

            // Once finished, divide by the number of cases

            diff = diff / cases;

            //Adds the difference in the EU of the decision
            difEU.add(diff);
        }

        return difEU;
    }  
    
    
    
    
        /**
     * Method to compare the policies obtained as a consequence
     * of two evalautions on the same IDiagram
     * @param reference results
     * @param <code>resultsToCompare</code> evaluation to compare with
     * @result <code>double</code> the distance between policies
     */
/*    public static Vector<Double> compareEU(Vector reference, Vector resultsToCompare) {
        Potential result;
        Potential resultToCompare;
        Vector vars;
        Vector varsForConf = new Vector();
        FiniteStates decision;
        Configuration partial, total, totalToCompare;
        double utility, utilityToCompare, max, diff = 0, diffLocal;
        long k;
        long cases;
        int i, j, indMax;
        Vector<Double> difEU = new Vector<Double>();
        
        // To compare both policies we must see the expected
        // utility of the proposed policy for the evaluation
        // respect to the evaluation passed as an argument
        // We will consider all tables

        for (i = 0; i < reference.size(); i++) {

            // Select the potential related with this own object

            result = new PotentialTable((Potential)reference.elementAt(i));

 
       //     result.print();
            // Select the potential related with the results to be compared
            // with this

            resultToCompare = new PotentialTable((Potential) resultsToCompare.elementAt(i));
       //     resultToCompare.print();


            // Make a configuration over the whole set of
            // variables except the last one. The last one is related
            // with the decision variable and it must be kept appart
            // to loop over it

            vars = result.getVariables();
            for (j = 0; j < vars.size() - 1; j++) {
                varsForConf.addElement(vars.elementAt(j));
            }

            // Build the configuration

            partial = new Configuration(varsForConf);

            // Build a new configuration with the whole set of variables
            // This configuration will be used to access to the table with
            // the optimal policy, given a certain configuration

            total = new Configuration(vars);

            // Retrieve the decision to consider

            if(vars.size()>0) {
            
                decision = (FiniteStates) vars.elementAt(j);

                // The decision is used to determine the number od states
                // of the related variable. So is computed the final size
                // of the potential with the optimal policy, without taking
                // into account the number of states of the decision

                cases = (long) FiniteStates.getSize(vars) / decision.getNumStates();

                // Built a configuration with the variables of the potential
                // passed as argument and that will be compared

                totalToCompare = new Configuration(resultToCompare.getVariables());

                // Once the configuration is done, we must go over
                // all of its values, to retrieve the optimal policy
                // for it

                diff = 0;
                for (k = 0; k < cases; k++) {

                    // Copy the values from partial configuration

                    total.resetConfiguration(partial);

                    // Get the optimal policy for each case, in the table
                    // related to this object

                    max = 0;
                    utility = 0;
                    for (j = 0, indMax = 0; j < decision.getNumStates(); j++) {
                        total.putValue(decision, j);
                        utility = result.getValue(total);

                        if (j == 0) {
                            max = utility;
                        } else {
                            if (max < utility) {
                                max = utility;
                                indMax = j;
                            }
                        }
                    }

                    // Set the value for the maximum value in the total
                    // configuration

                    total.putValue(decision, indMax);

                    // Once obtained the maximum for the base table,
                    // get this value for the second one

                    for (j = 0; j < totalToCompare.size(); j++) {
                        totalToCompare.putValue(total.getVariable(j).getName(), total.getValue(j));
                    }

                    // Get the expected utility related to this policy, but in the potential
                    // passed as argument

                    utilityToCompare = resultToCompare.getValue(totalToCompare);

                    // Compute the difference

                    diffLocal = max - utilityToCompare;
                    ;
                    diff += Math.pow(diffLocal, 2);

                    // Go to the next configuration

                    partial.nextConfiguration();
                }

                // Once finished, divide by the number of cases

                diff = diff / cases;

                // Get square root, as the global diff

                diff = Math.sqrt(diff);
                //Adds the difference in the EU of the decision
                difEU.add(diff);
            }
        }

        return difEU;
    }
    */
    
    /**
     * Method to compare the policies obtained as a consequence
     * of two evalautions on the same IDiagram
     * @param reference results
     * @param <code>resultsToCompare</code> evaluation to compare with
     * @result <code>double</code> the distance between policies
     */
    public static Vector<Double> compareEU(Vector reference, Vector resultsToCompare) {
        Potential result = null;
        Potential resultToCompare = null;
        Vector vars;
        Vector varsForConf = new Vector();
        FiniteStates decision;
        Configuration partial, total, totalToCompare;
        double utility, utilityToCompare, max, diff = 0, diffLocal;
        long k;
        long cases;
        int i, j, indMax;
        Vector<Double> difEU = new Vector<Double>();
        
        // To compare both policies we must see the expected
        // utility of the proposed policy for the evaluation
        // respect to the evaluation passed as an argument
        // We will consider all tables
        
        if(reference.size() != resultsToCompare.size())
            System.out.println();
        

        for (i = 0; i < reference.size(); i++) {

            // Select the potential related with this own object
            if(reference.elementAt(i) != null && resultsToCompare.elementAt(i) != null ) {
                if(!(reference.elementAt(i) instanceof PotentialIntervalTable))
                    result = new PotentialTable((Potential)reference.elementAt(i));
                else
                    result = (Potential)reference.elementAt(i);

                
                resultToCompare = new PotentialTable((Potential) resultsToCompare.elementAt(i));
//System.out.println(i); 
//            showDiff(result, resultToCompare, true);
                if(!(reference.elementAt(i) instanceof PotentialIntervalTable))
                    diff = compareUtilities(result, resultToCompare);
                else
                    diff = compareIntervalUtilities((PotentialIntervalTable) result, resultToCompare);
        }else{
                diff = -1;
            
        }
        
                difEU.add(diff);
            
        }

        return difEU;
    }
    
    
        /**
     * Method to compare the policies obtained as a consequence
     * of two evalautions on the same IDiagram
     * @param reference results
     * @param <code>resultsToCompare</code> evaluation to compare with
     * @result <code>double</code> the distance between policies
     */
    public static Vector<Double> compareEUabs(Vector reference, Vector resultsToCompare) {
        Potential result;
        Potential resultToCompare;
        Vector vars;
        Vector varsForConf = new Vector();
        FiniteStates decision;
        Configuration partial, total, totalToCompare;
        double utility, utilityToCompare, max, diff = 0, diffLocal;
        long k;
        long cases;
        int i, j, indMax;
        Vector<Double> difEU = new Vector<Double>();
        
        // To compare both policies we must see the expected
        // utility of the proposed policy for the evaluation
        // respect to the evaluation passed as an argument
        // We will consider all tables

        for (i = 0; i < reference.size(); i++) {

            // Select the potential related with this own object

            result = (Potential) reference.elementAt(i);
            


            // Select the potential related with the results to be compared
            // with this

            resultToCompare = (Potential) resultsToCompare.elementAt(i);

            // Make a configuration over the whole set of
            // variables except the last one. The last one is related
            // with the decision variable and it must be kept appart
            // to loop over it

            vars = result.getVariables();
            for (j = 0; j < vars.size() - 1; j++) {
                varsForConf.addElement(vars.elementAt(j));
            }

            // Build the configuration

            partial = new Configuration(varsForConf);

            // Build a new configuration with the whole set of variables
            // This configuration will be used to access to the table with
            // the optimal policy, given a certain configuration

            total = new Configuration(vars);

            // Retrieve the decision to consider

            decision = (FiniteStates) vars.elementAt(j);

            // The decision is used to determine the number od states
            // of the related variable. So is computed the final size
            // of the potential with the optimal policy, without taking
            // into account the number of states of the decision

            cases = (long) FiniteStates.getSize(vars) / decision.getNumStates();

            // Built a configuration with the variables of the potential
            // passed as argument and that will be compared

            totalToCompare = new Configuration(resultToCompare.getVariables());

            // Once the configuration is done, we must go over
            // all of its values, to retrieve the optimal policy
            // for it

            diff = 0;
            for (k = 0; k < cases; k++) {

                // Copy the values from partial configuration

                total.resetConfiguration(partial);

                // Get the optimal policy for each case, in the table
                // related to this object

                max = 0;
                utility = 0;
                for (j = 0, indMax = 0; j < decision.getNumStates(); j++) {
                    total.putValue(decision, j);
                    utility = result.getValue(total);

                    if (j == 0) {
                        max = utility;
                    } else {
                        if (max < utility) {
                            max = utility;
                            indMax = j;
                        }
                    }
                }

                // Set the value for the maximum value in the total
                // configuration

                total.putValue(decision, indMax);

                // Once obtained the maximum for the base table,
                // get this value for the second one

                for (j = 0; j < totalToCompare.size(); j++) {
                    totalToCompare.putValue(total.getVariable(j).getName(), total.getValue(j));
                }

                // Get the expected utility related to this policy, but in the potential
                // passed as argument

                utilityToCompare = resultToCompare.getValue(totalToCompare);

                // Compute the difference

                diffLocal = max - utilityToCompare;
                ;
                diff += Math.abs(diffLocal);

                // Go to the next configuration

                partial.nextConfiguration();
            }

            // Once finished, divide by the number of cases

            diff = diff / cases;


            //Adds the difference in the EU of the decision
            difEU.add(diff);
        }

        return difEU;
    }

    
    public static void showDiff(Potential p1, Potential p2, boolean showOnlyDiff) {
            PotentialTable t1 = new PotentialTable(p1);
            PotentialTable t2 = new PotentialTable(p2);
            
            
            System.out.println(t1);
            Configuration c = new Configuration(t1.getVariables());
            double v1, v2;
            for(int i=0; i<t1.getSize(); i++) {
                v1 = t1.getValue(c);
                v2 = t2.getValue(c);
                if(!showOnlyDiff || v1!=v2)
                    System.out.println(c.stringValues()+"\t"+v1+"\t"+v2+"\t"+(v1-v2));
                c.nextConfiguration();
            }
    
    }
    
    

        /**
         * 
         * Computes the error between two utility potentials
     */
    public static double compareUtilities(Potential p1, Potential p2) {

        PotentialTable t1 = new PotentialTable(p1);
        PotentialTable t2 = new PotentialTable(p2);
        
        
        if(t1.getSize() != t2.getSize())
            return -1;
        
        double diffEU = 0;
        
        Configuration c = new Configuration(t1.getVariables());
        double v1, v2;
        for(int i=0; i<t1.getSize(); i++) {
            v1 = t1.getValue(c);
            v2 = t2.getValue(c);
            if(v1 != v2)
            diffEU += Math.pow(v1-v2,2);
            c.nextConfiguration();
        }

        

        return Math.sqrt(diffEU/t1.getSize());
        
       
    }
    
    
    public static double compareIntervalUtilities(PotentialIntervalTable p1, Potential p2) {

        PotentialIntervalTable t1 = p1;
        PotentialTable t2 = new PotentialTable(p2);
        
        
        if(t1.getSize() != t2.getSize())
            return -1;
        
        double diffEU = 0;
        
        Configuration c = new Configuration(t1.getVariables());
        double v1, v2, e1, e2, err, v, alpha, beta;
        for(int i=0; i<t1.getSize(); i++) {
            
            v = t2.getValue(c);
            alpha = t1.getMinValue(c);
            beta = t1.getMaxValue(c);
            
            e1 = beta - v;
            e2 = v - alpha;
            
            err = Math.max(e1, e2);
            
           
            if(err != 0)
                diffEU += Math.pow(err,2);
            c.nextConfiguration();
        }

        

        return Math.sqrt(diffEU/t1.getSize());
        
       
    } 
    
    
    
    public static double getMeanIntervalSize(Potential p) {
    
        if(!(p instanceof PotentialIntervalTable))
            return 0;
        
        PotentialIntervalTable iTable = (PotentialIntervalTable) p;
        double n = iTable.getNumConfigurations();
        double sum = 0;
        for(int i=0; i<n; i++) {
            sum += (iTable.getMaxValue(i) - iTable.getMinValue(i));
        }
        
        return sum/n;
    
    }
   
}

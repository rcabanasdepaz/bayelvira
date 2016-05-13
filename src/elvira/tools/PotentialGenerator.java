/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools;

import elvira.Configuration;
import elvira.FiniteStates;
import elvira.Node;
import elvira.NodeList;
import elvira.potential.Potential;
import elvira.potential.PotentialTable;
import elvira.potential.binaryprobabilitytree.BinaryProbabilityTree;
import java.util.Random;
import java.util.Vector;

/**
 *  This class contains methods for randomly generate potentials
 * @author Rafael Cabañas de Paz - rcabanas@decsai.ugr.es
 */
public class PotentialGenerator {
    
     
    /**
     * Random number generator
     */
     private Random randGen;
    
     
     /**
      * Constructs the class using a seed.
      * @param seed 
      */
    public PotentialGenerator(long seed){
        randGen = new Random(seed);
        
    }
    
    /**
     * Creates a new generator
     */
    public PotentialGenerator(){
        randGen = new Random();
        
    }
    
    /**
     * Set the seed for the random number generator.
     * @param seed 
     */
    public void setSeed(long seed) {
        randGen.setSeed(seed);
    }
    
    /**
     * Generates a random BPT whose leaves are generated from a 
     * uniform distribution between 0 and max
     * @param max
     * @param numVars
     * @param numStates
     * @return 
     */
    private BinaryProbabilityTree getUniformBPT(double max, int numVars, int numStates) {
        
        return BinaryProbabilityTree.getTreeFromPotential(getUniformTable(max, numVars, numStates));
    }
    
    
    
    
    /**
     * Generates a random table whose values are generated from a 
     * uniform distribution between 0 and max
     * @param max
     * @param numVars
     * @param numStates
     * @return 
     */
    public PotentialTable getUniformTable(double max, int numVars, int numStates) {
        
        
        

        NodeList vars = new NodeList();
        
        char name = 'A';
        int ind = 0;
        FiniteStates node;

        
        for(int i=0; i<numVars; i++){
            

            node = new FiniteStates(numStates);
            node.setName(name+""+ind);
            
            vars.insertNode(node);
            name++;
            
            if(name>'Z') {
                name='A';
                ind++;
            }
            
        
        }       
        
        
        return new PotentialTable(randGen, vars, max);
        

    }
    
    /**
     * Generates a utility table whose values are biased
     * @param max
     * @param numVars
     * @param numStates
     * @return 
     */
    
public PotentialTable getRandomUtilityTable(double max, int numVars, int numStates) {
        NodeList vars = new NodeList();
        
        char name = 'A';
        int ind = 0;
        FiniteStates node;

        PotentialTable t;
        Vector marginals = new Vector();
        
        //Creates the list of variables and the marginal potentials
        for(int i=0; i<numVars; i++){            

            node = new FiniteStates(numStates);
            node.setName(name+""+ind);            
            vars.insertNode(node);
            name++;            
            if(name>'Z') {
                name='A';
                ind++;
            }
            
            
            marginals.add(new Vector());
            double value;
            for(int j=0; j<numStates; j++) {
                value = randGen.nextDouble()*max;
                ((Vector)marginals.get(i)).add(value);
            }
            
        
        }
        
        
        //Creates an empty table
        t = new PotentialTable(vars);
        
        //Fills the table adding the marginals utilities
        double[] values = t.getValues();
        Configuration conf = new Configuration(vars);
        Vector states;
        for(int i=0; i<values.length; i++) {
            states = conf.getStates();
            double value = 0;
            for(int j=0;j<states.size(); j++){
                
                int s = (Integer)states.get(j);
                value += (Double)((Vector)marginals.get(j)).get(s);
                
                
                
            }
            
            values[i] = value;
            conf.nextConfiguration();
        
        
        }
        
        
        t.setValues(values);
        
        
        return t;
  
}

/**
 * Generates a utility table whose values are biased
 * @param vars
 * @param max
 * @return 
 */
public PotentialTable getRandomUtilityTable(NodeList vars, double max) throws Exception {
        
        
        PotentialTable t;
        Vector marginals = new Vector();
        
        //Removes the value node
        NodeList nodes = vars.copy();
        nodes.removeNode(0);
        
        
        
        int numVars = nodes.size();
        
        //Creates the marginal potentials
        for(int i=0; i<numVars; i++){            

            marginals.add(new Vector());
            double value;
            int numStates = ((FiniteStates)(nodes.getNodes().get(i))).getNumStates();
            for(int j=0; j<numStates; j++) {
                value = randGen.nextDouble()*max;
                ((Vector)marginals.get(i)).add(value);
            }
            
        
        }
        
        
        //Creates an empty table
        t = new PotentialTable(nodes);
        
        //Fills the table adding the marginals utilities
        double[] values = t.getValues();
        Configuration conf = new Configuration(nodes);
        Vector states;
        for(int i=0; i<values.length; i++) {
            states = conf.getStates();
            double value = 0;
            for(int j=0;j<states.size(); j++){
                
                int s = (Integer)states.get(j);
                value += (Double)((Vector)marginals.get(j)).get(s);
                
                
                
            }
            
            values[i] = value;
            conf.nextConfiguration();
        
        
        }
        
        
        
        
        t.setValues(values);
        double p;
        p = 0.2 + (randGen.nextDouble() * 0.3);
        insertValues(t, 0, p);
        
      //  t.print();
        return t;
        
        
    
}


/**
 * Generates a utility table whose values are biased from a list of vars
 * @param vars
 * @return 
 */
public PotentialTable getRandomProbabilityTable(NodeList vars)  throws Exception{

        PotentialTable t;
        Vector marginals = new Vector();
        double max = 1;
        
        
        int numVars = vars.size();
        
       
        
        //Puts the conditioned variable at the end
        FiniteStates cond = (FiniteStates) vars.getNodes().get(0);
        vars.removeNode(0);
        vars.insertNode(cond);
        
        
        //Creates an empty table
        t = new PotentialTable(vars);
        
        //Fills the table adding the marginals utilities
        double[] values = t.getValues();
        Configuration conf = new Configuration(vars);
        Vector states;
        
        double cumulative = 0;
        
        for(int i=0; i<values.length; i++) {
            states = conf.getStates();
            double value = 0;
            
            //resets the cumulative probability
            if(((Integer)states.get(states.size()-1)==0))                
                cumulative = 0;
            values[i] = randGen.nextDouble()*(1-cumulative);
            cumulative+=values[i];
            conf.nextConfiguration();
        
        
        }
        
        
        
        
        
                //Puts the conditioned variable at the end
        int size = vars.getNodes().size();
        vars.interchange(0, size-1);
        
 
        
        t.setVariables(vars.getNodes());
        
        
                // First at all, get a list of antecessors of node

       

        
        // Use this list to call the method to repair

     //   Potential pot0 = pot.copy();
        

        
        
        
        t.setValues(values);
        double p;
        p = 0.2 + (randGen.nextDouble() * 0.4);
        insertValues(t, 0, p);
        
        p = 0.2 + (randGen.nextDouble() * 0.4);
        insertValues(t, 1, p);
        
        p = 0.2 + (randGen.nextDouble() * 0.4);
        insertValues(t, 0.5, p);
        
        Node child = vars.getNodes().get(0);
        Potential divisor = t.addVariable(child);
        t = (PotentialTable) t.divide(divisor);
        
    //    t.print();

      //  t.normalizeOver((FiniteStates)vars.getNodes().get(0));

        return t;

}
    



          
    
    
    
  /**
   * Changes randomly some values of a table t.
   * @param t
   * @param newValue
   * @param prob 
   */  
  public void alterRandomly(PotentialTable t, double newValue, double prob) {
        double[] values = t.getValues();
        
        double r;
        for(int i=0; i<values.length; i++) {
            r = randGen.nextDouble();
            if(r<prob)
                values[i] = newValue;
        }
        
        t.setValues(values);
      
  }
       /**
   * Changes randomly some values of a table t.
   * @param t
   * @param newValue
   * @param prob 
   */
    public void alterRandomlyGaussian(PotentialTable t, double newValue, double variance, double prob) {
        double[] values = t.getValues();
        
        double r;
        for(int i=0; i<values.length; i++) {
            r = randGen.nextDouble();
            if(r<prob) {
                values[i] = (randGen.nextGaussian()*variance)+newValue;
                
            }
        }
        
        t.setValues(values);
      
  }
    
         /**
   * Inserts a value in random positions
   * @param t
   * @param newValue
   * @param prob 
   */
    public void insertValues(PotentialTable t, double newValue, double prob) {
        double[] values = t.getValues();
        
        double r;
        for(int i=0; i<values.length; i++) {
            r = randGen.nextDouble();
            if(r<prob) {
                values[i] = newValue;
                
            }
        }
        
        t.setValues(values);
      
  }
  

    
    

    
     /**
   * Inserts values from a gaussian distribution
   * @param t
   * @param newValue
   * @param prob 
   */ 
   public void insertGaussianValues(PotentialTable t, double[] mean, double[] variance) {
        double[] values = t.getValues();
        
        double r;
        int dist;
        
        for(int i=0; i<values.length; i++) {
            
            //Choses the distrubution
            dist = randGen.nextInt(mean.length);
            values[i] = (randGen.nextGaussian()*variance[dist])+mean[dist];
                
            
        }
        
        t.setValues(values);
      
  }
     

    
    
    public static void main(String[] args) {
        
         PotentialGenerator gen = new PotentialGenerator(1234);
         gen.getUniformBPT(100, 4, 3).print(2);
         
         
        
    }
      
    
    
    
}
